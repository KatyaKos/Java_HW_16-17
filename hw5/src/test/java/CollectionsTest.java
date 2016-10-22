import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 22.10.2016.
 */
public class CollectionsTest {
    private LinkedList<Integer> list0 = new LinkedList<>();
    private LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(-1, 1, 2, 3, 4, 3, 5));
    private LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(1, 1, 4, 9, 16, 9, 25));
    private LinkedList<Integer> list3 = new LinkedList<>(Arrays.asList(1, 1, 4, 9));

    @Test
    public void map() throws Exception {
        Function1<Integer, Integer> square = x -> x * x;
        assertEquals(list2, Collections.map(square, list1));
        assertEquals(list0, Collections.map(square, list0));
    }

    @Test
    public void filter() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 1, 4, 9, 9));
        Predicate<Integer> lessThan10 = x -> x < 10;
        assertEquals(list, Collections.filter(lessThan10, list2));
        assertEquals(list0, Collections.filter(lessThan10, list0));
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate<Integer> lessThan10 = x -> x < 10;
        assertEquals(list3, Collections.takeWhile(lessThan10, list2));
        assertEquals(list0, Collections.takeWhile(lessThan10, list0));
    }

    @Test
    public void takeUnless() throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(-1));
        Predicate<Integer> moreThan0 = x -> x > 0;
        assertEquals(list, Collections.takeUnless(moreThan0, list1));
        assertEquals(list0, Collections.takeUnless(moreThan0, list0));
    }

    @Test
    public void foldr() throws Exception {
        Function2<Integer, Integer, Integer> plusMult = (x, y) -> (x + 1) * y;
        assertEquals((Integer) 200, Collections.foldr(plusMult, 1, list3));
        assertEquals((Integer) 1, Collections.foldr(plusMult, 1, list0));
    }

    @Test
    public void foldl() throws Exception {
        Function2<Integer, Integer, Integer> plusMult = (x, y) -> (x + 1) * y;
        assertEquals((Integer) 153, Collections.foldl(plusMult, 1, list3));
        assertEquals((Integer) 1, Collections.foldl(plusMult, 1, list0));
    }

}