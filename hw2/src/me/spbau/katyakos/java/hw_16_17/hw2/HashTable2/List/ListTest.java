package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2.List;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 19.09.2016.
 */
public class ListTest {
    private List list;

    @org.junit.Before
    public void prepare() throws Exception {
        list = new List();
    }

    @org.junit.Test
    public void appendTest() throws Exception {
        assertNull(list.append("Hail", "Fassbender"));
        assertNull(list.append("Ron Weasley", "Our King"));
        assertEquals("Fassbender", list.append("Hail", "Macbeth"));
        assertEquals("Macbeth", list.append("Hail", "Daleks"));
    }

    @org.junit.Test
    public void deleteTest() throws Exception {
        assertEquals(null, list.delete("Hail"));
        list.append("Hail", "Fassbender");
        list.append("Ron Weasley", "Our King");
        assertEquals(null, list.delete("Run Weasley"));
        assertEquals("Our King", list.delete("Ron Weasley"));
        list.append("Hail", "Macbeth");
        assertEquals("Macbeth", list.delete("Hail"));
    }

    @org.junit.Test
    public void findTest() throws Exception {
        assertNull(list.find("Hail"));
        list.append("Hail", "Fassbender");
        assertEquals("Fassbender", list.find("Hail"));
        list.delete("Hail");
        assertNull(list.find("Hail"));
        list.append("Ron Weasley", "Our King");
        assertNull(list.find("Run Weasley"));
        assertEquals("Our King", list.find("Ron Weasley"));
        list.append("Hail", "Macbeth");
        assertEquals("Macbeth", list.find("Hail"));
        list.delete("Run Weasley");
        assertNull(list.find("Run Weasley"));
    }

}