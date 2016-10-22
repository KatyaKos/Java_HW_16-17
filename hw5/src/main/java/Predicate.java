/**
 * Created by KatyaKos on 22.10.2016.
 * @author KatyaKos
 * Interface. Describes the predicate.
 * @param <T> type of the predicate's argument
 */
public interface Predicate<T> extends Function1<T, Boolean> {

    /**
     * Acts like '||'.
     */
    default Predicate<T> or(Predicate<? super T> predicate) {
        return (T t) -> apply(t) || predicate.apply(t);
    }

    /**
     * Acts like '&&'.
     */
    default Predicate<T> and(Predicate<? super T> predicate) {
        return (T t) -> apply(t) && predicate.apply(t);
    }

    /**
     * Acts like '!'.
     */
    default Predicate<T> not() {
        return (T t) -> ! apply(t);
    }

    /**
     * Always returns true.
     */
    default Predicate<T> ALWAYS_TRUE() {
        return (T t) -> true;
    }

    /**
     * Always returns false.
     */
    default Predicate<T> ALWAYS_FALSE() {
        return (T t) -> false;
    }
}
