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
public class SwitchPositionTest {
    static Switch sw101;
    static Sector s102;
    static SwitchPosition instance;

    public SwitchPositionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SX.startEmulation();
        SX.instance().configPort("", 0, 0, 0, 0);
        SX.instance().initPort();
        s102 = new Sector("s102",10, 2, 15);
        sw101 = new Switch("sw101", 10, 1, s102);
        instance = new SwitchPosition(sw101, 1);
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
     * Test of setPosition method, of class SwitchPosition.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        assertEquals(0, sw101.getPosition());
        instance.setPosition();
        assertEquals(1, sw101.getPosition());
    }

    /**
     * Test of getSector method, of class SwitchPosition.
     */
    @Test
    public void testGetSector() {
        System.out.println("getSector");
        assertEquals(s102, instance.getSector());
    }

    /**
     * Test of getId method, of class SwitchPosition.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(true, instance.getId().equals("sw101"));
    }

}