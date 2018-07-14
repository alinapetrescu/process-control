package dcrs.railway;

import dcrs.sx.*;

/**
 * Represents a locomotive of the miniature railway system.
 */
public class Locomotive extends RailwayElement implements Runnable {
    /**
     * This variable is set to true when the locomotive is in an
     * emergency stop state.
     */
    private boolean emergency = false;

    /**
     * Byte address of the locomotive.
     */
    private int address;

    /**
     * Desired speed of the locomotive. The locomotive will change
     * its speed slowly to make it match this value.
     */
    private int desiredSpeed = 0;

    /**
     * Backup of the speed of the locomotive before an emergency stop, so
     * that when the emergency is over the locomotive can continue its
     * journey.
     */
    private int speedBeforeEmergency = 0;

    /**
     * Length of the locomotive, in centimeters.
     */
    private int length = 0;

    /**
     * Inertia of the locomotive. It should be equals to 0 in the XML file
     * for the yellow locomotive, 1 for the other ones. A value of 0 will
     * greatly increase the accelerations of the locomotive.
     */
    private int inertia = 0;

    /**
     * Thread used for accelerating the locomotive slowly.
     */
    private Thread speedingThread;

    /**
     * Must absolutely be equals to 0, for it is the speed a locomotive
     * must have when it is stopped.
     */
    public final static int stop = 0;

    /**
     * Maximum speed of the locomotive.
     */
    public final static int driveMax = 31;

    /**
     * Speed "40" of the locomotive
     */
    public final static int drive40 = 20;

    /**
     * Speed "60" of the locomotive
     */
    public final static int drive60 = 25;

    /**
     * Speed 90 of the locomotive
     */
    public final static int drive90 = 29;

    /**
     * Speed of the locomotive before a stop
     */
    public final static int driveShort = 15;

    /**
     * Constructor of the class
     * @param id
     * @param address
     * @param length
     * @param inertia
     */
    public Locomotive(String id, int address, int length, int inertia) {
        //TODO: implementation done
        this.id = id;
        this.address = address;
        this.length = length;
        this.inertia = inertia;
    }

    /**
     * Makes the locomotive stop very fast. It can be used in order to
     * avoid a collision. Emergency should be set to true and the current
     * desiredSpeed should be saved for later usage.
     */
    public void emergencyStop() {
        //TODO: implementation done
        speedBeforeEmergency = desiredSpeed;
        //speedBeforeEmergency = getSpeed();
        emergency = true;
        setSpeed(0);
        //desiredSpeed = stop;
    }

    /**
     * Tells a locomotive that it can resume its journeys after an
     * emergency stop. (use speed stored in emergencyStop().
     */
    public void emergencyContinue() {
        //TODO: implementation done
        emergency = false;
        reachSpeed(speedBeforeEmergency);
        //desiredSpeed = speedBeforeEmergency;
        speedBeforeEmergency = 0;
    }

    /**
     * @return true if the locomotive is in an emergency stop state.
     */
    public boolean isEmergencyStopped() {
           return emergency;
    }

    /**
     * @return the current speed of the locomotive.
     */
    public int getSpeed() {
        //TODO: implementation done
        return SX.instance().getStatusByte(address)&31;
    }

    /**
     * @return the desired speed of the locomotive
     */
    public int getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     * @return the speed of the locomotive before an
     * emergency stop.
     */
    public int getSpeedBeforeEmergency() {
        return this.speedBeforeEmergency;
    }

    /**
     * Sets immediately the speed of a locomotive to
     * a given speed (if speed is ranged from 0 to 31).
     * @param speed
     */
    private void setSpeed(int speed) {
        //TODO: implementation done
            SX.instance().writeLock(address);
            /* Lili thinks that this is wrong and commented it
               if(this.getSpeed()>=0 && this.getSpeed()<=31){
               SX.instance().setStatusByte(address, speed);
            }
            */ 
            if(speed >= 0 && speed <= 31){
               int status = SX.instance().getStatusByte(address); 
               SX.instance().setStatusByte(address, status & (~31) | speed);
            }         
            SX.instance().writeUnlock(address);
    }

    /**
     * Tells the locomotive to reach a given speed.
     * @param speed
     */
    public void reachSpeed(int speed) {
        if (!emergency) {
            this.desiredSpeed = speed;
            // create and start new speed thread if needed
            if (speedingThread==null || !speedingThread.isAlive()){
                this.speedingThread = new Thread(this);
                this.speedingThread.start();
            }
        }
    }

    /**
     * acceleration process.
     * program executed by the drivingThread of the locomotive.
     * special handling in case of emergency
     */
    @Override
    public void run() {
        if (emergency) {
            this.setSpeed(stop);
        } else {
            while (this.desiredSpeed != this.getSpeed() && emergency == false) {
                if (this.desiredSpeed < this.getSpeed()) {
                    this.setSpeed(this.getSpeed() - 1);
                    try {
                        Thread.sleep(50+this.getSpeed()*10);
                    } catch (InterruptedException ex) {}
                } else {
                    this.setSpeed(this.getSpeed() + 1);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {}
                }
            }
        }
    }

    /**
     * @return the direction of the locomotive.
     */
    public int getDirection() {
        //TODO: implementation done
        return SX.instance().getStatusBit(address, 5);
    }

    /**
     * Changes the direction of the locomotive. This method should be
     * called only when the locomotive is stopped.
     * @param b
     */
    public void setDirection(int b) {
        //TODO: implementation done
        System.out.println("Setting direction of loc to " + b);
        SX.instance().writeLock(address);
        if(b==0 || b==1){
            SX.instance().setStatusBit(address, 5, b);
         }
        SX.instance().writeUnlock(address);
    }

    /**
     * @return the state of the locomotive lights.
     */
    public boolean isLightOn() {
        //TODO: implementation done 
        if((SX.instance().getStatusBit(address, 6))==0){
            return false;
       } 
       return true;
    }

    /**
     * sets the state of the locomotive lights on or off.
     * @param on
     */
    public void setLight(boolean on) {
        //TODO: implementation done
       SX.instance().writeLock(address);
       if(on){
            SX.instance().setStatusBit(address, 6, 1);
        }
        else{
           SX.instance().setStatusBit(address, 6, 0);
        }
        SX.instance().writeUnlock(address);
    }

    /**
     * @return the state of the locomotive horn, this corresponds to the 7th bit.
     * There is no horn anymore though, but let's implement it anyway :-)
     */
    public boolean isHornOn() {
        //TODO: implementation done
        if((SX.instance().getStatusBit(address, 7))==0){
            return false;
        } 
        return true;
    }

    /**
     * sets the state of the locomotive horn on or off.
     * @param on
     */
    public void setHorn(boolean on) {
        //TODO: implementation done
        SX.instance().writeLock(address);
        if(on){
            SX.instance().setStatusBit(address, 7, 1);
        }
        else{
            SX.instance().setStatusBit(address, 7, 0);
        }
        SX.instance().writeUnlock(address);
    }

    /**
     * Returns the length of the locomotive, in centimeters
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
     /**
     * Returns the address of the locomotive
     * @return the address
     */   
    public int getAddress(){
        return address;
    }
    
     /**
     * Returns the inertia of the locomotive
     * @return the inertia
     */   
    public int getInertia(){
        return inertia;
    }
}
