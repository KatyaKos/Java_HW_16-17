/**
 * Created by KatyaKos on 07.12.2016.
 */
public class ImplementationNotFoundException extends Exception {
    public ImplementationNotFoundException() {
        System.out.println("No implementation for dependency!");
    }
}
