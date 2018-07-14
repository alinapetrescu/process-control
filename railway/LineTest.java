/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dcrs.railway;

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
public class LineTest {

    static Station s1, s2, s3;
    static Line instance;

    public LineTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        s1 = new Station("s1");
        s2 = new Station("s2");
        s3 = new Station("s3");
        ArrayList<Station> stations = new ArrayList<Station>();
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);
        instance = new Line("line", stations);
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
     * Test of getCurrentStation method, of class Line.
     */
    @Test
    public void testGetCurrentStation() {
        System.out.println("getCurrentStation");
        assertEquals(s1, instance.getCurrentStation());
    }

    /**
     * Test of getNextStation method, of class Line.
     */
    @Test
    public void testGetNextStation() {
        System.out.println("getNextStation");
        assertEquals(s2, instance.getNextStation());
    }

    /**
     * Test of reachedNextStation method, of class Line.
     */
    @Test
    public void testReachedNextStation() {
        System.out.println("reachedNextStation");
        instance.reachedNextStation();
        assertEquals(s2, instance.getCurrentStation());
        assertEquals(s3, instance.getNextStation());
        instance.reachedNextStation();
        assertEquals(s3, instance.getCurrentStation());
        assertEquals(s1, instance.getNextStation());
        instance.reachedNextStation();
        assertEquals(s1, instance.getCurrentStation());
        assertEquals(s2, instance.getNextStation());
        instance.reachedNextStation();
        assertEquals(s2, instance.getCurrentStation());
        assertEquals(s3, instance.getNextStation());
    }

}