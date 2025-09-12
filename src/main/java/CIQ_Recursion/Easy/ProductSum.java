package CIQ_Recursion.Easy;

import java.util.List;

/**
 * Write a function that takes in a "special" array and returns its product sum.
 *
 * A "special" array is a non-empty array that contains either integers or other
 * "special" arrays. The product sum of a "special" array is the sum of its
 * elements, where "special" arrays inside it are summed themselves and then
 * multiplied by their level of depth.
 *
 * The depth of a "special" array is how far nested it is. For instance, the
 * depth of `[]` is `1`; the depth of the inner array in `[[]]` is `2`; the
 * depth of the innermost array in `[[[]]]` is `3`.
 *
 * Therefore, the product sum of `[x, y]` is `x + y`; the product sum of
 * `[x, [y, z]]` is `x + 2 * (y + z)`; the product sum of `[x, [y, [z]]]`
 * is `x + 2 * (y + 3z)`.
 *
 * SAMPLE INPUT:
 * array = [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
 *
 * SAMPLE OUTPUT:
 * 12 // calculated as: 5 + 2 + 2 * (7 - 1) + 3 + 2 * (6 + 3 * (-13 + 8) + 4)
 *
 * S/T COMPLEXITY:
 * O(n) time | O(d) space - where n is the total number of elements in the array,
 * including sub-elements, and d is the greatest depth of "special" arrays in the array
 *
 * ADDITIONAL NOTES:
 * I am keeping the `main()` method because I find it valuable to see what
 * it takes to create a List of Objects that either contains an Integer OR a List of
 * Objects.
 */

public class ProductSum {

    /**
     * Loop through each Object in the List of Objects, if it is
     * an Integer, then add the value to the `sum`. If it is an Object,
     * then it will recursively loop through all values within that
     * Object-List and increment the multiplier for that instance.
     * At the end of that instance, the sum is multipled by the
     * multiplier.
     * @param arr an ImmutableCollection$List (contains Integers and ArrayLists)
     * @param multiplier this increases by 1 for every sub-ArrayList located
     * @return the sum (multiplied) of each Integer
     */
    public static int sumLogic(List<Object> arr, int multiplier) {
        int sum = 0;
        for (Object obj : arr) {
            if (obj instanceof List) {
                @SuppressWarnings("unchecked") List<Object> subList = (List<Object>) obj;
                sum = sum + sumLogic(subList, multiplier + 1);
            } else {
                sum = sum + (Integer) obj;
            }
        }
        return sum * multiplier;
    }

    /**
     * This method is the method provided by AlgoExpert. The
     * tutorial in their video, using Python, has a `multiplier`
     * parameter with a default value of "1". If I add that variable
     * without the default value (due to Java not supporting that)
     * then the AlgoExpert test environment errors out. For that reason
     * I have converted this function into more of a 'helper' function
     * that passes the `array` through to another custom method
     * that DOES include the multiplier parameter
     * @param array an ImmutableCollection$List (contains Integers and ArrayLists)
     * @return the sum (multiplied) of each Integer
     */
    public static int productSum(List<Object> array) {
        System.out.println(array.getClass());
        int result = sumLogic(array, 1);
        return result;
    }

    public static void main(String[] args) {
        // TEST 01:
        // Input: [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
        // Exp. Output: 12
        List<Object> test01Input = List.of(
                5, 2,
                List.of(7, -1),
                3,
                List.of(6, List.of(-13, 8), 4)
        );
        int test01Output = productSum(test01Input);
        System.out.println("Sum of all integers: " + test01Output);
    }
}
