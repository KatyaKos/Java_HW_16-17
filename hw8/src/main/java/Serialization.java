import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * The Serialization class allows to serialize and deserialize objects of  simple classes.
 * We class class simple if it contains only primitive or String fields.
 */
public class Serialization {
    /**
     * Deserializes an object of given simple class from given stream.
     * @param stream stream with serialized object
     * @param clazz object of simple class
     * @param <T> simple class
     * @return the deserialized object
     */
    public static <T> T deserialize(InputStream stream, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Scanner scanner = new Scanner(stream);
        Arrays.sort(fields, Comparator.comparingInt(Field::hashCode));

        T answer;
        try {
            answer = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }

        for (Field field : fields) {
            setField(answer, field, scanner);
        }

        return answer;
    }

    /**
     * Sets fields in our object.
     */
    private static void setField(Object answer, Field field, Scanner scanner) {
        String string = scanner.nextLine();
        field.setAccessible(true);
        Class type = field.getType();
        try {
            if (type.equals(Boolean.TYPE)) {
                field.setBoolean(answer, Boolean.parseBoolean(string));
            } else if (type.equals(Byte.TYPE)) {
                field.setByte(answer, Byte.parseByte(string));
            } else if (type.equals(Character.TYPE)) {
                field.setChar(answer, string.charAt(0));
            } else if (type.equals(Short.TYPE)) {
                field.setShort(answer, Short.parseShort(string));
            } else if (type.equals(Integer.TYPE)) {
                field.setInt(answer, Integer.parseInt(string));
            } else if (type.equals(Long.TYPE)) {
                field.setLong(answer, Long.parseLong(string));
            } else if (type.equals(Float.TYPE)) {
                field.setFloat(answer, Float.parseFloat(string));
            } else if (type.equals(Double.TYPE)) {
                field.setDouble(answer, Double.parseDouble(string));
            } else if (type.equals(String.class)) {
                byte[] bytes = new byte[Integer.parseInt(string)];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = Byte.parseByte(scanner.nextLine());
                }
                field.set(answer, new String(bytes));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Deserialization algorithm is not correct");
        }
    }

    /**
     * Serializes given object to the given outputStream
     */

    /**
     * Serializes an object of given simple class to given stream.
     * @param object object of simple class
     * @param stream stream with the result of serialization
     */
    public static void serialize(Object object, OutputStream stream) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.sort(fields, Comparator.comparingInt(Field::hashCode));
        PrintWriter writer = new PrintWriter(stream);

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(object) instanceof String) {

                    byte[] bytes = ((String) field.get(object)).getBytes();
                    writer.println(bytes.length);
                    for (byte b : bytes) {
                        writer.println(b);
                    }

                } else {
                    writer.println(field.get(object));
                }
            }
            writer.flush();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Serialization algorithm is not correct");
        }
    }
}