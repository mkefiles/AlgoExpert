package CIQ_Arrays.Hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You're given the position of two knight pieces on an infinite chess board.
 * Write a function that returns the minimum number of turns required before
 * one of the knights is able to capture the other knight, assuming the knights
 * are working together to achieve this goal.
 *
 * The position of each knight is given as a list of 2 values, the x and y
 * coordinates. A knight can make 1 of 8 possible moves on any given turn. Each
 * of these moves involves moving in an "L" shape. This means they can either
 * move 2 squares horizontally and 1 square vertically, or they can move 1
 * square horizontally and 2 squares vertically. For example, if a knight is
 * currently at position [0, 0], then it can move to any of these 8 locations
 * on its next move:
 *
 * [
 *  [-2, 1], [-1, 2], [1, 2], [2, 1], [2, -1], [1, -2], [-1, -2], [-2, -1]
 * ]
 *
 * A knight is able to capture the other knight when it is able to move onto
 * the square currently occupied by the other knight.
 *
 * Each turn allows each knight to move up to one time. For example, if both
 * knights moved towards each other once, and then knightA captures knightB on
 * its next move, two turns would have been used (even though knightB never
 * made its second move).
 *
 *  SAMPLE INPUT:
 *  knightA = [0, 0]
 *  knightB = [4, 2]
 *
 *  SAMPLE OUTPUT:
 *  1 // KnightA moves to [2, 1], KnightB captures knightA on [2, 1]
 */

public class KnightConnection {
    public int knightConnection(int[] knightA, int[] knightB) {
        // DESC: Initialize necessary variables
        HashSet<String> visitedCoordinates = new HashSet<String>();
        Queue<Integer[]> availableCoordinates = new LinkedList<Integer[]>();
        int xCoordinate = 0;
        int yCoordinate = 0;
        double d = 0.0;
        int distance = 0;
        int numberOfMoves = 0;
        Integer[][] possibleMoves = {
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1}
        };

        // DESC: EDGE CASE: Check for identical positions between knights
        // NOTE: This ensures efficiency of operation
        if (knightA[0] == knightB[0] && knightA[1] == knightB[1]) {
            return numberOfMoves;
        }

        // DESC: STEP 01: Add position of Knight A to the Queue (incl.
        // ... "d" for the distance, which should be "0")
        xCoordinate = knightA[0];
        yCoordinate = knightA[1];
        distance = 0;
        availableCoordinates.add(new Integer[]{xCoordinate, yCoordinate, distance});

        // DESC: STEP 02: Add position of Knight A to the visited Set
        visitedCoordinates.add(Arrays.toString(new Integer[]{xCoordinate, yCoordinate}));

        // DESC: Loop through possible moves
        while (true) {
            // DESC: STEP 03: Remove the 'head' from the Queue and add
            // ... to a current-position variable
            Integer[] currentLocationAndDistance = availableCoordinates.remove();

            // DESC: STEP 04: Check if current-position of Knight A is
            // ... the same as Knight B
            // NOTE: If it is, then return "d" / 2 (rounded up)
            // NOTE: The Integer values, of the array, must be converted
            // ... to Double PRIOR TO the math to ensure decimal values
            // ... are not prematurely truncated
            if (currentLocationAndDistance[0] == knightB[0] && currentLocationAndDistance[1] == knightB[1]) {
                d = (double) currentLocationAndDistance[2];
                numberOfMoves = (int) Math.ceil(d / 2.0);
                break;
            }

            // DESC: STEP 05: Determine all possible moves based on the
            // ... current-position
            for (int cI = 0; cI < 8; cI++) {
                // possibleMoves
                // DESC: STEP 06: For each possible move...
                // DESC: Check if it exists in the visited Set
                if (visitedCoordinates.contains(Arrays.toString(new Integer[]{
                        currentLocationAndDistance[0] + possibleMoves[cI][0],
                        currentLocationAndDistance[1] + possibleMoves[cI][1]})) == true) {
                    // NOTE: The proposed move was already tried
                    // ... so it can be skipped to prevent duplicate
                    // ... "movements"
                    continue;
                } else {
                    // DESC: Add the proposed move to `visitedCoordinates`
                    // NOTE: The visited Set is a Set of Strings (to prevent the need
                    // ... to iterate through it each time) and it does NOT need
                    // ... the "d" value to be added
                    visitedCoordinates.add(Arrays.toString(new Integer[]{
                            currentLocationAndDistance[0] + possibleMoves[cI][0],
                            currentLocationAndDistance[1] + possibleMoves[cI][1]
                    }));

                    // DESC: Add the proposed move, with the distance, to
                    // ... the Queue as a potential move to cross-reference
                    availableCoordinates.add(new Integer[]{
                            currentLocationAndDistance[0] + possibleMoves[cI][0],
                            currentLocationAndDistance[1] + possibleMoves[cI][1],
                            currentLocationAndDistance[2] + 1
                    });
                }
            }
        }
        return numberOfMoves;
    }
}
