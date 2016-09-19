package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 */
public class Node {
    private Pair element;     //element.first == key, element.second == value
    protected Node next;

    Node() {
        element = new Pair();
        next = null;
    }
    Node(String key, String value) {
        element = new Pair(key, value);
        next = null;
    }
    public String getKey() {
        return element.getFirst();
    }
    public void setKey(String key) {
        element.setFirst(key);
    }
    public String getValue() {
        return element.getSecond();
    }
    public void setValue(String value) {
        element.setSecond(value);
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node nxt) {
        next = nxt;
    }
}
