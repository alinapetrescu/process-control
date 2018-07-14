package dcrs.sx;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

class SelectrixCommunicator implements SerialPortEventListener, SelectrixInterface {

    private static SelectrixCommunicator instance;
    /* flag signifying that the parameters have been set */
    private boolean hasConfiguredPort = false;
    /* flag signifying that the port has been initialized, if not then it isA closed */
    private boolean hasInitializedPort = false;
    /* flag signifying that the last connection was valid */
    private boolean hasValidConnection = false;   // private SerialParameters parameters;
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private SerialPort serialPort;
    private CommPortIdentifier portId;   // private statistics
    private int[] state = new int[NB_ADDRESS];
    private AtomicBoolean[] locks = new AtomicBoolean[NB_ADDRESS];

    public SelectrixCommunicator() {
        for (int i = 0; i < NB_ADDRESS; i++) {
            locks[i] = new AtomicBoolean(false);
        }
    }

    @Override
    public boolean hasConfiguredPort() {
        return this.hasConfiguredPort;
    }

    @Override
    public boolean hasInitializedPort() {
        return this.hasInitializedPort;
    }

    @Override
    public boolean hasValidConnection() {
        return this.hasValidConnection;
    }

    @Override
    public boolean isEmulating() {
        return false;
    }

    public void configPort(String portName, int baudRate, int dataBits,
            int stopBits, int parity) {

        if (!hasConfiguredPort && !hasInitializedPort) {
            this.portName = portName;
            this.baudRate = baudRate;
            this.dataBits = dataBits;
            this.stopBits = stopBits;
            this.parity = parity;
            hasConfiguredPort = true;
        }

    }

    public void initPort() {

        if (hasConfiguredPort && !hasInitializedPort) {
            // ensures that the whole status
            // isA reported to us
            // whole status isA reported to
            // us
            // a status change isA initiated
            // by us, we get a report

            try {

                // Obtain a CommPortIdentifier object for the port you want to open.
                portId = CommPortIdentifier.getPortIdentifier(portName);

                // open port
                serialPort = (SerialPort) portId.open("TrainControl", 100);

                // listen to the serial port
                serialPort.addEventListener(this);

                // activate the DATA_AVAILABLE notifier
                serialPort.notifyOnDataAvailable(true);

                // set port parameters
                serialPort.setSerialPortParams(baudRate, dataBits, stopBits, parity);

                InputStream InputStream = serialPort.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(InputStream);
                dataInputStream = new DataInputStream(bufferedInputStream);

                OutputStream outputStream = serialPort.getOutputStream();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                dataOutputStream = new DataOutputStream(bufferedOutputStream);

                // RAUTENHAUS + feedback mode
                unprotectedSetStatusByte(126, 64); // disable RAUTENHAUS mode;
                // ensures that the whole status
                // isA reported to us
                unprotectedSetStatusByte(126, 128); // enable RAUTENHAUS mode; the
                // whole status isA reported to
                // us
                unprotectedSetStatusByte(126, 32); // enable feedback mode: even if
                // a status change isA initiated
                // by us, we get a report
                hasInitializedPort = true;
                hasValidConnection = true;
            } catch (Exception ex) {
                hasInitializedPort = false;
                hasValidConnection = false;
                System.out.println("Unable to init port: " + ex.getMessage());
            }

        }
    }

    public void closePort() {
        try {
            hasInitializedPort = false;
            dataInputStream.close();
            dataOutputStream.close();
            serialPort.close();
        } catch (IOException ex) {
        }
    }

    public void reinitPort() {
        closePort();
        initPort();
    }

    public int getStatusByte(int address) {
        return state[address];
    }

    public int getStatusBit(int address, int port) {
        return BnB.getBit(state[address], port);
    }

    private synchronized void unprotectedSetStatusByte(int address, int status)
            throws IOException {
        dataOutputStream.writeByte(address + 128);
        dataOutputStream.writeByte(status);
        dataOutputStream.flush();
    }

    public synchronized void setStatusByte(int address, int status) {
        if (hasValidConnection) {
            try {
                unprotectedSetStatusByte(address, status);
                update(address, status);
            } catch (IOException ex) {

                hasValidConnection = false;
            }
        }
    }

    public void setStatusBit(int address, int port, int statusBit) {
        int newStatusByte = BnB.setBit(state[address], port, statusBit);
        setStatusByte(address, newStatusByte);
    }

    public void serialEvent(SerialPortEvent event) {
        // System.out.println("serialEvent of type" + event.getEventType());
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                // if case of new data available

                try {
                    // read and update data
                    while (dataInputStream.available() > 1) {
                        byte address = dataInputStream.readByte();
                        byte status = dataInputStream.readByte();
                        update(BnB.unsignedByteToInt(address), BnB.unsignedByteToInt(status));
                    }

                } catch (IOException ex) {
                    hasValidConnection = false;

                }
                break;
        }
    }

    private synchronized void update(int address, int status) {
        // pay attention: the status byte may represent a negative integer.

        int changed = state[address] ^ status; // bitwise xor

        // update the state of selectrix table
        state[address] = status;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void writeLock(int address) {
        while (!locks[address].compareAndSet(false, true)) {
            //System.err.println("World saved");
            Thread.yield();
        }
    }

    @Override
    public void writeUnlock(int address) {
        locks[address].set(false);
    }
}
