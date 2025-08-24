package VeryHard;

/**
 * You're looking to move into a new apartment on specific street, and you're
 * given a list of contiguous blocks on that street where each block contains an
 * apartment that you could move into.
 *
 * You also have a list of requirements: a list of buildings that are important
 * to you. For instance, you might value having a school and a gym near your
 * apartment. The list of blocks that you have contains information at every
 * block about all of the buildings that are present and absent at the block in
 * question. For instance, for every block, you might know whether a school, a
 * pool, an office, and a gym are present.
 *
 * In order to optimize your life, you want to pick an apartment block such that
 * you minimize the farthest distance you'd have to walk from your apartment to
 * reach any of your required buildings.
 *
 * Write a function that takes in a list of contiguous blocks on a specific
 * street and a list of your required buildings and that returns the location
 * (the index) of the block that's most optimal for you.
 *
 * If there are multiple most optimal blocks, your function can return the index
 * of any one of them.
 *
 * SAMPLE INPUT:
 * blocks = [
 *  {
 *      "gym": false,
 *      "school": true,
 *      "store": false,
 *  },
 *  {
 *      "gym": true,
 *      "school": false,
 *      "store": false,
 *  },
 *  {
 *      "gym": true,
 *      "school": true,
 *      "store": false,
 *  },
 *  {
 *      "gym": false,
 *      "school": true,
 *      "store": false,
 *  },
 *  {
 *      "gym": false,
 *      "school": true,
 *      "store": true,
 *  },
 * ]
 *
 * reqs = ["gym", "school", "store"]
 *
 * SAMPLE OUTPUT:
 * 3    // at index 3, the farthest you'd have to walk to reach a gym, a school,
 *      // ... or a store is 1 block; at any other index, you'd have to walk farther
 */

import java.util.List;
import java.util.Map;

public class ApartmentHunting {
    public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        // DESC: Initialize necessary variables
        int numberOfRequests = reqs.length;
        int numberOfBlocks = blocks.size();
        int blockLimitForArray = numberOfBlocks - 1;
        int[][] distanceToRequest = new int[numberOfRequests][numberOfBlocks];
        int[] distanceResults = new int[numberOfBlocks];
        int optimalDistance = Integer.MAX_VALUE;
        int optimalDistanceIndex = 0;

        // DESC: Loop (ASC; left-to-right) through all 'requests' and,
        // ... for each request, check if the 'block' contains that
        // ... specific request either AT the current-index [i] OR
        // ... BEFORE it [i - 1]
        // NOTE: Lead with the `reqs` array BECAUSE the problem needs
        // ... the ability to access the key-value pairs of `blocks`
        // ... by the key-name
        for (int cI1 = 0; cI1 < numberOfRequests; cI1++) {
            for (int cI2 = 0; cI2 < numberOfBlocks; cI2++) {
                // DESC: Check if the 0th index for cI1 because the logic
                // ... cannot look backwards without an Array Index Out of
                // ... Bounds error being thrown
                if (cI2 == 0) {
                    if (blocks.get(cI2).get(reqs[cI1]) == true) {
                        // DESC: Check if current value is in the CURRENT block
                        distanceToRequest[cI1][cI2] = 0;
                    } else {
                        // DESC: Assign value of "-1" meaning that it was NOT
                        // ... found in the CURRENT block and the PRIOR block
                        // ... could not be checked due to Array Out of Bounds
                        // NOTE: This should only every run once because we are
                        // ... only at the left-start once
                        distanceToRequest[cI1][cI2] = -1;
                    }
                } else {
                    if (blocks.get(cI2).get(reqs[cI1]) == true) {
                        // DESC: Check if current value is in the CURRENT block
                        distanceToRequest[cI1][cI2] = 0;
                    } else if (blocks.get(cI2 - 1).get(reqs[cI1]) == true) {
                        // DESC: Check if current value is in the PRIOR block
                        distanceToRequest[cI1][cI2] = 1;
                    } else if ((distanceToRequest[cI1][cI2 - 1] != 0) && (distanceToRequest[cI1][cI2 - 1] != -1)) {
                        // DESC: Check if PRIOR value has a value that is NOT
                        // ... equal to "0" or "-1" and update this with that
                        // ... value + 1
                        // NOTE: This would trigger, in theory, when the PRIOR
                        // ... block AND the CURRENT block do not contain the
                        // ... desired value as it would determine the distance
                        // ... to it based on the prior iterations determination
                        distanceToRequest[cI1][cI2] = 1 + distanceToRequest[cI1][cI2 - 1];
                    } else {
                        // DESC: Unsuccessful at locating solution so this will
                        // ... tell us where to look when an edge-case/error
                        // ... is encountered
                        distanceToRequest[cI1][cI2] = -1;
                    }
                }
            }
        }

        // DESC: Loop through all 'requests' in the same order as
        // ... above, however loop through the `blocks` in DESC (right
        // ... to-left order) where the code checks if the subsequent
        // ... 'block' [i + 1] contains the specific request
        // NOTE: At this point, the CURRENT block has already been checked
        // ... so no duplicate work needs to be done, however it is necessary
        // ... to check if the current value is "0" (first -- don't update it)
        // ... OR if the current value is less-than the proposed value PRIOR
        // ... TO updating it
        for (int cI1 = 0; cI1 < numberOfRequests; cI1++) {
            for (int cI2 = blockLimitForArray; cI2 > -1; cI2--) {
                // DESC: Check if the last index for cI2 because the logic
                // ... cannot look any further without an Array Index Out
                // ... of Bounds error being thrown AND no additional work
                // ... needs to be done on this one so it can be skipped
                // NOTE: In theory, the loop could simply start at the next
                // ... value over... neither here nor there
                if (cI2 == blockLimitForArray) {
                    continue;
                } else {
                    if (distanceToRequest[cI1][cI2] == 0) {
                        // DESC: Value at CURRENT place is "0", which suggests
                        // ... that the desired 'request' is AT this slot (cannot
                        // ... get any closer than "0" :))
                        continue;
                    } else if (distanceToRequest[cI1][cI2] == -1) {
                        // DESC: A value of "-1" suggests that the first
                        // ... iteration did not find the value so it must
                        // ... be replaced with the right-adjacent distance
                        // ... plus one
                        // NOTE: The entire "-1" default that was created is
                        // ... entirely under the assumption that, at least,
                        // ... one plot in `blocks` will contain the current
                        // ... iterations' request (i.e., there should be no
                        // ... scenario where a 'request' is not found whatsoever)
                        distanceToRequest[cI1][cI2] = distanceToRequest[cI1][cI2 + 1] + 1;
                    } else {
                        // DESC: The value is not "0" and not "-1" meaning that
                        // ... the initial left-to-right loop found a viable count
                        // ... and this is just determining if the right-to-left
                        // ... count is less-than what was previously found
                        distanceToRequest[cI1][cI2] = Math.min(distanceToRequest[cI1][cI2], distanceToRequest[cI1][cI2 + 1] + 1);
                    }
                }
            }
        }

        // DESC: Loop through the `distanceToRequest` to determine the
        // ... index of all `reqs` and for each 'request' at a specified
        // ... index, get the maximum value
        // NOTE: The maximum value will tell us the that apartment locations
        // ... longest walk to get to any of the desired 'request' after
        // ... which point we get the lowest value because that means that
        // ... apartment location is within the most opportune (shortest)
        // ... distance to all desired requests
        for (int cI1 = 0; cI1 < numberOfRequests; cI1++) {
            for (int cI2 = 0; cI2 < numberOfBlocks; cI2++) {
                distanceResults[cI2] = Math.max(distanceResults[cI2], distanceToRequest[cI1][cI2]);
            }
        }

        // DESC: Determine the lowest overall value then get the
        // ... respective index value
        for (int cI = 0; cI < numberOfBlocks; cI++) {
            if (distanceResults[cI] < optimalDistance) {
                optimalDistance = distanceResults[cI];
                optimalDistanceIndex = cI;
            }
        }

        return optimalDistanceIndex;
    }
}
