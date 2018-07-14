package dcrs.sim;

import dcrs.railway.Train;
import static dcrs.utils.Logger.*;

/**
 * This simulates a moving train. It reads the speed and direction
 * from a locomotive. It logs any change and updates its position.
 * @author Mathias Seuret
 */
public class SimTrain extends Movable {
    /**
     * The train being watched.
     */
    Train train;
    
    /**
     * Last observed speed.
     */
    int currentSpeed;
    
    /**
     * Last observed direction.
     */
    int currentDirection;
    
    /**
     * Constructor. Takes a train and its position as parameter.
     * @param t
     * @param seg
     * @param offset 
     */
    public SimTrain(Train t, Segment seg, double offset) {
        super(seg, offset);
        train = t;
        currentDirection = train.getLocomotive().getDirection();
    }
    
    /**
     * Updates the direction and the speed of the train, and moves it.
     */
    @Override
    public void update() {
        int newDirection = train.getLocomotive().getDirection();
        if (newDirection!=currentDirection) {
            log("Sim: " + getId()+" changed its direction to "+newDirection);
            currentDirection = newDirection;
        }
        
        int intSpeed = train.getLocomotive().getSpeed();
        if (intSpeed!=currentSpeed) {
            log("Sim: "+getId()+" reached speed "+intSpeed);
            currentSpeed = intSpeed;
        }
        speed = intSpeed / 1.5;
        direction = 1 - 2*train.getLocomotive().getDirection();
        super.update();
    }
    
    /**
     * Displayable text.
     * @return 
     */
    @Override
    public String getId() {
        return "Sim:"+train;
    }
}
