package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 * List that contains list (Node) inside it.
 */
public class List {
    private Node headElement;
    private int nodeLength;

    public List() {
        headElement = new Node();
        nodeLength = 0;
    }

    /**
     * Adds an element to List. Changes the value if such key already exists.
     * @param key - key of the element
     * @param value - value of the element
     * @return old value if the element with such key already exists and null otherwise
     */
    public String append(String key, String value) {
        Node temp = headElement;
        for (int i = 0; i < nodeLength; i++) {
            temp = temp.getNext();
            if (key.equals(temp.getKey())) {
                String oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            }
        }
        temp.setNext(new Node(key, value));
        nodeLength++;
        return null;
    }

    /**
     * Finds an element in the List.
     * @param key - key of the element
     * @return value if the element with such key already exists and null otherwise
     */
    public String find(String key) {
        Node temp = headElement;
        for (int i = 0; i < nodeLength; i++) {
            temp = temp.getNext();
            if (key.equals(temp.getKey())) {
                return temp.getValue();
            }
        }
        return null;
    }

    /**
     * Removes an element from the List.
     * @param key - key of the element
     * @return old value if the element with such key already exists and null otherwise
     */
    public String delete(String key) {
        Node temp = headElement;
        for (int i = 0; i < nodeLength - 1; i++) {
            if (key.equals(temp.getNext().getKey())) {
                String oldValue = temp.getNext().getValue();
                temp.setNext(temp.getNext().getNext());
                nodeLength--;
                return oldValue;
            }
            temp = temp.getNext();
        }

        if (nodeLength != 0 && key.equals(temp.getNext().getKey())) {
            String oldValue = temp.getNext().getValue();
            temp.setNext(null);
            nodeLength--;
            return oldValue;
        }
        return null;
    }

}