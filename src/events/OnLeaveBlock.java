/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.events;

import dcrs.railway.Block;

/**
 * This observer waits until the train leaves a block, and then do
 * some cleaning.
 * @author ms
 */
public class OnLeaveBlock extends Observer {
    /**
     * The block which is being watched.
     */
    private Block block;

    /**
     * Constructor of the class, takes a block as only parameter.
     * @param block
     */
    public OnLeaveBlock(Block block) {
        //TODO: implementation done
        this.block = block;
        this.start();
    }

    /**
     * @return true if the block is not occupied.
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if(!block.isOccupied()) return true;
        return false;
    }

    /**
     * Unlocks all sectors of the block.
     */
    @Override
    protected void process() {
        //TODO: implementation done 
        block.unlockSectors();
    }

}
