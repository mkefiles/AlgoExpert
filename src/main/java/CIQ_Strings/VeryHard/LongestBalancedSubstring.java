package CIQ_Strings.VeryHard;

/**
 * Write a function that takes in a string made up of parentheses (`(` and `)`).
 * The function should return an integer representing the length of the longest
 * balanced substring with regards to parentheses.
 *
 * A string is said to be balanced if it has as many opening parentheses as it
 * has closing parentheses and if no parenthesis is unmatched. Note that an
 * opening parenthesis can't match a closing parenthesis that comes before it,
 * and similarly, a closing parenthesis can't match an opening parenthesis that
 * comes after it.
 *
 * SAMPLE INPUT:
 * string = "(()))("
 *
 * SAMPLE OUTPUT:
 * 4 // The longest balanced substring is "(())"
 *
 * ADDITIONAL NOTES:
 * - There will NEVER be a 'balanced substring' where the length is an odd number
 *
 */

public class LongestBalancedSubstring {
    public int longestBalancedSubstring(String string) {
        int lengthOfString = string.length();
        int decrementingLoopBounds = lengthOfString - 1;
        int openingParenthesis = 0;
        int closingParenthesis = 0;
        int longestBalancedSubstring = 0;

        // DESC: Iterate from Left to Right
        // NOTE: The logic here is as follows:
        // - When a opening parenthesis is found, increment the counter
        // - When a closing parenthesis is found, increment the counter
        // - IF the two counters equal each other, update the `longestBalancedSubstring`
        // ... only if the number is greater
        // - IF the closing parenthesis counter exceeds the opening parenthesis
        // ... counter, then reset the count of both (this is because the 'balanced
        // ... substring' would start AFTER that being that no valid combination could
        // ... be used with values prior to it at that point)
        for (int leftCursor = 0; leftCursor < lengthOfString; leftCursor++) {
            // DESC: STEP 01: Increment the applicable counter
            if (string.charAt(leftCursor) == '(') {
                openingParenthesis = openingParenthesis + 1;
            }
            if (string.charAt(leftCursor) == ')') {
                closingParenthesis = closingParenthesis + 1;
            }

            // DESC: STEP 02: Check the counters against each other
            if (openingParenthesis == closingParenthesis) {
                int totalNumberOfBalancedParenthesis = openingParenthesis + closingParenthesis;
                longestBalancedSubstring = Math.max(longestBalancedSubstring, totalNumberOfBalancedParenthesis);
            }
            if (closingParenthesis > openingParenthesis) {
                openingParenthesis = 0;
                closingParenthesis = 0;
            }

        }

        // DESC: Reset the count of each parenthesis
        openingParenthesis = 0;
        closingParenthesis = 0;

        // DESC: Iterate from Right to Left
        // NOTE: The logic here is the same as the loop above, however it will
        // ... only reset the values when the OPENING parenthesis counter exceeds
        // ... the CLOSING parenthesis counter
        for (int rightCursor = decrementingLoopBounds; rightCursor >= 0; rightCursor--) {
            // DESC: STEP 01: Increment the applicable counter
            if (string.charAt(rightCursor) == ')') {
                closingParenthesis = closingParenthesis + 1;
            }
            if (string.charAt(rightCursor) == '(') {
                openingParenthesis = openingParenthesis + 1;
            }

            // DESC: STEP 02: Check the counters against each other
            if (closingParenthesis == openingParenthesis) {
                int totalNumberOfBalancedParenthesis = closingParenthesis + openingParenthesis;
                longestBalancedSubstring = Math.max(longestBalancedSubstring, totalNumberOfBalancedParenthesis);
            }
            if (openingParenthesis > closingParenthesis) {
                openingParenthesis = 0;
                closingParenthesis = 0;
            }
        }

        return longestBalancedSubstring;
    }
}