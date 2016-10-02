package me.spbau.katyakos.java.hw_16_17.hw1.HashTable.List;

/**
 * Created by KatyaKos on 18.09.2016.
 */
public class List {
    private Node headElement;
    private int nodeLength;

    public List() {
        headElement = new Node();
        nodeLength = 0;
    }

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
        if (nodeLength != 0 & key.equals(temp.getNext().getKey())) {
            String oldValue = temp.getNext().getValue();
            temp.setNext(null);
            nodeLength--;
            return oldValue;
        }
        return null;
    }

}
