import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 22.10.2016.
 */
public class Function1Test {
    private Function1<Integer, Integer> plus2 = x -> x + 2;
    private Function1<String, String> concatHail = str -> "Hail " + str;
    private Function1<Integer, Integer> multiply3 = x -> x * 3;

    @org.junit.Test
    public void applyTest() throws Exception {
        assertEquals((Integer) 6, plus2.apply(4));
        assertEquals((String) "Hail Fassbender", concatHail.apply("Fassbender"));
    }

    @org.junit.Test
    public void composeTest() throws Exception {
        assertEquals((Integer) 18, plus2.compose(multiply3).apply(4));
        assertEquals((String) "Hail Hail Fassbender", concatHail.compose(concatHail).apply("Fassbender"));
    }

}