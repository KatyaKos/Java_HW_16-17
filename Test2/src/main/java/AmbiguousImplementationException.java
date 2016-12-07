/**
 * Created by KatyaKos on 07.12.2016.
 */
public class AmbiguousImplementationException extends Exception {
    public AmbiguousImplementationException() {
        System.out.println("Multiple implementations for one dependency were found!");
    }
}
