import org.junit.Before;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 08.11.2016.
 */
public class MyTreeSetsTest {
    private MyTreeSet<Integer> setBasic;
    private MyTreeSet<Integer> setComparator;

    @Before
    public void setUp() throws Exception {
        setBasic = new MyTreeSets<>();
        setComparator = new MyTreeSets<>((a, b) -> -1 * a.compareTo(b));
    }

    @org.junit.Test
    public void addTest() throws Exception {
        assertTrue(setBasic.add(5));
        assertTrue(setBasic.add(6));
        assertTrue(setBasic.add(3));
        assertFalse(setBasic.add(6));
        assertTrue(setBasic.add(0));
        assertFalse(setBasic.add(0));

        assertTrue(setComparator.add(5));
        assertTrue(setComparator.add(6));
        assertTrue(setComparator.add(3));
        assertFalse(setComparator.add(6));
        assertTrue(setComparator.add(0));
        assertFalse(setComparator.add(0));
    }

    @org.junit.Test
    public void removeTest() throws Exception {
        addTest();
        assertFalse(setBasic.remove(2));
        assertTrue(setBasic.remove(5));
        assertTrue(setBasic.add(4));
        assertTrue(setBasic.remove(0));
        assertTrue(setBasic.add(7));
        assertFalse(setBasic.remove(0));

        assertFalse(setComparator.remove(2));
        assertTrue(setComparator.remove(5));
        assertTrue(setComparator.add(4));
        assertTrue(setComparator.remove(0));
        assertTrue(setComparator.add(2));
        assertFalse(setComparator.remove(0));
    }

    @org.junit.Test
    public void containsTest() throws Exception {
        removeTest();
        assertTrue(setBasic.contains(3));
        assertTrue(setBasic.contains(4));
        assertTrue(setBasic.contains(6));
        assertTrue(setBasic.contains(7));

        assertTrue(setComparator.contains(3));
        assertTrue(setComparator.contains(4));
        assertTrue(setComparator.contains(6));
        assertTrue(setComparator.contains(2));
    }

    private void buildTree() throws Exception {
        setBasic.add(3);
        setBasic.add(6);
        setBasic.add(4);
        setBasic.add(9);
        setBasic.add(5);
        setBasic.add(2);
        setBasic.add(0);
        setBasic.add(-1);
        setBasic.add(1);

        setComparator.add(6);
        setComparator.add(3);
        setComparator.add(0);
        setComparator.add(4);
        setComparator.add(2);
        setComparator.add(9);
        setComparator.add(12);
        setComparator.add(13);
        setComparator.add(10);
    }

    @org.junit.Test
    public void sizeTest() throws Exception {
        buildTree();
        assertEquals(9, setBasic.size());
        assertEquals(9, setComparator.size());
    }

    @org.junit.Test
    public void firstTest() throws Exception {
        buildTree();
        assertEquals(Integer.valueOf(-1), setBasic.first());
        assertEquals(Integer.valueOf(13), setComparator.first());
    }

    @org.junit.Test
    public void lastTest() throws Exception {
        buildTree();
        assertEquals(Integer.valueOf(9), setBasic.last());
        assertEquals(Integer.valueOf(0), setComparator.last());
    }

    @org.junit.Test
    public void lowerTest() throws Exception {
        buildTree();
        assertNull(setBasic.lower(-1));
        assertEquals(Integer.valueOf(0), setBasic.lower(1));
        assertEquals(Integer.valueOf(-1), setBasic.lower(0));
        assertEquals(Integer.valueOf(1), setBasic.lower(2));
        assertEquals(Integer.valueOf(2), setBasic.lower(3));
        assertEquals(Integer.valueOf(5), setBasic.lower(6));
        assertEquals(Integer.valueOf(3), setBasic.lower(4));
        assertEquals(Integer.valueOf(6), setBasic.lower(9));
        assertEquals(Integer.valueOf(4), setBasic.lower(5));
        assertEquals(Integer.valueOf(9), setBasic.lower(15));

        assertNull(setComparator.lower(13));
        assertEquals(Integer.valueOf(13), setComparator.lower(12));
        assertEquals(Integer.valueOf(12), setComparator.lower(10));
        assertEquals(Integer.valueOf(10), setComparator.lower(9));
        assertEquals(Integer.valueOf(9), setComparator.lower(6));
        assertEquals(Integer.valueOf(4), setComparator.lower(3));
        assertEquals(Integer.valueOf(6), setComparator.lower(4));
        assertEquals(Integer.valueOf(2), setComparator.lower(0));
        assertEquals(Integer.valueOf(3), setComparator.lower(2));
        assertEquals(Integer.valueOf(0), setComparator.lower(-1));
    }

    @org.junit.Test
    public void floorTest() throws Exception {
        buildTree();
        assertEquals(Integer.valueOf(6), setBasic.floor(7));
        assertEquals((Integer.valueOf(3)), setBasic.floor(3));

        assertEquals(Integer.valueOf(12), setComparator.floor(11));
        assertEquals(Integer.valueOf(2), setComparator.floor(1));
        assertEquals(Integer.valueOf(6), setComparator.floor(5));
        assertEquals(Integer.valueOf(9), setComparator.floor(8));
        assertEquals(Integer.valueOf(4), setComparator.floor(4));
    }

    @org.junit.Test
    public void higherTest() throws Exception {
        buildTree();
        assertNull(setBasic.higher(15));
        assertEquals(Integer.valueOf(2), setBasic.higher(1));
        assertEquals(Integer.valueOf(1), setBasic.higher(0));
        assertEquals(Integer.valueOf(3), setBasic.higher(2));
        assertEquals(Integer.valueOf(4), setBasic.higher(3));
        assertEquals(Integer.valueOf(9), setBasic.higher(6));
        assertEquals(Integer.valueOf(5), setBasic.higher(4));
        assertNull(setBasic.higher(9));
        assertEquals(Integer.valueOf(6), setBasic.higher(5));
        assertEquals(Integer.valueOf(0), setBasic.higher(-1));

        assertNull(setComparator.higher(-1));
        assertEquals(Integer.valueOf(10), setComparator.higher(12));
        assertEquals(Integer.valueOf(9), setComparator.higher(10));
        assertEquals(Integer.valueOf(6), setComparator.higher(9));
        assertEquals(Integer.valueOf(4), setComparator.higher(6));
        assertEquals(Integer.valueOf(2), setComparator.higher(3));
        assertEquals(Integer.valueOf(3), setComparator.higher(4));
        assertNull(setComparator.higher(0));
        assertEquals(Integer.valueOf(0), setComparator.higher(2));
        assertEquals(Integer.valueOf(12), setComparator.higher(13));
    }

    @org.junit.Test
    public void ceilingTest() throws Exception {
        buildTree();
        assertEquals(Integer.valueOf(9), setBasic.ceiling(7));
        assertEquals((Integer.valueOf(3)), setBasic.ceiling(3));

        assertEquals(Integer.valueOf(10), setComparator.ceiling(11));
        assertEquals(Integer.valueOf(0), setComparator.ceiling(1));
        assertEquals(Integer.valueOf(4), setComparator.ceiling(5));
        assertEquals(Integer.valueOf(6), setComparator.ceiling(8));
        assertEquals(Integer.valueOf(4), setComparator.ceiling(4));
    }

    @org.junit.Test
    public void iteratorTest() throws Exception {
        buildTree();
        Iterator<Integer> iteratorBasic = setBasic.iterator();
        Iterator<Integer> iteratorComparator = setComparator.iterator();
        for (Integer integer : Arrays.asList(-1, 0, 1, 2, 3, 4, 5, 6, 9)) {
            assertTrue(iteratorBasic.hasNext());
            assertEquals(integer, iteratorBasic.next());
        }
        assertFalse(iteratorBasic.hasNext());
        for (Integer integer : Arrays.asList(13, 12, 10, 9, 6, 4, 3, 2, 0)) {
            assertTrue(iteratorComparator.hasNext());
            assertEquals(integer, iteratorComparator.next());
        }
        assertFalse(iteratorComparator.hasNext());
    }

    @org.junit.Test
    public void descendingIteratorTest() throws Exception {
        buildTree();
        Iterator<Integer> iteratorBasic = setBasic.descendingIterator();
        Iterator<Integer> iteratorComparator = setComparator.descendingIterator();

        for (Integer integer : Arrays.asList(9, 6, 5, 4, 3, 2, 1, 0, -1)) {
            assertTrue(iteratorBasic.hasNext());
            assertEquals(integer, iteratorBasic.next());
        }
        assertFalse(iteratorBasic.hasNext());
        for (Integer integer : Arrays.asList(0, 2, 3, 4, 6, 9, 10, 12, 13)) {
            assertTrue(iteratorComparator.hasNext());
            assertEquals(integer, iteratorComparator.next());
        }
        assertFalse(iteratorComparator.hasNext());
    }

    @org.junit.Test
    public void descendingSetTest() throws Exception {
        buildTree();
        MyTreeSet<Integer> descendingBasic = setBasic.descendingSet();
        MyTreeSet<Integer> descendingComparator = setComparator.descendingSet();

        assertEquals(9, descendingBasic.size());
        assertEquals(Integer.valueOf(9), descendingBasic.first());
        assertEquals(Integer.valueOf(-1), descendingBasic.last());
        assertEquals(Integer.valueOf(2), descendingBasic.lower(1));
        assertEquals(Integer.valueOf(6), descendingBasic.higher(9));
        assertEquals(9, descendingComparator.size());
        assertEquals(Integer.valueOf(0), descendingComparator.first());
        assertEquals(Integer.valueOf(13), descendingComparator.last());
        assertEquals(Integer.valueOf(0), descendingComparator.lower(1));
        assertEquals(Integer.valueOf(10), descendingComparator.higher(9));
    }

}