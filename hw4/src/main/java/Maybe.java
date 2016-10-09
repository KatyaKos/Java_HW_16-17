import com.sun.istack.internal.Nullable;

import java.util.function.Function;

/**
 * Created by KatyaKos on 09.10.2016.
 * @author KatyaKos
 * Generic container that consists of one element (it can be empty or contain data of the given type).
 */
public class Maybe<T> {
    private @Nullable T value;

    public Maybe() {
        value = null;
    }

    public Maybe(T value) {
        this.value = value;
    }

    /**
     * Creates new Maybe container with an element inside.
     * @param element what we want to put
     * @param <T> type
     * @return new Maybe
     */
    public static <T> Maybe<T> just(T element) {
        return new Maybe(element);
    }

    /**
     * Creates new empty Maybe container.
     * @param <T> type
     * @return new Maybe
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe();
    }

    /**
     * Return the element from the container if it is not empty.
     * @throws MaybeException if Maybe container is empty
     */
    public T get() throws MaybeException {
        if (value == null) {
            throw new MaybeException();
        }
        return value;
    }

    /**
     * Tells if container stores empty element or not.
     * @return true if the element is not empty
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Creates new Maybe container with applyed function to the element of container.
     * @param mapper function that we want to apply
     * @param <U> type of returning value
     * @return new Maybe container
     */
    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        if (value == null) {
            return new Maybe();
        }

        return new Maybe(mapper.apply(value));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Maybe<?> maybe = (Maybe<?>) object;
        if (value == null) {
            return maybe.value == null;
        } else {
            return value.equals(maybe.value);
        }
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        } else {
            return value.hashCode();
        }
    }

}
