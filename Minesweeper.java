import java.util.Random;
import java.util.*;
public class Minesweeper {
    public char[][] board;

    public Minesweeper(int numRows, int numColumns, int numBombs) {
        // create board
        this.board = new char[numRows][numColumns];

        //place bombs
        placeBombs(numBombs);

        //input counts
        countBombs();
    }

    private void countBombs() {
        int numRows = board.length;
        int numColumns = board[0].length;

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                if (board[r][c] == 'B') {

                    //increment top row of bomb
                    if (r != 0) {
                        increment(r-1, c);
                        if (c != 0) {
                            increment(r-1, c-1);
                        }
                        if (c != numColumns) {
                            increment(r-1, c+1);
                        }
                    }

                    //increment bottom row of bomb
                   if (r != numRows) {
                        increment(r+1, c);
                        if (c != 0) {
                            increment(r+1, c-1);
                        }
                        if (c != numColumns) {
                            increment(r+1, c+1);
                        }
                   }

                    //increment left of bomb
                   if (c != 0) {
                        increment(r, c-1);
                   }

                    //increment right of bomb
                    if (c != numColumns) {
                        increment(r, c+1);
                    }
                }
            }
        }
    }

    public void increment(int r, int c){
        if (board[r][c] == '-') {
            board[r][c] = '1';
        } else if (board[r][c] != 'B') {
            board[r][c] += 1;
        }
    }

    private void placeBombs(int numBombs) {
        int numRows = board.length;
        int numColumns = board[0].length;

        //set dfault value to '-'
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                board[i][j] = '-';
            }
        }

        //place bombs
        for (int i = 0; i < numBombs; i++) {
            int randRowIdx = (int)(Math.random() * (numRows-1));
            int randColIdx = (int)(Math.random() * (numColumns-1));

            while (board[randRowIdx][randColIdx] == 'B') {
                randRowIdx = (int)(Math.random() * (numRows-1));
                randColIdx = (int)(Math.random() * (numColumns-1));
            }

            board[randRowIdx][randColIdx] = 'B';   
        }
    }

    public String toString() {
        int numRows = board.length;
        int numColumns = board[0].length;

        String result = "";

        for (int i = 0 ; i < numRows ; i++) {
            for (int j = 0; j < numColumns; j++) {
               result += board[i][j] + "|";
            }
            result += "\n";
        }

        return result;
    }
}
