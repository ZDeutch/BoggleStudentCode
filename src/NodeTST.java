public class NodeTST {

    // Tracks whether this is the end of a word
    boolean isWord;

    // Tracks the left, middle, and right node of a given root
    NodeTST left;
    NodeTST right;
    NodeTST middle;

    // Tracks the current letter of the root
    char current;

    // Constructor
    public NodeTST(char current) {
        this.current = current;
        this.isWord = false;
        this.left = null;
        this.right = null;
        this.middle = null;
    }

    // Getters and Setters

    public void setWord(boolean word) {
        isWord = word;
    }

    public char getCurrent() {
        return current;
    }

    public void setCurrent(char current) {
        this.current = current;
    }

    public NodeTST getLeft() {
        return left;
    }

    public void setLeft(char c) {
        left = new NodeTST(c);
    }

    public NodeTST getRight() {
        return right;
    }

    public void setRight(char c) {
        right = new NodeTST(c);
    }

    public NodeTST getMiddle() {
        return middle;
    }

    public void setMiddle(char c) {
        middle = new NodeTST(c);
    }
}
