import org.w3c.dom.Node;

public class TST {
    // Root tracks the current node in the word
    NodeTST root;

    public TST() {
        // Initialize the root to the middle of the alphabet
        root = null;
    }

    // Method to insert a string into the TST
    public void insert(String s) {
        NodeTST temp = root;

        for (int i = 0; i < s.length(); i++) {
            while (s.charAt(i) != temp.getCurrent()) {
                if (s.charAt(i) < temp.getCurrent()) {
                    if (temp.getLeft() == null) {
                        temp.setLeft(s.charAt(i));
                    }
                    temp = temp.getLeft();
                } else {
                    if (temp.getRight() == null) {
                        temp.setRight(s.charAt(i));
                    }
                    temp = temp.getRight();
                }
            }
            if (i < s.length() - 1) {
                if (temp.getMiddle() == null) {
                    temp.setMiddle(s.charAt(i));
                }
                temp = temp.getMiddle();
            }
        }
        temp.setWord(true);
    }

    public boolean hasPrefix(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return get(root, s, 0) != null;
    }

    // method to find whether a given string is in the TST
    public boolean find(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        NodeTST node = get(root, s, 0);
        return node != null && node.isWord;
    }

    public NodeTST get(NodeTST node, String s, int d) {
        if (node == null) {
            return null;
        }

        if (d >= s.length()) {
            return null;
        }

        char c = s.charAt(d);

        if (c < node.getCurrent()) {
            return get(node.getLeft(), s, 0);
        } else if (c > node.getCurrent()) {
            return get(node.getRight(), s, 0);
        } else if (d < s.length() - 1) {
            return get(node.getMiddle(), s, d + 1);
        } else {
            return node;
        }
    }


}
