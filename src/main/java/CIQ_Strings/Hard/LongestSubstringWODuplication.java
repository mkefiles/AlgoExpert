package CIQ_Strings.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that takes in a string and returns its longest substring
 * without duplicate characters.
 *
 * You can assume that there will only be one longest substring without duplication.
 *
 * SAMPLE INPUT:
 * string = "clementisacap"
 *
 * SAMPLE OUTPUT:
 * "mentisac"
 *
 * ADDITIONAL NOTES:
 * - The general concept is as follows... loop through the string and add each
 * ... Character (as a Key) and its Integer-index (as the value). When a duplicate
 * ... is found, simply move the loop-cursor back to the MAX of either the index
 * ... right after the first occurrence or the current `startingIndex` value (this
 * ... prevents re-reviewing characters). The `startingIndex` and the currentIndex
 * ... is what is used to create a substring and test against the `output` to only
 * ... reassign it if it is longer
 */

public class LongestSubstringWODuplication {
    public static String longestSubstringWithoutDuplication(String str) {
        // DESC: Initialize necessary variables
        int stringLength = str.length();
        Map<Character, Integer> lastSeenIndex = new HashMap<Character, Integer>();
        String output = "";
        int startingIndex = 0;

        // DESC: Edge-case with ONE character
        if (stringLength == 1) {
            return str;
        }

        // DESC: Loop through each letter in the input `str`
        for (int charIndex = 0; charIndex < stringLength; charIndex++) {

            // DESC: Check if the current character is already in the K-V D.S
            if (lastSeenIndex.containsKey(str.charAt(charIndex)) == true) {

                // NOTE: We capture `currentSubstring` in two locations because
                // ... IF the current `charIndex` is already in the K-V D.S.
                // ... then we do not want to include that character in the
                // ... `currentSubstring` otherwise we do want it included
                String currentSubstring = str.substring(startingIndex, charIndex);

                if (lastSeenIndex.get(str.charAt(charIndex)) == charIndex) {
                    // DESC: If the K-V D.S. contains the current letter AND the 'value'
                    // ... is the same as the current index, then that just indicates
                    // ... that we had to step backwards at one point and it is not
                    // ... a duplicate
                    if (currentSubstring.length() > output.length()) {
                        output = currentSubstring;
                    }
                } else {
                    // DESC: A duplicate letter has been encountered (i.e., the
                    // ... 'value' is NOT the same), proceed to logic, which
                    // ... ultimately moves the cursor backwards
                    int priorIndex = lastSeenIndex.get(str.charAt(charIndex));

                    // NOTE: Get the `max()` of the startingIndex and the
                    // ... priorIndex + 1 BECAUSE in the following example
                    // ... where you have "clementisacap" and you are on the
                    // ... second "c" (index 10), if you navigate BACK to
                    // ... `priorIndex + 1`, then the code will have inaccurate
                    // ... output/erratic behavior as it would end up
                    // ... re-encountering the duplicate "e"s ahead of the
                    // ... first "c"
                    startingIndex = Math.max(startingIndex, priorIndex + 1);

                    // NOTE: Remove the current Key/Value from the D.S. so
                    // ... it does not re-throw a 'duplicate' scenario
                    lastSeenIndex.remove(str.charAt(charIndex));

                    // DESC: Move the loop-cursor back to the necessary
                    // ... location
                    charIndex = startingIndex;

                    if (currentSubstring.length() > output.length()) {
                        output = currentSubstring;
                    }
                }
            } else {

                // NOTE: We capture `currentSubstring` in two locations because
                // ... IF the current `charIndex` is already in the K-V D.S.
                // ... then we do not want to include that character in the
                // ... `currentSubstring` otherwise we do want it included
                String currentSubstring = str.substring(startingIndex, charIndex + 1);

                // DESC: Logic for a unique letter
                lastSeenIndex.put(str.charAt(charIndex), charIndex);
                if (currentSubstring.length() > output.length()) {
                    output = currentSubstring;
                }
            }
        }

        return output;
    }
}