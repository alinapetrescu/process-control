package dcrs.sx;


/**
 * BnB (Bits and Bytes) is a class that provides static methods to work with integers as Bits and Bytes.
 * @author benoitpointet
 */
public final class BnB {

    /**
     *
     * @param b a byte value (0..255)
     * @param n
     * @return bit at position n of byte b.
     */
    public static int getBit(int b, int n) {
		return (b >> n) & 1;
	}

    /**
     * 
     * @param b a byte value (0..255)
     * @param n a bit position (0..7)
     * @param v a bit value (0,1)
     * @return the result of setting bit n of byte b to value v
     */
    public static int setBit(int b, int n, int v) {
		int newB;
		if (v == 0) {
			newB = b & (int) (255 - Math.pow(2, n));
		} else {
			newB = b | (int) Math.pow(2, n);
		}
		return newB;
	}

	static int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}
	
	public static boolean isBit(int b) {
		return (b == 0 || b == 1);
	}
	
	public static boolean isByte(int b) {
		return isWordOfLength(b,8);
	}

	public static boolean isWordOfLength(int w, int l) {
		return (w >= 0 && w < Math.pow(2, l));
	}
}
