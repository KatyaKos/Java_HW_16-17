/**
 * Created by KatyaKos on 07.12.2016.
 */
public class InjectionCycleException extends Exception {
    public InjectionCycleException() {
        System.out.println("Dependencies make cycle!");
    }
}
