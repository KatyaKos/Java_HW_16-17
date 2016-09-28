import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

/**
 * Created by KatyaKos on 28.09.2016.
 */
public class TrieTest {
    Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @Test
    public void addTest() throws Exception {
        assertTrue(trie.add(""));
        assertFalse(trie.add(""));
        assertTrue(trie.add("Hail Fassbender"));
        assertFalse(trie.add("Hail Fassbender"));
        assertTrue(trie.add("Hail Fassbendery"));
        assertTrue(trie.add("Weasley our king"));
    }

    @Test
    public void removeTest() throws Exception {
        trie.add("Hail Fassbender");
        trie.add("Hail Fassbender");
        assertTrue(trie.remove("Hail Fassbender"));
        trie.add("Hail Fassbendery");
        assertTrue(trie.remove("Hail Fassbender"));
        assertFalse(trie.remove("Hail Fassbender"));
        assertFalse(trie.remove(""));
        trie.add("");
        assertTrue(trie.remove(""));
    }

    @Test
    public void containsTest() throws Exception {
        trie.add("Hail Fassbender");
        trie.add("Hail Fassbender");
        assertTrue(trie.contains("Hail Fassbender"));
        trie.remove("Hail Fassbender");
        assertTrue(trie.contains("Hail Fassbender"));
        trie.add("Hail Fassbendery");
        assertTrue(trie.contains("Hail Fassbendery"));
        trie.remove("Hail Fassbender");
        assertTrue(trie.contains("Hail Fassbendery"));
        trie.remove("Hail Fassbender");
        assertFalse(trie.contains("Hail Fassbender"));
        assertFalse(trie.contains(""));
        trie.add("");
        assertTrue(trie.contains(""));
    }

    @Test
    public void sizeTest() throws Exception {
        trie.add("Hail Fassbender");
        trie.add("Hail Fassbender");
        assertEquals(2, trie.size());
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.size());
        trie.add("Hail Fassbendery");
        assertEquals(2, trie.size());
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.size());
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.size());
        trie.add("");
        assertEquals(2, trie.size());
    }

    @Test
    public void howManyStartsWithPrefixTest() throws Exception {
        trie.add("Hail Fassbender");
        trie.add("Hail Fassbender");
        assertEquals(2, trie.howManyStartsWithPrefix("Hail F"));
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.howManyStartsWithPrefix("Hail F"));
        trie.add("Hail Fassbendery");
        assertEquals(2, trie.howManyStartsWithPrefix(""));
        assertEquals(2, trie.howManyStartsWithPrefix("Hail F"));
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.howManyStartsWithPrefix("Hail F"));
        trie.remove("Hail Fassbender");
        assertEquals(1, trie.howManyStartsWithPrefix("Hail F"));
        assertEquals(0, trie.howManyStartsWithPrefix("Tail"));
        assertEquals(1, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void serializableTest() throws Exception {
        trie.add("Hail Fassbender");
        trie.add("Hail Weasley");
        trie.add("Ha");
        trie.add("");
        Trie newTrie = new Trie();
        FileOutputStream fout = new FileOutputStream("trie.in");
        trie.serialize(fout);
        FileInputStream fin = new FileInputStream("trie.in");
        newTrie.deserialize(fin);
        assertEquals(4, newTrie.size());
        assertTrue(newTrie.contains("Hail Fassbender"));
        assertTrue(newTrie.contains("Hail Weasley"));
        assertTrue(newTrie.contains("Ha"));
        assertTrue(newTrie.contains(""));
        fin.close();
        fout.close();
    }

}