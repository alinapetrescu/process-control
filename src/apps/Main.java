package dcrs.apps;

import dcrs.gui.*;
import dcrs.railway.*;
import dcrs.sim.Simulator;
import dcrs.sx.SX;
import gnu.io.SerialPort;


/**
 * Simple application example to get you started.
 */
public class Main {
   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) throws InterruptedException, Exception 
   {
        /*
       **************************** railway configuration
       */
      Railway railway = RailwayFactory.getInstance().getConfiguredRailway();
      
       /*
       *************************** railway system connection
       */
      SX.instance().configPort("/dev/ttyS0", 19200, SerialPort.DATABITS_8,
              SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
      try {
        SX.instance().initPort();
        Thread.sleep(100);
      } catch (Throwable e) {
         System.out.println("["+e.getMessage()+"]");
      }
      
      if (!SX.instance().hasValidConnection()){
          System.out.println("No valid connection. If you are using the "+
                             "computer of the lab, check if the railway "+
                             "is switched on and make sure that no other "+
                             "instance of this application is running.\n"+
                             "You might try to remove /var/lock/LCK..ttyS0"+
                             "either by deleting the file or by rebooting.");
          SX.startEmulation();
          Simulator sim = new Simulator(Railway.instance());
      }
      
      Thread.sleep(100);      
      railway.initSignals();

      Train blueTrain = railway.getTrainById("blueTrain");
      
      new RailwayMonitor(Railway.instance());
      
      Railway.instance().smallguis.add(new SmallGUI(blueTrain));
      
      //new SmallGUI(railway.getTrainById(railway.getId()));
      
      new Manual_GUI(Railway.instance());
      
      new TrainManagGUI(Railway.instance());
   }
}
