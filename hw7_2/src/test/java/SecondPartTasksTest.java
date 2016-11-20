import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(Arrays.asList("Hail Fassbender! Hail Macbeth!", "Hail Severus Snape", "All Hail Matthew!"), SecondPartTasks.findQuotes(Arrays.asList("test/test1.txt", "test/test2.txt", "test/test3.txt"), "Hail"));
        assertEquals(Arrays.asList("Ron Weasley is our King!", "McConaughey is the King"), SecondPartTasks.findQuotes(Arrays.asList("test/test1.txt", "test/test2.txt", "test/test3.txt"), "King"));
        assertEquals(Arrays.asList("Erik Lehnsherr is Fassbender!", "Fassbender and Charles Francis Xavier", "McConaughey is the King"), SecondPartTasks.findQuotes(Arrays.asList("test/test1.txt", "test/test3.txt"), "is"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), 0.01);
    }

    @Test
    public void testFindPrinter() {
        assertEquals("Newt Scamander", SecondPartTasks.findPrinter(ImmutableMap.of(
                        "Bulgakov", Arrays.asList("Master and Margarita", "Woland", "Begemoth and Fagotto"),
                        "J.M.Barrie", Arrays.asList("Peter Pan", "The Boy who wouldn't Grow Up"),
                        "Newt Scamander", Arrays.asList("Fantastic Beasts and  Where to Find Them", "Hufflepuff", "Cutest wizard ever")
                ))
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(ImmutableMap.of("Bertie Botts", 12, "Choc Frog", 26, "Butterscotch Beer", 18), SecondPartTasks.calculateGlobalOrder(Arrays.asList(
                        ImmutableMap.of("Bertie Botts", 5, "Choc Frog", 6),
                        ImmutableMap.of("Bertie Botts", 7, "Butterscotch Beer", 8, "Choc Frog", 9),
                        ImmutableMap.of("Butterscotch Beer", 10, "Choc Frog", 11)
                ))
        );
    }
}