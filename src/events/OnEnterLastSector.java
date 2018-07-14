/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.events;

import dcrs.railway.Block;
import dcrs.railway.Train;

/**
 * This observer waits until the last sector of a block is occupied. Then, it
 * adapts the train's speed and starts the next observers.
 * @author ms
 */
class OnEnterLastSector extends Observer {
    /**
     * The train which is expected to arrive at the end of the block soon.
     */
    Train train;

    /**
     * The block which is being watched.
     */
    Block block;

    /**
     * Constructor of the class. Takes as parameter the train and
     * the block.
     * @param train
     * @param block
     */
    public OnEnterLastSector(Train train, Block block) {
        //TODO: implementation done 
        //(don't forget to start the observer)
        this.train = train;
        this.block = block;
        this.start();
    }

    /**
     * Returns true if the last sector of the block is occupied.
     * @return
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if(block.isLastSectorOccupied()) return true;
        return false;
    }

    /**
     * Adapt the trains speed according to the speed limitation of the next
     * block (read the signal).
     * 
     * Then, if the train is in its last block, start an OnHalt observer.
     * Otherwise, start an OnEnterBlock and an OnLeaveBlock observer.
     */
    @Override
    protected void process() {
        //TODO: implementation done
        //train.getLocomotive().reachSpeed(train.getRoute().getCurrentBlock().getEndSignal().getSpeed());
        train.getLocomotive().reachSpeed(block.getEndSignal().getSpeed());
        //if(train.getRoute().getCurrentBlock() == train.getRoute().getLastBlock())
        //if(block == train.getRoute().getLastBlock()) 
        if(train.getRoute().isInLastBlock() == true)
        {
            //train.getLocomotive().reachSpeed(0);
            new OnHalt(train);
        }else
        {
            //train.getLocomotive().reachSpeed(train.getRoute().getCurrentBlock().getEndSignal().getSpeed());
            //train.getLocomotive().reachSpeed(block.getEndSignal().getSpeed());
            //train.getLocomotive().reachSpeed(train.getRoute().getNextBlock().getStartSignal().getSpeed());
            new OnEnterBlock(train, train.getRoute().getNextBlock());
            //new OnLeaveBlock(train.getRoute().getCurrentBlock());
            new OnLeaveBlock(block);
        }
    }
}
