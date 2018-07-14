package dcrs.railway;

/**
 * Represents a switch with given position, of the miniature railway system.
 */
public class SwitchPosition extends RailwayElement {
   
   /**
    * the switch concerned
    */
   private Switch sw;

   /**
    * the position it should be setPosition to
    */
   private int position;

   /**
    * Constructor, takes as parameter a switch and the expected position.
    * @param sw
    * @param position
    */
   public SwitchPosition(Switch sw, int position) {
       //TODO: implementation done
       this.sw = sw;
       this.position = position;
   }

   /**
    * switches the switch to "position"
    */
   public void setPosition() {       
      //TODO: implementation done
       sw.setPosition(position);
   }

   public int getPosition() {
       return position;
   }
   
   /**
    * @return the sector which contains the switch
    */
    public Sector getSector() {
        //TODO: implementation done
        return sw.getSector();
    }

    /**
     * @return the ID of the switch
     */
    @Override
    public String getId() {
        //TODO: implementation done
        return sw.getId();
    }
}
