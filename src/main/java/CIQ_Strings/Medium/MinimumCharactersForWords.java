package CIQ_Strings.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that takes in an array of words and returns the smallest
 * array of characters needed to form all of the words. The characters don't need
 * to be in any particular order.
 *
 * For example, the characters `["y", "r", "o", "u"]` are needed to form the words
 * `["your", "you", "or", "yo"]`.
 *
 * Note: the input words won't contain any spaces; however, they might contain
 * punctuation and/or special characters.
 *
 * SAMPLE INPUT:
 * words = ["this", "that", "did", "deed", "them!", "a"]
 *
 * SAMPLE OUTPUT:
 * ["t", "t", "h", "i", "s", "a", "d", "d", "e", "e", "m", "!"]
 * // The characters could be ordered differently.
 */

public class MinimumCharactersForWords {
    public char[] minimumCharactersForWords(String[] words) {
        // DESC: Initialize necessary variables
        Map<Character, Integer> characterFrequency = new HashMap<Character, Integer>();

        // DESC: A nested loop that iterates through each letter
        // ... of each word
        for (String word : words) {

            // NOTE: Use a temporary HashMap to hold the count
            // ... of each character in the current word
            Map<Character, Integer> charactersInWord = new HashMap<Character, Integer>();

            for (int c = 0; c < word.length(); c++) {
                char key = word.charAt(c);
                int value = 0;

                // DESC: If the temporary K-V D.S. contains the
                // ... current letter, then increment it otherwise
                // ... add a new K-V pair
                if (charactersInWord.containsKey(key) == true) {
                    value = charactersInWord.get(key);
                    value = value + 1;
                    charactersInWord.put(key, value);
                } else {
                    charactersInWord.put(key, 1);
                }
            }

            // DESC: Iterate through the final K-V D.S. adding
            // ... the letter (as the key) and the greater value
            // ... between what is currently in the D.S. and what
            // ... is in the temporary K-V D.S.
            for (Character key : charactersInWord.keySet()) {
                int proposedValue = charactersInWord.get(key);
                if (characterFrequency.containsKey(key) == true) {
                    int currentValue = characterFrequency.get(key);
                    if (proposedValue > currentValue) {
                        characterFrequency.put(key, proposedValue);
                    }
                } else {
                    characterFrequency.put(key, proposedValue);
                }
            }
        }

        // DESC: Due to the output expecting a primitive `char` array
        // ... the code needs to determine the overall number of
        // ... characters so to get the length of the `output` array
        int totalNumberOfChars = 0;
        for (Character key : characterFrequency.keySet()) {
            totalNumberOfChars = totalNumberOfChars + characterFrequency.get(key);
        }

        // DESC: Create the `output` array and loop through each
        // ... key in the final K-V D.S. and output that key the
        // ... number of times it appears (based on the value)
        // ... to `output`
        char[] output = new char[totalNumberOfChars];
        int index = 0;
        for (Character key : characterFrequency.keySet()) {
            int valueCount = characterFrequency.get(key);
            while (valueCount > 0) {
                output[index] = key;
                index = index + 1;
                valueCount = valueCount - 1;
            }
        }

        return output;
    }
}
