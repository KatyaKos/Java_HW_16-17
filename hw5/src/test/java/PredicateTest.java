import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 22.10.2016.
 */
public class PredicateTest {
    private Predicate<Integer> is20 = x -> x == 20;
    private Predicate<Integer> isEven = x -> x % 2 == 0;
    private Predicate<String> containsFassbender = s -> s.contains("Fassbender");
    private Predicate<String> length10 = s -> s.length() == 10;

    @Test
    public void or() throws Exception {
        assertTrue(isEven.or(is20).apply(22));
        assertTrue(length10.or(containsFassbender).apply("Fassbender"));
        assertFalse(isEven.or(is20).apply(19));
    }

    @Test
    public void and() throws Exception {
        assertFalse(isEven.and(is20).apply(22));
        assertTrue(length10.and(containsFassbender).apply("Fassbender"));
        assertFalse(isEven.and(is20).apply(19));
    }

    @Test
    public void not() throws Exception {
        assertFalse(isEven.not().apply(22));
        assertFalse(length10.not().apply("Fassbender"));
        assertTrue(isEven.not().apply(18));
    }

    @Test
    public void ALWAYS_TRUE() throws Exception {
        assertTrue(isEven.ALWAYS_TRUE().apply(19));
    }

    @Test
    public void ALWAYS_FALSE() throws Exception {
        assertFalse(is20.ALWAYS_FALSE().apply(20));
    }

}