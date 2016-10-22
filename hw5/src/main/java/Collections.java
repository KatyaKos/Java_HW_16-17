import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by KatyaKos on 22.10.2016.
 * Simple Collections class with simple methods.
 */
public class Collections {

    /**
     * Applies a function on each element of an iterable object.
     * @param function how to change elements
     * @param list iterable object
     * @param <T> type of elements in List
     * @param <R> type of function's result
     * @return list of function's results
     */
    public static <T, R> List<R> map(Function1<? super T, ? extends R> function, Iterable<T> list) {
        List<R> answer = new LinkedList<R>();
        for (T element : list) {
            answer.add(function.apply(element));
        }
        return answer;
    }

    /**
     * Leaves only elements that suit our condition
     * @param predicate condition to check
     * @param list iterable object
     * @param <T> type of elements in List
     * @return list of "good" elements
     */
    public static <T> List<T> filter(Predicate<? super T> predicate, Iterable<T> list) {
        List<T> answer = new LinkedList<T>();
        for (T element : list) {
            if (predicate.apply(element)) {
                answer.add(element);
            }
        }
        return answer;
    }

    /**
     * Takes elements until it meets one that doesn't suit predicate
     * @param predicate condition to check
     * @param list iterable object
     * @param <T> type of elements in List
     * @return list of first "good" elements
     */
    public static <T> List<T> takeWhile(Predicate<? super T> predicate, Iterable<T> list) {
        List<T> answer = new LinkedList<T>();
        for (T element : list) {
            if (!predicate.apply(element)) {
                return answer;
            }
            answer.add(element);
        }
        return answer;
    }

    /**
     * Takes elements until it meets one that suits predicate
     * @param predicate condition to check
     * @param list iterable object
     * @param <T> type of elements in List
     * @return list of first "bad" elements
     */
    public static <T> List<T> takeUnless(Predicate<? super T> predicate, Iterable<T> list) {
        List<T> answer = new LinkedList<T>();
        for (T element : list) {
            if (predicate.apply(element)) {
                return answer;
            }
            answer.add(element);
        }
        return answer;
    }

    /**
     * Combines elements of the data structure's hierarchy, using the function in a right-associative way.
     * @param function function to apply
     * @param init initial element
     * @param list iterable object
     * @param <T> type of elements in List
     * @param <R> type of result
     * @return result of applying function on all elements in right-associative way
     */
    public static <T, R> R foldr(Function2<? super T, ? super R, ? extends R> function, R init, Iterable<T> list) {
        return foldr(function, init, list.iterator());
    }

    private static <T, R> R foldr(Function2<? super T, ? super R, ? extends R> function, R init, Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            return init;
        } else {
            return function.apply(iterator.next(), foldr(function, init, iterator));
        }
    }

    /**
     * Combines elements of the data structure's hierarchy, using the function in a left-associative way.
     * @param function function to apply
     * @param init initial element
     * @param list iterable object
     * @param <T> type of elements in List
     * @param <R> type of result
     * @return result of applying function to all elements in left-associative way
     */
    public static <T, R> R foldl(Function2<? super R, ? super T, ? extends R> function, R init, Iterable<T> list) {
        for (T element : list) {
            init = function.apply(init, element);
        }
        return init;
    }
}
