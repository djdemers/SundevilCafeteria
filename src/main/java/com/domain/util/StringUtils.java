package com.domain.util;

/**
 * Utility class for common string operations.
 *
 * Purpose:
 * - Provides helper methods for handling and manipulating strings.
 * - Reduces code duplication by centralizing string-related logic.
 */
public class StringUtils {

    /**
     * Capitalizes the first letter of a given string.
     *
     * @param input The input string.
     * @return The string with the first letter capitalized, or the original string if it's null/empty.
     */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param input The input string.
     * @return true if the string is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    /**
     * Joins an array of strings with a specified delimiter.
     *
     * @param delimiter The delimiter to use for joining.
     * @param elements  The array of strings to join.
     * @return A single string with elements joined by the delimiter.
     */
    public static String join(String delimiter, String... elements) {
        if (elements == null || elements.length == 0) {
            return "";
        }
        return String.join(delimiter, elements);
    }

    /**
     * Trims and removes extra spaces from a string.
     *
     * @param input The input string.
     * @return The trimmed string with extra spaces removed.
     */
    public static String trimAndRemoveExtraSpaces(String input) {
        if (input == null) {
            return null;
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    // TODO: Add more utility methods as needed, such as:
    // - String reversal
    // - Substring validation
    // - Formatting strings
}
