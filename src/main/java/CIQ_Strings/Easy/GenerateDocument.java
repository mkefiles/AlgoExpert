package CIQ_Strings.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * You're given a string of available characters and a string representing a
 * document that you need to generate. Write a function that determines if you
 * can generate the document using the available characters. If you can generate
 * the document, your function should return `true`; otherwise, it should return
 * `false`.
 *
 * You're only able to generate the document if the frequency of unique
 * characters in the characters string is greater than or equal to the frequency
 * of unique characters in the document string. For example, if you're given
 * `characters = "abcabc"` and `document = "aabbccc"` you CANNOT generate the
 * document because you're missing one `c`.
 *
 * The document that you need to create may contain any characters, including
 * special characters, capital letters, numbers, and spaces.
 *
 * Note: you can always generate the empty string (`""`).
 *
 * SAMPLE INPUT:
 * characters = "Bste!hetsi ogEAxpelrt x "
 * document = "AlgoExpert is the Best!"
 *
 * SAMPLE OUTPUT:
 * true
 */

public class GenerateDocument {
    public boolean generateDocument(String characters, String document) {
        // DESC: Initialize necessary variables
        int numberOfCharacters = characters.length();
        int documentLength = document.length();
        Map<Character, Integer> charsInCharacters = new HashMap<Character, Integer>();
        boolean canGenerateDocument = true;

        // DESC: Loop through `characters` and add all characters to
        // ... a K-V D.S. (increment for duplicates)
        for (int i = 0; i < numberOfCharacters; i++) {
            Character currentCharacter = characters.charAt(i);
            Integer currentValue = 0;
            if (charsInCharacters.containsKey(currentCharacter) == true) {
                currentValue = charsInCharacters.get(currentCharacter);
                currentValue = currentValue + 1;
                charsInCharacters.put(currentCharacter, currentValue);
            } else {
                charsInCharacters.put(currentCharacter, 1);
            }
        }

        // DESC: Loop through `document` and check if the character
        // ... exists in it (if so, handle accordingly)
        for (int i = 0; i < documentLength; i++) {
            Character currentCharacter = document.charAt(i);
            Integer currentValue = 0;
            if (charsInCharacters.containsKey(currentCharacter) && charsInCharacters.get(currentCharacter) != 0) {
                currentValue = charsInCharacters.get(currentCharacter);
                currentValue = currentValue - 1;
                charsInCharacters.put(currentCharacter, currentValue);
            } else {
                return false;
            }
        }

        return canGenerateDocument;
    }
}
