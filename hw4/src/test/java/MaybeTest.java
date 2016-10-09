import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 09.10.2016.
 */
public class MaybeTest {
    Maybe<String> maybeTest0 = new Maybe();
    Maybe<String> maybeTest1 = new Maybe("Trion");
    Maybe<Integer> maybeTest2 = new Maybe(42);

    @Test
    public void justTest() throws Exception {
        assertEquals(maybeTest1, Maybe.just("Trion"));
        assertNotEquals(maybeTest0, Maybe.just("Trion"));
        assertEquals(maybeTest2, Maybe.just(42));
    }

    @Test
    public void nothingTest() throws Exception {
        assertEquals(maybeTest0, Maybe.nothing());
        assertNotEquals(maybeTest1, Maybe.nothing());
        assertNotEquals(maybeTest2, Maybe.nothing());
    }

    @Test(expected = MaybeException.class)
    public void getTest() throws Exception {
        assertEquals("Trion", maybeTest1.get());
        assertEquals((Integer) 42, maybeTest2.get());
        maybeTest0.get();
    }

    @Test
    public void isPresentTest() throws Exception {
        assertFalse(maybeTest0.isPresent());
        assertTrue(maybeTest1.isPresent());
        assertTrue(maybeTest2.isPresent());
    }

    @Test
    public void mapTest() throws Exception {
        Maybe<Integer> maybeNull = new Maybe();
        Maybe<String> maybeString = new Maybe("Trio");
        Maybe<Integer> maybeInteger = new Maybe(21);
        assertEquals(maybeTest0, maybeNull.map(x -> x.toString()));
        assertEquals(maybeTest1, maybeString.map(x -> x + 'n'));
        assertEquals(maybeTest2, maybeInteger.map(x -> x * 2));
    }

}