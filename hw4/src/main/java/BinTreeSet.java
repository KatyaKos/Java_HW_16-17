/**
 * Created by KatyaKos on 09.10.2016.
 * @author KatyaKos
 * Generic-Set based on Binary (not Balanced) Tree.
 */
public class BinTreeSet<T extends Comparable<T>> {
    private BinTreeNode root;
    private int size;

    /**
     * Node of Binary Tree.
     */
    private class BinTreeNode {
        private T value;
        private BinTreeNode left;
        private BinTreeNode right;

        public BinTreeNode(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BinTreeSet() {
        size = 0;
        root = null;
    }

    /**
     * Returns size of the Set.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if there is such element in the Set.
     * @param element what we want to find
     * @return true if there is the element and false otherwise
     */
    public boolean contains(T element) {
        BinTreeNode temp = root;
        while (temp != null) {
            int compare = element.compareTo(temp.value);
            if (compare == 0) {
                return true;
            } else if (compare > 0) {
                if (temp.right != null ) {
                    temp = temp.right;
                } else {
                    return false;
                }
            } else {
                if (temp.left != null ) {
                    temp = temp.left;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Adds new element to the Set if it does not exist already.
     * @param element what we want to add
     * @return true if the element is already in the Set and false otherwise
     */
    public boolean add(T element) {
        BinTreeNode temp = root;
        if (root == null) {
            root = new BinTreeNode(element);
            size++;
            return false;
        }

        while (true) {
            int compare = element.compareTo(temp.value);
            if (compare == 0) {
                return true;
            } else if (compare > 0) {
                if (temp.right != null ) {
                    temp = temp.right;
                } else {
                    temp.right = new BinTreeNode(element);
                    size++;
                    return false;
                }
            } else {
                if (temp.left != null ) {
                    temp = temp.left;
                } else {
                    temp.left = new BinTreeNode(element);
                    size++;
                    return false;
                }
            }
        }
    }
}
