package CIQ_Strings.Medium;

import java.util.*;

/**
 * Write a function that takes in an array of strings and groups anagrams together.
 *
 * Anagrams are strings made up of exactly the same letters, where order doesn't
 * matter. For example, `"cinema"` and `"iceman"` are anagrams; similarly, `"foo"`
 * and `"ofo"` are anagrams.
 *
 * Your function should return a list of anagram groups in no particular order.
 *
 * SAMPLE INPUT:
 * words  = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]
 *
 * SAMPLE OUTPUT:
 * [["yo", "oy"], ["flop", "olfp"], ["act", "tac", "cat"], ["foo"]]
 */

public class GroupAnagrams {

    /**
     * Using the built-in `.sort()` method, this small function
     * takes a String input, creates an array of chars from it,
     * sorts the array and concatenates all chars back to a String
     * @param input - unsorted String
     * @return sorted String
     */
    public static String stringSort(String input) {
        // DESC: Create an array of characters from the
        // ... input array
        char[] arrayOfStringLetters = input.toCharArray();

        // DESC: Sort the array in-place in O(n log(n) T.C.
        Arrays.sort(arrayOfStringLetters);

        // DESC: Reassemble the characters as a sorted String
        return String.valueOf(arrayOfStringLetters);
    }

    /**
     * Given an ArrayList of Strings, determine which strings
     * are anagrams and return grouped-anagrams together.
     *
     * NOTE: This will return words that DON'T have matches in
     * their own group because that is what the test-cases suggest.
     *
     * @param words - an ArrayList NOT a List
     * @return a List of String-Lists
     */
    public static List<List<String>> groupAnagrams(List<String> words) {
        // DESC: Initialize necessary variables
        List<List<String>> output = new ArrayList<List<String>>();
        Map<String, ArrayList<String>> groupedAnagrams = new HashMap<String, ArrayList<String>>();

        // DESC: Iterate through `words`, sort each word, check
        // ... if the sorted version exists in the K-V D.S. and
        // ... handle accordingly
        // NOTE: The 'key' is the SORTED version, yet the UNSORTED
        // ... version is added as the 'value'
        for (String word : words) {
            String sortedWord = stringSort(word);
            if (groupedAnagrams.containsKey(sortedWord) == true) {
                groupedAnagrams.get(sortedWord).add(word);
            } else {
                groupedAnagrams.put(sortedWord, new ArrayList<String>());
                groupedAnagrams.get(sortedWord).add(word);
            }
        }

        // DESC: Iterate through K-V D.S. and output all Array
        // ... Lists to the `output` List of String-Lists
        for (String k : groupedAnagrams.keySet()) {
            output.add(groupedAnagrams.get(k));
        }

        return output;
    }
}
