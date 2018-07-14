package dcrs.events;

import dcrs.railway.Block;
import dcrs.railway.Train;
import dcrs.railway.YBlock;
import dcrs.routing.RouteFactory;

/**
 * This observer waits until the train has stopped at its destination. When it
 * is done, some data will be cleaned and a new route will be asked.
 *
 * @author ms
 */
public class OnHalt extends Observer {

    /**
     * The train which is being watched
     */
    Train train;

    /**
     * Constructor of the class. Takes only the train as parameter.
     *
     * @param train
     */
    public OnHalt(Train train) {
        //TODO: implementation done
        //(don't forget to start the observer)
        this.train = train;
        this.start();
    }

    /**
     * @return true if the locomotive has stopped moving.
     */
    @Override
    protected boolean test() {
        //TODO: implementation done
        if (train.getLocomotive().getSpeed() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Description inside the method.
     */
    @Override
    protected void process() {

        /*
         * Set the signal id of the train to the end id of
         * the block on which the train is.
         */
        //TODO: implementation done
        train.setSignalId(train.getRoute().getCurrentBlock().getEndId());

        /*
         * Unlock the sectors which are on the block of the train ; that
         * is required because there is no OnleaveBlock observer in
         * this case.
         */
        //TODO: implementation done
        //train.getRoute().getCurrentBlock().unlockSectors();
        train.getRoute().unlockSectors();

        /*
         * Remove the route of the train by setting it to null.
         */
        //TODO: implementation done
        train.setRoute(null);

        if (train.getLine() != null) {
            train.getLine().reachedNextStation();
        }
        //nothing else to do
        /*
         * A little break, just for fun
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        /*
         * The next station on the line station has been reached
         */


        /*
         * Start an OnRouteSecured observer, and then request a route
         * from the Graph.
         */
        if (train.getLine() == null) {
            new OnRouteSecured(train);
        }
        // request route only if "start line" button is clicked (line exist and isLooping is true)
        // if "Send to station" button is clicked it will request the route only to come to the last station
        // (line exist and train is not at the last station)
        if (train.getLine() != null && (train.getLine().isLooping() || !train.getLine().isAtLastStation())) {
            new OnRouteSecured(train);
            RouteFactory.instance().requestRoute(train, train.getLine().getNextStation());

        } else {
            return;
        }
    }
}