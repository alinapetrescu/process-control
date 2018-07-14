package dcrs.sx;

/**
 *
 * @author benoitpointet
 */
public interface SelectrixInterface {

    public static final int NB_ADDRESS = 104;

    boolean hasConfiguredPort();

    boolean hasInitializedPort();

    boolean hasValidConnection();

    boolean isEmulating();

    void configPort(String portName, int baudRate, int dataBits, int stopBits, int parity);

    void initPort();

    void reinitPort();

    void closePort();

    int getStatusByte(int addressByte);

    void setStatusByte(int address, int status);

    int getStatusBit(int address, int position);

    void setStatusBit(int address, int position, int status);

    void writeLock(int address);

    void writeUnlock(int address);

    @Override
    String toString();
}
