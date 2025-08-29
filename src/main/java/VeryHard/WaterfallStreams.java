package VeryHard;

/**
 * You're given a two-dimensional array that represents the structure of an
 * indoor waterfall and a positive integer that represents the column that the
 * waterfall's water source will start at. More specifically, the water source
 * will start directly above the structure and will flow downwards.
 *
 * Each row in the array contains `0`s and `1`s, where a `0` represents a free
 * space and a `1` represents a block that water can't pass through. You can
 * imagine that the last row of the array contains buckets that the water will
 * eventually flow into; thus, the last row of the array will always contain only
 * `0`s. You can also imagine that there are walls on both sides of the structure,
 * meaning that water will never leave the structure; it will either be trapped
 * against a wall or flow into one of the buckets in the last row.
 *
 * As water flows downwards, if it hits a block, it splits evenly to the left and
 * right-hand side of that block. In other words, 50% of the water flows left and
 * 50% of it flows right. If a water stream is unable to flow to the left or to
 * the right (because of a block or a wall), the water stream in question becomes
 * trapped and can no longer continue to flow in that direction; it effectively
 * gets stuck in the structure and can no longer flow downwards, meaning that 50%
 * of the previous water stream is forever lost.
 *
 * Lastly, the input array will always contain at least two rows and one column,
 * and the space directly below the water source (in the first row of the array)
 * will always be empty, allowing the water to start flowing downwards.
 *
 * Write a function that returns the percentage of water inside each of the
 * bottom buckets after the water has flowed through the entire structure.
 *
 * SAMPLE INPUT:
 * array = [
 *     [0, 0, 0, 0, 0, 0, 0],
 *     [1, 0, 0, 0, 0, 0, 0],
 *     [0, 0, 1, 1, 1, 0, 0],
 *     [0, 0, 0, 0, 0, 0, 0],
 *     [1, 1, 1, 0, 0, 1, 0],
 *     [0, 0, 0, 0, 0, 0, 1],
 *     [0, 0, 0, 0, 0, 0, 0],
 * ]
 * source = 3
 *
 * SAMPLE OUTPUT:
 * [0, 0, 0, 25, 25, 0, 0]
 * // The water will flow as follows:
 * // [
 * //   [0, 0, 0, ., 0, 0, 0],
 * //   [1, ., ., ., ., ., 0],
 * //   [0, ., 1, 1, 1, ., 0],
 * //   [., ., ., ., ., ., .],
 * //   [1, 1, 1, ., ., 1, 0],
 * //   [0, 0, 0, ., ., 0, 1],
 * //   [0, 0, 0, ., ., 0, 0],
 * // ]
 *
 */

/**
 * ADDITIONAL NOTES:
 * - The moment a free-block, in the current row, is located... move the
 * ... `rowAbove` down (i.e., proceed lower)
 * - The input array does not need to be modified to get this to work
 */

public class WaterfallStreams {
    public double[] waterfallStreams(double[][] array, int source) {
        // DESC: Initialize necessary variables
        double[] rowAbove = array[0];
        rowAbove[source] = -1.00;
        int arrayHeight = array.length;
        int arrayWidth = array[0].length;

        // DESC: Address edge-cases with one column
        // ... upfront (to avoid unnecessary computations)
        // NOTE: In the event that a 'block' (i.e., '1')
        // ... is found, no water will get through otherwise
        // ... all water will get through
        if (arrayWidth == 1) {
            for (int rowIndex = 1; rowIndex < arrayHeight; rowIndex++) {
                if (array[rowIndex][0] == 1) {
                    return new double[]{0.0};
                }
            }
            return new double[]{100.00};
        }

        // DESC: Loop through the rows (main logic starts here)
        // NOTE: This starts at '1' because the 0th row is always
        // ... the source and provides no significant value/info.
        for (int rowIndex = 1; rowIndex < arrayHeight; rowIndex++) {

            // DESC: Initialize the `currentRow` to the current
            // ... `rowIndex` for every iteration (i.e., move it lower)
            double[] currentRow = array[rowIndex];

            // DESC: Loop through the values in each column (from
            // ... the perspective of the `currentRow`)
            for (int columnIndex = 0; columnIndex < arrayWidth; columnIndex++) {
                double valueAbove = rowAbove[columnIndex];

                // DESC: Check if the value above has water
                // NOTE: The value of '-1' (and any lower NEGATIVE
                // ... value representing water is coder-discretion)
                // ... so this would change pending on what value was
                // ... set at the `source` location near the top
                boolean hasWaterAbove = valueAbove < 0;

                // DESC: Check if the current value in the `currentRow`
                // ... has a block
                // NOTE: Free-space is reflected by a '0' and 'blocks'
                // ... are reflected by a '1'
                boolean hasBlock = currentRow[columnIndex] == 1;

                // DESC: Check if the value above is water
                // NOTE: If the value above is NOT water, that means
                // ... that it is a block so there is no reason to do
                // ... anything as water cannot travel here so skip
                // ... to the next array-block
                if (hasWaterAbove == false) {
                    continue;
                }

                // DESC: Check if the current value in the `currentRow`
                // ... is a free-space
                // NOTE: If so, then set the value because that means water
                // ... can travel freely here
                if (hasBlock == false) {
                    currentRow[columnIndex] = currentRow[columnIndex] + valueAbove;
                    continue;
                }

                // DESC: Split the water capacity here (because the
                // ... subsequent logic indicates that a 'floor' for
                // ... the water has been encountered and the water
                // ... will travel to the left and right now)
                double splitWater = valueAbove / 2.00;

                // DESC: Iterate the right-cursor
                // NOTE: The initializer of the loop auto-adds '1'
                // ... upfront to ensure that the logic beneath it is
                // ... not attempting an Out-of-Bounds scenario as
                // ... it is incremented PRIOR TO the remaining logic
                int rightCursorIndex = columnIndex;
                while (rightCursorIndex + 1 < arrayWidth) {
                    rightCursorIndex = rightCursorIndex + 1;

                    // DESC: If a block is encountered, cease looking
                    // ... to the right
                    if (rowAbove[rightCursorIndex] == 1) {
                        break;
                    }

                    // DESC: If a free-space, in the `currentRow` is
                    // ... located, then update the value for the allowed
                    // ... water capacity as it can travel through here
                    // NOTE: Using the `+=` here ensures that, in the event
                    // ... where there are multiple streams that re-join
                    // ... the overall value is used not overwritten
                    if (currentRow[rightCursorIndex] != 1) {
                        currentRow[rightCursorIndex] = currentRow[rightCursorIndex] + splitWater;
                        break;
                    }
                }

                // DESC: Iterate the left-cursor
                // NOTE: The initializer of the loop auto-subtracts '1'
                // ... upfront to ensure that the logic beneath it is
                // ... not attempting an Out-of-Bounds scenario as
                // ... it is decremented PRIOR TO the remaining logic
                int leftCursorIndex = columnIndex;
                while (leftCursorIndex - 1 >= 0) {
                    leftCursorIndex = leftCursorIndex - 1;

                    // DESC: If a block is encountered, cease looking
                    // ... to the right
                    if (rowAbove[leftCursorIndex] == 1) {
                        break;
                    }

                    // DESC: If a free-space, in the `currentRow` is
                    // ... located, then update the value for the allowed
                    // ... water capacity as it can travel through here
                    // NOTE: Using the `+=` here ensures that, in the event
                    // ... where there are multiple streams that re-join
                    // ... the overall value is used not overwritten
                    if (currentRow[leftCursorIndex] != 1) {
                        currentRow[leftCursorIndex] = currentRow[leftCursorIndex] + splitWater;
                        break;
                    }
                }
            }

            // DESC: Move the `rowAbove` down by one (the `currentRow`
            // ... is updated (and reset) at the top of the loop)
            rowAbove = currentRow;
        }

        // DESC: The LAST value in the `rowAbove` value will be what
        // ... is used to determine the output water-flow
        // NOTE: Multiply by NEGATIVE 100 to flip the NEGATIVE water
        // ... value back to a POSITIVE value (then to the necessary
        // ... percentage output). The NEGATIVE is not necessary if a
        // ... NEGATIVE value is not used to represent water upfront
        double[] finalPercentages = new double[arrayWidth];
        for (int i = 0; i < arrayWidth; i++) {
            finalPercentages[i] = rowAbove[i] * -100;
        }

        return finalPercentages;
    }
}
