package dcrs.railway;

import java.util.ArrayList;

/**
 * Represents a block of the miniature railway system.
 */
public class Block extends RailwayElement {

    /**
     * the maximum speed for a train driving through the block. By default
     * 31 but is changed in the constructor.
     */
    private int maxSpeed = 31;

    /**
     * Array containing the sectors of the block, in the correct
     * order.
     */
    private ArrayList<Sector> sectors = new ArrayList();

    /**
     * the switches in the block and their required positions
     */
    //indique quelle branche le train prend
    private ArrayList<SwitchPosition> switchPositions = new ArrayList();
    
    /**
     * the Blocks following this block's end signal in SAME direction
     */
    private ArrayList<Block> nextBlocks = new ArrayList();

    /**
     * Direction of the Block
     */
    private int direction = 0; //Fribourg-->Schwarzssee = 0

    /**
     * Reference to the signal which is at the beginning of the block.
     */
    private Signal startSignal = null;
    
    /**
     * Reference to the signal which is at the end of the block.
     */
    private Signal endSignal = null;

    /**
     * the ID of the starting signal
     */
    protected String startId = "";
    
    /**
     the ID of the starting signal at the end of the block
     */
    protected String endId = "";

    /**
     * Approximation of the length of the block, in centimeters.
     */
    private float length;

    protected Block() {
        // to allow inheritance from YBlock, without this constructor
        // ont would have to pre-build a YBlock outside of its constructor.
    }

    /**
     * Creates a Block.
     * @param id its unique id.
     * @param startID the ID of the starting signal.
     * @param endID the ID of the starting signal of the following block.
     * @param maxSpeed its maximum speed.
     * @param sectors its sectors.
     * @param switchPositions its switchPositions.
     */
    public Block(String id, String startID, String endID, int direction, int maxSpeed,
            ArrayList<Sector> sectors,
            ArrayList<SwitchPosition> switchPositions) {
        //TODO: implement constructor. Also compute the length of the block
        // (using the length of the sectors).
         this.id = id;
         this.startId = startID;
         this.endId = endID;
         this.direction = direction;
         this.maxSpeed = maxSpeed;
         this.sectors = sectors;
         this.switchPositions = switchPositions;
         this.length = 0;
         for(Sector s : sectors){
             this.length += s.getLength();
         }
    }
    
    /** to add set the nextblocks ArrayList
     * @param next a block following this block */
    public void addNextBlocks(ArrayList<Block> next) {
        //TODO: implementation done
        //nextBlocks.addAll(next);  même effet que la boucle for ci-dessous
        for(Block b: next)
        {
            nextBlocks.add(b);
        }
    }
    

    /** @return the IDs of the starting signals. */
    public String getStartId() {
        //TODO: implementation done
        return startId;
    }

    /** @return the ID of the starting signal of the following block. */
    public String getEndId() {
        //TODO: implementation done
        return endId;
    }
     
     /** @return the max speed a train may drive in this block. */
    public int getMaxSpeed() {
        //TODO: implementation done
        return maxSpeed;
    }

    /**
     * Sets the maximum speed allowed on the block.
     * @param s
     */
    public void setMaxSpeed(int s) {
        //TODO: implementation done
        maxSpeed=s;
    }

    
    /**
     * Returns the direction of the block.
     * @return either 0 or 1
     */
    public int getDirection() {
        //TODO: implementation done
        return direction;
    }

    /**
     * Sets the direction of the block. The parameter must be either
     * 0 or 1.
     * @param dir        
     */
    public void setDirection(int dir) {
        //TODO: implementation done
        if(dir == 0 || dir == 1)
        {
            direction=dir;
        }
    }

    /**
     * @return the first sector of the block. It is used for detecting
     * when a train enters it.
     */
    public Sector getFirstSector() {
        //TODO: implementation done
        return sectors.get(0);
    }

    /**
     * @return the last sector of the block. It is used for detecting
     * when a train is about to leave it.
     */
    public Sector getLastSector() {
        //TODO: implementation done
        return sectors.get(sectors.size()-1);
    }

    /**
     * @return the list of the blocks which start signal is the end signal
     * of the instance whose method is called.
     */
    public ArrayList<Block> getNextBlocks() {
        //TODO: implementation done
        return nextBlocks;
    }

    /**
     * @return a list containing the sectors of the block. They are in
     * the correct order.
     */
    public ArrayList<Sector> getSectors() {
        //TODO: implementation done
        return sectors;
    }

    /**
     * @return an estimation of the length of the block, in centimeters.
     */
    public float getLength() {
        //TODO: implementation done
        return length;
    }

    /**
     * Sets the length of the block, in centimeters.
     * @param length
     */
    public void setLength(float length) {
        //TODO: implementation done
        this.length = length;
    }

    /**
     * @return the crossing time of the block : the length divided by
     * the maximal allowed speed. The result it <b>not</b> in seconds.
     * If the block is a YBlock, multiply the value by 5.
     */
    public float getCrossingTime() {
        //TODO: implementation done
        float time = length/maxSpeed;
        //if it is a YBlock
        if(containsStop())
        {
            //multiply par 5 cette valeur
            time = time*5;
        }
        return time;
    }

    /**
     * Sets the start signal of the block
     * @param s
     */
    public void setStartSignal(Signal s){
        //TODO: implementation done
        startSignal=s;
    }
    
     /**
     * Sets the end signal of the block
     * @param s
     */
    public void setEndSignal(Signal s){
        //TODO: implementation done
        endSignal=s;
    }

    /**
     * @return the start signal of the block
     */
    public Signal getStartSignal(){
        //TODO: implementation done
        return startSignal;
    }
    
    /**
     * @return the end signal of the block
     */
    public Signal getEndSignal(){
        //TODO: implementation done
        return endSignal;
    }


    /**
     * @return true if any sector of the block is occupied, otherwise false.
     */
    public boolean isOccupied(){
        //TODO: implementation done
        for (Sector s: sectors)
        {
            if(s.isOccupied())
            {
                return true;
            }
        }
        return false;
    }
    
     /**
     * @return true if any sector of the block is locked, otherwise false.
     */
    public boolean isLocked(){
        //TODO: implementation done
        for (Sector s: sectors)
        {
            if(s.isLocked())
            {
                return true;
            }
        }
        return false;
        
    }  

     /**
     * @return true if the block is securable, that is not occupied nor locked.
     */
    public boolean isSecurable() {
        //TODO: implementation done
        if(!isOccupied() && !isLocked())
        {
            return true;
        }
        return false;
    }

    /**
     * Locks all sectors of the block.
     */
    public void lockSectors(){
        //TODO: implementation done
       for(Sector s : sectors)
       {
           s.lock(id);
       }
    }

    /**
     * Unlocks all sectors of the block.
     */
    public void unlockSectors(){
        //TODO: implementation done
       for(Sector s : sectors)
       {
           s.unlock(id);
       }
    }

    /**
     * Set the switches of the block in the correct position, if they
     * are locked by the block.
     */
    public void setSwitches(){
        //TODO: implementation done
        if(isLocked())
        {
            for(SwitchPosition sp : switchPositions)
            {
                sp.setPosition();
            }
        }  
    }

    /**
     * Returns true if the first sector is occupied, otherwise false.
     */
    public boolean isFirstSectorOccupied() {
        //TODO: implementation done
        if(sectors.get(0).isOccupied())
        {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the last sector of the block is occupied, otherwise false
     */
    public boolean isLastSectorOccupied() {
        //TODO: implementation done
        if(sectors.get(sectors.size()-1).isOccupied())
        {
            return true;
        }
        return false;
    }

    /**
     * Returns the list of switchPositions of the block.
     * @return the switchPositions
     */
    public ArrayList<SwitchPosition> getSwitchPositions() {
        //TODO: implementation done
        return switchPositions;
    }

    /**
     * Returns true if the block contains a stop. Only YBlocks contain stops,
     * therefore this method returns always false.
     */
    public boolean containsStop() {
        return false;
    }
}
