package Hard;

/**
 * Imagine that you're a teacher who's just graded the final exam in a class. You
 * have a list of student scores on the final exam in a particular order (not
 * necessarily sorted), and you want to reward your students. You decide to do so
 * fairly by giving them arbitrary rewards following two rules:
 *
 * 1. All students must receive at least one reward
 * 2. Any given student must receive strictly more rewards than an adjacent
 * student (a student immediately to the left or to the right) with a lower
 * score and must receive strictly fewer rewards than an adjacent student with
 * a higher score
 *
 * Write a function that takes in a list of scores and returns the minimum number
 * of rewards that you must give out to students to satisfy the two rules.
 *
 * You can assume that all students have different scores; in other words, the
 * scores are all unique.
 *
 * SAMPLE INPUT:
 * scores = [8, 4, 2, 1, 3, 6, 7, 9, 5]
 *
 * SAMPLE OUTPUT:
 * 25 // You would give out the following rewards: [4, 3, 2, 1, 2, 3, 4, 5, 1]
 */

public class MinRewards {
    public static int minRewards(int[] scores) {
        // DESC: Initialize necessary variables
        int arrayLength = scores.length;
        int[] rewardsCounter = new int[arrayLength];
        int minRewards = 0;

        // DESC: Loop through all 'scores' and give
        // ... student a participation-trophy reward
        // ... of "1"
        for (int cI = 0; cI < arrayLength; cI++) {
            rewardsCounter[cI] = 1;
        }

        // DESC: Loop through all 'scores' and give the
        // ... rewards using the algorithm of:
        // ... cV = pV + 1 where cV is 'currentValue' and
        // ... pV is 'priorValue'
        // NOTE: Start at index of "1" because there is no
        // ... pV for index of 0
        // NOTE: The algorithm of cV = pV + 1, in a sense,
        // ... negates the need for the first loop IF I were
        // ... to have a simple else that assigned the value
        // ... to "1", however that explicit loop makes this
        // ... more understandable and does not alter T.C.
        for (int cI = 1; cI < arrayLength; cI++) {
            if (scores[cI] > scores[cI - 1]) {
                rewardsCounter[cI] = rewardsCounter[cI - 1] + 1;
            } else {
                continue;
            }
        }

        // DESC: Loop through all 'scores', in reverse order,
        // ... and give the rewards using the algorithm of:
        // ... MAX of (cV = pV + 1) or (cV)
        // NOTE: Start at an index that is second from the last
        // ... because there is no index for the arrayLength number
        for (int cI = arrayLength - 2; cI >= 0; cI--) {
            if (scores[cI] > scores[cI + 1]) {
                rewardsCounter[cI] = Math.max(rewardsCounter[cI], rewardsCounter[cI + 1] + 1);
            } else {
                continue;
            }
        }

        // DESC: Loop through all rewards and sum them
        // NOTE: This could have been handled in the prior loop,
        // ... however it does not alter the T.C. (because the
        // ... constant is dropped) so I use an additional loop
        // ... to make the code more understandable
        for (int cI = 0; cI < arrayLength; cI++) {
            minRewards = minRewards + rewardsCounter[cI];
        }

        return minRewards;
    }
}