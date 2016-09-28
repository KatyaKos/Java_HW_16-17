import java.io.*;

/**
 * Created by KatyaKos on 27.09.2016.
 * @author KatyaKos
 * Any trie consists of many Vertexes. The way to Vertex is substring.
 * Vertex is terminal means that there is at least one string that ends in this Vertex.
 */
public class Vertex implements Serializable {
    private final static int ALPHABET = 95; //from 32 to 126
    Vertex[] next;
    Vertex previous;
    private int terminalSize;
    private int childrenSize;
    private int subTrieSize;

    public Vertex() {
        next = new Vertex[ALPHABET];
        for (int i = 0; i < ALPHABET; i++) {
            next[i] = null;
        }
        previous = null;
        terminalSize = 0;
        childrenSize = 0;
        subTrieSize = 0;
    }

    /**
     * Returns how many strings start from such substring (substring == way to this Vertex)
     */
    public int getTrieSize() {
        return subTrieSize + terminalSize;
    }

    /**
     * Returns if there are any strings that end in this Vertex.
     */
    public boolean getTerminal() {
        return terminalSize != 0;
    }

    /**
     * Makes the Vertex terminal.
     * @return true if Vertex has already been terminal
     */
    public boolean addTerminal() {
        terminalSize++;
        return terminalSize > 1;
    }

    /**
     * Tells the Vertex that user removes the string that was in this Vertex.
     */
    public void deleteTerminal() {
        terminalSize--;
    }

    /**
     * Returns code of the character. Code 0 is for ' ' and 94 is for '~'.
     */
    private int getCode(int character) {
        return character - ' ';
    }

    /**
     * Returns the Vertex where the character leads to.
     */
    public Vertex getNext(char character) {
        return next[getCode(character)];
    }

    /**
     * Returns the Vertex where we came from.
     */
    public Vertex getPrevious() {
        return previous;
    }

    /**
     * Returns if this character leads to some Vertex.
     * There is a way from the Vertex to such character if we have already added it.
     * @return true if there is already such substring in the Trie.
     */
    public boolean checkNext(char character) {
        return null != next[getCode(character)];
    }

    /**
     * Tells the Vertex who it's parent is.
     */
    private void makePrevious(Vertex parent) {
        previous = parent;
    }

    /**
     * Makes new way from the Vertex with the character in parameters.
     * @return new Vertex
     */
    public Vertex makeNext(char character) {
        int i = getCode(character);
        subTrieSize++;
        if (null != next[i]) {
            return next[i];
        }
        next[i] = new Vertex();
        next[i].makePrevious(this);
        childrenSize++;
        return next[i];
    }

    /**
     * Returns how many ways come from this Vertex.
     */
    private int size() {
        return childrenSize;
    }

    /**
     * Trying to delete the Vertex that the character in parameters leads to.
     * Deletes it only if there are no ways that come from the Vertex and it is not terminal.
     */
    public void deleteVertex(char character) {
        int i = getCode(character);
        subTrieSize--;
        Vertex deleted = next[i];
        if (deleted.size() == 0 && !deleted.getTerminal()) {
            next[i] = null;
            childrenSize--;
        }
    }

    /**
     * Writes all fields of object to stream and writes it's children recursively.
     * @param out - stream
     * @throws IOException
     */
    public void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(terminalSize);
        out.writeInt(childrenSize);
        for (int i = 0; i < ALPHABET; i++) {
            if (next[i] != null) {
                out.writeChar(i + ' ');
                out.writeObject(next[i]);
            }
        }
    }

    /**
     * Reads object from the stream and then reads it's children recursively.
     * @param in - stream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        terminalSize = in.readInt();
        childrenSize = in.readInt();
        for (int i = 0; i < childrenSize; i++) {
            char character = in.readChar();
            makeNext(character);
            next[character - ' '] = (Vertex) in.readObject();
        }
    }

}