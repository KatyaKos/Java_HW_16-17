import java.io.*;

/**
 * Created by KatyaKos on 26.09.2016.
 * @author KatyaKos
 * Trie class for strings using alphabet that consists of ascii symbols [32, 126].
 */
public class Trie implements IOStreamSerializable {
    private Vertex root;

    public Trie() {
        root = new Vertex();
    }

    /**
     *Returns number of strings already added to Trie.
     */
    public int size() {
        return root.getTrieSize();
    }

    /**
     * Adds string to Trie.
     * @param element - string that we want to add, ascii symbols [32, 126].
     * @return true if the string is already in the Trie.
     */
    public boolean add(String element) {
        char[] charArray = element.toCharArray();
        Vertex temp = root;
        for (char c : charArray) {
            temp = temp.makeNext(c);
        }
        return !temp.addTerminal();
    }

    /**
     * Tells if a string is in the Trie.
     * @param element - string that we want to check, ascii symbols [32, 126].
     * @return true if the string is already in the Trie.
     */
    public boolean contains(String element) {
        char[] charArray = element.toCharArray();
        Vertex temp = root;
        for (char c : charArray) {
            if (temp.checkNext(c)) {
                temp = temp.getNext(c);
            } else {
                return false;
            }
        }
        return temp.getTerminal();
    }

    /**
     * Removes a string form the Trie.
     * @param element - string that we want to remove, ascii symbols [32, 126].
     * @return true if the string was in the Trie.
     */
    public boolean remove(String element) {
        char[] charArray = element.toCharArray();
        Vertex temp = root;
        if (contains(element)) {
            for (char c : charArray) {
                temp = temp.getNext(c);
            }
            temp.deleteTerminal();
            temp = temp.getPrevious();
            for (int i = charArray.length - 1; i >= 0; i--) {
                temp.deleteVertex(charArray[i]);
                temp = temp.getPrevious();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tells the number of strings in the Trie that start with prefix.
     * @param prefix - substring we want to find in strings.
     * @return number of strings
     */
    public int howManyStartsWithPrefix(String prefix) {
        char[] charArray = prefix.toCharArray();
        Vertex temp = root;
        for (char c : charArray) {
            if (temp.checkNext(c)) {
                temp = temp.getNext(c);
            } else {
                return 0;
            }
        }
        return temp.getTrieSize();
    }

    /**
     * Prints the Trie to stream.
     * @param out - stream where you want to print
     * @throws IOException
     */
    public void serialize(OutputStream out) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)){
            objectOutputStream.writeObject(root);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Reads the Trie from stream.
     * @param in - stream where you want to read from
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(in)){
            root = (Vertex) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } catch (ClassNotFoundException c){
            System.out.print("There is no class Trie.");
        }
    }

}
