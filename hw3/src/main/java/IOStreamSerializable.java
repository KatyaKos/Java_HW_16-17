import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by KatyaKos on 28.09.2016.
 * @author KatyaKos
 * An interface with IO serialization methods.
 */
public interface IOStreamSerializable {
    /**
     * Prints the object to the OutputStream.
     * @param out stream where you want to print
     * @throws IOException when there is something wrong with output stream.
     */
    void serialize(OutputStream out) throws IOException;

    /**
     * Reads the object from the InputStream.
     * @param in stream where you want to read from
     * @throws IOException when there is something wrong with output stream.
     * @throws ClassNotFoundException when the given object is not a class.
     */
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
