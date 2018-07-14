package dcrs.sx;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author benoitpointet
 */
class SelectrixEmulator implements SelectrixInterface {

    private static SelectrixInterface instance;
    /* flag signifying that the parameters have been set */
    private boolean hasConfiguredPort = false;
    /* flag signifying that the port has been initialized, if not then it isA closed */
    private boolean hasInitializedPort = false;
    /* flag signifying that the last connection was valid */
    private boolean hasValidConnection = false;
    /* private SerialParameters parameters; */
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    private int readCounter = 0;
    private int writeCounter = 0;
    private int initCounter = 0;
    private int[] state = new int[NB_ADDRESS];
    private AtomicBoolean[] locks = new AtomicBoolean[NB_ADDRESS];

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
        return true;
    }

    @Override
    public void configPort(String portName, int baudRate, int dataBits,
            int stopBits, int parity) {

        if (!this.hasConfiguredPort && !this.hasInitializedPort) {
            this.portName = portName;
            this.baudRate = baudRate;
            this.dataBits = dataBits;
            this.stopBits = stopBits;
            this.parity = parity;
            this.hasConfiguredPort = true;
        }
        initPort();
    }

    @Override
    public void initPort() {
        this.hasInitializedPort = true;
        this.hasValidConnection = true;
        for (int i = 0; i < state.length; i++) {
            state[i] = 0;
        }

        for (int i = 0; i < locks.length; i++) {
            locks[i] = new AtomicBoolean(false);
        }
    }

    @Override
    public void closePort() {
        this.hasInitializedPort = false;
        this.hasValidConnection = false;
    }

    @Override
    public void reinitPort() {
        closePort();
        initPort();
    }

    @Override
    public int getStatusByte(int address) {
        return state[address];
    }

    @Override
    public void setStatusByte(int address, int status) {
        if (hasValidConnection) {
            update(address, status);
        }
    }

    @Override
    public int getStatusBit(int address, int port) {
        return BnB.getBit(state[address], port);
    }

    @Override
    public void setStatusBit(int address, int port, int statusBit) {
        int newStatusByte = BnB.setBit(state[address], port, statusBit);
        setStatusByte(address, newStatusByte);
    }

    private synchronized void update(int address, int status) {
        state[address] = status;
    }

    @Override
    public void writeLock(int address) {
        while (locks[address].compareAndSet(false, true)) {
            Thread.yield();
        }
    }

    @Override
    public void writeUnlock(int address) {
        locks[address].set(false);
    }
}
