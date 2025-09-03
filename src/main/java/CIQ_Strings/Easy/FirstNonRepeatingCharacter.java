package CIQ_Strings.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that takes in a string of lowercase English-alphabet letters
 * and returns the index of the string's first non-repeating character.
 *
 * The first non-repeating character is the first character in a string that
 * occurs only once.
 *
 * If the input string doesn't have any non-repeating characters, your function
 * should return -1.
 *
 * SAMPLE INPUT:
 * string = "abcdcaf"
 *
 * SAMPLE OUTPUT:
 * 1 // The first non-repeating character is "b" and is found at index 1.
 */

public class FirstNonRepeatingCharacter {
    public int firstNonRepeatingCharacter(String string) {
        // DESC: Initialize necessary variables
        int numberOfCharacters = string.length();
        Map<Character, Integer> letterIndexTracker = new HashMap<Character, Integer>();
        int indexOfNonRepeatingNumber = Integer.MAX_VALUE;

        // DESC: Loop through input `string` and determine
        // ... if the character has been added to the K-V D.S.
        // NOTE: If the character HAS been added, then change
        // ... the value to "-1" (indicating a repeating character
        // ... was found) otherwise, on the initial add, use the
        // ... characters index value as the 'value'
        for (int i = 0; i < numberOfCharacters; i++) {
            int currentValue = 0;
            if (letterIndexTracker.containsKey(string.charAt(i)) == true) {
                letterIndexTracker.put(string.charAt(i), -1);
            } else {
                letterIndexTracker.put(string.charAt(i), i);
            }
        }

        // DESC: Loop through the K-V D.S. and, excluding "-1"
        // ... values, determine the lowest index 'value'
        // ... available
        for (Character c : letterIndexTracker.keySet()) {
            if (letterIndexTracker.get(c) == -1) {
                continue;
            } else {
                if (letterIndexTracker.get(c) < indexOfNonRepeatingNumber) {
                    indexOfNonRepeatingNumber = letterIndexTracker.get(c);
                }
            }
        }

        // DESC: If the proposed index was never changed from
        // ... Integer.MAX_VALUE, then there is no non-repeating
        // ... value therefore return "-1"
        if (indexOfNonRepeatingNumber == Integer.MAX_VALUE) {
            return -1;
        } else {
            return indexOfNonRepeatingNumber;
        }
    }
}
