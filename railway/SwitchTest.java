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
public class SwitchTest {

    static Sector s101;
    static Switch instance;

    public SwitchTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SX.startEmulation();
        SX.instance().configPort("", 0, 0, 0, 0);
        SX.instance().initPort();
        s101 = new Sector("s101",10, 1, 10);
        instance = new Switch("sw131", 13, 1, s101);
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
     * Test of getPosition method, of class Switch.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        assertEquals(0, instance.getPosition());
        SX.instance().setStatusBit(instance.address, instance.bitpos, 1);
        assertEquals(1, instance.getPosition());
    }

    /**
     * Test of setPosition method, of class Switch.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        instance.setPosition(0);
        assertEquals(0, instance.getPosition());
    }

    /**
     * Test of getSector method, of class Switch.
     */
    @Test
    public void testGetSector() {
        System.out.println("getSector");
        assertEquals(s101, instance.getSector());
    }

}