package CIQ_Strings.Hard;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a function that takes in two strings: a main string and a potential
 * substring of the main string. The function should return a version of the main
 * string with every instance of the substring in it wrapped between underscores.
 *
 * If two or more instances of the substring in the main string overlap each
 * other or sit side by side, the underscores relevant to these substrings should
 * only appear on the far left of the leftmost substring and on the far right of
 * the rightmost substring. If the main string doesn't contain the other string
 * at all, the function should return the main string intact.
 *
 * SAMPLE INPUT:
 * string = "testthis is a testtest to see if testestest it works"
 * substring = "test"
 *
 * SAMPLE OUTPUT:
 * "_test_this is a _testtest_ to see if _testestest_ it works"
 */

public class UnderscorifySubstring {
    public static String underscorifySubstring(String str, String substring) {
        // DESC: Initialize logic-wide variables here
        int stringLength = str.length();

        // DESC: Handle scenarios where the substring
        // ... does NOT appear in the string
        if (str.contains(substring) == false) {
            return str;
        }

        // DESC: Traverse string and output all the start
        // ... and end index values, where the that instance of
        // ... a substring equals the `substring` input,
        // ... to the `locatedIndices` 2D array
        int substringLength = substring.length();
        ArrayList<ArrayList<Integer>> locatedIndices = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < stringLength; i++) {

            // NOTE: Ensure that the substring length does
            // ... not go out of bounds for the input string
            int remainingLength = stringLength - i;
            if (remainingLength >= substringLength) {
                String testSubstring = str.substring(i, i + substringLength);
                if (testSubstring.equals(substring) == true) {
                    locatedIndices.add(new ArrayList<>(Arrays.asList(i, i + substringLength)));
                }
            }
        }

        // DESC: Traverse `locatedIndices` 2D array and
        // ... merge all X/Y coordinates where they overlap
        // NOTE: This also entails deleting the 'merged'
        // ... coordinate so the logic will NOT increment
        // ... when it finds a situation where there is a merge
        // ... as it needs to retest the current/updated coordinates
        // ... against the one that just moved next to it
        int numberOfCoordinates = locatedIndices.size();
        int coordinatesIndex = 0;
        while (coordinatesIndex != numberOfCoordinates) {
            if (coordinatesIndex + 1 < numberOfCoordinates) {
                int nextValueX = locatedIndices.get(coordinatesIndex + 1).get(0);
                int nextValueY = locatedIndices.get(coordinatesIndex + 1).get(1);
                int currentValueY = locatedIndices.get(coordinatesIndex).get(1);
                if (nextValueX <= currentValueY) {
                    locatedIndices.get(coordinatesIndex).set(1, nextValueY);
                    locatedIndices.remove(coordinatesIndex + 1);
                    numberOfCoordinates = numberOfCoordinates - 1;
                    continue;
                }
            }
            coordinatesIndex = coordinatesIndex + 1;
        }

        // DESC: Assemble the output string with the necessary
        // ... underscores
        // NOTE: This checks to see if the current index-value
        // ... equals the x-value of the current coordinate
        // ... IF it is, then append that substring with underscores
        // ... and move to the next coordinate
        // ... IF it is NOT, then simply append the current letter
        // ... to the output
        String output = "";
        int indexInArray = 0;
        for (int i = 0; i < stringLength; i++) {
            if (indexInArray + 1 < numberOfCoordinates) {
                int currentXValue = locatedIndices.get(indexInArray).get(0);
                int currentYValue = locatedIndices.get(indexInArray).get(1);
                if (i < currentXValue) {
                    output = output + str.charAt(i);
                }
                if (i == currentXValue) {
                    output = output + "_" + str.substring(currentXValue, currentYValue) + "_";
                    i = currentYValue - 1;
                    indexInArray = indexInArray + 1;
                }
            } else if (indexInArray + 1 == numberOfCoordinates) {
                int currentXValue = locatedIndices.get(indexInArray).get(0);
                int currentYValue = locatedIndices.get(indexInArray).get(1);
                if (i < currentXValue) {
                    output = output + str.charAt(i);
                }
                if (i == currentXValue) {
                    output = output + "_" + str.substring(currentXValue, currentYValue) + "_";
                    i = currentYValue - 1;
                    indexInArray = indexInArray + 1;
                }
            } else {
                output = output + str.charAt(i);
                indexInArray = indexInArray + 1;
            }
        }

        return output;
    }
}
