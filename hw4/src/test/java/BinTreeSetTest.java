import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 09.10.2016.
 */
public class BinTreeSetTest {
    private BinTreeSet set;
    @Before
    public void setUp() throws Exception {
        set = new BinTreeSet();
    }

    @org.junit.Test
    public void addTest() throws Exception {
        assertFalse(set.add("Trion"));
        assertTrue(set.add("Trion"));
        assertFalse(set.add("Karry"));
        assertFalse(set.add("Sirin"));
        assertFalse(set.add("Haron"));
        assertTrue(set.add("Karry"));
    }

    @org.junit.Test
    public void sizeTest() throws Exception {
        assertEquals(0, set.size());
        set.add("Trion");
        set.add("Trion");
        assertEquals(1, set.size());
        set.add("Karry");
        assertEquals(2, set.size());
        set.add("Sirin");
        set.add("Haron");
        assertEquals(4, set.size());
        set.add("Karry");
        assertEquals(4, set.size());
    }

    @org.junit.Test
    public void containsTest() throws Exception {
        assertFalse(set.contains("Torren"));
        set.add("Trion");
        assertTrue(set.contains("Trion"));
        set.add("Trion");
        assertTrue(set.contains("Trion"));
        assertFalse(set.contains("Trione"));
        set.add("Karry");
        set.add("Sirin");
        set.add("Haron");
        set.add("Karry");
        assertFalse(set.contains("Kerry"));
        assertTrue(set.contains("Sirin"));
    }

}