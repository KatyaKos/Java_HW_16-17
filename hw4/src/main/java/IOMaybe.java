import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KatyaKos on 09.10.2016.
 * This class can read  from file, square integers and write the result to another file (all not-integers turn into 'Nothing').
 */
public class IOMaybe {
    private ArrayList<Maybe<Integer>> data;

    public IOMaybe() {
        data = new ArrayList<Maybe<Integer>>();
    }

    /**
     * Forgets about all the data we already have.
     */
    public void clear() {
        data.clear();
    }

    public void read(InputStream reader) {
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()) {
            String string = scanner.next();
            Maybe<Integer> maybe;
            try {
                maybe = Maybe.just(Integer.parseInt(string));
            } catch (NumberFormatException exception) {
                maybe = Maybe.nothing();
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
