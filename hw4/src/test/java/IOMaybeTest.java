import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 09.10.2016.
 */
public class IOMaybeTest {
    private IOMaybe numberList = new IOMaybe();

    @Test
    public void ioTest() throws Exception {
        FileInputStream fin = new FileInputStream("maybe.in");
        numberList.read(fin);
        FileOutputStream fout = new FileOutputStream("maybe.in");
        numberList.writeSquare(fout);
        fin.close();
        fout.close();

        fin = new FileInputStream("maybe.in");
        Scanner scanner = new Scanner(fin);
        ArrayList<Maybe<Integer>> oldData = numberList.getData();
        int i = 0;

        while (scanner.hasNext()) {
            String string = scanner.next();
            Maybe<Integer> maybe = oldData.get(i);
            if (!maybe.isPresent()) {
                assertEquals("null", string);
            } else {
                assertEquals(string, maybe.map(x -> x * x).get().toString());
            }
            i++;
        }
    }

}