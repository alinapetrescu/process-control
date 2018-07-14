/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dcrs.events;

import java.util.*;
import dcrs.railway.Block;
import dcrs.railway.Locomotive;
import dcrs.railway.Sector;
import dcrs.railway.Train;
import dcrs.railway.YBlock;
import dcrs.routing.RouteFactory;
import dcrs.railway.Railway;
import dcrs.events.OnEmergency;

/**
 *
 * @author Martina
 */
public class OnRoute extends Observer {

    Train train;
    Railway railway;
    public ArrayList<Sector> sec;
    int psec;

    public OnRoute(Train train) {
        this.train = train;
        railway = Railway.instance();
        sec = train.getRoute().getSectorsAhead();
        this.start();
    }

    @Override
    protected boolean test() {
        // when the Train arrives at his destination, return true.
        if (sec.size() == 0) {
            return true;
        }
        // check if any sector ahead of the train is occupied. return true if found one.
        for (int i = 1; i < sec.size(); i++) {
            if (sec.get(i).isOccupied() == true) {
                psec = i;
                return true;
            }
        }
        //return false if the first sector is occupied and sec is empty. (the train may be in the first sector)
        if (!sec.isEmpty() && sec.get(0).isOccupied()) {
            sec.remove(0);
            return false;
        }
        return false;
    }

    @Override
    protected void process() {
        //kill the Observer if arrived at destination without any event.(do nothing)
        if (sec.size() == 0) {
        } else {
            //if obstacle found, immediatley stop the train("Notbremse"). start the OnEmergency Observer.
            train.getLocomotive().emergencyStop();

            train.getRoute().unlockSectors();  // release all the sectors so other trains can eventually still get to their destination.
            new OnEmergency(train, psec);
        }
    }
}
