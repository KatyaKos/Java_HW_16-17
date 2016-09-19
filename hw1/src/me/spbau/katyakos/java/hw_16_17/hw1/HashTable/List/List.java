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
            temp = temp.next;
            if (key.equals(temp.getKey())) {
                String oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            }
        }
        temp.next = new Node(key, value);
        nodeLength++;
        return null;
    }

    public String find(String key) {
        Node temp = headElement;
        for (int i = 0; i < nodeLength; i++) {
            temp = temp.next;
            if (key.equals(temp.getKey())) {
                return temp.getValue();
            }
        }
        return null;
    }

    public String delete(String key) {
        Node temp = headElement;
        for (int i = 0; i < nodeLength - 1; i++) {
            if (key.equals(temp.next.getKey())) {
                String oldValue = temp.next.getValue();
                temp.next = temp.next.next;
                nodeLength--;
                return oldValue;
            }
            temp = temp.next;
        }
        if (nodeLength != 0 && key.equals(temp.next.getKey())) {
            String oldValue = temp.next.getValue();
            temp.next = null;
            nodeLength--;
            return oldValue;
        }
        return null;
    }

}
