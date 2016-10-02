package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

/**
 * Created by KatyaKos on 19.09.2016.
 * @author KatyaKos
 * Pair is the element that consists of two values.
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

    /**
     * Sets both values.
     */
    public void setPair(String el1, String el2) {
        first = el1;
        second = el2;
    }

    /**
     * Sets the first value.
     */
    public void setFirst(String el) {
        first = el;
    }

    /**
     * Sets the second value.
     */
    public void setSecond(String el) {
        second = el;
    }

    /**
     * Returns the first value.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Returns the second value.
     */
    public String getSecond() {
        return second;
    }
}
