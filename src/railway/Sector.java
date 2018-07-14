package dcrs.railway;

import dcrs.sx.SX;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a sector of the miniature railway system.
 */
public class Sector extends RailwayElement {
    /**
     * byte address of the sector
     */
    public int address;

    /**
     * bit position address of the sector
     */
    public int bitpos;

    /**
     * Estimation of the length of the sector
     */
    private int length;

    /**
     * True if there is a switch on the sector
     */
    private boolean hasSwitch;
    
    /**
     * Set containing the IDs of the objects which have put a lock
     * on the sector. 
     * L'identité d'un objet RailwayElement est une String
     */
    private Set<String> locks = new HashSet<String>();

    /**
     * create a new Sector.
     * @param id its id.
     * @param address its decoder address.
     * @param bitpos its bit position on the decoder.
     */
    public Sector(String id, int address, int bitpos, int length) {
        //TODO: implementation done
        this.id = id;
        this.address = address;
        this.bitpos = bitpos;
        this.length = length;
    }
    
    /** 
     * @return true if the sector is locked, false otherwise
     */
    public boolean isLocked() {    
        //TODO: implementation done
        if(locks.isEmpty())
        {
            return false;
        }
        return true;
    }
    
    /**
     * @param e
     * @return true if the specified RailwayElement has put a lock
     * on this sector, false otherwise.
     */
    public boolean isLockedBy(RailwayElement e) {
        //TODO: implementation done
        if(locks.contains(e.getId()))
        {
            return true;
        }
        return false;
    }

    /**
     * @return whether the sector is physically occupied or not
     */
    public boolean isOccupied() {
        //TODO: implementation done
        if(SX.instance().getStatusBit(address, bitpos) == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns an estimation of the length of the sector
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Adds a lock to the element.
     * @param id
     */
    public void lock(String id) {        
        //TODO: implementation done
        locks.add(id);
    }

    /**
     * Sets the length of the sector
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length=length;
    }

    /**
     * Returns true if the sector contains a switch, false otherwise
     * @return hasSwitch
     */
    public boolean hasSwitch() {
        return hasSwitch; 
    }

    /**
     * Sets the hasSwitch value, which indicates if the sector contains
     * a switch or not.
     * @param hasSwitch
     */
    public void setHasSwitch(boolean hasSwitch) {
        this.hasSwitch=hasSwitch;
    }
    
    /**
     * Removes a lock from the object.
     * @param id
     */
    public void unlock(String id) {
        //TODO: implementation done
        locks.remove(id);
    }
}
