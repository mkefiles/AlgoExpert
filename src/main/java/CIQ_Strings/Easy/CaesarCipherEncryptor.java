package CIQ_Strings.Easy;

/**
 * Given a non-empty string of lowercase letters and a non-negative integer
 * representing a key, write a function that returns a new string obtained by
 * shifting every letter in the input string by k positions in the alphabet,
 * where k is the key.
 *
 * Note that letters should "wrap" around the alphabet; in other words, the
 * letter `z` shifted by one returns the letter `a`.
 *
 * SAMPLE INPUT:
 * string = "xyz"
 * key = 2
 *
 * SAMPLE OUTPUT:
 * "zab"
 */

public class CaesarCipherEncryptor {

    public static int asciiLoopAround (int characterDecimalValue, int key) {
        // DESC: Check the `characterDecimalValue` and
        // ... `key` against multiple possible scenarios
        if (key > 26) {

            // NOTE: In the event where the 'key' is
            // ... evenly divisible by 26, it just flips
            // ... back to the original letter
            int moduloOf26 = key % 26;
            if (moduloOf26 == 0) {
                return characterDecimalValue;
            } else {

                // NOTE: In the event where the `key` is
                // ... NOT evenly divisible by 26, the code
                // ... needs to determine if the difference
                // ... provided by modulo exceeds 122 or is
                // ... less-than/equal-to 122 and handle accordingly
                int overallDecimalValue = characterDecimalValue + moduloOf26;
                if (overallDecimalValue > 122) {
                    return overallDecimalValue - 26;
                } else {
                    return overallDecimalValue;
                }
            }
        } else if (key == 26) {
            // NOTE: A `key` of 26 requires no further
            // ... change because it will loop around
            // ... back to where it started
            return characterDecimalValue;
        } else {
            int overallDecimalValue = characterDecimalValue + key;
            if (overallDecimalValue > 122) {
                // NOTE: A value that must loop back
                // ... around no more than once
                return overallDecimalValue - 26;
            } else {
                // NOTE: A value that does NOT need to
                // ... loop back around
                return characterDecimalValue + key;
            }
        }
    }

    public static String caesarCypherEncryptor(String str, int key) {
        // DESC: Initialize necessary variables
        int numberOfLetters = str.length();
        String output = "";
        char letterInString = '-';
        int decimalValueOfChar = 0;

        // DESC: Edge-Case: A `key` of "0" ... leave
        // ... the String as-is
        if (key == 0) {
            return str;
        }

        // DESC: Loop through every character in the String
        for (int cI = 0; cI < numberOfLetters; cI++) {
            // DESC: The following steps occur:
            // - Get the current character (in the input String)
            // - Convert it to the decimal equivalent (this happens
            // ... through implicit casting)
            // - Add the integer `key` to the decimal value
            // - Append the calculated integer to the new String
            letterInString = str.charAt(cI);
            decimalValueOfChar = letterInString;
            output = output + ((char) asciiLoopAround(decimalValueOfChar, key));
        }

        return output;
    }
}
