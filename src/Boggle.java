import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();
        TST tst = new TST();

        for (int i = 0; i < dictionary.length; i++) {
            tst.insert(dictionary[i]);
        }

        ArrayList<String> foundWords = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                DFS(board, i, j, "", visited, tst, foundWords);
            }
        }

        for (int i = 0; i < foundWords.size(); i++) {
            goodWords.add(foundWords.get(i));
        }


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public int numIslands(char[][] grid, boolean[][] visited, TST tst, ArrayList<String> foundWords) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j, "", visited, tst, foundWords);
                    count++;
                }
            }
        }
        return count;
    }

    public static void DFS(char[][] grid, int i, int j, String current, boolean[][] visited, TST tst, ArrayList<String> goodWords) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] == '0') {
            return;
        }

        String newWord = current + grid[i][j];

        visited[i][j] = true;

        if (tst.find(newWord)) {
            goodWords.add(newWord);
        }

        // Mark this square as visited

        DFS(grid, i - 1, j, newWord, visited, tst, goodWords);
        DFS(grid, i + 1, j, newWord, visited, tst, goodWords);
        DFS(grid, i, j - 1, newWord, visited, tst, goodWords);
        DFS(grid, i, j + 1, newWord, visited, tst, goodWords);

        visited[i][j] = false;
    }
}
