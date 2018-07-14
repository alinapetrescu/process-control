/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.railway;

import dcrs.sx.SX;
import java.util.ArrayList;
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
public class BlockTest {
    
    static Sector s101, s102, s103;
    static ArrayList<Sector> sectors;
    static Switch sw131;
    static SwitchPosition sp131;
    static ArrayList<SwitchPosition> switchPositions;
    static Signal a1, a2;
    static Block instance;
    static ArrayList<Block> nextBlocks;

    public BlockTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
      SX.startEmulation();
      SX.instance().configPort("", 0, 0, 0, 0);
      SX.instance().initPort();
      s101 = new Sector("s101",10, 1, 10);
      s102 = new Sector("s102",10, 2, 15);
      s103 = new Sector("s103",10, 3, 20);
      sectors = new ArrayList<Sector>();
      sectors.add(s101);
      sectors.add(s102);
      sectors.add(s103);
      a1 = new Signal("A1", 11);
      a2 = new Signal("A2", 12);
      sw131 = new Switch("sw13", 13, 1, s102);
      sp131 = new SwitchPosition(sw131, 1);
      switchPositions = new ArrayList<SwitchPosition>();
      switchPositions.add(sp131);
      nextBlocks = new ArrayList<Block>();
      instance = new Block("block", "A1", "A2", 0, 23, sectors, switchPositions);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //TODO: void
    }

    @Before
    public void setUp() {
        //TODO: void
    }

    @After
    public void tearDown() {
        //TODO: void
    }

    /**
     * Test of addNextBlocks method, of class Block.
     */
    @Test
    public void testAddNextBlocks() {
        System.out.println("addNextBlocks");
        Block b2 = new Block("block", "A1", "A2", 1, 23, sectors, switchPositions);
        nextBlocks.add(b2);
        instance.addNextBlocks(nextBlocks);
        assertEquals(true, instance.getNextBlocks().contains(b2));
    }

    /**
     * Test of getStartId method, of class Block.
     */
    @Test
    public void testGetStartId() {
        System.out.println("getStartId");
        assertEquals(true, instance.getStartId().equals("A1"));
    }

    /**
     * Test of getEndId method, of class Block.
     */
    @Test
    public void testGetEndId() {
        System.out.println("getEndId");
        assertEquals(true, instance.getEndId().equals("A2"));
    }

    /**
     * Test of getMaxSpeed method, of class Block.
     */
    @Test
    public void testGetMaxSpeed() {
        System.out.println("getMaxSpeed");
        assertEquals(23, instance.getMaxSpeed());
    }

    /**
     * Test of setMaxSpeed method, of class Block.
     */
    @Test
    public void testSetMaxSpeed() {
        System.out.println("setMaxSpeed");
        instance.setMaxSpeed(17);
        assertEquals(17, instance.getMaxSpeed());
    }

    /**
     * Test of getDirection method, of class Block.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        assertEquals(0, instance.getDirection());
    }

    /**
     * Test of setDirection method, of class Block.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        instance.setDirection(1);
        assertEquals(1, instance.getDirection());
        instance.setDirection(0);
        assertEquals(0, instance.getDirection());
    }

    /**
     * Test of getFirstSector method, of class Block.
     */
    @Test
    public void testGetFirstSector() {
        System.out.println("getFirstSector");
        assertEquals(s101, instance.getFirstSector());
    }

    /**
     * Test of getLastSector method, of class Block.
     */
    @Test
    public void testGetLastSector() {
        System.out.println("getLastSector");
        assertEquals(s103, instance.getLastSector());
    }

    /**
     * Test of getNextBlocks method, of class Block.
     */
    @Test
    public void testGetNextBlocks() {
        System.out.println("getNextBlocks");
        assertEquals(true, instance.getNextBlocks()!=null);
        // add next blocks has been tested, size is 1 now.
        assertEquals(1, instance.getNextBlocks().size());
    }

    /**
     * Test of getSectors method, of class Block.
     */
    @Test
    public void testGetSectors() {
        System.out.println("getSectors");
        assertEquals(true, instance.getSectors()!=null);
        assertEquals(3, instance.getSectors().size());
        assertEquals(true, instance.getSectors().contains(s101));
        assertEquals(true, instance.getSectors().contains(s102));
        assertEquals(true, instance.getSectors().contains(s103));
    }

    /**
     * Test of getLength method, of class Block.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        assertEquals(45.0f, 0.1f, instance.getLength());
    }

    /**
     * Test of setLength method, of class Block.
     */
    @Test
    public void testSetLength() {
        System.out.println("setLength");
        instance.setLength(54.0f);
        assertEquals(54.0f, 0.1f, instance.getLength());
    }

    /**
     * Test of getCrossingTime method, of class Block.
     */
    @Test
    public void testGetCrossingTime() {
        System.out.println("getCrossingTime");
        assertEquals(54.0f/17.0f, 0.1f, instance.getCrossingTime());
    }

    /**
     * Test of setStartSignal method, of class Block.
     */
    @Test
    public void testSetStartSignal() {
        System.out.println("setStartSignal");
        instance.setStartSignal(a2);
        assertEquals(a2, instance.getStartSignal());
    }

    /**
     * Test of getStartSignal method, of class Block.
     */
    @Test
    public void testGetStartSignal() {
        System.out.println("getStartSignal");
        assertEquals(a2, instance.getStartSignal());
    }

    /**
     * Test of isOccupied method, of class Block.
     */
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        assertEquals(false, instance.isOccupied());
        SX.instance().setStatusBit(s101.address, s101.bitpos, 1);
        assertEquals(true, instance.isOccupied());
    }

    /**
     * Test of isSecurable method, of class Block.
     */
    @Test
    public void testIsSecurable() {
        System.out.println("isSecurable");
        assertEquals(false, instance.isSecurable());
        SX.instance().setStatusBit(s101.address, s101.bitpos, 0);
        assertEquals(true, instance.isSecurable());
        s102.lock("lock");
        assertEquals(false, instance.isSecurable());
        s102.unlock("lock");
    }

    /**
     * Test of lockSectors method, of class Block.
     */
    @Test
    public void testLockSectors() {
        System.out.println("lockSectors");
        assertEquals(false, s101.isLocked());
        assertEquals(false, s102.isLocked());
        assertEquals(false, s103.isLocked());
        instance.lockSectors();
        assertEquals(true, s101.isLockedBy(instance));
        assertEquals(true, s102.isLockedBy(instance));
        assertEquals(true, s103.isLockedBy(instance));
    }

    /**
     * Test of unlockSectors method, of class Block.
     */
    @Test
    public void testUnlockSectors() {
        System.out.println("unlockSectors");
        instance.unlockSectors();
        assertEquals(false, s101.isLockedBy(instance));
        assertEquals(false, s102.isLockedBy(instance));
        assertEquals(false, s103.isLockedBy(instance));
    }

    /**
     * Test of setSwitches method, of class Block.
     */
    @Test
    public void testSetSwitches() {
        System.out.println("setSwitches");
        assertEquals(0, SX.instance().getStatusBit(sw131.address, sw131.bitpos));
        instance.setSwitches();
        assertEquals(1, SX.instance().getStatusBit(sw131.address, sw131.bitpos));
        s102.lock(instance.id);
        instance.setSwitches();
        assertEquals(1, SX.instance().getStatusBit(sw131.address, sw131.bitpos));
    }

    /**
     * Test of isFirstSectorOccupied method, of class Block.
     */
    @Test
    public void testIsFirstSectorOccupied() {
        System.out.println("isFirstSectorOccupied");
        assertEquals(false, instance.isFirstSectorOccupied());
        SX.instance().setStatusBit(s101.address, s101.bitpos, 1);
        assertEquals(true, instance.isFirstSectorOccupied());
    }

    /**
     * Test of isLastSectorOccupied method, of class Block.
     */
    @Test
    public void testIsLastSectorOccupied() {
        System.out.println("isLastSectorOccupied");
        assertEquals(false, instance.isLastSectorOccupied());
        SX.instance().setStatusBit(s103.address, s103.bitpos, 1);
        assertEquals(true, instance.isLastSectorOccupied());
    }

    /**
     * Test of getSwitchPositions method, of class Block.
     */
    @Test
    public void testGetSwitchPositions() {
        System.out.println("getSwitchPositions");
        assertEquals(switchPositions, instance.getSwitchPositions());
    }

    /**
     * Test of containsStop method, of class Block.
     */
    @Test
    public void testContainsStop() {
        System.out.println("containsStop");
        assertEquals(false, instance.containsStop());
    }

}