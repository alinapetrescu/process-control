/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.events;

import dcrs.railway.Locomotive;
import dcrs.railway.Train;
import static dcrs.utils.Logger.log;

/**
 * This class is used for starting trains when they receive a route.
 * @author ms
 */
public class OnRouteSecured extends Observer {
    /**
     * The train which is being watched.
     */
    Train train;

    /**
     * Constructor of the class. Takes only a train as parameter.
     * @param train
     */
    public OnRouteSecured(Train train) {
        //TODO: implementation done
        //(start the observer)
        System.out.println("OnRouteSecured started");
        this.train = train;
        this.start();
        log("New OnRouteSecured for "+train);
    }

    /**
     * @return true if the train has received a route (which means that
     * its reference to a route is not null).
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if(train.getRoute() != null) return true;
        return false;
    }

    /**
     * Prepares the route (set next switches and signals), starts an
     * OnEnterLastSector observer, sets the direction of the locomotive (using the current block),
     * and then, if the train is already in its
     * last block, set its speed to drive short, otherwise to the
     * speed given by the signal.
     */
    @Override
    protected void process() {
        System.out.println("OnRouteSecured process");
        if(train.isDeleted() == false)
        {

            //TODO: implementatione done
            train.getRoute().setNextSignals();
            train.getRoute().setNextSwitches();
            train.getLocomotive().setDirection(train.getRoute().getCurrentBlock().getDirection());

            if(train.getRoute().isInLastBlock())
            {
                train.getLocomotive().reachSpeed(Locomotive.driveShort);
            }else
            {
            train.getLocomotive().reachSpeed(train.getRoute().getCurrentBlock().getEndSignal().getSpeed()); 
            }

            new OnEnterLastSector(train, train.getRoute().getCurrentBlock());
            new OnRoute(train);
            }
        else{
                         System.out.println("TRain deleted");
        }
    }

}
