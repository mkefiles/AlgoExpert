package CIQ_Strings.Easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a function that takes in a list of unique strings and returns a list of
 * semordnilap pairs.
 *
 * A semordnilap pair is defined as a set of different strings where the reverse
 * of one word is the same as the forward version of the other. For example the
 * words "diaper" and "repaid" are a semordnilap pair, as are the words
 * "palindromes" and "semordnilap".
 *
 * The order of the returned pairs and the order of the strings within each pair
 * does not matter.
 *
 * SAMPLE INPUT:
 * words = ["diaper", "abc", "test", "cba", "repaid"]
 *
 * SAMPLE OUTPUT:
 * [["diaper", "repaid"], ["abc", "cba"]]
 */

public class Semordnilap {
    public static boolean wordPairCheck(String word01, String word02) {
        // DESC: Initialize necessary variables
        int wordLength = word01.length();
        int upperLimit = wordLength;

        // DESC: Loop through words and compare
        // NOTE: `word01` is iterated left-to-right and
        // ... `word02` is iterated right-to-left
        // NOTE: This function is set to return 'true'
        // ... by default and will only return 'false'
        // ... if the characters at any given iteration
        // ... do not match
        for (int i = 0; i < wordLength; i++) {
            upperLimit = upperLimit - 1;
            if (word01.charAt(i) == word02.charAt(upperLimit)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
    public ArrayList<ArrayList<String>> semordnilap(String[] words) {
        // DESC: Initialize necessary variables
        int numberOfWords = words.length;
        int cursorOneIndex = 0;
        int cursorTwoIndex = 1;
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();

        // DESC: Remove edge-case of an empty input
        if (numberOfWords == 0) {
            return output;
        }

        // DESC: Loop through input (with two cursors)
        while (true) {
            if (cursorTwoIndex != numberOfWords) {

                // DESC: Check if the words at both cursors
                // ... have an equivalent length
                // NOTE: The character-by-character cross-
                // ... reference is ONLY done when the lengths
                // ... are equivalent
                if (words[cursorOneIndex].length() == words[cursorTwoIndex].length()) {
                    if (wordPairCheck(words[cursorOneIndex], words[cursorTwoIndex]) == true) {
                        output.add(new ArrayList<>(Arrays.asList(words[cursorOneIndex], words[cursorTwoIndex])));
                        cursorOneIndex = cursorOneIndex + 1;
                        cursorTwoIndex = cursorOneIndex + 1;
                    } else {
                        cursorTwoIndex = cursorTwoIndex + 1;
                    }
                } else {
                    cursorTwoIndex = cursorTwoIndex + 1;
                }

            }

            // DESC: In the event where `cursorTwoIndex` has
            // ... made it to the end of the array-length
            // ... check where the `cursorOneIndex` is
            // ... and handle accordingly
            if (cursorTwoIndex == numberOfWords) {
                if (cursorOneIndex == numberOfWords - 1) {
                    break;
                } else {
                    cursorOneIndex = cursorOneIndex + 1;
                    cursorTwoIndex = cursorOneIndex + 1;
                }
            }
        }

        return output;
    }
}
