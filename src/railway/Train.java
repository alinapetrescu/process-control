package dcrs.railway;

import dcrs.events.*;
import dcrs.routing.*;

/**
 * Represents a train of the miniature railway system.
 */
public class Train extends RailwayElement {

    /**
     * the line (sequence of stations) this train follows
     */
    private Line line = null;
    /**
     * the route (sequence of blocks) this train follows
     */
    private Route route = null;
    /**
     * the locomotive driving this train
     */
    private Locomotive locomotive = null;

    /**
     * The ID of the signal at the head of the train
     */
    private String signalId = "";
    
    boolean isDeleted = false;

    public boolean isDeleted() {
        return isDeleted;
    }

    /** creates a Train.
     * @param id its unique id.
     * @param locomotive its locomotive. */
    public Train(String id, Locomotive locomotive) {
        this.id = id;
        this.locomotive = locomotive;
    }

    /**
     * @return an estimation of the length of the linetrain
     */
    public int getLength() {
        return locomotive.getLength();
    }

    /**
     * @return the line of the train
     */
    public Line getLine() {
        return line;
    }

    /**
     * Sets the line of the train
     * @param line
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * @return the locomotive of the train
     */
    public Locomotive getLocomotive() {
        return locomotive;
    }

    /**
     * Sets the locomotive of the train
     * @param locomotive
     */
    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    /**
     * @return the route of the train
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Sets the route of the train
     * @param route
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * Sets the signal at which the train is stopped
     * @param signalId
     */
    public void setSignalId(String signalId) {
        this.signalId = signalId;
    }

    /**
     * Returns
     * @return the id at the signal at which the train is stopped
     */
    public String getSignalId() {
        return signalId;
    }

    /**
     * Makes the train start its line.
     * 1. Start an OnRouteSecured observer for the train
     * 2. Request a route by calling the requestRoute method
     * of the RouteFactory.
     */
    public void startLine() {
        //TODO: implementation done
        new OnRouteSecured(this);
//        System.out.println("train: " + this);
//        System.out.println("line: " + line);
//        System.out.println("nextStation: " + line.getNextStation());
        RouteFactory.instance().requestRoute(this, line.getNextStation());
    }
}