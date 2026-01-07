public class TST {
    // Root tracks the current node in the word
    NodeTST root;

    public TST() {
        // Initialize the root to the middle of the alphabet
        root = new NodeTST('m');
    }

    // Method to insert a string into the TST
    public void insert(String s) {
        // Set the root to the middle of the alphabet
        NodeTST temp = root;

        // For each letter in the word
        for (int i = 0; i < s.length(); i++) {
            // While the given letter node is not the same as the node being looked at
            while (s.charAt(i) != temp.getCurrent()) {

                // If it is less then insert it to the left of the node
                // Then set temp to be the left node
                if (s.charAt(i) < temp.getCurrent()) {
                    if (temp.getLeft() == null) {
                        temp.setLeft(s.charAt(i));
                    }
                    temp = temp.getLeft();
                    // Otherwise add it to the right side if it is undefined
                    // Then set temp to be the right node
                } else {
                    if (temp.getRight() == null) {
                        temp.setRight(s.charAt(i));
                    }
                    temp = temp.getRight();
                }
            }

            // Lastly, if the middle node is null
            if (temp.getMiddle() == null) {
                // Then create a direct dropdown from the node to be the current letter
                temp.setMiddle(s.charAt(i));
            }
        }
        // Set the last letter to true to mark the end of the word
        temp.setWord(true);
    }

    // method to find whether a given string is in the TST
    public boolean find(String s) {
        // Set the root to the temp
        NodeTST temp = root;

        // For each letter in the word
        for (int i = 0; i < s.length(); i++) {
            // If the letter is not the same as the temp, check the left and right node
            // If those nodes are empty, then the string must not be in the node and can return false
            while (s.charAt(i) != temp.getCurrent()) {
                if (s.charAt(i) > temp.getCurrent()) {
                    if (temp.getRight() == null) {
                        return false;
                    }
                    temp = temp.getRight();
                } else if (s.charAt(i) < temp.getCurrent()) {
                    if (temp.getLeft() == null) {
                        return false;
                    }
                    temp = temp.getLeft();
                }
            }

            // Also, if the middle is not defined, then you return false because the letter doesn't appear in the TST
            if (temp.getMiddle() == null) {
                return false;
            }
            temp = temp.getMiddle();
        }
        // Otherwise return true
        return true;
    }


}
