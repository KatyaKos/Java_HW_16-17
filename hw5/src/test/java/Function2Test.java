import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 22.10.2016.
 */
public class Function2Test {
    private Function2<Integer, Integer, Integer> plus2 = (x, y) -> x + y + 2;
    private Function2<String, String, String> concatHail = (str1, str2) ->  str1 + ' ' + str2 + " is our King";
    private Function1<Integer, Integer> multiply3 = x -> x * 3;
    private Function2<Integer, Integer, Integer> plusMultiply2 = (x, y) -> x * 2 + y;

    @Test
    public void applyTest() throws Exception {
        assertEquals((Integer) 11, plus2.apply(4, 5));
        assertEquals((String) "Ron Weasley is our King", concatHail.apply("Ron", "Weasley"));
    }

    @Test
    public void composeTest() throws Exception {
        assertEquals((Integer) 33, plus2.compose(multiply3).apply(4, 5));
    }

    @Test
    public void bind1Test() throws Exception {
        assertEquals((Integer) 13, plusMultiply2.bind1(4).apply(5));
    }

    @Test
    public void bind2Test() throws Exception {
        assertEquals((Integer) 14, plusMultiply2.bind2(4).apply(5));
    }

    @Test
    public void curryTest() throws Exception {
        assertEquals((Integer) 13, plusMultiply2.curry().apply(4).apply(5));
        assertEquals((String) "Ron Weasley is our King", concatHail.curry().apply("Ron").apply("Weasley"));
    }

}