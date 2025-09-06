package CIQ_Strings.Medium;

/**
 * Write a function that, given a string, returns its longest palindromic
 * substring.
 *
 * A palindrome is defined as a string that's written the same forward and
 * backward. Note that single-character strings are palindromes.
 *
 * You can assume that there will only be one longest palindromic substring.
 *
 * SAMPLE INPUT:
 * string = "abaxyzzyxf"
 *
 * SAMPLE OUTPUT:
 * "xyzzyx"
 *
 * ADDITIONAL NOTES:
 * - To justify the code below, I am using TWO independent while-loops BECAUSE
 * ... both 'even' and 'odd' logic needs to be able to work independent of one
 * ... another because we, in all situations (e.g., 'bbb' / 'bbbb'), do not know
 * ... if what we locate is "true even" / "true odd" just by looking at the adjacent
 * ... values. My thought-process is that there will only be a handful of scenarios
 * ... where BOTH loops need to run, one after the other, as such I cannot imagine
 * ... having independent loop-logic would alter T.C. in any drastic manner. Not
 * ... to mention KISS ... it is easier to isolate and modify fault logic when it
 * ... does not intertwine itself with other logic.
 * - The general concept is that you are checking the 'center-out' of every character
 * ... to see if there is a palindrome and, if so, update the output string IFF it is
 * ... longer than the current output
 * - To keep the Space-Complexity down, a new string is NOT created when checking if
 * ... the substring is a palindrome... the code just passes the same string around
 * ... and traverses the substring based on the left-cursor and right-cursor index
 * ... values
 */

public class LongestPalindromicSubstring {

    /**
     * Using two cursors (left and right) check if the value
     * at cursor-one equals the value at cursor-two. The moment
     * the two values are not equivalent, return false
     * NOTE: This operates on a substring in lieu of using the
     * additional space to create a new String
     * @param indexStart where, in the String, to start checking
     * @param indexEnd where, in the String, to stop checking
     * @param str the main String to substring
     * @return true/false confirming/denying there is a Palindrome
     */
    public static boolean isPalindrome(int indexStart, int indexEnd, String str) {
        int substringLength = (indexEnd - indexStart) + 1;
        int leftCursor = 0;
        int rightCursor = indexStart + substringLength;

        for (int i = indexStart; i < indexStart + substringLength; i++) {
            leftCursor = i;
            rightCursor = rightCursor - 1;

            if (rightCursor < leftCursor) {
                break;
            }

            if (str.charAt(leftCursor) == str.charAt(rightCursor)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindromicSubstring(String str) {
        // DESC: Initialize necessary variables
        int stringLength = str.length();
        boolean shouldEvenLoopRun = true;
        boolean shouldOddLoopRun = true;

        // DESC: Handle edge-case of length of "1"
        if (stringLength == 1) {
            return String.valueOf(str.charAt(0));
        }

        // DESC: Primary logic for all other string lengths
        String output = "";
        for (int i = 0; i < stringLength; i++) {

            // DESC: Ensure loops are not attempting to access any
            // ... out-of-bounds values
            // NOTE: This should, technically, only prevent the primary
            // ... logic from running at the start and the end of the
            // ... string so each loop will need its own check
            if (i - 1 == -1) {

                // DESC: Address single-value palindrome
                // NOTE: In theory, this should only ever update
                // ... once because the length will never be
                // ... greater than "1" after the first letter
                if (1 > output.length()) {
                    output = String.valueOf(str.charAt(i));
                }
            } else {

                // DESC: This is used to ensure a 'look-forward'
                // ... is possible, however ONLY the 'odd-check'
                // ... needs to 'look-forward'
                if (i + 1 == stringLength) {
                    shouldEvenLoopRun = true;
                    shouldOddLoopRun = false;
                }

                // DESC: The 'even-check' loop-logic
                // NOTE: The 'even-check' looks at the value prior
                // ... to the index AND the index itself
                int leftCursorEVEN = i - 1;
                int rightCursorEVEN = i;
                while (shouldEvenLoopRun == true) {

                    // DESC: Ensure cursors are not out-of-bounds
                    boolean isLeftOutOfBounds = (leftCursorEVEN == -1) ? true : false;
                    boolean isRightOutOfBounds = (rightCursorEVEN == stringLength) ? true : false;

                    // DESC: End loop if either cursor is out-of-bounds
                    if (isLeftOutOfBounds == true || isRightOutOfBounds == true) {
                        break;
                    }

                    // DESC: Check if each iteration is a palindrome
                    if (isPalindrome(leftCursorEVEN, rightCursorEVEN, str) == true) {

                        // DESC: Update `output` IF the substring length
                        // ... exceeds the `output` length
                        boolean isSubstringLongerThanOutput =
                                (((rightCursorEVEN - leftCursorEVEN) + 1) > output.length()) ? true : false;
                        if (isSubstringLongerThanOutput == true) {
                            output = str.substring(leftCursorEVEN, rightCursorEVEN + 1);
                        }

                        // DESC: Move cursors outward
                        leftCursorEVEN = leftCursorEVEN - 1;
                        rightCursorEVEN = rightCursorEVEN + 1;
                    } else {
                        break;
                    }
                }

                // DESC: The 'odd-check' loop-logic
                // NOTE: The 'odd-check' looks at the value prior
                // ... to the index AND the value after the index
                int leftCursorODD = i - 1;
                int rightCursorODD = i + 1;
                while(shouldOddLoopRun == true) {

                    // DESC: Ensure cursors are not out-of-bounds
                    boolean isLeftOutOfBounds = (leftCursorODD == -1) ? true : false;
                    boolean isRightOutOfBounds = (rightCursorODD == stringLength) ? true : false;

                    // DESC: End loop if either cursor is out-of-bounds
                    if (isLeftOutOfBounds == true || isRightOutOfBounds == true) {
                        break;
                    }

                    // DESC: Check if each iteration is a palindrome
                    if (isPalindrome(leftCursorODD, rightCursorODD, str) == true) {

                        // DESC: Update `output` IF the substring length
                        // ... exceeds the `output` length
                        boolean isSubstringLongerThanOutput =
                                (((rightCursorODD - leftCursorODD) + 1) > output.length()) ? true : false;
                        if (isSubstringLongerThanOutput == true) {
                            output = str.substring(leftCursorODD, rightCursorODD + 1);
                        }

                        // DESC: Move cursors outward
                        leftCursorODD = leftCursorODD - 1;
                        rightCursorODD = rightCursorODD + 1;
                    } else {
                        break;
                    }
                }
            }
        }

        return output;
    }
}