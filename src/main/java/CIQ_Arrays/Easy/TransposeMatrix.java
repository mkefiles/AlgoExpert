package CIQ_Arrays.Easy;

import java.util.*;

/**
 * You're given a 2D array of integers `matrix`. Write a function
 * that returns the transpose of the matrix.
 *
 * The transpose of a matrix is a flipped version of the original matrix across
 * its main diagonal (which runs from top-left to bottom-right); it switches
 * the row and column indices of the original matrix.
 *
 * You can assume the input matrix always has at least 1 value; however its
 * width and height are not necessarily the same.
 *
 * SAMPLE INPUT:
 * matrix = [
 *  [1, 2],
 *  [3, 4],
 *  [5, 6]
 *  ]
 *
 * SAMPLE OUTPUT:
 * [[1, 3, 5],
 * [2, 4, 6]]
 */

public class TransposeMatrix {
    public int[][] transposeMatrix(int[][] matrix) {
        // DESC: Initialize necessary variables
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;

        // DESC: Create new transposed matrix
        // NOTE: Rows and columns are intentionally flipped here (to transpose)
        // ... as the typical format, when creating a matrix, is to use the
        // ... following: `... new int[rows][cols];`
        int[][] transposedMatrix = new int[numberOfColumns][numberOfRows];

        // DESC: Loop through initial matrix
        // NOTE: Loop through rows
        for (int outerIncrementer = 0; outerIncrementer < numberOfRows; outerIncrementer++) {

            // NOTE: Loop through columns
            for (int innerIncrementer = 0; innerIncrementer < numberOfColumns; innerIncrementer++) {
                // DESC: Initialize necessary variables
                int currentRowCursor = outerIncrementer;
                int currentColumnCursor = innerIncrementer;
                int transposedRowCursor = currentColumnCursor;
                int transposedColumnCursor = currentRowCursor;
                int currentValue = matrix[currentRowCursor][currentColumnCursor];

                // DESC: Update transposed matrix with data
                transposedMatrix[transposedRowCursor][transposedColumnCursor] = currentValue;
            }
        }

        // DESC: Return updated table
        return transposedMatrix;
    }
}
