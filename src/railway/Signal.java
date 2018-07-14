package dcrs.railway;

import dcrs.sx.SX;

/**
 * This class represents a signal of the railway.
 * @author tcstrain
 */
public class Signal extends RailwayElement {

    /**
     * Constant describing the speed 40.
     */
    public final static int speed40 = 40;
    /**
     * Constant describing the speed 60
     */
    public final static int speed60 = 60;
    /**
     * Constant describing the speed 90
     */
    public final static int speed90 = 90;
    /**
     * Id of the signal
     */
    private String Id;
    /**
     * Address of the signal
     */
    private int address;
    /**
     * Bit position of a LED
     */
    private int g1bitpos;
    /**
     * Bit position of a LED
     */
    private int g2bitpos;
    /**
     * Bit position of a LED
     */
    private int r1bitpos;
    /**
     * Bit position of a LED
     */
    private int r2bitpos;
    /**
     * Bit position of a LED
     */
    private int o1bitpos;
    /**
     * Bit position of a LED
     */
    private int o2bitpos;
    /**
     * Bit position of a LED
     */
    private int numbitpos;
    /**
     * Tells whether a light exists or not.
     */
    private boolean existsg1 = false;
    /**
      * Tells whether a light exists or not.
     */
    private boolean existsg2 = false;
    /**
      * Tells whether a light exists or not.
     */
    private boolean existsr1 = false;
    /**
      * Tells whether a light exists or not.
     */
    private boolean existsr2 = false;
    /**
     * Tells whether a light exists or not.
     */
    private boolean existso1 = false;
    /**
     * Tells whether a light exists or not.
     */
    private boolean existso2 = false;
    /**
     * Tells whether a light exists or not.
     */
    private boolean existsnum = false;
    /**
     * The speed indicated by the signal
     */
    private int speed = 0;

    /**
     * Constructor of the class. Takes an id and an address
     * as parameters
     * @param Id
     * @param address
     */
    public Signal(String Id, int address) {
        this.Id = Id;
        this.address = address;
    }

    /**
     * @return the ID of the signal
     */
    @Override
    public String getId() {
        return Id;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existsg1
     */
    public void setExistsg1(boolean existsg1) {
        this.existsg1 = existsg1;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existsg2
     */
    public void setExistsg2(boolean existsg2) {
        this.existsg2 = existsg2;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existsnum
     */
    public void setExistsnum(boolean existsnum) {
        this.existsnum = existsnum;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existso1
     */
    public void setExistso1(boolean existso1) {
        this.existso1 = existso1;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existso2
     */
    public void setExistso2(boolean existso2) {
        this.existso2 = existso2;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existsr1
     */
    public void setExistsr1(boolean existsr1) {
        this.existsr1 = existsr1;
    }

    /**
     * Give true to indicate that the signal has this light
     * @param existsr2
     */
    public void setExistsr2(boolean existsr2) {
        this.existsr2 = existsr2;
    }

    /**
     * Sets the bit of the led
     * @param g1bitpos
     */
    public void setG1bitpos(int g1bitpos) {
        this.g1bitpos = g1bitpos;
    }

    /**
     * Sets the bit of the led
     * @param g2bitpos
     */
    public void setG2bitpos(int g2bitpos) {
        this.g2bitpos = g2bitpos;
    }

    /**
     * Sets the bit of the led
     * @param numbitpos
     */
    public void setNumbitpos(int numbitpos) {
        this.numbitpos = numbitpos;
    }

    /**
     * Sets the bit of the led
     * @param o1bitpos
     */
    public void setO1bitpos(int o1bitpos) {
        this.o1bitpos = o1bitpos;
    }

    /**
     * Sets the bit of the led
     * @param o2bitpos
     */
    public void setO2bitpos(int o2bitpos) {
        this.o2bitpos = o2bitpos;
    }

    /**
     * Sets the bit of the led
     * @param r1bitpos
     */
    public void setR1bitpos(int r1bitpos) {
        this.r1bitpos = r1bitpos;
    }

    /**
     * Sets the bit of the led
     * @param r2bitpos
     */
    public void setR2bitpos(int r2bitpos) {
        this.r2bitpos = r2bitpos;
    }

    /**
     * Switch all leds off
     */
    public void setNone() {
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 0);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 0);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 0);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 0);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 0);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 0);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 0);
        }
    }

    /**
     * Switch all leds on
     */
    public void setAll() {
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 1);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 1);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 1);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 1);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 1);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 1);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 1);
        }
    }

    /**
     * Sets the signal to drive max, the maximal speed of the locomotive.
     */
    public void setDriveMax() {
        speed = Locomotive.driveMax;
        System.out.println("Signal " + this + " set to Free");
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 1);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 0);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 0);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 0);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 0);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 0);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 0);
        }
    }

    /**
     * Sets the signal to drive 40, a low speed of the locomotive
     */
    public void setDrive40() {
        speed = Locomotive.drive40;
        System.out.println("Signal " + this + " set to Free40");
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 1);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 0);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 0);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 0);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 1);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 0);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 0);
        }
    }

    /**
     * Sets the signal to drive 60, an average speed of the locomotive
     */
    public void setDrive60() {
        speed = Locomotive.drive60;
        System.out.println("Signal " + this + " set to Free60");
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 1);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 1);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 0);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 0);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 0);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 0);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 1);
        }
    }

    /**
     * Sets the signal to drive short, the slowest speed of the locomotive
     */
    public void setShort() {
        speed = Locomotive.driveShort;
        System.out.println("Signal " + this + " set to Short");
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 0);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 0);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 0);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 0);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 1);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 1);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 0);
        }
    }

    /**
     * Sets the signal to stop - red
     */
    public void setStop() {
        speed = Locomotive.stop;
        //System.out.println("Signal " + this + " set to Stop");
        if (existsg1) {
            SX.instance().setStatusBit(address, g1bitpos, 0);
        }
        if (existsg2) {
            SX.instance().setStatusBit(address, g2bitpos, 0);
        }
        if (existsr1) {
            SX.instance().setStatusBit(address, r1bitpos, 1);
        }
        if (existsr2) {
            SX.instance().setStatusBit(address, r2bitpos, 1);
        }
        if (existso1) {
            SX.instance().setStatusBit(address, o1bitpos, 0);
        }
        if (existso2) {
            SX.instance().setStatusBit(address, o2bitpos, 0);
        }
        if (existsnum) {
            SX.instance().setStatusBit(address, numbitpos, 0);
        }
    }

    /**
     * Returns the speed given by the signal
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }


    /**
     * Sets the signal. The parameter must be either Signal.speed40,
     * Signal.speed60 or Signal.speed90.
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        switch (speed) {
            case speed40:
                setDrive40();
                break;
            case speed60:
                setDrive60();
                break;
            case speed90:
                setDriveMax();
                break;
            default:
                System.err.println("Unknown speed: " + speed);
                setDrive40();
                break;
        }
    }
}
