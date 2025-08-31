package CIQ_Arrays.Medium;

import java.util.Arrays;

/**
 * You're hosting an event at a food festival and want to showcase the best
 * possible pairing of two dishes from the festival that complement each
 * other's flavor profile.
 *
 * Each dish has a flavor profile represented by an integer. A negative integer
 * means a dish is sweet, while a positive integer means a dish is savory. The
 * absolute value of that integer represents the intensity of that flavor. For
 * example, a flavor profile of -3 is slightly sweet, one of -10 is extremely
 * sweet, one of 2 is mildly savory, and one of 8 is significantly savory.
 *
 * You're given an array of these dishes and a target combined flavor profile.
 * Write a function that returns the best possible pairing of two dishes (the
 * pairing with a total flavor profile that's closest to the target one). Note
 * that this pairing must include one sweet and one savory dish. You're also
 * concerned about the dish being too savory, so your pairing should never be
 * more savory than the target flavor profile.
 *
 * All dishes will have a positive or negative flavor profile; there are no
 * dishes with a 0 value. For simplicity, you can assume that there will be at
 * most one best solution. If there isn't a valid solution, your function
 * should return `[0, 0]`. The returned array should be sorted,
 * meaning the sweet dish should always come first.
 *
 * SAMPLE INPUT:
 * dishes = [-3, -5, 1, 7]
 * target = 8
 *
 * SAMPLE OUTPUT:
 * [-3, 7] // The combined profile of 4 is closest without going over
 */

public class SweetAndSavory {
    public int[] sweetAndSavory(int[] dishes, int target) {
        // DESC: Initialize necessary variables
        int numberOfDishes = dishes.length;
        int[] outputArray = {0,0};
        int numberOfSweetDishes = 0;
        int numberOfSavoryDishes = 0;
        int sweetDishesIndex = 0;
        int savoryDishesIndex = 0;

        // DESC: Kick out any dish-assortments with a length
        // ... less-than two
        // NOTE: An assortment with less-than two dishes cannot
        // ... possibly contain both a negative and a positive value
        // ... therefore it is 'invalid' input
        if (numberOfDishes < 2) {
            return outputArray;
        }

        // DESC: Sort the initial array
        Arrays.sort(dishes);

        // DESC: Determine number of negative and positive values
        for (int i = 0; i < numberOfDishes; i++) {
            if (dishes[i] < 0) {
                numberOfSweetDishes = numberOfSweetDishes + 1;
            } else {
                numberOfSavoryDishes = numberOfSavoryDishes + 1;
            }
        }

        // DESC: Create the separate arrays
        int[] sweetDishes = new int[numberOfSweetDishes];
        int counterX = numberOfSweetDishes - 1;
        int[] savoryDishes = new int[numberOfSavoryDishes];
        int counterY = 0;

        // DESC: Kick out any dish-assortments that do not
        // ... contain a negative AND a positive number
        // NOTE: A 'valid' dish-pairing requires a 'sweet' dish
        // ... (i.e., a negative value) AND a 'savory' dish
        // ... (i.e., a positive value)
        if (numberOfSweetDishes == 0 || numberOfSavoryDishes == 0) {
            return outputArray;
        }

        // DESC: Populate separate arrays with the `sweetDishes`
        // ... array in DESC order and the `savoryDishes` in
        // ... ASC order
        for (int i = 0; i < numberOfDishes; i++) {
            if (dishes[i] < 0) {
                // NOTE: Negative numbers must be updated from the back
                // ... of the array because this array is sorted in ASC
                sweetDishes[counterX] = dishes[i];
                counterX = counterX - 1;
            } else {
                // NOTE: Positive numbers can be added in regular order
                // ... because they are already sorted in ASC
                savoryDishes[counterY] = dishes[i];
                counterY = counterY + 1;
            }
        }

        // DESC: Loop through arrays to determine optimal output
        while (true) {
            if ((sweetDishes[sweetDishesIndex] + savoryDishes[savoryDishesIndex]) < target) {

                if (outputArray[0] == 0 || outputArray[1] == 0) {
                    // DESC: Update the `outputArray` if it is set at [0, 0]
                    // NOTE: This should only run once and is just intended
                    // ... to update the `outputArray` if it is at its
                    // ... initialized/default value
                    outputArray[0] = sweetDishes[sweetDishesIndex];
                    outputArray[1] = savoryDishes[savoryDishesIndex];
                }
                else if ((outputArray[0] + outputArray[1]) < (sweetDishes[sweetDishesIndex] + savoryDishes[savoryDishesIndex])) {
                    // DESC: Update the `outputArray` if its current sum,
                    // ... which would be the value from the prior iteration,
                    // ... is less than the proposed sum (i.e., the current
                    // ... iteration)
                    outputArray[0] = sweetDishes[sweetDishesIndex];
                    outputArray[1] = savoryDishes[savoryDishesIndex];
                }

                // DESC: Increase the 'savory' index
                // NOTE: If the 'sweet' index were to increase, then
                // ... it would take the sum further away not closer
                if (savoryDishesIndex + 1 == numberOfSavoryDishes) {
                    // NOTE: Assuming the value is beneath the `target`
                    // ... if all values of the 'savory' dishes have
                    // ... been exhausted, then altering the 'sweet'
                    // ... dishes will only take us further away from
                    // ... the `target` value
                    return outputArray;
                } else {
                    savoryDishesIndex = savoryDishesIndex + 1;
                }
            } else if ((sweetDishes[sweetDishesIndex] + savoryDishes[savoryDishesIndex]) == target) {
                // NOTE: In the event that we have an exact-match to
                // ... the `target`, we cannot get any better so we
                // ... should stop checking for other optimal values
                outputArray[0] = sweetDishes[sweetDishesIndex];
                outputArray[1] = savoryDishes[savoryDishesIndex];
                return outputArray;
            } else {
                // NOTE: This runs ONLY when the 'sweet' and 'savory'
                // ... exceeds the `target`, in which case we need to
                // ... bring the value down
                if (sweetDishesIndex + 1 == numberOfSweetDishes) {
                    // NOTE: Assuming the value is over the `target`
                    // ... if all values of the 'sweet' dishes have
                    // ... been exhausted, then altering the 'savory'
                    // ... dishes will only take us further above the
                    // ... `target` value
                    return outputArray;
                } else {
                    sweetDishesIndex = sweetDishesIndex + 1;
                }
            }
        }
    }
}
