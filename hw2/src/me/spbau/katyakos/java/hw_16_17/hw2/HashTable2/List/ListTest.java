package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 19.09.2016.
 */
public class ListTest {
    List list;

    @org.junit.Before
    public void prepare() throws Exception {
        list = new List();
    }

    @org.junit.Test
    public void append() throws Exception {
        assertEquals(null, list.append("Hail", "Fassbender"));
        assertEquals(null, list.append("Ron Weasley", "Our King"));
        assertEquals("Fassbender", list.append("Hail", "Macbeth"));
        assertEquals("Macbeth", list.append("Hail", "Daleks"));
    }

    @org.junit.Test
    public void delete() throws Exception {
        assertEquals(null, list.delete("Hail"));
        list.append("Hail", "Fassbender");
        list.append("Ron Weasley", "Our King");
        assertEquals(null, list.delete("Run Weasley"));
        assertEquals("Our King", list.delete("Ron Weasley"));
        list.append("Hail", "Macbeth");
        assertEquals("Macbeth", list.delete("Hail"));
    }

    @org.junit.Test
    public void find() throws Exception {
        assertEquals(null, list.find("Hail"));
        list.append("Hail", "Fassbender");
        assertEquals("Fassbender", list.find("Hail"));
        list.delete("Hail");
        assertEquals(null, list.find("Hail"));
        list.append("Ron Weasley", "Our King");
        assertEquals(null, list.find("Run Weasley"));
        assertEquals("Our King", list.find("Ron Weasley"));
        list.append("Hail", "Macbeth");
        assertEquals("Macbeth", list.find("Hail"));
        list.delete("Run Weasley");
        assertEquals(null, list.find("Run Weasley"));
    }

}