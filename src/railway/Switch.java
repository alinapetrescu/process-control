package dcrs.railway;

import dcrs.sx.*;

/**
 * Represents a switch of the miniature railway system.
 */
public class Switch extends RailwayElement {

    /**
     * byte address of the switch
     */
    int address;

    /**
     * bit position address of the switch
     */
    int bitpos;

    /**
     * sector of the switch
     */
    private Sector sector;

    /** create a Switch, based on
     * @param id its id.
     * @param address its decoder address.
     * @param bitpos its bit position on the decoder.
     */
    public Switch(String id, int address, int bitpos, Sector sector) {
        //TODO: implementation done
        this.id = id;
        this.address = address;
        this.bitpos= bitpos;
        this.sector= sector;
    }

    /**
     * @return the saved position of the switch. May be false if
     * the switch has been moved manually.
     */
    public int getPosition() {
        //TODO: implementation done
        return SX.instance().getStatusBit(address, bitpos);
    }

    /**
     * sets the position of the switch
     * @param statusBit the new status bit value
     */
    public void setPosition(int statusBit) {
        //TODO: implementation done
        if(statusBit == 0 || statusBit == 1)
        {
            SX.instance().setStatusBit(address, bitpos, statusBit);
        }
    }

    /**
     * @return the sector on which the switch is.
     */
    public Sector getSector() {
        return sector;
    }
}