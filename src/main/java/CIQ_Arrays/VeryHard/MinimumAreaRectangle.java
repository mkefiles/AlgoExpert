package CIQ_Arrays.VeryHard;

import java.util.*;

/**
 * You're given an array of points plotted on a 2D graph (the xy-plane). Write a
 * function that returns the minimum area of any rectangle that can be formed
 * using any 4 of these points such that the rectangle's sides are parallel to
 * the x and y axes (i.e., only rectangles with horizontal and vertical sides
 * should be considered--no rectangles with diagonal sides). If no rectangle can
 * be formed, your function should return `0`.
 *
 * The input array will contain points represented by arrays of two integers
 * `[x, y]`. The input array will never contain duplicate points.
 *
 * SAMPLE INPUT:
 * points = [
 *     [1, 5],
 *     [5, 1],
 *     [4, 2],
 *     [2, 4],
 *     [2, 2],
 *     [1, 2],
 *     [4, 5],
 *     [2, 5],
 *     [-1, -2],
 * ]
 *
 * SAMPLE OUTPUT:
 * 3    // The rectangle with corners [1, 5], [2, 5], [1, 2], and [2, 2]
 *      // has the minimum area: 3.
 *
 * ADDITIONAL NOTES:
 * The general concept is that if you find two points that are diagonal (i.e.,
 * the X-Axis values and the Y-Axis values cannot be the same), you can derive
 * the opposing points that would create a rectangle. By checking for the two
 * opposing points, you can determine if all necessary points exist.
 *
 * Point A: [1, 2]
 * Point B: [2, 5]
 * Point C: [pAX, pBY]  =>  [1, 5]
 * Point D: [pBX, pAY]  =>  [2, 2]
 */

public class MinimumAreaRectangle {
    public int minimumAreaRectangle(int[][] points) {
        // DESC: Initialize necessary variables
        int numberOfPoints = points.length;
        int[][] opposingPoints = new int[numberOfPoints][2];
        int minimumArea = Integer.MAX_VALUE;
        Set<List<Integer>> uniquePoints = new HashSet<>();

        // DESC: EDGE-CASE: Address input that has
        // ... less-than four points upfront as a
        // ... rectangle is not possible with fewer
        // ... than four points
        if (numberOfPoints < 4) {
            return 0;
        }

        // DESC: Convert `points` to a set
        // NOTE: This enables constant-time search
        // ... abilities, which does not affect the
        // ... overall T.C., so it is not a net-loss
        for (int[] point : points) {
            uniquePoints.add(Arrays.asList(point[0], point[1]));
        }

        // NOTE: Nested loop where the first
        // ... loop gets point one and the
        // ... internal loop gets point two
        for (List pointOne : uniquePoints) {
            for (List pointTwo : uniquePoints) {
                // DESC: Move nested-loop to next
                // ... iteration IF working on the
                // ... same values
                if (pointTwo.equals(pointOne)) {
                    continue;
                } else {
                    // DESC: Ensure that `pointTwo` is
                    // ... diagonal to `pointOne`
                    // NOTE: To be diagonal, neither the X-Axis
                    // ... value NOR the Y-Axis value can equal
                    // ... each other as that would suggest that
                    // ... the line is either straight up and down
                    // ... or straight left to right
                    boolean isXEqual = pointOne.get(0) == pointTwo.get(0);
                    boolean isYEqual = pointOne.get(1) == pointTwo.get(1);
                    if (isXEqual == false && isYEqual == false) {
                        int p1x = (Integer) pointOne.get(0);
                        int p2y = (Integer) pointTwo.get(1);
                        int p2x = (Integer) pointTwo.get(0);
                        int p1y = (Integer) pointOne.get(1);

                        // DESC: Check if the 'set' contains the
                        // ... proposed opposing points
                        if (uniquePoints.contains(Arrays.asList(p1x, p2y)) && uniquePoints.contains(Arrays.asList(p2x, p1y))) {
                            int rectangleHeight = Math.abs(p1y - p2y);
                            int rectangleWidth = Math.abs(p1x - p2x);
                            int rectangleArea = rectangleWidth * rectangleHeight;
                            minimumArea = Math.min(rectangleArea, minimumArea);
                        }
                    }
                }
            }
        }

        // DESC: Due to use `Integer.MAX_VALUE` for
        // ... the initial value, the logic needs to
        // ... check if a result was ever found and
        // ... return the appropriate value
        // NOTE: If the `minimumArea` variable is still
        // ... equal to its original value, then that
        // ... indicates that no valid rectangle was located
        if (minimumArea < Integer.MAX_VALUE) {
            return minimumArea;
        } else {
            return 0;
        }
    }
}