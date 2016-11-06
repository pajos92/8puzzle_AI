package puzzle8;

import java.util.Random;

/**
 *
 * @author Panos Mats
 */
public class Board {

    /**
     * 2-d array that corresponds to the actual board
     */
    private int[][] matrix = new int[3][3];

    /**
     * Constructor with a specific matrix given
     *
     * @param matrixGiven 2-d array used to create the board state
     */
    public Board(int[][] matrixGiven) {
        for (int i = 0; i < matrixGiven.length; i++) {
            for (int j = 0; j < matrixGiven[0].length; j++) {
                this.matrix[i][j] = matrixGiven[i][j];
            }
        }
    }

    /**
     * Constructor generates a random starting state, used when we create a new
     * root node to start a search from
     */
    public Board() {
        _generateRandomState();
    }

    /**
     *
     * @param b Board object to check with
     * @return true if this board and param board are equal
     */
    public boolean equals(Board b) {
        for (int i = 0; i < b.getMatrix().length; i++) {
            for (int j = 0; j < b.getMatrix()[0].length; j++) {
                if (this.getMatrix()[i][j] != b.getMatrix()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param t target given to check for
     * @return true if this board is target
     */
    public boolean isTarget(Target t) {
        for (int i = 0; i < this.getMatrix().length; i++) {
            for (int j = 0; j < this.getMatrix()[0].length; j++) {
                if (getMatrix()[i][j] != t.getbTarget()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @return false if moving up is NOT available
     */
    public boolean checkAvailableUp() {
        return this.getNullRowPos() != 0;
    }

    /**
     *
     * @return false if moving down is NOT available
     */
    public boolean checkAvailableDown() {
        return this.getNullRowPos() != 2;
    }

    /**
     *
     * @return false if moving left is NOT available
     */
    public boolean checkAvailableLeft() {
        return this.getNullColPos() != 0;
    }

    /**
     *
     * @return false if moving right is NOT available
     */
    public boolean checkAvailableRight() {
        return this.getNullColPos() != 2;
    }

    /**
     *
     * @return a new board object of an array moved up
     */
    public Board moveUp() {
        Board bMoved = new Board(this.getMatrix());
        for (int i = 0; i < bMoved.getMatrix().length; i++) {
            for (int j = 0; j < bMoved.getMatrix()[0].length; j++) {
                if (bMoved.getMatrix()[i][j] == 0) {
                    bMoved.getMatrix()[i][j] = bMoved.getMatrix()[i - 1][j];
                    bMoved.getMatrix()[i - 1][j] = 0;
                    return bMoved;
                }
            }
        }
        return bMoved;
    }

    /**
     *
     * @return a new board object of an array moved down
     */
    public Board moveDown() {
        Board bMoved = new Board(this.getMatrix());
        for (int i = 0; i < bMoved.getMatrix().length; i++) {
            for (int j = 0; j < bMoved.getMatrix()[0].length; j++) {
                if (bMoved.getMatrix()[i][j] == 0) {
                    bMoved.getMatrix()[i][j] = bMoved.getMatrix()[i + 1][j];
                    bMoved.getMatrix()[i + 1][j] = 0;
                    return bMoved;
                }
            }
        }
        return bMoved;
    }

    /**
     *
     * @return a new board object of an array moved left
     */
    public Board moveLeft() {
        Board bMoved = new Board(this.getMatrix());
        for (int i = 0; i < bMoved.getMatrix().length; i++) {
            for (int j = 0; j < bMoved.getMatrix()[0].length; j++) {
                if (bMoved.getMatrix()[i][j] == 0) {
                    bMoved.getMatrix()[i][j] = bMoved.getMatrix()[i][j - 1];
                    bMoved.getMatrix()[i][j - 1] = 0;
                    return bMoved;
                }
            }
        }
        return bMoved;
    }

    /**
     *
     * @return a new board object of an array moved right
     */
    public Board moveRight() {
        Board bMoved = new Board(this.getMatrix());
        for (int i = 0; i < bMoved.getMatrix().length; i++) {
            for (int j = 0; j < bMoved.getMatrix()[0].length; j++) {
                if (bMoved.getMatrix()[i][j] == 0) {
                    bMoved.getMatrix()[i][j] = bMoved.getMatrix()[i][j + 1];
                    bMoved.getMatrix()[i][j + 1] = 0;
                    return bMoved;
                }
            }
        }
        return bMoved;
    }

    /**
     *
     * @return the row where empty space is positioned
     */
    public int getNullRowPos() {
        int rowPos = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowPos = i;
                }

            }
        }
        return rowPos;
    }

    /**
     *
     * @return the column where empty space is positioned
     */
    public int getNullColPos() {
        int colPos = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    colPos = j;
                }

            }
        }
        return colPos;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        String array = "\t";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getMatrix()[i][j] == 0) {
                    array += "   ";
                } else {
                    array += "[" + this.getMatrix()[i][j] + "]";
                }
            }
            array += "\n\t";
        }
        return array;
    }

    public String toString2() {
        return getMatrix()[0][0] + "," + getMatrix()[0][1] + "," + getMatrix()[0][2] + "," + getMatrix()[1][0] + "," + getMatrix()[1][1] + "," + getMatrix()[1][2] + "," + getMatrix()[2][0] + "," + getMatrix()[2][1] + "," + getMatrix()[2][2];
    }

    private void _generateRandomState() {

        /* Use 1-d board for conveniency */
        int[] newBoard = new int[9];
        boolean flagExists = false;
        int i = 0;
        /* Fill the array with unique random numbers 0-8 */
        while (i < newBoard.length) {
            flagExists = false;
            Random r = new Random();
            int number = r.nextInt(9);
            /* Check if exists until now */
            for (int j = 0; j < i; j++) {
                if (number == newBoard[j]) {
                    flagExists = true;
                    break;
                }
            }
            /* If it doesn't already exist, insert it in our board */
            if (!flagExists) {
                newBoard[i] = number;
                i++;
                flagExists = false;
            }
        }
        /* Fill the real 2-d matrix with above values */
        for (int w = 0; w < matrix.length; w++) {
            for (int k = 0; k < matrix[0].length; k++) {
                matrix[w][k] = newBoard[(w + k) + (w * 2)];
            }
        }
    }
}
