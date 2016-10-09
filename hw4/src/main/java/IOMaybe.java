import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KatyaKos on 09.10.2016.
 */
public class IOMaybe {
    private ArrayList<Maybe<Integer>> data;

    public IOMaybe() {
        data = new ArrayList<Maybe<Integer>>();
    }

    public void clear() {
        data.clear();
    }

    public void read(InputStream reader) {
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()) {
            String string = scanner.next();
            Maybe<Integer> maybe;
            try {
                maybe = new Maybe(Integer.parseInt(string));
            } catch(NumberFormatException exception) {
                maybe = new Maybe();
            }

            data.add(maybe);
        }
    }

    public void writeSquare(OutputStream writer) {
        PrintWriter printer = new PrintWriter(writer);
        for (Maybe<Integer> maybe : data) {
            if (maybe.isPresent()) {
                printer.println(maybe.map(x -> x * x).get());
            } else {
                printer.println("null");
            }
        }
        printer.flush();
    }

    public ArrayList<Maybe<Integer>> getData() {
        return data;
    }
}
