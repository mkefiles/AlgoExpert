package CIQ_Recursion.Easy;

/**
 * IMPORTANT CALLOUT:
 * Even though this "OPTIMAL SOLUTION" (or rather the challenge itself) is under the
 * RECURSION category (my directory structure lines up with the categorization used on
 * AlgoExpert), the MOST OPTIMAL solution, provided by the trainer at AlgoExpert, does
 * NOT use recursion whatsoever... it uses a While Loop. UGH.
 *
 * ADDITIONAL NOTE:
 * I, initially, tried getting the same logic to work in a recursive fashion and
 * succeeded IFF it was only intended to do one test-case per Java-file execution.
 * Reason for that, is I used `static` class-level variables for the "base" values,
 * however it seems as if the AlgoExpert test-environment runs all of the test-cases
 * in the same runtime meaning that the class-level values were retaining the value
 * of test case "n" when it got to test case "n+1" and that was having unexpected
 * output. I, personally, am not sure how else to have "base" values when used in a
 * recursive fashion. I tried using a second function (that ran recursively) and passing
 * applicable variables to it from the first function (that way I could control what was
 * passed in lieu of being locked into what the AlgoExpert test-environment passed on
 * every invocation), however the output was still misbehaving. That is when I decided to
 * watch the coded solution and realized that they provided three solutions (with the third
 * being the target-solution due to T-S Complexity efficiency) and the third, most optimal,
 * solution was also the one that they DID NOT use recursion on.
 */

/**
 * Fiboanacci - The first two characters, "0" and "1", are given and every subsequent number
 * ... is the summation of the prior two numbers
 *
 * The Fibonacci sequence is defined as follows: the first number of the sequence
 * is `0`, the second number is `1`, and the nth number is the sum of the (n - 1)th
 * and (n - 2)th numbers. Write a function that takes in an integer `n` and returns
 * the nth Fibonacci number.
 *
 * Important note: the Fibonacci sequence is often defined with its first two
 * numbers as `F0 = 0` and `F1 = 1`. For the purpose of this question, the first
 * Fibonacci number is `F0`; therefore, `getNthFib(1)` is equal to `F0`, `getNthFib(2)`
 * is equal to `F1`, etc..
 *
 * SAMPLE INPUT:
 * n = 6
 *
 * SAMPLE OUTPUT:
 * 5 // 0, 1, 1, 2, 3, 5
 *
 * TIME COMPLEXITY:
 * O(n) time | O(1) space - where n is the input number
 */

public class NthFibonacci {

    public static int getNthFib(int n) {
        // DESC: Handle the "base-cases" where 'n' is "1" or "2"
        // NOTE: Using the array was my creative way of handling
        // ... two potential "base-case" return types without needing
        // ... a separate branch for each
        if (n == 1 || n == 2) {
            int[] options = new int[] {0, 1};
            return options[n - 1];
        }

        // DESC: Initialize base variables for recursive function
        // NOTE: The following explanations for each variable
        // - `counter` starts at "3" because "1" and "2" are handled with
        // ... the first two values, in Fibonacci, always being "0" and "1"
        // - `lastTwoValues` starts as "0" and "1" given the explanation above
        // - `nextValue` starts as "0" and "1" given the explanation above
        int counter = 3;
        int[] lastTwoValues = new int[] {0, 1};

        while (counter <= n) {
            int nextValue = lastTwoValues[0] + lastTwoValues[1];
            lastTwoValues[0] = lastTwoValues[1];
            lastTwoValues[1] = nextValue;
            counter = counter + 1;
        }

        return lastTwoValues[1];
    }
}
