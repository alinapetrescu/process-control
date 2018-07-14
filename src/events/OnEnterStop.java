/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.events;

import dcrs.railway.Train;
import dcrs.railway.YBlock;

/**
 * This observer waits until the train arrives in the stop sector of
 * a YBlock. Then, it makes the train stop and starts an OnStopped
 * observer.
 * @author ms
 */
public class OnEnterStop extends Observer {
    /**
     * The train
     */
    Train train;

    /**
     * The YBlock which is being watched.
     */
    YBlock yblock;

    /**
     * Constructor of the class. Takes a YBlock and a train as parameters.
     * @param train
     * @param yblock
     */
    public OnEnterStop(Train train, YBlock yblock) {
        //TODO: implementation done
        //(don't forget to start the observer)
        this.train=train;
        this.yblock=yblock;
        this.start();
    }

    /**
     * @return true if the stop sector of the yblock is occupied.
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if(yblock.getStopSector().isOccupied()) return true;
        return false;
    }

    /**
     * Tells the train to stop and starts an OnStopped observer.
     */
    @Override
    protected void process() {
        //TODO: implementation done
        train.getLocomotive().reachSpeed(0);
        new OnStopped(train, yblock);
        
    }

}
