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
public class SectorTest {

    static int address;
    static int bitpos;
    static Sector instance;

    public SectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SX.startEmulation();
        SX.instance().configPort("", 0, 0, 0, 0);
        SX.instance().initPort();
        address = 12;
        bitpos = 3;
        instance = new Sector("testSector",address,bitpos, 35);
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
     * Test of isOccupied method, of class Sector.
     */
    @Test
    public void testIsOccupied() {
        assertEquals(false,instance.isOccupied());
        SX.instance().setStatusBit(address, bitpos, 1);
        assertEquals(true,instance.isOccupied());
        SX.instance().setStatusBit(address, bitpos, 0);
        assertEquals(false,instance.isOccupied());
    }

    /**
     * Test of getLength method, of class Sector.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        instance.setLength(15);
        int result = instance.getLength();
        assertEquals(15, result);
    }

    /**
     * Test of setLength method, of class Sector.
     */
    @Test
    public void testSetLength() {
        System.out.println("setLength");
        instance.setLength(25);
        assertEquals(25, instance.getLength());
    }

    /**
     * Test of hasSwitch method, of class Sector.
     */
    @Test
    public void testHasSwitch() {
        System.out.println("hasSwitch");
        instance.setHasSwitch(false);
        assertEquals(false, instance.hasSwitch());
        instance.setHasSwitch(true);
        assertEquals(true, instance.hasSwitch());
    }

    /**
     * Test of setHasSwitch method, of class Sector.
     */
    @Test
    public void testSetHasSwitch() {
        System.out.println("setHasSwitch");
        instance.setHasSwitch(false);
        assertEquals(false, instance.hasSwitch());
        instance.setHasSwitch(true);
        assertEquals(true, instance.hasSwitch());
    }

}