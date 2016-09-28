import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by KatyaKos on 28.09.2016.
 */
public interface IOStreamSerializable {
    void serialize(OutputStream out) throws IOException;
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
