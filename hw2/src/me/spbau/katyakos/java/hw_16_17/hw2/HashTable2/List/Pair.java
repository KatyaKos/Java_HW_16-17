package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
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
