import java.util.ArrayList;
import java.util.Arrays;

// Boggle by Zander Deutch

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<>();

        // Build a TST to store all the words in dictionary

        TST tst = new TST();
        for (String s : dictionary) {
            tst.insert(s);
        }

        // Use temp arrayList to manipulate the words that are found
        ArrayList<String> foundWords = new ArrayList<>();

        // Create a visited array for each starting position in the board to track available moves
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                DFS(board, i, j, "", visited, tst, foundWords);
            }
        }

        // Remove duplicates - a word might be formed from multiple starting positions
        goodWords.addAll(foundWords);


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Depth-First Search to find words on the boggle board
    // Uses backtracking to explore all possible paths
    public static void DFS(char[][] grid, int i, int j, String current, boolean[][] visited, TST tst, ArrayList<String> foundWords) {
        // Base Case 1 is if any of the coordinates are out of bounds for the board
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        // Base Case 2 is if the cell is already used in the word path
        if (visited[i][j]) {
            return;
        }

        // Add the current cells character to build the new word
        String newWord = current + grid[i][j];

        // Stops if no dictionary word starts with this prefix
        if (!tst.hasPrefix(newWord)) {
            return;
        }

        // Marks current cell as visited so it doesn't repeat the letter
        visited[i][j] = true;

        // If the current word is in dictionary then add to results
        if (tst.find(newWord)) {
            if (!foundWords.contains(newWord)) {
                foundWords.add(newWord);
            }
        }

        // Recursively explore all 4 adjacent directions
        DFS(grid, i - 1, j, newWord, visited, tst, foundWords);
        DFS(grid, i + 1, j, newWord, visited, tst, foundWords);
        DFS(grid, i, j - 1, newWord, visited, tst, foundWords);
        DFS(grid, i, j + 1, newWord, visited, tst, foundWords);

        // Unmark cell so other paths can use it
        visited[i][j] = false;
    }
}
