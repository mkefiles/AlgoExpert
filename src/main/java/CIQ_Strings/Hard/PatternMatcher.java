package CIQ_Strings.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 * You're given two non-empty strings. The first one is a pattern consisting of
 * only `"x"`s and / or `"y"`s; the other one is a normal string of alphanumeric
 * characters. Write a function that checks whether the normal string matches
 * the pattern.
 *
 * A string `S0` is said to match a pattern if replacing all `"x"`s in the pattern
 * with some non-empty substring `S1` of `S0` and replacing all `"y"`s in the
 * pattern with some non-empty substring `S2` of `S0` yields the same string `S0`.
 *
 * If the input string doesn't match the input pattern, the function should
 * return an empty array; otherwise, it should return an array holding the
 * strings `S1` and `S2` that represent `"x"` and `"y"` in the normal string, in
 * that order. If the pattern doesn't contain any `"x"`s or `"y"`s, the respective
 * letter should be represented by an empty string in the final array that you return.
 *
 * You can assume that there will never be more than one pair of strings
 * `S1` and `S2` that appropriately represent `"x"` and `"y"` in the normal string.
 *
 * SAMPLE INPUT:
 * pattern = "xxyxxy"
 * string = "gogopowerrangergogopowerranger"
 *
 * SAMPLE OUTPUT:
 * ["go", "powerranger"]
 */

public class PatternMatcher {
    public static String[] patternMatcher(String pattern, String str) {
        // DESC: Initialize logic-wide variables
        int lengthOfString = str.length();
        int lengthOfPattern = pattern.length();
        boolean patternContainsX = pattern.contains("x");
        boolean patternContainsY = pattern.contains("y");

        // NOTE: Logic for a pattern that contain both 'x' and 'y'
        if (patternContainsX == true && patternContainsY == true) {

            // DESC: Create a 'standardized' pattern where it always
            // ... starts with an 'x' AND create a K-V D.S. that contains
            // ... the count of X, count of Y and the first 'y' position
            char[] newPattern = new char[lengthOfPattern];
            Map<String, Integer> patternInformation = new HashMap<String, Integer>();
            patternInformation.put("xCount", 0);
            patternInformation.put("yCount", 0);
            boolean hasXandYBeenFlipped = false;
            for (int i = 0; i < lengthOfPattern; i++) {
                if (pattern.charAt(0) == 'x') {
                    newPattern[i] = pattern.charAt(i);
                    if (pattern.charAt(i) == 'x') {
                        int xCount = patternInformation.get("xCount");
                        xCount = xCount + 1;
                        patternInformation.put("xCount", xCount);
                    }

                    if (pattern.charAt(i) == 'y') {
                        int yCount = patternInformation.get("yCount");
                        yCount = yCount + 1;
                        patternInformation.put("yCount", yCount);

                        if (patternInformation.containsKey("firstYPos") == false) {
                            patternInformation.put("firstYPos", i);
                        }
                    }
                }
                if (pattern.charAt(0) == 'y') {
                    hasXandYBeenFlipped = true;
                    if (pattern.charAt(i) == 'y') {
                        newPattern[i] = 'x';

                        int xCount = patternInformation.get("xCount");
                        xCount = xCount + 1;
                        patternInformation.put("xCount", xCount);
                    }
                    if (pattern.charAt(i) == 'x') {
                        newPattern[i] = 'y';

                        int yCount = patternInformation.get("yCount");
                        yCount = yCount + 1;
                        patternInformation.put("yCount", yCount);

                        if (patternInformation.containsKey("firstYPos") == false) {
                            patternInformation.put("firstYPos", i);
                        }
                    }
                }
            }

            // DESC: Iterate through string length to determine possible
            // ... combinations
            // NOTE: Using the proposed length of X, it is possible to derive
            // ... the proposed length of Y
            // NOTE: Due to the 'standardization' of the pattern (see above)
            // ... and the fact that any patterns without both X and Y would
            // ... not make it here, it is safe to assume that the first substring
            // ... of the input array will be associated with an 'X' pattern.
            // ... Given that, the loop grows the proposed 'X' string on every
            // ... iteration and the proposed 'Y' string is DERIVED using the
            // ... formulas below. Next, the `proposedOutput` is assembled
            // ... based on the `newPattern` array (an 'x' receives the `proposedX`
            // ... string and a 'y' receives the `proposedY` string). Finally,
            // ... the `proposedOutput` is tested against the input string and,
            // ... in the event that it equals return the two words that match the
            // ... pattern.
            int xCount = patternInformation.get("xCount");
            int yCount = patternInformation.get("yCount");
            int firstYPos = patternInformation.get("firstYPos");
            for (int i = 1; i < lengthOfString; i++) {

                String proposedX = str.substring(0, i);
                int proposedLengthOfX = proposedX.length();
                int proposedLengthOfY = (lengthOfString - (proposedLengthOfX * xCount)) / yCount;

                // DESC: Handle potential issues where the `proposedLengthOfY`
                // ... evaluates to a negative value
                if (proposedLengthOfY <= 0) {
                    return new String[] {};
                }

                int proposedYIndex = firstYPos * proposedLengthOfX;
                String proposedY = str.substring(proposedYIndex, proposedYIndex + proposedLengthOfY);

                String proposedOutput = "";
                for (char c : newPattern) {
                    if (c == 'x') {
                        proposedOutput = proposedOutput + proposedX;
                    }
                    if (c == 'y') {
                        proposedOutput = proposedOutput + proposedY;
                    }
                }

                if (str.equals(proposedOutput) == true) {
                    // NOTE: The logic below reverses the 'standardization'
                    // ... because the order of the output matters
                    if (hasXandYBeenFlipped) {
                        return new String[] {proposedY, proposedX};
                    } else {
                        return new String[]{proposedX, proposedY};
                    }
                }
            }
        }

        // NOTE: Logic for a pattern that ONLY contains 'x'
        if (patternContainsX == true && patternContainsY == false) {

            // DESC: If the pattern only contains 'x', then simply
            // ... determine, first, if the input string length is
            // ... evenly divisible by the pattern-length. If it is,
            // ... then create a string out of the number of times
            // ... the substring of `numberOfX` length and test it
            // ... against the input string
            int numberOfX = pattern.length();
            if (lengthOfString % numberOfX == 0) {
                int numberOfSubstrings = lengthOfString / numberOfX;
                String firstSubstring = str.substring(0, numberOfX);
                String proposedOutput = "";
                for (int i = 0; i < numberOfSubstrings; i++) {
                    proposedOutput = proposedOutput + firstSubstring;
                }
                if (str.equals(proposedOutput)) {
                    return new String[] {firstSubstring, ""};
                }
            }
        }

        // NOTE: Logic for a pattern that ONLY contains 'y'
        if (patternContainsY == true && patternContainsX == false) {

            // DESC: If the pattern only contains 'y', then simply
            // ... determine, first, if the input string length is
            // ... evenly divisible by the pattern-length. If it is,
            // ... then create a string out of the number of times
            // ... the substring of `numberOfY` length and test it
            // ... against the input string
            int numberOfY = pattern.length();
            if (lengthOfString % numberOfY == 0) {
                int numberOfSubstrings = lengthOfString / numberOfY;
                String firstSubstring = str.substring(0, numberOfY);
                String proposedOutput = "";
                for (int i = 0; i < numberOfSubstrings; i++) {
                    proposedOutput = proposedOutput + firstSubstring;
                }
                if (str.equals(proposedOutput)) {
                    return new String[] {"", firstSubstring};
                }
            }
        }

        // DESC: Return an empty String array in the event where
        // ... none of the tests above were successful
        return new String[] {};
    }
}