/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dcrs.events;

import dcrs.railway.Block;
import dcrs.railway.Locomotive;
import dcrs.railway.Sector;
import dcrs.railway.Train;
import dcrs.railway.YBlock;

import dcrs.railway.Railway;

/**
 *
 * @author Martina
 */
public class OnEmergency extends Observer {

    Train train;
    Railway railway;
    Integer psec;

    public OnEmergency(Train train, Integer psec) {
        this.train = train;
        this.psec = psec;
        // prints which sector exaclty. has a problem. 
        System.out.println("There is a problem on sector " + train.getRoute().getSectorsAhead().get(psec) + ". The train [" + train.getId() + "] can't move on. Please check and clear tracks.");
        if (train.getRoute().getSectorsAhead().get(psec - 1).isOccupied()) {
            System.out.println("Train stopped very close to the obstacle. Train may have to be moved manually.");
        }
        this.start();
    }
    // Observer waits unitl the specific problem on the sector is away (no more occupied)

    @Override
    protected boolean test() {
        //observes if the secotr with the obstacle is still occupied. returns True if not and prints a little message.
        if (train.getRoute().getSectorsAhead().get(psec).isOccupied()) {
            return false;
        }
        System.out.print("The obstacle on sector " + train.getRoute().getSectorsAhead().get(psec) + " should be removed now.");
        return true;
    }

    @Override
    protected void process() {
        // Lock the line again, it has been unlocked in the OnRoute. let the train move again and restart the OnRoute Observer.
        train.getRoute().setNextSignals();
        train.getRoute().setNextSwitches();
        train.getLocomotive().emergencyContinue();

        new OnRoute(train);
    }
}
