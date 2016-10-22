/**
 * Created by KatyaKos on 22.10.2016.
 * @author KatyaKos
 * Interface. Describes the function of one argument.
 * @param <T> type of the function's argument
 * @param <R> type of the function's result
 */
@FunctionalInterface
public interface Function1<T, R> {

    /**
     * Applies the function on argument.
     * @param element argument to apply
     * @return result of applying
     */
    R apply(T element);

    /**
     * Composition of function after with our function f.
     * @param after function to compose
     * @param <V> type of after's result
     * @return after(f(x))
     */
    default <V> Function1<T, V> compose(final Function1<? super R, V> after) {
        return (T t) -> after.apply(apply(t));
    }
}
