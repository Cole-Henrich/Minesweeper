package org.coleh.minesweeper;

//.. "//.." denotes work NOT done by Mr. Sea,
//.. so that his pure work can be kept pure for reference.

/*..*/
  //Multiline format
/*..*/


import java.util.Arrays;
/**
 * In this lab, you will (mostly) complete your Minesweeper game.
 * When the game is over, you need to display a Win or Lose message and no further actions should be allowed.
 *
 * Specifically:
 * 1. Each time a button is clicked, the board should update correctly.
 *      1. The number on the button (when clicked) represents the number of mines adjacent to the space.
 *      2. If the number of mines adjacent to a visible space is zero, then all spaces adjacent to it should be 'clicked' for the player.
 * 2. You must display a win/lose message at the end of the game and no further actions (except Settings) is allowed:
 *      1. The player loses if a mine is selected.
 *      p2. The player wins if all non-mine spaces have been selected.
 */
public class BoardLogic {
    //You should only need to add one class -- BoardLogic:
    
    //Attributes Mr. Sea used:

    private int[][] board;
    boolean gameOver;
    int numSafe;

    //Methods Mr. Sea created:

    public BoardLogic(int rows, int cols, double deathTrapRatio) {

        /*..*/

        board = new int[rows][cols];
        numSafe = (int) (numSpaces()*(1-deathTrapRatio));
        board = reset();

        /*..*/
        }
        //Accessors:

        /** @return the number of rows in the game*/
        /*..*/ public /*..*/ int numRows (){/*..*/ return board.length; /*..*/}

        /** @return the number of columns in the game*/
        /*..*/public /*..*/  int numCols () {/*..*/ return board[0].length;/*..*/}

        /** @return the total number of spaces in the game*/
        /*..*/ public /*..*/ int numSpaces () {/*..*/return (board.length)*(board[1].length);/*..*/}

        /**
         * @return the number of NON-mine spaces left
         */
        /*..*/public /*..*/ int numOpen () {
            int count = 0;
            for (int[] row : board) {
                for (int space : row) {
                    if (space < 0 && space != -9) {
                        count++;
                    }
                }
            }
            return count;
        }

        /** @return true if the game is over
         */
        /*..*/ public boolean /*..*/ isGameOver () {/*..*/
            return gameOver;
            /*..*/}



        /**
         * @param row the row index
         * @param col the column index
         * @return -1 for a covered space, 9 for a mine, and 1-8 otherwise.
         */
            /*..*/ public /*..*/  int getSpace ( int row, int col){
                int space = board[row][col];
                if (space < 0 && space !=-10 && space!=-9){space = -1;}
                return space;
            }

        //Mutators:
        /** resets the board to all zeros, adds mines, and populates the board*/
            /*..*/ private int[][] /*..*/ reset() {
            /*..*/
            for (int[] ints : board) Arrays.fill(ints, -10);
            board = addMines();
            board = populateBoard();
            return board;
            /*..*/
        }
        /**
         * randomly places mines in the board according to the difficulty of the game
         */
        private /*void*/ /*..*/int[][]/*..*/ addMines () {
            /*..*/
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int random = (int) (Math.random()*numSpaces());
                    if (random > numSafe){
                        board[i][j] = -9;
                    }
                }
            }
            return board;
            /*..*/
        }
    /**
     * calculates the number of mines around each space.
     * Should be called after addMines.
     * @return board
     */
    private /*void*/ /*..*/int[][] /*..*/ populateBoard () {
        /*..*/
            /*
            Essentially, in a board:
                  j
                0 1 2
             i  3 x 5
                6 7 8
           The if statements are arranged in that order, 0-8.
             */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int count = 0;
                if (board[i][j] != -9) {
                    // Above
                    if (i > 0) {
                        // space 0
                        if (j > 0) {
                            if (board[i - 1][j - 1] == -9) {
                                count--;
                            }
                        }
                        //space 1
                        if (board[i - 1][j] == -9) {
                            count--;
                        }
                        //space 2
                        if (j < board[i].length - 1) {
                            if (board[i - 1][j + 1] == -9) {
                                count--;
                            }
                        }
                    }
                    // Same Row
                    //space 3
                    if (j > 0) {
                        if (board[i][j - 1] == -9) {
                            count--;
                        }
                    }
                    //space 5
                    if (j < board[i].length - 1) {
                        if (board[i][j + 1] == -9) {
                            count--;
                        }
                    }
                    // Below
                    if (i < board.length - 1) {
                        //space 6
                        if (j > 0) {
                            if (board[i + 1][j - 1] == -9) {
                                count--;
                            }
                        }
                        //space 7
                        if (board[i + 1][j] == -9) {
                            count--;
                        }
                        //space 8
                        if (j < board[i].length - 1) {
                            if (board[i + 1][j + 1] == -9) {
                                count--;
                            }
                        }
                    }
                    board[i][j] = count;
                }
            }
        }
        return board;
    }
        /**
         * Uncovers the space at (row, col).
         * It must also uncover all adjacent spaces if the current space has zero mines around it.
         * @param row //.. the row index
         * @param col //.. the column index
         * return true if you've picked a mine (I kinda just tossed that functionality in there;
         * the rest is more focused on whether you picked a 0.
         */
        /*..*/public boolean /*..*/ pickSpace ( int row, int col){
            /*..*/
            if (row < 0 || col < 0 ||row >=numRows() ||col >=numCols() || gameOver){return true;}
            boolean b = false;
            if (board[row][col] == -10) {
                board[row][col] = 0;
                b = true;
            }
            board[row][col] = Math.abs(board[row][col]);

            if (b) {
                for (int k = row - 1; k <= row + 1; k++) {
                    for (int l = col - 1; l <= col + 1; l++) {
                        pickSpace(k, l);
                    }
                }
            }
            return board[row][col]==9;
            /*..*/
        }
        int[][] printBoard(){
            board = reset();
            for (int i = 0; i < this.numRows(); i++) {
                for (int j = 0; j < this.numCols(); j++) {
                    String val = String.valueOf(board[i][j]);
                    if (val.equals("0")){
                        val = "-0";//for formatting, no mathematical importance.
                    }
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            return board;
        }
}

