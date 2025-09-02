package CIQ_Strings.Easy;

/**
 * Write a function that takes in a non-empty string and that returns a boolean
 * representing whether the string is a palindrome.
 *
 * A palindrome is defined as a string that's written the same forward and
 * backward. Note that single-character strings are palindromes.
 *
 * SAMPLE INPUT:
 * string = "abcdcba"
 *
 * SAMPLE OUTPUT:
 * true // it's written the same forward and backward
 */

public class PalindromeCheck {

    public static boolean isPalindrome(String str) {
        // DESC: Initialize necessary variables
        int numberOfCharacters = str.length();
        boolean isAPalindrome = true;
        int leftCursorIndex = 0;
        int rightCursorIndex = numberOfCharacters - 1;

        // DESC: Edge-Case: Any string with a length
        // ... of "1" is ALWAYS a palindrome
        if (numberOfCharacters == 1) {
            isAPalindrome = true;
            return isAPalindrome;
        }

        // DESC: Loop through String with a left-
        // ... and right-cursor
        // NOTE: The left- and right-cursors will
        // ... compare the char to ensure they are
        // ... equal to each other
        while (true) {
            if (leftCursorIndex > rightCursorIndex) {
                break;
            } else {
                if (str.charAt(leftCursorIndex) == str.charAt(rightCursorIndex)) {
                    leftCursorIndex = leftCursorIndex + 1;
                    rightCursorIndex = rightCursorIndex - 1;
                } else {
                    isAPalindrome = false;
                    return isAPalindrome;
                }
            }
        }

        // NOTE: The default value is `true` because
        // ... the logic is set up to modify it ONLY
        // ... when it is no longer `true`
        return isAPalindrome;
    }
}
