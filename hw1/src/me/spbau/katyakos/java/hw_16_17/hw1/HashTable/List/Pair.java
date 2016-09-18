package me.spbau.katyakos.java.hw_16_17.hw1.HashTable.List;

/**
 * Created by KatyaKos on 18.09.2016.
 */
public class Pair {
    private String first;
    private String second;

    Pair() {
        first = null;
        second = null;
    }
    Pair(String el1, String el2) {
        first = el1;
        second = el2;
    }
    public void setPair(String el1, String el2) {
        first = el1;
        second = el2;
    }
    public void setFirst(String el) {
        first = el;
    }
    public void setSecond(String el) {
        second = el;
    }
    public String getFirst() {
        return first;
    }
    public String getSecond() {
        return second;
    }
}
