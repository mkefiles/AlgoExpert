package CIQ_Strings.Easy;

/**
 * Write a function that takes in a non-empty string and returns its run-length
 * encoding.
 *
 * From Wikipedia, "run-length encoding is a form of lossless data compression in
 * which runs of data are stored as a single data value and count, rather than as
 * the original run." For this problem, a run of data is any sequence of
 * consecutive, identical characters. So the run `"AAA"` would be
 * run-length-encoded as `"3A"`.
 *
 * To make things more complicated, however, the input string can contain all
 * sorts of special characters, including numbers. And since encoded data must be
 * decodable, this means that we can't naively run-length-encode long runs. For
 * example, the run `"AAAAAAAAAAAA"` (12 `A`s), can't naively be encoded as
 * `"12A"`, since this string can be decoded as either `"AAAAAAAAAAAA"` or
 * `"1AA"`. Thus, long runs (runs of 10 or more characters) should be encoded
 * in a split fashion; the aforementioned run should be encoded as `"9A3A"`.
 *
 * SAMPLE INPUT:
 * string = "AAAAAAAAAAAAABBCCCCDD"
 *
 * SAMPLE OUTPUT:
 * "9A4A2B4C2D"
 */

public class RunLengthEncoding {
    public static String encoder(char letter, int count) {
        // DESC: Initialize necessary variables
        String output = "";
        int remainder = 0;
        int countsOfNine = 0;

        // DESC: Return applicable encoding based on
        // ... the count
        if (count <= 9) {
            // NOTE: A `count` less-than or equal-to
            // ... "9" is in the format of #A
            output = output + count + letter;
            return output;
        } else {
            // NOTE: A `count` greater-than "9" needs
            // ... to determine how many divisions of
            // ... "9" AND determine the remainder then
            // ... append them to the output
            // NOTE: The remainder is not appended if it
            // ... is "0"
            countsOfNine = count / 9;
            remainder = count % 9;
            for (int i = 1; i <= countsOfNine; i++) {
                output = output + 9 + letter;
            }

            if (remainder != 0) {
                output = output + remainder + letter;
            }
        }
        return output;
    }

    public String runLengthEncoding(String string) {
        // DESC: Initialize necessary variables
        int numberOfCharacters = string.length();
        int cursorOneIndex = 0;
        int cursorTwoIndex = 1;
        boolean flag = true;
        String output = "";
        int differenceBetweenCursors = 0;

        // DESC: Edge-Case: Handle a length of "1"
        if (numberOfCharacters == 1) {
            return "1" + string.charAt(0);
        }

        // DESC: A quick-cheat for 'tricking' the
        // ... logic to work as anticipated
        // NOTE: I noticed that adding a random
        // ... character at the end of the String
        // ... outputs the correct run-length encoding
        // ... and the Space-Complexity won't change
        // ... from O(n) because it would be O(2n) where
        // ... the constant of "2" is dropped
        int lastInputCharacterDecVal = string.charAt(numberOfCharacters - 1);
        int revisedDecVal = lastInputCharacterDecVal - 1;
        String revisedInput = string + ((char) revisedDecVal);
        numberOfCharacters = revisedInput.length();

        // DESC: A double-cursor loop that checks the character
        // ... at p1 against the character at p2
        while (flag == true) {
            // DESC: Ensure that the cursor does not go out
            // ... of bounds for the array
            if (cursorTwoIndex == numberOfCharacters - 1) {
                flag = false;
            }

            // DESC: Check if the character at the index-values
            // ... are equal to each other
            if (revisedInput.charAt(cursorTwoIndex) == revisedInput.charAt(cursorOneIndex)) {
                // NOTE: Simply move the second cursor if the values
                // ... are the same
                cursorTwoIndex = cursorTwoIndex + 1;
            } else {
                // NOTE: Otherwise, get the difference, get the
                // ... encoded output, move the first cursor and
                // ... increment the second cursor
                differenceBetweenCursors = cursorTwoIndex - cursorOneIndex;
                output = output + encoder(revisedInput.charAt(cursorOneIndex), differenceBetweenCursors);
                cursorOneIndex = cursorTwoIndex;
                cursorTwoIndex = cursorTwoIndex + 1;
            }
        }
        return output;
    }
}
