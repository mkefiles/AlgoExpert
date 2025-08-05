package Medium;

/**
 * Write a function that takes in a non-empty array of integers and returns an
 * array of the same length, where each element in the output array is equal to
 * the product of every other number in the input array.
 *
 * In other words, the value at `output[i]` is equal to the product of
 * every number in the input array other than `input[i]`.
 *
 * Note that you're expected to solve this problem without using division.
 *
 * SAMPLE INPUT:
 * array = [5, 1, 4, 2]
 *
 * SAMPLE OUTPUT:
 * [8, 40, 10, 20]
 * // 8 is equal to 1 x 4 x 2
 * // 40 is equal to 5 x 4 x 2
 * // 10 is equal to 5 x 1 x 2
 * // 20 is equal to 5 x 1 x 4
 */

public class ArrayOfProducts {
    public int[] arrayOfProducts(int[] array) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int[] outputArray = new int[arrayLength];
        boolean leftProductFlag = false;
        boolean rightProductFlag = false;

        // DESC: Loop and populate left-side 'products'
        for (int i = 0; i < arrayLength; i++) {
            // DESC: Create loop-scope `product` holder and index
            int product = 1;
            int leftProductIndex = i;

            // DESC: Update `outputArray` for all 'one' values
            outputArray[i] = 1;

            // DESC: Update flag
            leftProductFlag = false;

            // DESC: Disregard the first index-value
            // NOTE: This is calculating the product of all values
            // ... to the left of the current index and index of
            // ... zero does not have anything to the left
            while (leftProductFlag != true) {
                if (leftProductIndex == 0) {
                    // DESC: Kill the loop if index zeroes out
                    leftProductFlag = true;
                    break;
                } else if (leftProductIndex - 1 < 0) {
                    // DESC: Kill the loop if the 'search' goes into negative index
                    // NOTE: Personally, I think this is excessive and would never run
                    // ... however I got the code to run on the first attempt so I have
                    // ... decided to leave it
                    leftProductFlag = true;
                    break;
                } else {
                    // DESC: Get the product of the left value(s) then decrement
                    // ... the index to work towards left-most bounds
                    product = product * array[leftProductIndex - 1];
                    leftProductIndex = leftProductIndex - 1;
                }
            }

            // DESC: Update `outputArray` with new product
            outputArray[i] = product;
        }

        // DESC: Loop and populate right-side 'products'
        // ... then create the `outputArray`
        for (int i = arrayLength - 1; i >= 0; i--) {
            // DESC: Create loop-scope `product` holder and index
            int product = 1;
            int rightProductIndex = i;

            // DESC: Update flag
            rightProductFlag = false;

            // DESC: Disregard the last index-value
            // NOTE: This is calculating the product of all values
            // ... to the right of the current index and index of
            // ... arrayLength - 1 does not have anything to the right
            while (rightProductFlag != true) {
                if (rightProductIndex == arrayLength - 1) {
                    // DESC: Kill the loop if index is at end of array
                    rightProductFlag = true;
                    break;
                } else if (rightProductIndex + 1 > arrayLength - 1) {
                    // DESC: Kill the loop if the 'search' goes into out of length-index
                    // NOTE: Personally, I think this is excessive and would never run
                    // ... however I got the code to run on the first attempt so I have
                    // ... decided to leave it
                    rightProductFlag = true;
                    break;
                } else {
                    // DESC: Get the product of the right value(s) then increment
                    // ... the index to work towards right-most bounds
                    product = product * array[rightProductIndex + 1];
                    rightProductIndex = rightProductIndex + 1;
                }
            }

            // DESC: Update `outputArray` with new product (multipled by existing product)
            outputArray[i] = outputArray[i] * product;
        }

        return outputArray;
    }
}
