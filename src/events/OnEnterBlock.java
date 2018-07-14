package dcrs.events;

import dcrs.railway.Block;
import dcrs.railway.Train;
import dcrs.railway.YBlock;

/**
 * This observer waits until a train enters in a block. The
 * entry is detected when the first sector of the block is occupied.
 * @author ms
 */
class OnEnterBlock extends Observer {
    Train train;
    Block block;

    /**
     * Constructor of the block. It takes in input the train which is
     * expected to enter the block, and the block.
     * @param train
     * @param block
     */
    public OnEnterBlock(Train train, Block block) {
        this.train = train;
        this.block = block;
        this.start();
    }

    /**
     * Checks if the train enters in the block.
     * @return true if the first sector of the block is occupied.
     */
    @Override
    protected boolean test() {
        return block.isFirstSectorOccupied();
    }

    /**
     * Process:
     * 
     * Tell the  route that the train has reached the next block.
     * 
     * Update the train's signalId.
     * 
     * Set the signal of the beginning of the block to stop.
     * 
     * Launch the next observer (the type of observer to launch depends on
     * whether the block is yblock or not).
     */
    @Override
    protected void process() {
        
       
        train.getRoute().reachedNextBlock(train);
        train.setSignalId(train.getRoute().getCurrentBlock().getEndId());
        block.getStartSignal().setStop();
        if (!block.containsStop()) { // not YBlock
            /*
             * Next observer
             */
            new OnEnterLastSector(train, block);
            
            
        } else { // YBlock
          
             /*
              * create a new observer to prepare the stop - we do
              * know that we are in a YBlock, so the cast is valid.
              */
             new OnEnterStop(train, (YBlock)block);
        }
    }

}
