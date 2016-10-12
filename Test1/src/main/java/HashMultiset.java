import javafx.util.Pair;

import java.util.*;

/**
 * Created by KatyaKos on 05.10.2016.
 */
public class HashMultiset<E> extends HashSet<E> implements Multiset<E> {
    private int size;
    LinkedHashMap<E, Integer> mapOfElements;
    private HashSet<E> distinctElements;
    private LinkedHashSet<Multiset.Entry<E>> entries;


    public HashMultiset() {
        size = 0;
        mapOfElements = new LinkedHashMap<E, Integer>();
        distinctElements = new HashSet<E>();
        entries = new LinkedHashSet<Multiset.Entry<E>>();
    }

    @Override
    public boolean contains(Object o) {
        if (count(o) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        size++;
        int number = count(e);
        if (number == 0) {
            mapOfElements.put(e, 1);
            distinctElements.add(e);
            entries.add(new Entry(e, 1));
            return true;
        }
        //mapOfElements.remove(e);
        mapOfElements.put(e, number + 1);
        entries.remove(new Entry(e, number));
        entries.add(new Entry(e, number + 1));
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int number = count(o);
        if (number == 0) {
            return false;
        }
        E e = (E) o;
        if (number == 1) {
            mapOfElements.remove(o);
            distinctElements.remove(o);
            entries.remove(new Entry(e, 1));
        } else {
            //mapOfElements.remove(o);
            mapOfElements.put(e, number - 1);
            entries.remove(new Entry(e, number));
            entries.add(new Entry(e, number - 1));
        }
        size--;
        return true;
    }

    @Override
    public int count(Object element) {
        Integer number = mapOfElements.get(element);
        if (number == null) {
            return 0;
        }
        return number;
    }

    @Override
    public Set<E> elementSet() {
        return distinctElements;
    }

    @Override
    public Set<Multiset.Entry<E>> entrySet() {
        return entries;
    }

    @Override
    public Iterator<E> iterator() {
        return new ThisIterator();
    }

    @Override
    public int size() {
        return size;
    }

    private class Entry implements Multiset.Entry<E> {
        private E element;
        private int size;

        public Entry(E element, int size) {
            this.size = size;
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        @Override
        public int getCount() {
            return size;
        }

        public boolean equals(Object o) {
            if (o instanceof Multiset.Entry) {
                Entry entry = (Entry) o;
                return element.equals(entry.element) && size == entry.size;
            }
            return false;
        }

        public int hashCode() {
            return element.hashCode();
        }
    }

    private class ThisIterator implements Iterator<E> {
        private Iterator<Multiset.Entry<E>> iterator;
        private E leftElement;

        public ThisIterator() {
            iterator = entries.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            Multiset.Entry<E> entry = iterator.next();
            leftElement = entry.getElement();
            return leftElement;
        }

        @Override
        public void remove() {
            HashMultiset.this.remove(leftElement);
        }
    }
}
