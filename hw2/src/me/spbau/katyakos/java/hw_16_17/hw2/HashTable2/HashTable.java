package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2;

import me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 * Hash Table. Method - separate chaining with linked lists. Keys and values are Strings.
 */
public class HashTable {
    private int keyNumber;
    private final int module = 115249;
    private final int primary = 307;
    private List[] list;

    public HashTable() {
        clear();
    }

    /**
     * Counts the polynomial hash of the key that is String.
     * @param key - key of the element
     * @return hash of the key
     */
    private int getHash(String key) {
        int hash = 0;
        char[] charArray = key.toCharArray();
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * primary + charArray[i]) % module;
        }
        return hash;
    }

    /**
     * Returns number of keys in HashTable
     */
    public int size() {
        return keyNumber;
    }

    /**
     * Tells if HashTable contains a key.
     * @param key - key of the element
     * @return true if HashTable contains such key and false otherwise
     */
    public boolean contains(String key) {
        int hash = getHash(key);
        String found = list[hash].find(key);
        return null != found;
    }

    /**
     * Tells th value of an element.
     * @param key - key of the element
     * @return value of the element with such key (null if it does not exist)
     */
    public String get(String key) {
        int hash = getHash(key);
        return list[hash].find(key);
    }

    /**
     * Adds an element to HashTable. Changes the value if such key already exists.
     * @param key - key of the element
     * @param value - value of the element
     * @return old value if the element with such key already exists and null otherwise
     */
    public String put(String key, String value) {
        int hash = getHash(key);
        String found = list[hash].append(key, value);
        if (found != null) {
            return found;
        }
        keyNumber++;
        return null;
    }

    /**
     * Removes an element from the HashTable.
     * @param key - key of the element
     * @return old value if the element with such key already exists and null otherwise
     */
    public String remove(String key) {
        int hash = getHash(key);
        String found = list[hash].delete(key);
        if (found != null) {
            keyNumber--;
            return found;
        }
        return null;
    }

    /**
     * Clears the whole HashTable.
     */
    public final void clear() {
        keyNumber = 0;
        list = new List[module];
        for (int i = 0; i < module; i++){
            list[i] = new List();
        }
    }

}
