package CIQ_Arrays.VeryHard;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine that you want to schedule a meeting of a certain duration with a
 * co-worker. You have access to your calendar and your co-worker's calendar
 * (both of which contain your respective meetings for the day, in the form of
 * `[startTime, endTime]`), as well as both of your daily bounds (i.e., the
 * earliest and latest times at which you're available for meetings every day,
 * in the form of `[earliestTime, latestTime]`).
 *
 * Write a function that takes in your calendar, your daily bounds, your
 * co-worker's calendar, your co-worker's daily bounds, and the duration of the
 * meeting that you want to schedule, and that returns a list of all the time
 * blocks (in the form of `[startTime, endTime]`) during which you schedule the
 * meeting, ordered from earliest time block to latest.
 *
 * Note that times will be given and should be returned in military time. For
 * example: `8:30`, `9:01` and `23:56`.
 *
 * Also note that the given calendar times will be sorted by start time in
 * ascending order, as you would expect them to appear in a calendar application
 * like Google Calendar.
 *
 * SAMPLE INPUT:
 * calendar1 = [['9:00', '10:30'], ['12:00', '13:00'], ['16:00', '18:00']]
 * dailyBounds1 = ['9:00', '20:00']
 * calendar2 = [['10:00', '11:30'], ['12:30', '14:30'], ['14:30', '15:00'], ['16:00', '17:00']]
 * dailyBounds2 = ['10:00', '18:30']
 * meetingDuration = 30
 *
 * SAMPLE OUTPUT:
 * [['11:30', '12:00'], ['15:00', '16:00'], ['18:00', '18:30']]
 *
 * ADDITIONAL NOTES:
 * When it comes to sorting the times, the following logic is used:
 *
 * - IF sT01 < sT02, then append t01 and t02
 * - IF sT01 == sT02, then check the eT...
 * -- IF eT01 > eT02, then append t02 and t01
 * -- ELSE append t01 and t02
 * - ELSE append t02 and t01
 *
 * Where `sT` is startTime, `eT` is endTime and `t` is time.
 *
 * Also, I appended the EEs 'clock-out' times in a separate line
 * of logic because I did NOT see any situation where an appt.
 * start-time for one EE was greater-than the clock-out time
 * of the opposing EE. If this ever needed to be expanded-upon,
 * then that edge-case would need to be taken into consideration.
 */

public class CalendarMatching {
    /**
     * Convert the string-value (format of "HH:MM" or "H:MM")
     * to a double value where the 'HH' is the main number and
     * the 'MM' is the decimal number. This enables the code to
     * properly compare if a time-frame is greater-than, equal-to
     * or less-than another time-frame
     * @param timeCode (a string repr. of the proposed time)
     * @return `timeValue` (a decimal repr. of the provided string)
     */
    public static double timeStrToDecimal(String timeCode) {
        double hourValue = (timeCode.length() == 4)
                ? Double.parseDouble(timeCode.substring(0, 1))
                : Double.parseDouble(timeCode.substring(0, 2));
        double minuteValue = Double.parseDouble(timeCode.substring(timeCode.length() - 2));
        minuteValue = minuteValue / 60.00;
        double timeValue = hourValue + minuteValue;

        return timeValue;
    }

    public static List<StringMeeting> calendarMatching(
            List<StringMeeting> calendar1, StringMeeting dailyBounds1,
            List<StringMeeting> calendar2, StringMeeting dailyBounds2, int meetingDuration
    ) {
        // DESC: Initialize necessary variables
        int calendarOneLength = calendar1.size();
        int calendarTwoLength = calendar2.size();
        int busyCalendarLength = calendarOneLength + calendarTwoLength + 4;
        List<StringMeeting> mergedCalendar = new ArrayList<StringMeeting>(busyCalendarLength);
        List<StringMeeting> availableTimeSlots = new ArrayList<StringMeeting>();
        int calOneCursor = 0;
        int calTwoCursor = 0;
        boolean calOneFlag = true;
        boolean calTwoFlag = true;

        // DESC: Add daily bounds (start) to `mergedCalendar`
        // NOTE: Time-frames in `mergedCalendar` are all
        // ... "unavailable" time-frames
        if (timeStrToDecimal(dailyBounds1.start) > timeStrToDecimal(dailyBounds2.start)) {
            mergedCalendar.add(new StringMeeting("0:00", dailyBounds2.start));
            mergedCalendar.add(new StringMeeting("0:00", dailyBounds1.start));
        } else {
            mergedCalendar.add(new StringMeeting("0:00", dailyBounds1.start));
            mergedCalendar.add(new StringMeeting("0:00", dailyBounds2.start));
        }

        // DESC: Check if the overall `busyCalendarLength`
        // ... is "4", which indicates that both schedules
        // ... are empty
        if (busyCalendarLength == 4) {
            if (timeStrToDecimal(dailyBounds1.end) > timeStrToDecimal(dailyBounds2.end)) {
                mergedCalendar.add(new StringMeeting(dailyBounds2.end, "23:59"));
                mergedCalendar.add(new StringMeeting(dailyBounds1.end, "23:59"));
            } else {
                mergedCalendar.add(new StringMeeting(dailyBounds1.end, "23:59"));
                mergedCalendar.add(new StringMeeting(dailyBounds2.end, "23:59"));
            }
            calOneFlag = false;
            calTwoFlag = false;
        }

        // DESC: Add times from  both calendars to
        // ... the `mergedCalendar` in ASC order
        while (calOneFlag == true || calTwoFlag == true) {
            // DESC: Ensure that neither calendars iterate out of bounds
            if (calendarOneLength == 0) {
                calOneFlag = false;
            }
            if (calendarTwoLength == 0) {
                calTwoFlag = false;
            }

            // DESC: Assuming both arrays have time-frames left
            // ... then compare the start and end times of the
            // ... current interval and the next interval
            // ... to determine where the values are intersecting
            if (calOneFlag == true && calTwoFlag == true) {
                if (timeStrToDecimal(calendar1.get(calOneCursor).start) < timeStrToDecimal(calendar2.get(calTwoCursor).start)) {
                    mergedCalendar.add(new StringMeeting(calendar1.get(calOneCursor).start, calendar1.get(calOneCursor).end));
                    if (calOneCursor + 1 == calendarOneLength) {
                        calOneFlag = false;
                    } else {
                        calOneCursor = calOneCursor + 1;
                    }
                } else if (timeStrToDecimal(calendar1.get(calOneCursor).start) == timeStrToDecimal(calendar2.get(calTwoCursor).start)) {
                    if (timeStrToDecimal(calendar1.get(calOneCursor).end) > timeStrToDecimal(calendar2.get(calTwoCursor).end)) {
                        mergedCalendar.add(new StringMeeting(calendar2.get(calTwoCursor).start, calendar2.get(calTwoCursor).end));
                        mergedCalendar.add(new StringMeeting(calendar1.get(calOneCursor).start, calendar1.get(calOneCursor).end));
                    } else {
                        mergedCalendar.add(new StringMeeting(calendar1.get(calOneCursor).start, calendar1.get(calOneCursor).end));
                        mergedCalendar.add(new StringMeeting(calendar2.get(calTwoCursor).start, calendar2.get(calTwoCursor).end));
                    }
                    if (calOneCursor + 1 == calendarOneLength) {
                        calOneFlag = false;
                    } else {
                        calOneCursor = calOneCursor + 1;
                    }
                    if (calTwoCursor + 1 == calendarTwoLength) {
                        calTwoFlag = false;
                    } else {
                        calTwoCursor = calTwoCursor + 1;
                    }
                } else {
                    mergedCalendar.add(new StringMeeting(calendar2.get(calTwoCursor).start, calendar2.get(calTwoCursor).end));
                    if (calTwoCursor + 1 == calendarTwoLength) {
                        calTwoFlag = false;
                    } else {
                        calTwoCursor = calTwoCursor + 1;
                    }
                }
            } else if (calOneFlag == true && calTwoFlag == false) {
                mergedCalendar.add(new StringMeeting(calendar1.get(calOneCursor).start, calendar1.get(calOneCursor).end));
                if (calOneCursor + 1 == calendarOneLength) {
                    calOneFlag = false;
                } else {
                    calOneCursor = calOneCursor + 1;
                }
            } else if (calOneFlag == false && calTwoFlag == true) {
                mergedCalendar.add(new StringMeeting(calendar2.get(calTwoCursor).start, calendar2.get(calTwoCursor).end));
                if (calTwoCursor + 1 == calendarTwoLength) {
                    calTwoFlag = false;
                } else {
                    calTwoCursor = calTwoCursor + 1;
                }
            }
        }

        // DESC: Add daily bounds (end) to `mergedCalendar`
        // NOTE: Time-frames in `mergedCalendar` are all
        // ... "unavailable" time-frames
        if (timeStrToDecimal(dailyBounds1.end) > timeStrToDecimal(dailyBounds2.end)) {
            mergedCalendar.add(new StringMeeting(dailyBounds2.end, "23:59"));
            mergedCalendar.add(new StringMeeting(dailyBounds1.end, "23:59"));
        } else {
            mergedCalendar.add(new StringMeeting(dailyBounds1.end, "23:59"));
            mergedCalendar.add(new StringMeeting(dailyBounds2.end, "23:59"));
        }

        // DESC: Consolidate all intersecting time-frames
        // ... in `mergedCalendar`
        int i = 0;
        while (true) {
            int mergedCalenderLength = mergedCalendar.size();
            // DESC: Ensure that the code is operating within
            // ... bounds of the array BEFORE completing any
            // ... additional logic
            if (i < mergedCalenderLength && i + 1 != mergedCalenderLength) {
                // DESC: This checks to see if tF[i+1] intersects with tF[i]
                // ... and, if so, updates the value at [i] then removes (or
                // ... outright removes) the intersecting/lower time-frame
                // NOTE: Due to the fact that the logic is, basically, consuming
                // ... the subsequent values that meet the criteria, the array
                // ... is not incrementing because it skips time-frames when
                // ... it increments on every run
                if ((timeStrToDecimal(mergedCalendar.get(i + 1).start) <= timeStrToDecimal(mergedCalendar.get(i).end)) && (timeStrToDecimal(mergedCalendar.get(i + 1).end) >= timeStrToDecimal(mergedCalendar.get(i).end))) {
                    mergedCalendar.set(i, new StringMeeting(mergedCalendar.get(i).start, mergedCalendar.get(i + 1).end));
                    mergedCalendar.remove(i + 1);
                    i = i + 0;
                } else if ((timeStrToDecimal(mergedCalendar.get(i + 1).start) <= timeStrToDecimal(mergedCalendar.get(i).end)) && (timeStrToDecimal(mergedCalendar.get(i + 1).end) <= timeStrToDecimal(mergedCalendar.get(i).end))) {
                    mergedCalendar.remove(i + 1);
                    i = i + 0;
                } else {
                    i = i + 1;
                }
            } else {
                break;
            }
        }

        // DESC: Extract valid available slots from what
        // ... remains in `mergedCalendar`
        // NOTE: The decimal-time (e.g., .50 == 30 minutes)
        // ... is converted to an 'int' by multiplying it on
        // ... "60" that way it is a proper format-to-format
        // ... comparison
        int j = 0;
        int mergedCalenderLength = mergedCalendar.size();
        while (true) {
            if (j < mergedCalenderLength && j + 1 != mergedCalenderLength) {
                int decimalToTime = (int) (((timeStrToDecimal(mergedCalendar.get(j + 1).start)) - (timeStrToDecimal(mergedCalendar.get(j).end))) * 60);
                if (decimalToTime >= meetingDuration) {
                    availableTimeSlots.add(new StringMeeting(mergedCalendar.get(j).end, mergedCalendar.get(j + 1).start));
                    j = j + 1;
                } else {
                    j = j + 1;
                }
            } else {
                break;
            }
        }

        return availableTimeSlots;
    }

    // DESC: This is included with the challenge (i.e., I
    // ... did not create or modify this in any way)
    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
}