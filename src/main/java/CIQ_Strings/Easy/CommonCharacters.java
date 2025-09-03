package CIQ_Strings.Easy;

import java.util.*;

/**
 * Write a function that takes in a non-empty list of non-empty strings and
 * returns a list of characters that are common to all strings in the list,
 * ignoring multiplicity.
 *
 * Note that the strings are not guaranteed to only contain alphanumeric characters. The list
 * you return can be in any order.
 *
 * SAMPLE INPUT:
 * strings = ["abc", "bcd", "cbaccd"]
 *
 * SAMPLE OUTPUT:
 * ["b", "c"] // The characters could be ordered differently.
 *
 * ADDITIONAL NOTES:
 * - The version tested in AlgoExpert used a `currentIteration` variable
 * ... initialized at "1" and the `outerIndex` initialized to "0". In reviewing
 * ... the loops for Time-Space Complexity, after the fact, I realized that they
 * ... should have been "2" and "1" respectively because there is no need to
 * ... re-work the first String in the array as that creates duplicate work
 */

public class CommonCharacters {
    public String[] commonCharacters(String[] strings) {
        // DESC: Initialize necessary variables
        int numberOfStrings = strings.length;
        Map<Character, Integer> commonChars = new HashMap<Character, Integer>();

        // DESC: Edge-Case: Input-array of length "1"
        if (numberOfStrings == 1) {
            // NOTE: Use a Set to strip out duplicate
            // ... values that way you know what unique
            // ... values are 'common' in the single
            // ... String
            Set<Character> uniqueCharacters = new HashSet<Character>();

            // DESC: Loop through every character and
            // ... add to the Set
            for (int i = 0; i < strings[0].length(); i++) {
                uniqueCharacters.add(strings[0].charAt(i));
            }

            // DESC: Output each 'common' character to
            // ... the `output` array of Strings
            // NOTE: The loop 'appends' to each empty
            // ... String in the array because Java would
            // ... not allow type-casting
            int numberOfCharacters = uniqueCharacters.size();
            String[] output = new String[numberOfCharacters];
            int counter = 0;
            for (Character c : uniqueCharacters) {
                output[counter] = String.valueOf(c);
                counter = counter + 1;
            }

            return output;
        }

        // DESC: Add all characters of the first value
        // ... to a K-V D.S.
        // NOTE: The first string is the base-point as
        // ... the 'common' characters, technically, must
        // ... be present in ALL strings so the first string
        // ... will set the basis point to check against
        for (int i = 0; i < strings[0].length(); i++) {
            // NOTE: This If-Statement ensures that there are
            // ... no duplicates add to the HashMap
            if (commonChars.containsKey(strings[0].charAt(i)) == false) {
                commonChars.put(strings[0].charAt(i), 1);
            }
        }

        // DESC: Loop through remaining strings and increment
        // ... counter
        // NOTE: The counter will be incremented by the overall
        // ... iteration number IFF the value exists in the D.S.
        // ... AND the current value is one-less than the current
        // ... iteration (this basically ensures that only characters
        // ... that exists in all strings are taken into account)
        // NOTE: `currentIteration` starts at "2" and `outerIndex`
        // ... at "1" because the first String in the array was
        // ... worked in the loop above and there is no need for
        // ... duplicate work
        int currentIteration = 2;
        for (int outerIndex = 1; outerIndex < numberOfStrings; outerIndex++) {
            for (int innerIndex = 0; innerIndex < strings[outerIndex].length(); innerIndex++) {
                boolean keyExistsInDS = commonChars.containsKey(strings[outerIndex].charAt(innerIndex));
                boolean valueIsOneLessThanIteration = false;
                if (keyExistsInDS == true) {
                    valueIsOneLessThanIteration =
                            (currentIteration - commonChars.get(strings[outerIndex].charAt(innerIndex)) == 1) ?
                                    true : false;
                }
                if (keyExistsInDS == true && valueIsOneLessThanIteration == true) {
                    commonChars.put(strings[outerIndex].charAt(innerIndex), currentIteration);
                }
            }
            currentIteration = currentIteration + 1;
        }

        // DESC: Loop through `commonChars` and determine the number
        // ... 'values' that equal the overall number of strings in
        // ... the input array
        // NOTE: If the value has the same number as the number of
        // ... strings, then that suggests that the char is in all
        // ... strings
        int numberOfCommonChars = 0;
        for (Character c : commonChars.keySet()) {
            if (commonChars.get(c) == numberOfStrings) {
                numberOfCommonChars = numberOfCommonChars + 1;
            }
        }

        // DESC: Output common characters to the array
        // NOTE: Due to the fact that array-length needs to be
        // ... fixed, this loop needs to be two-stepped to
        // ... determine the size of the array using the
        // ... prior loop
        String[] output = new String[numberOfCommonChars];
        int counter = 0;
        for (Character c : commonChars.keySet()) {
            if (commonChars.get(c) == numberOfStrings) {
                output[counter] = String.valueOf(c);
                counter = counter + 1;
            }
        }

        return output;
    }
}