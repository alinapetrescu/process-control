package dcrs.routing;

import dcrs.railway.*;
import java.util.ArrayList;

/**
 * Represents a route (sequence of blocks) a train follows to reach the next station on its line.
 */
public class Route extends RailwayElement {

    /**
     * the sequence of blocks leading to aim station
     */
    private ArrayList<Block> blocks;
    public ArrayList<Sector> sectorsAhead = new ArrayList<Sector>();
    
    int initialLengthOfSectorsAhead;
    
    //returns length of sectors of entire route
    public int getInitialLengthOfSectorsAhead() {
        return initialLengthOfSectorsAhead;
    }
    
    public ArrayList<Sector> getSectorsAhead() {
        return sectorsAhead;
    }
    //returns length of sectors that train should visit
    public int LengthOfSectorsAhead(){
       int  x=0;
       for(Sector each : sectorsAhead){
            x += each.getLength();
        }
       return x;
    }
    

    /**
     * The index of the block on which the train is
     */
    private int currentBlockIndex = 0;

    public Route(ArrayList<Block> blocks) {
        //TODO: implementation done
        this.blocks = blocks;
        for (int i = 1; i < blocks.size(); i++) {
            sectorsAhead.addAll(blocks.get(i).getSectors());
            
        }
        initialLengthOfSectorsAhead = 0;
        for(Sector each : sectorsAhead){
            initialLengthOfSectorsAhead += each.getLength();
        }
        
    }

    
    
    /**
     * @return the current block
     */
    public Block getCurrentBlock() {
        //TODO: implementation done
        return blocks.get(currentBlockIndex);
    }

    /**
     * @return the next block
     */
    public Block getNextBlock() {
        //TODO: implementation done
        return blocks.get(currentBlockIndex+1);
    }

    /**
     * Tells the route that the train entered the next block (updates currentBlockIndex)
     * @param train
     */
    public void reachedNextBlock(Train train) {
        //TODO: implementation done
        currentBlockIndex++;
    }

    /**
     * @return the last block of the route
     */
    public Block getLastBlock() {
        //TODO: implementation done
        return blocks.get(blocks.size()-1);
    }

    /**
     * @return the first block of the route
     */
    public Block getFirstBlock() {
        //TODO: implementation done
        return blocks.get(0);
    }

    /**
     * @return the blocks of the route
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * locks the sectors of each block of the route (except for the first block).
     */
    public void lockSectors(){
        for(int i=1; i<blocks.size(); i++){
            blocks.get(i).lockSectors();
        }
    }

    /**
     * unlocks the sectors of each block of the route (except for the first block).
     */
    public void unlockSectors(){
        for(int i=1; i<blocks.size(); i++){
            blocks.get(i).unlockSectors();
        }
    }

    /**
     * @return true if the train is in the last block of its route
     */
    public boolean isInLastBlock() {
        //TODO: implementation done
        if(currentBlockIndex == blocks.size()-1)
        {
            return true;
        }
        return false;
    }

    /**
     * set the switches between the current position of the train and
     * the stop sector of the first encountered yblock - or the
     * end of the route
     */
    public void setNextSwitches() {
        for (int i=currentBlockIndex; i<blocks.size(); i++) {
            if (blocks.get(i).containsStop()) {
                if (i==currentBlockIndex) {
                    ((YBlock)blocks.get(i)).setNextSwitches();
                } else {
                    blocks.get(i).setSwitches();
                    return;
                }
            }else{
                //If the current block is not a YBlock, don't switch its switches.
                if(i>currentBlockIndex){
                blocks.get(i).setSwitches();
                }
            }
        }
    }

    /**
     * sets the signals between the current position of the train and the
     * stop of the first encountered yblock - or the end of the route
     */
    public void setNextSignals() {
        for (int i=currentBlockIndex; i<blocks.size(); i++) {
            boolean current = (i==currentBlockIndex);
            boolean last = (i==blocks.size()-1);
            // is it a YBlock ?
            if (blocks.get(i).containsStop()) {
                YBlock yb = (YBlock) blocks.get(i);
                if (!current) {
                    yb.getStartSignal().setShort();
                    yb.getStopSignal().setStop();
                    yb.getEndSignal().setStop(); //safety measure
                    break;
                } 
                else {
                    yb.getStartSignal().setStop(); //safety measure
                }
            } else {
                // then it is a Block
                Block b = blocks.get(i);
                if (!current && !last) {
                    b.getStartSignal().setSpeed(b.getMaxSpeed());
                    
                } else if (!current && last) {
                    b.getStartSignal().setShort();
                    b.getEndSignal().setStop(); //safety measure
                } 
            }
        }
    }

    /**
     * @return the id of the route
     */
    @Override
    public String getId() {
        return this.getFirstBlock().getId() + blocks.toString();
    }

    /**
     * @return the IDs of the blocks
     */
    @Override
    public String toString() {
        return blocks.toString();
    }

}
