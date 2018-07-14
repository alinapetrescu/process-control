/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dcrs.sim;

import dcrs.railway.Switch;
import dcrs.sx.SX;
import static dcrs.utils.Logger.*;

/**
 * This classes watches a switch and logs any change.
 * @author ms
 */
public class SimSwitch implements Element {
    /**
     * The switch being watched.
     */
    Switch sw;
    
    /**
     * Its last detected position.
     */
    int position;
    
    /**
     * Constructor.
     * @param s 
     */
    public SimSwitch(Switch s) {
        sw = s;
        position = sw.getPosition();
    }
    
    /**
     * Logs any change.
     */
    @Override
    public void update() {
        int newPosition = sw.getPosition();
        if (newPosition != position) {
            log("Sim: "+this+" moved to position "+newPosition);
            position = newPosition;
        }
    }
    
    /**
     * Displayable name.
     * @return 
     */
    @Override
    public String toString() {
        return "Sim:"+sw;
    }
}
