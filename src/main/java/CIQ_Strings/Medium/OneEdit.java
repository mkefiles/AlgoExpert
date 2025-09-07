package CIQ_Strings.Medium;

/**
 * You're given two strings `stringOne` and `stringTwo`. Write a function
 * that determines if these two strings can be made equal using only one edit.
 *
 * There are 3 possible edits:
 * - Replace: One character in one string is swapped for a different character
 * - Add: One character is added at any index in one string.
 * - Remove: One character is removed at any index in one string.
 *
 * Note that both strings will contain at least one character. If the strings
 * are the same, your function should return true.
 *
 * SAMPLE INPUT:
 * stringOne = "hello"
 * stringTwo = "hollo"
 *
 * SAMPLE OUTPUT:
 * true // A single replace at index 1 of either string can make the strings equal
 */

public class OneEdit {
    public boolean oneEdit(String stringOne, String stringTwo) {
        // DESC: Initialize necessary variables
        int stringOneLength = stringOne.length();
        int stringTwoLength = stringTwo.length();

        // DESC: Remove the easiest check (i.e., where the difference
        // ... in length is greater-than one)
        // NOTE: This would suggest that any ONE edit would be inadequate
        if (Math.abs(stringOneLength - stringTwoLength) > 1) {
            return false;
        }

        // DESC: If both strings have an equal lenght, then simply
        // ... count the number of times a character does not line up
        // NOTE: This determines if just one 'replace' would be adequate
        if (stringOneLength == stringTwoLength) {
            int errorCounter = 0;
            for (int i = 0; i < stringOneLength; i++) {
                if (stringOne.charAt(i) != stringTwo.charAt(i)) {
                    errorCounter = errorCounter + 1;
                }
            }
            if (errorCounter > 1) {
                return false;
            } else {
                return true;
            }
        }

        // DESC: Check if an 'add' or a 'remove' would solve this
        // NOTE: If both words are only off by one character (this
        // ... would be confirmed by the time the code reaches this
        // ... point), then simply verifying that each character at
        // ... all points up to the length of the shortest word would
        // ... need to be equal to each other, otherwise it would require
        // ... a 'replace' AND an 'add' / 'remove', which is a `false`
        // NOTE: In the event that the letters at the current index do
        // ... NOT add up, it may suggest that a value needs to be added
        // ... to the shorter string to shift all values forward so, for
        // ... those scenarios, the code also checks the current character
        // ... of the shorter string against the value at `i + 1` in the
        // ... longer string
        if (stringOneLength < stringTwoLength) {
            for (int i = 0; i < stringOneLength; i++) {
                if (stringOne.charAt(i) != stringTwo.charAt(i)) {
                    if (stringOne.charAt(i) != stringTwo.charAt(i + 1)) {
                        return false;
                    }
                }
            }
        }

        if (stringTwoLength < stringOneLength) {
            for (int i = 0; i < stringTwoLength; i++) {
                if (stringTwo.charAt(i) != stringOne.charAt(i)) {
                    if (stringTwo.charAt(i) != stringOne.charAt(i + 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
