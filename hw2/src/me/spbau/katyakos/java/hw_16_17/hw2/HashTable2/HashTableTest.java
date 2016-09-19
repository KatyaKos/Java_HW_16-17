package me.spbau.katyakos.java.hw_16_17.hw2.HashTable2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 19.09.2016.
 */
public class HashTableTest {
    HashTable hashTable;
    @Before
    public void prepare() throws Exception {
        hashTable = new HashTable();
    }

    @Test
    public void put() throws Exception {
        assertEquals(null, hashTable.put("Hail", "Fassbender"));
        assertEquals(null, hashTable.put("Ron Weasley", "Our King"));
        assertEquals("Fassbender", hashTable.put("Hail", "Macbeth"));
        assertEquals("Macbeth", hashTable.put("Hail", "Daleks"));
    }

    @Test
    public void remove() throws Exception {
        assertEquals(null, hashTable.remove("Hail"));
        hashTable.put("Hail", "Fassbender");
        hashTable.put("Ron Weasley", "Our King");
        assertEquals(null, hashTable.remove("Run Weasley"));
        assertEquals("Our King", hashTable.remove("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertEquals("Macbeth", hashTable.remove("Hail"));
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, hashTable.size());
        hashTable.put("Hail", "Fassbender");
        assertEquals(1, hashTable.size());
        hashTable.remove("Hail");
        assertEquals(0, hashTable.size());
        hashTable.put("Ron Weasley", "Our King");
        hashTable.put("Hail", "Macbeth");
        assertEquals(2, hashTable.size());
        hashTable.remove("Run Weasley");
        assertEquals(2, hashTable.size());
    }

    @Test
    public void contains() throws Exception {
        assertEquals(false, hashTable.contains("Hail"));
        hashTable.put("Hail", "Fassbender");
        assertEquals(true, hashTable.contains("Hail"));
        hashTable.remove("Hail");
        assertEquals(false, hashTable.contains("Hail"));
        hashTable.put("Ron Weasley", "Our King");
        assertEquals(false, hashTable.contains("Run Weasley"));
        assertEquals(true, hashTable.contains("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertEquals(true, hashTable.contains("Hail"));
        assertEquals(true, hashTable.contains("Hail"));
        hashTable.remove("Run Weasley");
        assertEquals(false, hashTable.contains("Run Weasley"));
    }

    @Test
    public void get() throws Exception {
        assertEquals(null, hashTable.get("Hail"));
        hashTable.put("Hail", "Fassbender");
        assertEquals("Fassbender", hashTable.get("Hail"));
        hashTable.remove("Hail");
        assertEquals(null, hashTable.get("Hail"));
        hashTable.put("Ron Weasley", "Our King");
        assertEquals(null, hashTable.get("Run Weasley"));
        assertEquals("Our King", hashTable.get("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertEquals("Macbeth", hashTable.get("Hail"));
        assertEquals("Macbeth", hashTable.get("Hail"));
        hashTable.remove("Run Weasley");
        assertEquals(null, hashTable.get("Run Weasley"));
    }

    @Test
    public void clear() throws Exception {
        hashTable.put("Hail", "Fassbender");
        hashTable.put("Ron Weasley", "Our King");
        hashTable.put("Hail", "Macbeth");
        hashTable.put("Hail", "Daleks");
        hashTable.clear();
        assertEquals(null, hashTable.get("Hail"));
        assertEquals(0, hashTable.size());
    }

}