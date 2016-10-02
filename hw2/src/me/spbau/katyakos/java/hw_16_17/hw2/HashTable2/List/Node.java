package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 * Node is the part of List that consists of some data and a link to the next Node.
 */
public class Node {
    private Pair element;     //element.first == key, element.second == value
    private Node next;

    Node() {
        element = new Pair();
        next = null;
    }

    Node(String key, String value) {
        element = new Pair(key, value);
        next = null;
    }

    /**
     * Returns the key.
     */
    public String getKey() {
        return element.getFirst();
    }

    /**
     * Sets the key.
     */
    public void setKey(String key) {
        element.setFirst(key);
    }

    /**
     * Returns the value.
     */
    public String getValue() {
        return element.getSecond();
    }

    /**
     * Sets the value.
     */
    public void setValue(String value) {
        element.setSecond(value);
    }

    /**
     * Returns the next element.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next element.
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
