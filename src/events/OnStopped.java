/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.events;
import dcrs.railway.Locomotive;
import dcrs.railway.Train;
import dcrs.railway.YBlock;

/**
 * This class handles what happens when the train has stopped at the middle
 * signal of a YBlock.
 * @author ms
 */
class OnStopped extends Observer {
    /**
     * The train being watched.
     */
    Train train;

    /**
     * The YBlock on which the train is.
     */
    YBlock yblock;

    /**
     * Constructor of the class, takes a train and YBlock as parameters.
     * @param train
     * @param yblock
     */
    public OnStopped(Train train, YBlock yblock) {
        //TODO: implementation done
        //(start the observer)
        this.train = train;
        this.yblock = yblock;
        this.start();
    }

    /**
     * Returns true when the speed of the train is zero.
     * @return
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if(train.getLocomotive().getSpeed()==0) return true;
        return false;
    }

    /**
     * Prepares the route (set next signals and switches), sets the direction of
     * the locomotive (using the current YBlock), sets the speed of the locomotive (drive short if it is
     * in the last block, otherwise reads the signal), starts an
     * OnEnterLastSector observer.
     */
    @Override
    protected void process() {
        //TODO: implementation done
        train.getRoute().setNextSignals();
        train.getRoute().setNextSwitches();
        train.getLocomotive().setDirection(yblock.getDirection());
        
        if(train.getRoute().isInLastBlock())
        {
            train.getLocomotive().reachSpeed(Locomotive.driveShort);
        }else
        {
           train.getLocomotive().reachSpeed(yblock.getEndSignal().getSpeed()); 
        }
        
        new OnEnterLastSector(train, yblock);
    }
}
