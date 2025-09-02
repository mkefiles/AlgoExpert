package CIQ_Arrays.VeryHard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You're given an array of points plotted on a 2D graph (the xy-plane). Write a
 * function that returns the maximum number of points that a single line (or
 * potentially multiple lines) on the graph passes through.
 *
 * The input array will contain points represented by an array of two integers
 * `[x, y]`. The input array will never contain duplicate points and will always
 * contain at least one point.
 *
 * SAMPLE INPUT:
 * points = [
 *     [1, 1],
 *     [2, 2],
 *     [3, 3],
 *     [0, 4],
 *     [-2, 6],
 *     [4, 0],
 *     [2, 1],
 * ]
 *
 * SAMPLE OUTPUT:
 * 4 // A line passes through points: [-2, 6], [0, 4], [2, 2], [4, 0].
 *
 * ADDITIONAL NOTES:
 * - This WILL run slowly IF a large coordinator number (either x and/or y) is
 * ... provided to it because the GCF calculation must iterate over all values
 * ... up to that point (I did not need to try and determine a better work-around as
 * ... I was able to patch the test-case of an input of "0" or "1" with a quick
 * ... kick-out clause)
 * - A key-value data-structure is initialized for every outer-loop iteration
 * ... this D.S. stores the fraction (as a String) and the counter for every
 * ... time that fraction is located
 * - The general concept is that if you can determine the slope of every line
 * ... p1 and p2 (where p2 is the one changing) and a different p2 results in
 * ... the same 'slope' (i.e., 'm' OR 'rise over run'), then you can conclude
 * ... that multiple lines with the same origin and same slope are one in the
 * ... same overall line that encapsulates all points with that 'slope'
 * - There is no need to determine the Y-Intercept (i.e., y = mx + b) because
 * ... you are working with the same p1 across all p2 options then the number
 * ... of same slopes is incremented
 * - This uses a String as the 'key' in the D.S. because the fraction-representation
 * ... does not need to worry about the limitations that a computer has on
 * ... decimal precision (the decimal precision could throw false-positives if
 * ... off by one decimal value)
 */

public class LineThroughPoints {
    /**
     * Given a numerator and a denominator, this will determine
     * the factors of each then it will locate the greatest
     * factor that is common amongst both values.
     *
     * NOTE: Each value will need to be divided by the GCF to
     * get its lowest fraction
     * NOTE: A GCF of "1" means that there is no way to reduce
     * the fraction
     * @param numerator
     * @param denominator
     * @return the greatest-common factor
     */
    public static int greatestCommonFactor (int numerator, int denominator) {
        // DESC: Get the absolute value of
        // ... the numbers (i.e., remove any
        // ... negative sign)
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // DESC: If either numerator OR denominator
        // ... is a "1" or a "0", then return "1"
        // NOTE: There can be no greater GCF in those
        // ... scenarios AND, not to mention, a test-case
        // ... where the value provided is in the millions
        // ... is slowing this down drastically
        if (numerator == 1 || denominator == 1) {
            return 1;
        }

        if (numerator == 0 || denominator == 0) {
            return 1;
        }

        // DESC: Initialize necessary variables
        int gcf = 1;
        ArrayList<Integer> numeratorFactor = new ArrayList<Integer>();
        ArrayList<Integer> denominatorFactor = new ArrayList<Integer>();

        // DESC: Determine factor
        // NOTE: A 'factor' is any value that 'x'
        // ... is evenly divisible by (if a * b = m,
        // ... then a AND b are factors of m)
        for (int i = 1; i <= numerator; i++) {
            if (numerator % i == 0) {
                numeratorFactor.add(i);
            }
        }

        // DESC: Determine factor
        // NOTE: A 'factor' is any value that 'x'
        // ... is evenly divisible by (if a * b = m,
        // ... then a AND b are factors of m)
        for (int i = 1; i <= denominator; i++) {
            if (denominator % i == 0) {
                denominatorFactor.add(i);
            }
        }

        // DESC: Determine the greater value
        // NOTE: The greater value will have more
        // ... factors and the code needs to start
        // ... with that value so it does not miss
        // ... a value
        int denominatorListLimit = denominatorFactor.size() - 1;
        int numeratorListLimit = numeratorFactor.size() - 1;
        if (numerator < denominator) {
            // DESC: Determine the GCF
            // NOTE: GCF is the largest factor that
            // ... appears in BOTH of the lists of
            // ... factors
            for (int i = denominatorListLimit; i >= 0; i--) {
                if (numeratorFactor.contains(denominatorFactor.get(i))) {
                    gcf = denominatorFactor.get(i);
                    break;
                }
            }
        } else {
            // DESC: Determine the GCF
            // NOTE: GCF is the largest factor that
            // ... appears in BOTH of the lists of
            // ... factors
            for (int i = numeratorListLimit; i >= 0; i--) {
                if (denominatorFactor.contains(numeratorFactor.get(i))) {
                    gcf = numeratorFactor.get(i);
                    break;
                }
            }
        }

        return gcf;
    }

    /**
     * Given a numerator and a denominator, determine if
     * the overall fraction should be negative or positive
     * @param numerator
     * @param denominator
     * @return a boolean representation stating if the fraction is negative
     */
    public static boolean isFractionNegative (int numerator, int denominator) {
        boolean isNumeratorNegative = (numerator < 0) ? true : false;
        boolean isDenominatorNegative = (denominator < 0) ? true : false;

        if (isNumeratorNegative == false && isDenominatorNegative == false) {
            // NOTE: This indicates that the overall fraction
            // ... is positive
            return false;
        } else if (isNumeratorNegative == true && isDenominatorNegative == true) {
            // NOTE: This indicates that the overall function
            // ... is positive because BOTH values are negative
            // ... so they negate each other
            return false;
        } else {
            // NOTE: This indicates that the overall function
            // ... is negative because ONE value is negative
            return true;
        }
    }

    /**
     * Given a numerator, a denominator and the expected 'sign' (as
     * a boolean), return a hashKey representation
     * @param numerator
     * @param denominator
     * @param isNegative
     * @return a string representation for a hash key
     */
    public static String assembleFractionAsString (int numerator, int denominator, boolean isNegative) {
        // DESC: Get the absolute value of
        // ... the numbers (i.e., remove any
        // ... negative sign)
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // DESC: Handle scenarios where numerator OR
        // ... denominator is a "0"
        // NOTE: A "0" in either of these scenarios
        // ... suggest either a horizontal OR vertical
        // ... line respectively (negativity does not
        // ... matter in these scenarios)
        if (numerator == 0) {
            return "0 RISE";
        }

        if (denominator == 0) {
            return "0 RUN";
        }

        // DESC: Assemble the hashKey
        if (isNegative == true) {
            String hashKey = "-" + numerator + ":" + denominator;
            return hashKey;
        } else {
            String hashKey = numerator + ":" + denominator;
            return hashKey;
        }
    }

    public int lineThroughPoints (int[][] points) {
        // DESC: Initialize necessary variables
        int numberOfPoints = points.length;
        int maxNumberOfPoints = 0;

        // DESC: Handle edge-case of one coordinate and two
        // ... coordinates (these do not require excessive logic)
        if (numberOfPoints == 1) {
            maxNumberOfPoints = 1;
            return maxNumberOfPoints;
        }

        if (numberOfPoints == 2) {
            maxNumberOfPoints = 2;
            return maxNumberOfPoints;
        }

        // DESC: Iterate through each value where
        // ... pointOne is the external loop and
        // ... pointTwo is the nested loop
        for (int outerIndex = 0; outerIndex < numberOfPoints; outerIndex++) {
            int[] pointOne = points[outerIndex];
            Map<String, Integer> pointsCounter = new HashMap<>();

            for (int innerIndex = 0; innerIndex < numberOfPoints; innerIndex++) {
                int[] pointTwo = points[innerIndex];

                // DESC: Skip beyond where the values
                // ... are the same index so to not compare
                // ... the value against itself
                if (innerIndex == outerIndex) {
                    continue;
                } else {
                    // DESC: Determine the slope of the
                    // ... two points
                    // NOTE: This relies on the equation of
                    // ... m = (y2 - y1) / (x2 - x1), which
                    // ... boils down to RISE over RUN
                    int riseValue = pointTwo[1] - pointOne[1];
                    int runValue = pointTwo[0] - pointOne[0];

                    // DESC: Get the Greatest Common Factor between
                    // ... the numerator (rise) and denominator (run)
                    // NOTE: A GCF of "1" means that it cannot be reduced
                    // ... any further otherwise it MUST be reduced to add
                    // ... to the HashMap
                    int gcf = greatestCommonFactor(riseValue, runValue);
                    if (gcf == 1) {
                        boolean isOverallFractionNegative = isFractionNegative(riseValue, runValue);
                        String hashKey = assembleFractionAsString(riseValue, runValue, isOverallFractionNegative);
                        if (pointsCounter.containsKey(hashKey) == true) {
                            int valueIncrementer = pointsCounter.get(hashKey) + 1;
                            pointsCounter.put(hashKey, valueIncrementer);
                        } else {
                            // NOTE: The 'value' starts at "2" because each line
                            // ... will, by default, consist of two points
                            pointsCounter.put(hashKey, 2);
                        }
                    } else {
                        // NOTE: A GCF greater-than "1" indicates that the
                        // ... fraction must be reduced PRIOR to insertion
                        int reducedRiseValue = riseValue / gcf;
                        int reducedRunValue = runValue / gcf;
                        boolean isOverallFractionNegative = isFractionNegative(riseValue, runValue);
                        String hashKey = assembleFractionAsString(reducedRiseValue, reducedRunValue, isOverallFractionNegative);
                        if (pointsCounter.containsKey(hashKey) == true) {
                            int valueIncrementer = pointsCounter.get(hashKey) + 1;
                            pointsCounter.put(hashKey, valueIncrementer);
                        } else {
                            // NOTE: The 'value' starts at "2" because each line
                            // ... will, by default, consist of two points
                            pointsCounter.put(hashKey, 2);
                        }
                    }
                }
            }

            for (String key : pointsCounter.keySet()) {
                int currentValue = pointsCounter.get(key);
                if (currentValue > maxNumberOfPoints) {
                    maxNumberOfPoints = currentValue;
                }
            }

        }

        return maxNumberOfPoints;
    }
}
