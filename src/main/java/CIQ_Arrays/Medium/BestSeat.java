package CIQ_Arrays.Medium;

/**
 * You walk into a theatre you're about to see a show in. The usher within the
 * theatre walks you to your row and mentions you're allowed to sit anywhere
 * within the given row. Naturally you'd like to sit in the seat that gives you
 * the most space. You also would prefer this space to be evenly distributed on
 * either side of you (e.g. if there are three empty seats in a row, you would
 * prefer to sit in the middle of those three seats).
 *
 * Given the theatre row represented as an integer array, return
 * the seat index of where you should sit. Ones represent occupied seats and zeroes
 * represent empty seats.
 *
 * You may assume that someone is always sitting in the
 * first and last seat of the row. Whenever there are two equally good seats,
 * you should sit in the seat with the lower index. If there is no seat to sit
 * in, return -1. The given array will always have a length of at least one
 * and contain only ones and zeroes.
 *
 * SAMPLE INPUT:
 * seats = [1, 0, 1, 0, 0, 0, 1]
 *
 * SAMPLE OUTPUT:
 * 4
 */

public class BestSeat {

    public int bestSeat(int[] seats) {
        // DESC: Initialize necessary variables
        int arrayLength = seats.length;
        int maxIndex = arrayLength - 1;
        int bestSeatIndex = -1;
        int maxSpace = 0;
        int leftCursorIndex = 0;
        int rightCursorIndex = 1;
        boolean flag = true;

        // DESC: Get rid of one or fewer seats
        if (arrayLength <= 1) {
            return -1;
        } else {
            // DESC: Loop through array with two cursors
            // NOTE: Use the following equations:
            // ... Best Seat (Index) = (R + L) / 2
            // ... Max Space = R - L - 1
            while (flag) {
                // DESC: Ensure that right-cursor is not going
                // ... out of bounds
                // NOTE: Based on the challenge prompt, the seat(s) at
                // ... the beginning AND end of every array will ALWAYS
                // ... be occupied, which means that it could always be
                // ... a stopping point on the right-side of the preferred
                // ... seating
                if (rightCursorIndex == maxIndex + 1) {
                    break;
                } else {
                    if (seats[rightCursorIndex] == 1) {
                        // DESC: Seat is occupied, so run calculations
                        // DESC: Determine if maxSpace is greater than current value
                        if ((rightCursorIndex - leftCursorIndex) - 1 > maxSpace) {
                            // DESC: Calculate bestSeatIndex, update maxSpace, move
                            // ... leftCursorIndex and increment rightCursorIndex
                            bestSeatIndex = (rightCursorIndex + leftCursorIndex) / 2;
                            maxSpace = (rightCursorIndex - leftCursorIndex) - 1;
                            leftCursorIndex = rightCursorIndex;
                            rightCursorIndex = rightCursorIndex + 1;
                        } else {
                            leftCursorIndex = rightCursorIndex;
                            rightCursorIndex = rightCursorIndex + 1;
                        }
                    } else {
                        // DESC: Increment beyond any zero(es)
                        rightCursorIndex = rightCursorIndex + 1;
                    }
                }
            } // LOOP END
        }

        // DESC: bestSeatIndex starts at -1 because that indicates
        // ... that there are no seats available
        return bestSeatIndex;
    }
}
