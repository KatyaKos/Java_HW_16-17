package me.spbau.katyakos.java.hw_16_17.hw1.HashTable;

import me.spbau.katyakos.java.hw_16_17.hw1.HashTable.List.List;

/**
 * Created by KatyaKos on 18.09.2016.
 */
public class HashTable {
    private int keyNumber;
    private final int module = 115249;
    private final int primary = 307;
    private List[] list;

    public HashTable() {
        keyNumber = 0;
        list = new List[module];
        for (int i = 0; i < module; i++){
            list[i] = new List();
        }
    }

    private int getHash(String key) {
        int hash = 0;
        char[] charArray = key.toCharArray();
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * primary + charArray[i]) % module;
        }
        return hash;
    }

    public int size() {
        return keyNumber;
    }
    public boolean contains(String key) {
        int hash = getHash(key);
        String found = list[hash].find(key);
        if (null != found) {
            return true;
        }
        return false;
    }
    public String get(String key) {
        int hash = getHash(key);
        return list[hash].find(key);
    }
    public String put(String key, String value) {
        int hash = getHash(key);
        String found = list[hash].append(key, value);
        if (found != null) {
            return found;
        }
        keyNumber++;
        return null;
    }
    public String remove(String key) {
        int hash = getHash(key);
        String found = list[hash].delete(key);
        if (found != null) {
            keyNumber--;
            return found;
        }
        return null;
    }
    public void clear() {
        keyNumber = 0;
        list = new List[module];
        for (int i = 0; i < module; i++){
            list[i] = new List();
        }
    }

}
