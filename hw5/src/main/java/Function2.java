/**
 * Created by KatyaKos on 22.10.2016.
 * @author KatyaKos
 * Interface. Describes the function of two arguments.
 * @param <T> type of the function's first argument
 * @param <U> type of the function's second argument
 * @param <R> type of the function's result
 */
@FunctionalInterface
public interface Function2<T, U, R> {

    /**
     * Applies the function on argument.
     * @param element1 first argument to apply
     * @param element2 second argument to apply
     * @return result of applying
     */
    R apply(T element1, U element2);

    /**
     * Composition of function after with our function f.
     * @param after function to compose
     * @param <V> type of after's result
     * @return after(f(x, y))
     */
    default <V> Function2<T, U, V> compose(final Function1<? super R, V> after) {
        return (T t, U u) -> after.apply(apply(t, u));
    }

    /**
     * Bind of the first argument.
     * @param element1 first argument
     * @return function(element1, _)
     */
    default Function1<U, R> bind1(T element1) {
        return (U element2) -> apply(element1, element2);
    }

    /**
     * Bind of the second argument.
     * @param element2 second argument
     * @return function(_, element2)
     */
    default Function1<T, R> bind2(U element2) {
        return (T element1) -> apply(element1, element2);
    }

    /**
     * Turning Function2 into Function1.
     * @return curried version
     */
    default Function1<T, Function1<U, R>> curry() {
        return (T element1) -> (U element2) -> apply(element1, element2);
    }
}