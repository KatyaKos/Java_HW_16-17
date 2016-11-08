/**
 * Created by KatyaKos on 06.11.2016.
 */
import java.util.Iterator;
import java.util.Set;

/**
 * Set of comparable elements interface that is capable of finding elements higher or lower than given.
 * @author KatyaKos
 * @param <E> type of elements in tge set
 */
public interface MyTreeSet<E> extends Set<E> {

    /**
     * Returns an iterator for the set in descending order.
     */
    Iterator<E> descendingIterator();

    /**
     * Returns a set of elements in reverse order.
     */
    MyTreeSet<E> descendingSet();


    /**
     * Returns the lowest element.
     * @return element if set is not empty and null otherwise
     */
    E first();

    /**
     * Returns the highest element.
     * @return element if set is not empty and null otherwise
     */
    E last();


    /**
     * Returns the greatest element in the set strictly less than the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    E lower(E element);

    /**
     * Returns the greatest element in the set less than or equal to the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    E floor(E element);


    /**
     * Returns the smallest element in the set greater than or equal to the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    E ceiling(E element);

    /**
     * Returns the smallest element in the set strictly greater than the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    E higher(E element);
}