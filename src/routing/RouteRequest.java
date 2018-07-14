package dcrs.routing;

import dcrs.railway.Train;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used by the route factory to store route requests
 * @author ms
 */
public class RouteRequest {
    /**
     * The train which asked for a route
     */
    private Train train;

    /**
     * The list of accepted destinations
     */
    private List<Node> to = new LinkedList<Node>();

    /**
     * The list of possible starting nodes
     */
    private List<Node> from = new LinkedList<Node>();

    /**
     * Constructor : takes a train and a list of possible destinations as
     * parameters.
     * @param train
     * @param to
     */
    protected RouteRequest(Train train, List<String> to) {
        this.train = train;
        /*
         * add destination nodes
         */
        for (String id : to) {
            this.to.addAll(RouteFactory.instance().getNodesByEndId(id));
        }

        /*
         * Add starting nodes. It is possible to start at the stop signal
         * of a YBlock.
         */
        this.from.addAll(RouteFactory.instance().getNodesByEndId(train.getSignalId()));
        //this.from.addAll(RouteFactory.instance().getNodesByStartId(train.getSignalId()));
        this.from.addAll(RouteFactory.instance().getNodesByStopId(train.getSignalId()));
    }

    /**
     * @return the train requesting the route
     */
    protected Train getTrain() {
        return train;
    }

    /**
     * @return the possible start nodes
     */
    protected List<Node> getFrom() {
        return from;
    }

    /**
     * @return the possible destination nodes
     */
    protected List<Node> getTo() {
        return to;
    }
    
    public List<String> getToIds(){
        List<String> result = new LinkedList<String>();
        for(Node n : to){
            result.add(n.getBlock().getEndId());
        }
        return result;
    }

    /**
     * returns the class in a printable form
     */
    @Override
    public String toString() {
        return "Request[ Train="+train+", from="+train.getSignalId()+" to="+to+"]";
    }

}
