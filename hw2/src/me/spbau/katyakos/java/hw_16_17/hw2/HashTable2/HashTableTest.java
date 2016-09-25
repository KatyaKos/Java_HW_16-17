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
    public void putTest() throws Exception {
        assertNull(hashTable.put("Hail", "Fassbender"));
        assertNull(hashTable.put("Ron Weasley", "Our King"));
        assertEquals("Fassbender", hashTable.put("Hail", "Macbeth"));
        assertEquals("Macbeth", hashTable.put("Hail", "Daleks"));
    }

    @Test
    public void removeTest() throws Exception {
        assertEquals(null, hashTable.remove("Hail"));
        hashTable.put("Hail", "Fassbender");
        hashTable.put("Ron Weasley", "Our King");
        assertNull(hashTable.remove("Run Weasley"));
        assertEquals("Our King", hashTable.remove("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertEquals("Macbeth", hashTable.remove("Hail"));
    }

    @Test
    public void sizeTest() throws Exception {
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
    public void containsTest() throws Exception {
        assertFalse(hashTable.contains("Hail"));
        hashTable.put("Hail", "Fassbender");
        assertTrue(hashTable.contains("Hail"));
        hashTable.remove("Hail");
        assertFalse(hashTable.contains("Hail"));
        hashTable.put("Ron Weasley", "Our King");
        assertFalse(hashTable.contains("Run Weasley"));
        assertTrue(hashTable.contains("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertTrue(hashTable.contains("Hail"));
        assertTrue(hashTable.contains("Hail"));
        hashTable.remove("Run Weasley");
        assertFalse(hashTable.contains("Run Weasley"));
    }

    @Test
    public void getTest() throws Exception {
        assertNull(hashTable.get("Hail"));
        hashTable.put("Hail", "Fassbender");
        assertEquals("Fassbender", hashTable.get("Hail"));
        hashTable.remove("Hail");
        assertNull(hashTable.get("Hail"));
        hashTable.put("Ron Weasley", "Our King");
        assertNull(hashTable.get("Run Weasley"));
        assertEquals("Our King", hashTable.get("Ron Weasley"));
        hashTable.put("Hail", "Macbeth");
        assertEquals("Macbeth", hashTable.get("Hail"));
        assertEquals("Macbeth", hashTable.get("Hail"));
        hashTable.remove("Run Weasley");
        assertNull(hashTable.get("Run Weasley"));
    }

    @Test
    public void clearTest() throws Exception {
        hashTable.put("Hail", "Fassbender");
        hashTable.put("Ron Weasley", "Our King");
        hashTable.put("Hail", "Macbeth");
        hashTable.put("Hail", "Daleks");
        hashTable.clear();
        assertNull(hashTable.get("Hail"));
        assertEquals(0, hashTable.size());
    }

}