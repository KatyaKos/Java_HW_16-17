import java.util.*;

/**
 * @author KatyaKos
 * Realization of MeTreeSet interface that is based on a binary search tree.
 * @param <E> type of elements in the set
 */
public class MyTreeSets<E> extends AbstractSet<E> implements MyTreeSet<E> {
    /**
     * Size of the set.
     */
    private int size;

    /**
     * Comparator we use to compare elements. It is '<' if the user will not give his own to the constructor.
     */
    private Comparator<? super E> comparator;

    /**
     * Start node. Root of the tree.
     */
    private Node<E> root;

    public MyTreeSets() {
        comparator = (a, b) -> ((Comparable<E>) a).compareTo(b);
    }

    public MyTreeSets(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Adds a new element to the set.
     * @param element what we want to add
     * @return true if there was no such element in the set and false otherwise
     */
    @Override
    public boolean add(E element) {
        if (size == 0) {
            root = new Node<>(element);
            size = 1;
            return true;
        }
        size++;
        Node<E> current = root;
        while (true) {
            int result = comparator.compare(element, current.value);
            if (result == 0) {
                size--;
                return false;
            } else if (result < 0) {
                if (current.left == null) {
                    current.left = new Node<>(element, current, -1);
                    return true;
                } else {
                    current = current.left;
                }
            } else{
                if (current.right == null) {
                    current.right = new Node<>(element, current, 1);
                    return true;
                } else {
                    current = current.right;
                }
            }
        }
    }

    /**
     * Removes an element from the set.
     * @param o what we want to remove
     * @return true if the elements was in the set and it was possible to remove it and false otherwise
     */
    @Override
    public boolean remove(Object o) {
        Node<E> current = contain(o);
        if (current == null) {
            return false;
        }
        E element = (E) o;
        if (current.value != element) {
            return false;
        }
        size--;

        if (current.left == null && current.right == null) {
            if (current.parentDirection < 0) {
                current.parent.left = null;
                current.parent = null;
            } else if (current.parentDirection > 0){
                current.parent.right = null;
                current.parent = null;
            } else {
                root = null;
            }
            return true;
        }

        if (current.left == null) {
            current.right.parent = current.parent;
            if (current.parentDirection < 0) {
                current.parent.left = current.right;
                current.right.parentDirection = -1;
                current.parent = null;
                current.right = null;
            } else if (current.parentDirection > 0){
                current.parent.right = current.right;
                current.right.parentDirection = 1;
                current.parent = null;
                current.right = null;
            } else {
                root = current.right;
            }
            return true;
        }

        Node<E> swapNode = current.left;
        while (swapNode.right != null) {
            swapNode = swapNode.right;
        }
        Node<E> tempSwapNode = new Node<>(swapNode.value, current.parent, current.parentDirection);
        if (swapNode.parentDirection > 0) {
            swapNode.parent.right = swapNode.left;
            if (swapNode.left != null) {
                swapNode.left.parent = swapNode.parent;
                swapNode.left.parentDirection = 1;
            }
        } else {
            swapNode.parent.left = swapNode.left;
            if (swapNode.left != null) {
                swapNode.left.parent = swapNode.parent;
                swapNode.left.parentDirection = -1;
            }
        }
        swapNode.parent = null;
        swapNode.left = null;

        if (current.left != null) {
            tempSwapNode.left = current.left;
            tempSwapNode.left.parent = tempSwapNode;
        }
        if (current.right != null) {
            tempSwapNode.right = current.right;
            tempSwapNode.right.parent = tempSwapNode;
        }
        if (current.parentDirection < 0) {
            current.parent.left = tempSwapNode;
        } else if (current.parentDirection > 0){
            current.parent.right = tempSwapNode;
        } else {
            root = tempSwapNode;
        }

        current.left = null;
        current.right = null;
        current.parent = null;
        return true;
    }

    /**
     * Tells if an element is in the set.
     * @param o what we want to find
     * @return true if element is in the set and false otherwise
     */
    @Override
    public boolean contains(Object o) {
        Node<E> result = contain(o);
        if (result == null) {
            return false;
        }
        E element = (E) o;
        if (result.value != element) {
            return false;
        }
        return true;
    }

    private Node<E> contain(Object o) {
        if (size == 0 || o == null) {
            return null;
        }
        E element = (E) o;
        Node<E> current = root;

        while (true) {
            int result = comparator.compare(element, current.value);
            if (result == 0) {
                return current;
            } else if (result < 0) {
                if (current.left == null) {
                    return current;
                } else {
                    current = current.left;
                }
            } else{
                if (current.right == null) {
                    return current;
                } else {
                    current = current.right;
                }
            }
        }
    }

    /**
     * Returns the lowest element.
     * @return element if set is not empty and null otherwise
     */
    public E first() {
        if (size == 0) {
            return null;
        }
        Node<E> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    /**
     * Returns the highest element.
     * @return element if set is not empty and null otherwise
     */
    public E last() {
        if (size == 0) {
            return null;
        }
        Node<E> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    /**
     * Returns the greatest element in the set strictly less than the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    public E lower(E element) {
        Node<E> current = contain(element);
        if (current == null) {
            return null;
        }
        if (current.value == element) {
            if (current.left == null) {
                while (current.parentDirection == -1) {
                    current = current.parent;
                }
                if (current.parent == null) {
                    return null;
                }
                return current.parent.value;
            }
            current = current.left;
            while (current.right != null) {
                current = current.right;
            }
            return current.value;
        }
        return floor(element);
    }

    /**
     * Returns the greatest element in the set less than or equal to the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    public E floor(E element) {
        Node<E> current = contain(element);
        if (current == null) {
            return null;
        }
        if (comparator.compare(element, current.value) < 0) {
            while (current.parentDirection == -1) {
                current = current.parent;
            }
            if (current.parent == null) {
                return null;
            }
            return current.parent.value;
        } else {
            return current.value;
        }
    }

    /**
     * Returns the smallest element in the set strictly greater than the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    public E higher(E element) {
        Node<E> current = contain(element);
        if (current == null) {
            return null;
        }
        if (current.value == element) {
            if (current.right == null) {
                while (current.parentDirection == 1) {
                    current = current.parent;
                }
                if (current.parent == null) {
                    return null;
                }
                return current.parent.value;
            }
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        }
        return ceiling(element);
    }

    /**
     * Returns the smallest element in the set greater than or equal to the given element.
     * @param element what we want to compare
     * @return null if there is no such element and the suitable element otherwise
     */
    public E ceiling(E element) {
        Node<E> current = contain(element);
        if (current == null) {
            return null;
        }
        if (comparator.compare(element, current.value) > 0) {
            while (current.parentDirection == 1) {
                current = current.parent;
            }
            if (current.parent == null) {
                return null;
            }
            return current.parent.value;
        } else {
            return current.value;
        }
    }

    /**
     * Returns size of the set.
     */
    public int size() {
        return size;
    }

    /**
     * Returns iterator on the beginning of the set.
     */
    public Iterator<E> iterator() {
        return new MyTreeSetsIterator();
    }

    /**
     * Returns an iterator for the set in descending order.
     */
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    /**
     * Returns a set of elements in reverse order.
     */
    public MyTreeSet<E> descendingSet() {
        return new MyDescendingSet();
    }

    private static class Node<E>{
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;
        private int parentDirection;
        private E value;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> parent, int direction) {
            this.value = value;
            this.parent = parent;
            parentDirection = direction;
        }
    }

    /**
     * Iterator Class for the set.
     */
    private class MyTreeSetsIterator implements Iterator<E> {
        /**
         * The node the iterator is before.
         */
        private Node<E> node;
        /**
         * State is 1 if we checked both node's children and 0 otherwise.
         */
        private Stack<Integer> state;

        public MyTreeSetsIterator() {
            state = new Stack<>();
            if (root != null) {
                node = root;
                state.push(0);
                while (node.left != null) {
                    node = node.left;
                    state.push(0);
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            E element = node.value;
            state.pop();
            if (node.right == null) {
                node = node.parent;
                while (node != null && state.pop() == 1) {
                    node = node.parent;
                }
                if (node != null) {
                    state.push(0);
                }
            } else {
                state.push(1);
                node = node.right;
                state.push(0);
                while (node.left != null) {
                    node = node.left;
                    state.push(0);
                }
            }
            return element;
        }
    }

    /**
     * The set in the reverse oder.
     */
    private class MyDescendingSet extends AbstractSet<E> implements MyTreeSet<E> {

        @Override
        public Iterator<E> descendingIterator() {
            return MyTreeSets.this.iterator();
        }

        @Override
        public MyTreeSet<E> descendingSet() {
            return MyTreeSets.this;
        }

        @Override
        public E first() {
            return MyTreeSets.this.last();
        }

        @Override
        public E last() {
            return MyTreeSets.this.first();
        }

        @Override
        public E lower(E element) {
            return MyTreeSets.this.higher(element);
        }

        @Override
        public E floor(E element) {
            return MyTreeSets.this.ceiling(element);
        }

        @Override
        public E ceiling(E element) {
            return MyTreeSets.this.floor(element);
        }

        @Override
        public E higher(E element) {
            return MyTreeSets.this.lower(element);
        }

        @Override
        public Iterator<E> iterator() {
            return new DescendingIterator();
        }

        @Override
        public int size() {
            return MyTreeSets.this.size();
        }

        /**
         * Iterator for the set in the reverse order.
         */
        private class DescendingIterator implements Iterator<E> {

            private Node<E> node;
            private Stack<Integer> state;

            public DescendingIterator() {
                state = new Stack<>();
                if (root != null) {
                    node = root;
                    state.push(0);
                    while (node.right != null) {
                        node = node.right;
                        state.push(0);
                    }
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (hasNext() == false) {
                    throw new NoSuchElementException();
                }
                E element = node.value;
                state.pop();
                if (node.left == null) {
                    node = node.parent;
                    while (node != null && state.pop() == 1) {
                        node = node.parent;
                    }
                    if (node != null) {
                        state.push(0);
                    }
                } else {
                    state.push(1);
                    node = node.left;
                    state.push(0);
                    while (node.right != null) {
                        node = node.right;
                        state.push(0);
                    }
                }
                return element;
            }
        }
    }
}
