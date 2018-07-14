/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.railway;

import dcrs.sx.SX;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ms
 */
public class LocomotiveTest {

    static Locomotive instance;

    public LocomotiveTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SX.startEmulation();
        SX.instance().configPort("", 0, 0, 0, 0);
        SX.instance().initPort();
        instance = new Locomotive("myLoc", 10, 30, 0);
        instance.reachSpeed(10);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of emergencyStop method, of class Locomotive.
     */
    @Test
    public void testEmergencyStop() {
        System.out.println("emergencyStop");
        instance.emergencyStop();
        try {
            Thread.sleep(750);
        } catch (InterruptedException ie){}
        assertEquals(0, instance.getSpeed());
        assertEquals(true, instance.isEmergencyStopped());
        assertEquals(true, instance.getSpeedBeforeEmergency()>0);
    }

    /**
     * Test of emergencyContinue method, of class Locomotive.
     */
    @Test
    public void testEmergencyContinue() {
        System.out.println("emergencyContinue");
        instance.emergencyStop();
        instance.emergencyContinue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie){}
        assertEquals(true, instance.getSpeed()>0);
        assertEquals(false, instance.isEmergencyStopped());
    }

    /**
     * Test of isEmergencyStopped method, of class Locomotive.
     */
    @Test
    public void testIsEmergencyStopped() {
        System.out.println("isEmergencyStopped");
        // previously tested
    }

    /**
     * Test of getSpeed method, of class Locomotive.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        int s = SX.instance().getStatusByte(10) & 31;
        assertEquals(true, instance.getSpeed()==s);
    }

    /**
     * Test of getDesiredSpeed method, of class Locomotive.
     */
    @Test
    public void testGetDesiredSpeed() {
        System.out.println("getDesiredSpeed");
        instance.reachSpeed(17);
        assertEquals(17, instance.getDesiredSpeed());
    }

    /**
     * Test of getSpeedBeforeEmergency method, of class Locomotive.
     */
    @Test
    public void testGetSpeedBeforeEmergency() {
        System.out.println("getSpeedBeforeEmergency");
        instance.reachSpeed(15);
        instance.emergencyStop();
        assertEquals(15, instance.getSpeedBeforeEmergency());
        instance.emergencyContinue();
    }

    /**
     * Test of reachSpeed method, of class Locomotive.
     */
    @Test
    public void testReachSpeed() {
        System.out.println("reachSpeed");
        instance.reachSpeed(5);
        try {
            Thread.sleep(2000);
        }catch(InterruptedException ie){}
        assertEquals(5, instance.getSpeed());
    }

    /**
     * Test of run method, of class Locomotive.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        // nothing to do
    }

    /**
     * Test of getDirection method, of class Locomotive.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        assertEquals(0, instance.getDirection());
        instance.setDirection(1);
        assertEquals(1, instance.getDirection());
        instance.setDirection(0);
        assertEquals(0, instance.getDirection());
    }

    /**
     * Test of setDirection method, of class Locomotive.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        // tested before
    }

    /**
     * Test of isLightOn method, of class Locomotive.
     */
    @Test
    public void testIsLightOn() {
        System.out.println("isLightOn");
        SX.instance().setStatusBit(10, 6, 0);
        assertEquals(false, instance.isLightOn());
        SX.instance().setStatusBit(10, 6, 1);
        assertEquals(true, instance.isLightOn());
    }

    /**
     * Test of setLight method, of class Locomotive.
     */
    @Test
    public void testSetLight() {
        System.out.println("setLight");
        // tested before
    }
    
    /**
     * Test of getLength method, of class Locomotive.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        assertEquals(30, instance.getLength());
    }

}