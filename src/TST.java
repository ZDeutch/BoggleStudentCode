public class TST {
    // Root tracks the current node in the word
    NodeTST root;

    public TST() {
        // Initialize the root to the middle of the alphabet
        root = null;
    }

    // Method to insert a string into the TST
    public void insert(String s) {
        // Handle edge cases
        if (s == null || s.length() == 0) {
            return;
        }

        // Initialize root if this is first insertion
        if (root == null) {
            root = new NodeTST(s.charAt(0));
        }

        NodeTST temp = root;


        // Iterate each character in the word
        for (int i = 0; i < s.length(); i++) {
            // Go left if character is smaller, right if larger
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

            // Once the node is created, add a middle path to the next character
            // As long as its not at the end
            if (i < s.length() - 1) {
                if (temp.getMiddle() == null) {
                    temp.setMiddle(s.charAt(i + 1));
                }
                temp = temp.getMiddle();
            }
        }

        // Mark the last node as end of word
        temp.setWord(true);
    }

    // Checks if any word in the TST starts with the given prefix
    public boolean hasPrefix(String s) {
        // Makes sure string isn't null
        if (s == null || s.length() == 0) {
            return false;
        }
        // Returns whether it appears in the TST
        return get(root, s, 0) != null;
    }

    // method to find whether a given string is in the TST
    public boolean find(String s) {
        // Check edge cases
        if (s == null || s.length() == 0) {
            return false;
        }

        // Identify if the string appears and return whether its a word
        NodeTST node = get(root, s, 0);
        return node != null && node.isWord;
    }

    // Helper method to navigate through the TST to find a node
    public NodeTST get(NodeTST node, String s, int d) {
        // Base case 1 is if no path exists
        if (node == null) {
            return null;
        }

        // Base case 2 is if you searched past the end of the string
        if (d >= s.length()) {
            return null;
        }

        char c = s.charAt(d);

        // Navigate using BST logic for current character
        // If character is smaller go left, otherwise go right
        if (c < node.getCurrent()) {
            return get(node.getLeft(), s, d);
        } else if (c > node.getCurrent()) {
            return get(node.getRight(), s, d);
            // If the matching character is found then go middle
        } else if (d < s.length() - 1) {
            return get(node.getMiddle(), s, d + 1);
        } else {
            // Return the last character
            return node;
        }
    }


}
