package Hard;

import java.util.*;

/**
 * Write a function that takes in a list of Cartesian coordinates (i.e., (x, y)
 * coordinates) and returns the number of squares that can be formed by these
 * coordinates.
 *
 * A square must have its four corners amongst the coordinates in order to be
 * counted. A single coordinate can be used as a corner for multiple different
 * squares.
 *
 * You can also assume that no coordinate will be farther than 100 units from the
 * origin.
 *
 * SAMPLE INPUT:
 * points= [
 *  [1, 1],
 *  [0, 0],
 *  [-4, 2],
 *  [-2, -1],
 *  [0, 1],
 *  [1, 0],
 *  [-1, 4]
 * ]
 *
 * SAMPLE OUTPUT:
 * 2    // [1, 1], [0, 0], [0, 1], and [1, 0] makes a square, as does
 *      // ... [1, 1], [-4, 2], [-2, -1], and [-1, 4]
 */

public class CountSquares {
    public int countSquares(int[][] points) {
        // DESC: Initialize necessary variables
        int numberOfPoints = points.length;
        HashSet<List> uniqueCoordinates = new HashSet<List>();
        int numberOfSquares = 0;

        // DESC: EDGE-CASE: Disqualify any input where
        // ... the number of provided coordinates is
        // ... less-than four
        if (numberOfPoints < 4) {
            return 0;
        }

        // DESC: Use Set to strip out duplicate points
        // ... in the `points` array
        // NOTE: Off of a quick look through the test
        // ... cases, no duplicates were noticed, however
        // ... the video mentioned removing duplicates so
        // ... this is more of a precaution
        for (int cI = 0; cI < numberOfPoints; cI++) {
            uniqueCoordinates.add(Arrays.asList(points[cI][0], points[cI][1]));
        }

        // DESC: Using a nested-loop, iterate through the
        // ... List and check all possible pair combinations
        // NOTE: Where `c1` is coordinateOne and `c2` is
        // ... coordinateTwo
        for (List c1 : uniqueCoordinates) {
            for (List c2 : uniqueCoordinates) {
                if (c2.equals(c1)) {
                    // DESC: Skip coordinate if it is the same
                    continue;
                } else {
                    // DESC: Check if the current pair is a
                    // ... NON-DIAGONAL (if so, then skip
                    // ... otherwise proceed with the logic)
                    // NOTE: "0" is X and "1" is Y
                    // NOTE: Testing ONLY the DIAGONAL values
                    // ... ensures that duplicates are reduced
                    if (c2.get(0) == c1.get(0) || c2.get(1) == c1.get(1)) {
                        continue;
                    } else {
                        // DESC: Set X and Y coordinates to their
                        // ... own variables to make re-use easier
                        Double c1X = ((Integer) c1.get(0)).doubleValue();
                        Double c2X = ((Integer) c2.get(0)).doubleValue();
                        Double c1Y = ((Integer) c1.get(1)).doubleValue();
                        Double c2Y = ((Integer) c2.get(1)).doubleValue();

                        // DESC: Determine the mid-point of the
                        // ... proposed square
                        Double midpointX = (c1X + c2X) / 2;
                        Double midpointY = (c1Y + c2Y) / 2;

                        // DESC: Determine the distance between
                        // ... the point and mid-point (of one value)
                        Double xDistance = c1X - midpointX;
                        Double yDistance = c1Y - midpointY;

                        // DESC: Determine proposed THIRD point
                        Integer proposedX3 = ((Double) (midpointX + yDistance)).intValue();
                        Integer proposedY3 = ((Double) (midpointY - xDistance)).intValue();
                        List<Integer> proposedC3 = new ArrayList<Integer>(Arrays.asList(proposedX3, proposedY3));

                        // DESC: Determine proposed FOURTH point
                        Integer proposedX4 = ((Double) (midpointX - yDistance)).intValue();
                        Integer proposedY4 = ((Double) (midpointY + xDistance)).intValue();
                        List<Integer> proposedC4 = new ArrayList<Integer>(Arrays.asList(proposedX4, proposedY4));

                        // DESC: Check if BOTH coordinates exist
                        // ... and, if so, increment the counter
                        if (uniqueCoordinates.contains(proposedC3) && uniqueCoordinates.contains(proposedC4)) {
                            numberOfSquares = numberOfSquares + 1;
                        }
                    }
                }
            }
        }

        // DESC: Get rid of duplicate square coordinates
        // NOTE: The easiest way to get rid of the
        // ... inevitable duplicates is to divide by
        // ... "4" because not only is the loop checking
        // ... c1 against c2 to get p3 and p4, it also
        // ... checks c2 against c1, c3 against c4 and
        // ... c4 against c3
        numberOfSquares = numberOfSquares / 4;

        return numberOfSquares;
    }
}