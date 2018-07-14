package dcrs.railway;

import java.util.ArrayList;

/**
 * Represents a line of the miniature railway system. It is mainly a list
 * of stations to visit and the number of the last visited station.
 */
public class Line extends RailwayElement {

    /**
     * the sequence of stations to visit
     */
    public ArrayList<Station> stations;

    /**
     * index of the last visited station
     */
    int visitIndex = 0;//Note from Martina(FR= ind:0; SCH= ind:1; GR= ind:2 )/not important

    boolean isLooping=true;
    boolean isAtLastStation=false;
    
    /**
     * Constructor of the class. The parameters are the id of the line and
     * the list of stations to visit.
     * @param id
     * @param stations
     */
    public Line(String id, ArrayList<Station> stations) {
        this.id = id;
        this.stations = stations;
    }

   /** @return the last station the train visited, using the list of stations
    * and the index*/
    public Station getCurrentStation() {
        //TODO: implementation done
        return stations.get(visitIndex);     
    }

    public void setNonLooping() {
        isLooping=false;
    }
    
    public boolean isLooping(){
        return isLooping;
    }
     public boolean isAtLastStation(){
         if(visitIndex==(stations.size()-1)){
             return true;
         }
        return false;
     }
    
    /**
     * @return the next station to visit, using the list of stations and the
     * index. If we have reached the end of the list, go back to the first
     * element. 
     */
    public Station getNextStation() {
        //TODO: implementation done
        if(visitIndex==stations.size()-1)
        {
            return stations.get(0);
        }
        return stations.get(visitIndex+1);
    }

    /**
     * tells the line that next station has been reached. (increment the
     * visitIndex). If we have reached the end of the list, go back to the first
     * element.
     */
    public void reachedNextStation() {
        
        //TODO: implementation done
        
        
        if(visitIndex==stations.size()-1)
        {
           visitIndex=0;
        }else{
            visitIndex=visitIndex+1;
        }
    }
}