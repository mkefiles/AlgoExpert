package Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * You're given a list of integers `nums`. Write a function that returns a
 * boolean representing whether there exists a zero-sum subarray of `nums`.
 *
 * A zero-sum subarray is any subarray where all of the values add up to zero.
 * A subarray is any contiguous section of the array. For the purposes of this
 * problem, a subarray can be as small as one element and as long as the
 * original array.
 *
 * SAMPLE INPUT:
 * nums = [-5, -5, 2, 3, -2]
 *
 * SAMPLE OUTPUT:
 * True // The subarray [-5, 2, 3] has a sum of 0
 */

public class ZeroSumSubarray {
    public boolean zeroSumSubarray(int[] nums) {
        // DESC: Initialize necessary variables
        int numsLength = nums.length;
        Set<Integer> sums = new HashSet<Integer>();
        int currentSum = 0;

        // DESC: Add '0' to set (this will automatically)
        // ... end any iteration where the sum is zero
        sums.add(0);

        // DESC: Loop over input array
        for (int i = 0; i < numsLength; i++) {
            // DESC: Check if 'i' is zero
            // NOTE: Any single '0' qualifies as a zero-sum array
            if (nums[i] == 0) {
                return true;
            } else {
                currentSum = currentSum + nums[i];
                // DESC: Check if number exists in set
                // NOTE: A number that exists in a set suggests
                // ... that a zero-sum array exists because when
                // ... the sum of [0, x] equals the sum of [0, y]
                // ... then all values in-between did not fluctuate
                // ... the overall sum
                if (sums.contains(currentSum)) {
                    return true;
                } else {
                    sums.add(currentSum);
                }
            }
        }

        // DESC: A default return of 'false' suggests that no
        // ... 'true' was returned thus the looping ended and
        // ... no zero-sub array exists
        return false;
    }
}
