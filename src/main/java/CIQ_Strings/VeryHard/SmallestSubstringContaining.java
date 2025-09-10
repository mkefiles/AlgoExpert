package CIQ_Strings.VeryHard;

import java.util.HashMap;

/**
 * TECHNIQUE: The Sliding Window
 * - Two cursors (left and right)
 * - Both start at '0'
 * - Right moves first and, pending logic, left moves
 */

/**
 * You're given two non-empty strings: a big string and a small string. Write a
 * function that returns the smallest substring in the big string that contains
 * all of the small string's characters.
 *
 * Note that:
 * - The substring can contain other characters not found in the small string
 * - The characters in the substring don't have to be in the same order as they
 * ... appear in the small string.
 * - If the small string has duplicate characters, the substring has to contain
 * ... those duplicate characters (it can also contain more, but not fewer).
 *
 * You can assume that there will only be one relevant smallest substring.
 *
 * SAMPLE INPUT:
 * bigString = "abcd$ef$axb$c$"
 * smallString = "$$abf"
 *
 * SAMPLE OUTPUT:
 * "f$axb$"
 *
 * ADDITIONAL NOTES:
 * - Note to self, review WHEN the indices are iterated AND the fact that
 * ... the Left-Cursor loop is nested within the Right-Cursor loop
 */

public class SmallestSubstringContaining {
    public static String smallestSubstringContaining(String bigString, String smallString) {
        // DESC: Get Characters and number of occurrences from `smallString`
        // ... and store in K-V D.S.
        HashMap<Character, Integer> targetCharacterCounts = new HashMap<Character, Integer>();
        int smallStringLength = smallString.length();
        for (int i = 0; i < smallStringLength; i++) {
            Character currentLetter = smallString.charAt(i);
            if (targetCharacterCounts.containsKey(currentLetter) == true) {
                int currentValue = targetCharacterCounts.get(currentLetter);
                currentValue = currentValue + 1;
                targetCharacterCounts.put(currentLetter, currentValue);
            } else {
                targetCharacterCounts.put(currentLetter, 1);
            }
        }

        // DESC: Initialize necessary variables
        int numberOfUniqueChars = targetCharacterCounts.keySet().size();
        int numberOfUniqueCharsFound = 0;
        int[] substringBounds = new int[] {0, Integer.MAX_VALUE};
        int leftIndex = 0;
        int rightIndex = 0;
        HashMap<Character, Integer> substringCharacterCounts = new HashMap<Character, Integer>();

        // DESC: Iterate through string with Right Cursor and, as applicable,
        // ... Left Cursor
        while (rightIndex < bigString.length()) {
            Character rightCursorLetter = bigString.charAt(rightIndex);

            // DESC: If the target-letters do not contain the current letter
            // ... then it can be disregarded
            if (targetCharacterCounts.containsKey(rightCursorLetter) == false) {
                rightIndex = rightIndex + 1;
                continue;
            }

            // DESC: Increase the count, in the substringCharacterCounts, for
            // the current character
            if (substringCharacterCounts.containsKey(rightCursorLetter) == true) {
                int currentValue = substringCharacterCounts.get(rightCursorLetter);
                currentValue = currentValue + 1;
                substringCharacterCounts.put(rightCursorLetter, currentValue);
            } else {
                substringCharacterCounts.put(rightCursorLetter, 1);
            }

            // DESC: Determine if the number of appearances of the current character
            // ... is equal between the substring K-V D.S. and the target K-V D.S.
            if (substringCharacterCounts.get(rightCursorLetter) == targetCharacterCounts.get(rightCursorLetter)) {
                numberOfUniqueCharsFound++;
            }

            // DESC: Iterate through the string with the Left Cursor
            while((numberOfUniqueCharsFound == numberOfUniqueChars) && (leftIndex <= rightIndex)) {
                // DESC: Update the `substringBounds` IF the current index-values
                // ... are less-than the current substring bounds
                int proposedLength = rightIndex - leftIndex;
                int currentLength = substringBounds[1] - substringBounds[0];
                if (proposedLength < currentLength) {
                    substringBounds[0] = leftIndex;
                    substringBounds[1] = rightIndex;
                }

                Character leftCursorLetter = bigString.charAt(leftIndex);

                // DESC: If the `targetCharacterCounts` does not contain
                // ... the left letter, then move the cursor and proceed
                if (targetCharacterCounts.containsKey(leftCursorLetter) == false) {
                    leftIndex++;
                    continue;
                }

                // DESC: If the `targetCharacterCounts` does contain the
                // ... left letter, then the `numberOfUniqueCharsFound`
                // ... must be decremented because it is no longer "found"
                if (substringCharacterCounts.get(leftCursorLetter) == targetCharacterCounts.get(leftCursorLetter)) {
                    numberOfUniqueCharsFound = numberOfUniqueCharsFound - 1;
                }

                // DESC: Decrement the count for the letter, update the index
                // ... and proceed
                int currentValue = substringCharacterCounts.get(leftCursorLetter);
                currentValue--;
                substringCharacterCounts.put(leftCursorLetter, currentValue);

                leftIndex++;
            }
            rightIndex++;
        }

        // DESC: If the end-bound was never changed, then the `smallString`
        // ... requirements were never found, otherwise return the substring
        if(substringBounds[1] == Integer.MAX_VALUE) {
            return "";
        } else {
            String output = bigString.substring(substringBounds[0], substringBounds[1] + 1);
            return output;
        }
    }
}
