package com.project.util;

import java.util.List;

public class CronFormatter {
    /**
     * This prints out the list of input values
     *
     * @param field  One of the time fields in the cron expression
     * @param values Expanded time values for the given time field
     */
    public static void format(String field, List<String> values) {
        System.out.print(String.format("%-14s", field) + " ");
        values.stream().map(x -> x + " ").forEachOrdered(System.out::print);
        System.out.println();
    }
}