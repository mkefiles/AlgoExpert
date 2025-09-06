package CIQ_Strings.Medium;

import java.util.ArrayList;

/**
 * You're given a string of length 12 or smaller, containing only digits. Write a
 * function that returns all the possible IP addresses that can be created by
 * inserting three `.`s in the string.
 *
 * An IP address is a sequence of four positive integers that are separated by
 * `.`s, where each individual integer is within the range `0 - 255`, inclusive.
 *
 * An IP address isn't valid if any of the individual integers contains leading
 * `0`s. For example, `"192.168.0.1"` is a valid IP address, but `"192.168.00.1"`
 * and `"192.168.0.01" aren't, because they contain `"00"` and `01`, respectively.
 * Another example of a valid IP address is `"99.1.1.10"`; conversely, `"991.1.1.0"`
 * isn't valid, because `"991"` is greater than 255.
 *
 * Your function should return the IP addresses in string format and in no
 * particular order. If no valid IP addresses can be created from the string,
 * your function should return an empty list.
 *
 * Note: check out our Systems Design Fundamentals on SystemsExpert to learn more
 * about IP addresses!
 *
 * SAMPLE INPUT:
 * string = "1921680"
 *
 * SAMPLE OUTPUT:
 * [
 *     "1.9.216.80",
 *     "1.92.16.80",
 *     "1.92.168.0",
 *     "19.2.16.80",
 *     "19.2.168.0",
 *     "19.21.6.80",
 *     "19.21.68.0",
 *     "19.216.8.0",
 *     "192.1.6.80",
 *     "192.1.68.0",
 *     "192.16.8.0"
 * ] // The IP addresses could be ordered differently.
 */

public class ValidIPAddresses {

    /**
     * Assemble (x4) Strings (substrings of input String)
     * in any Array of Strings (e.g., ["19","2","16","80"])
     * @param start always "0" as all Strings start at 0
     * @param sec01 end/start index of Section 01 / 02
     * @param sec02 end/start index of Section 02 / 03
     * @param sec03 end/start index of Section 03 / 04
     * @param IPAddress base-string (to substring)
     * @return an Array of substrings
     */
    public static String[] assembleIPSections (int start, int sec01, int sec02, int sec03, String IPAddress) {
        String[] output = new String[4];

        output[0] = IPAddress.substring(start, sec01);
        output[1] = IPAddress.substring(sec01, sec02);
        output[2] = IPAddress.substring(sec02, sec03);
        output[3] = (IPAddress.length() - sec03 == 0) ? "" : IPAddress.substring(sec03, IPAddress.length());

        return output;
    }

    /**
     * Iterates through each substring in an Array and
     * verifies that each matches necessary criteria
     * to be a section within an IP Address (IPv4)
     * @param proposedIP an Array of Strings
     * @return false if ANY value in an array fails
     */
    public static boolean areSectionsValid(String[] proposedIP) {
        // DESC: Check validity of each substring
        for (int i = 0; i < 4; i++) {
            // DESC: Check if length is 0 or greater-than 3
            int substringLength = proposedIP[i].length();
            if (substringLength == 0 || substringLength > 3) {
                return false;
            }

            // DESC: Check if leading character is "0"
            if (substringLength > 1) {
                char leadingCharacter = proposedIP[i].charAt(0);
                if (leadingCharacter == '0') {
                    return false;
                }
            }

            // DESC: Check if value is between 0 and 255
            int substringAsInt = Integer.parseInt(proposedIP[i]);
            if (substringAsInt < 0 || substringAsInt > 255) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<String> validIPAddresses(String string) {

        // DESC: Initialize necessary variables
        int stringLength = string.length();
        ArrayList<String> validIPAddresses = new ArrayList<String>();

        // DESC: Edge-Cases: Any input less-than four
        // ... cannot possibly be a valid IP Address
        if (stringLength < 4) {
            return validIPAddresses;
        }

        // DESC: Edge-Cases: Any input greater-than twelve
        // ... cannot possibly be a valid IP Address
        if (stringLength > 12) {
            return validIPAddresses;
        }

        // DESC: Handle length of 4 directly
        if (stringLength == 4) {
            String[] output = assembleIPSections(0, 1, 2, 3, string);
            if (areSectionsValid(output) == true) {
                String IPAddress = output[0] + "." + output[1] + "." + output[2] + "." + output[3];
                validIPAddresses.add(IPAddress);
            }
            return validIPAddresses;
        }

        // DESC: Handle length of 12 directly
        if (stringLength == 12) {
            String[] output = assembleIPSections(0, 3, 6, 9, string);
            if (areSectionsValid(output) == true) {
                String IPAddress = output[0] + "." + output[1] + "." + output[2] + "." + output[3];
                validIPAddresses.add(IPAddress);
            }
            return validIPAddresses;
        }

        // DESC: A triple-loop that determines ALL possible
        // ... numbers of values each section can hold (1 - 3)
        // NOTE: This feeds every VALID combination to the
        // ... other functions that, basically, creates the
        // ... four possible substring sections then verifies
        // ... each substring
        for (int sec01Index = 1; sec01Index <= 3; sec01Index++) {
            for (int sec02Index = 1; sec02Index <= 3; sec02Index++) {
                for (int sec03Index = 1; sec03Index <= 3; sec03Index++) {
                    int start = 0;
                    int section01 = start + sec01Index;
                    int section02 = section01 + sec02Index;
                    int section03 = section02 + sec03Index;
                    int totalOfSections = sec01Index + sec02Index + sec03Index;

                    // NOTE: A `totalOfSections` greater-than the
                    // ... `stringLength` is invalid because, as
                    // ... an example, if the loops determine a
                    // ... length of 2-2-2 and the input-length is
                    // ... 5, then it cannot possibly satisfy a
                    // ... substring build like 12.12.12.xxx
                    if (totalOfSections > stringLength) {
                        continue;
                    } else {
                        String[] output = assembleIPSections(start,section01, section02, section03, string);
                        if (areSectionsValid(output) == true) {
                            String IPAddress = output[0] + "." + output[1] + "." + output[2] + "." + output[3];
                            validIPAddresses.add(IPAddress);
                        }
                    }
                }
            }
        }

        return validIPAddresses;
    }
}
