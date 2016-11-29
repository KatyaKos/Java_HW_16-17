import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 29.11.2016.
 */
public class SerializationTest {
    MyClass clazz;
    @org.junit.Before
    public void setUp() throws Exception {
        clazz = new MyClass("Mister Sandman, bring me a dream", 'c', true, (byte)0xe0, (short)1, 420, Long.MAX_VALUE, (float)1.0, Double.MAX_VALUE);
    }

    @org.junit.Test
    public void serializeDeserializeTest() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Serialization.serialize(clazz, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        MyClass result = Serialization.deserialize(inputStream, MyClass.class);

        assertNotNull(result);
        assertEquals(clazz.stringField, result.stringField);
        assertEquals(clazz.charField, result.charField);
        assertEquals(clazz.booleanField, result.booleanField);
        assertEquals(clazz.byteField, result.byteField);
        assertEquals(clazz.shortField, result.shortField);
        assertEquals(clazz.intField, result.intField);
        assertEquals(clazz.longField, result.longField);
        assertEquals(clazz.floatField, result.floatField, 0);
        assertEquals(clazz.doubleField, result.doubleField, 0);
    }

    private static class MyClass {
        private String stringField;
        private char charField;
        private boolean booleanField;
        private byte byteField;
        private short shortField;
        private int intField;
        private long longField;
        private float floatField;
        private double doubleField;

        public MyClass(String stringField, char charField, boolean booleanField, byte byteField,
                       short shortField, int intField, long longField, float floatField, double doubleField) {
            this.stringField = stringField;
            this.charField = charField;
            this.booleanField = booleanField;
            this.byteField = byteField;
            this.shortField = shortField;
            this.intField = intField;
            this.longField = longField;
            this.floatField = floatField;
            this.doubleField = doubleField;
        }
    }


}