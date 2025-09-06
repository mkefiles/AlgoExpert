package CIQ_Strings.Medium;

/**
 * Write a function that takes in a string of words separated by one or more
 * whitespaces and returns a string that has these words in reverse order. For
 * example, given the string `"tim is great"`, your function should return "great is tim"`.
 *
 * For this problem, a word can contain special characters, punctuation, and
 * numbers. The words in the string will be separated by one or more whitespaces,
 * and the reversed string must contain the same whitespaces as the original
 * string. For example, given the string `"whitespaces    4"` you would be expected to
 * return `"4    whitespaces"`.
 *
 * Note that you're not allowed to use any built-in `split` or `reverse` methods/functions.
 * However, you are allowed to use a built-in `join` method/function.
 *
 * Also note that the input string isn't guaranteed to always contain words.
 *
 * SAMPLE INPUT:
 * string = "AlgoExpert is the best!"
 *
 * SAMPLE OUTPUT:
 * "best! the is AlgoExpert"
 *
 * ADDITIONAL NOTES:
 * - The general concept here is that, by starting at the
 * ... end of the `string` and toggling between counting
 * ... the number of spaces vs. the number of non-spaces,
 * ... the logic is able to append the sub-string of the
 * ... current-index plus the count ONLY when it toggles
 * - At the end of the iteration, because the toggle does
 * ... NOT occur, the code checks IF it is at the end then
 * ... appends the latest grouping depending on if it finishes
 * ... in the 'space' branch OR 'non-space' branch
 * - SPACE is determined by the ASCII decimal value of "32"
 */

public class ReverseWordsInString {
    public String reverseWordsInString(String string) {
        // DESC: Initialize necessary variables
        int stringLength = string.length();
        String output = "";
        int characterCounter = 0;
        int spaceCounter = 0;

        // DESC: Loop through the string starting at
        // ... the end of it
        for (int i = stringLength - 1; i >= 0; i--) {

            // NOTE: If the current character is a SPACE
            // NOTE: The logic here works as follows:
            // - IF the `characterCounter` is NOT "0", then append that
            // ... grouping of characters to the end of `output`
            // - Increment the `spaceCounter` (i.e., the current index
            // ... value holds a space character)
            // - If the iteration reaches the end AND is on a space
            // ... character, then append the current grouping of spaces
            // ... to the end of `output`
            if (string.charAt(i) == 32) {

                if (characterCounter != 0) {
                    output = output + string.substring(i + 1, i + characterCounter + 1);
                }

                spaceCounter = spaceCounter + 1;

                if (i == 0) {
                    output = output + string.substring(i, i + spaceCounter);
                }

                characterCounter = 0;
            }

            // NOTE: If the current character is anything but a SPACE
            // NOTE: The logic here works as follows:
            // - IF the `spaceCounter` is NOT "0", then append that
            // ... grouping of spaces to the end of `output`
            // - Increment the `characterCounter` (i.e., the current index
            // ... value holds a non-space character)
            // - If the iteration reaches the end AND is on a non-space
            // ... character, then append the current grouping of characters
            // ... to the end of `output`
            if (string.charAt(i) != 32) {

                if (spaceCounter != 0) {
                    output = output + string.substring(i + 1, i + spaceCounter + 1);
                }

                characterCounter = characterCounter + 1;

                if (i == 0) {
                    output = output + string.substring(i, i + characterCounter);
                }

                spaceCounter = 0;
            }
        }

        return output;
    }
}
