/**
 * Created by KatyaKos on 26.09.2016.
 * @author KatyaKos
 */
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.add("Hail Fassbender"));
        System.out.println(trie.add("Hail Fassbender"));
        System.out.println(trie.add("Hail Fassbendery"));
        System.out.println(trie.remove("Hail Fassbender"));
        System.out.println(trie.contains("Hail Fassbender"));
        System.out.println(trie.add("Hail Daleks"));
        System.out.println(trie.size());
        System.out.println(trie.howManyStartsWithPrefix("Hail F"));

    }
}
