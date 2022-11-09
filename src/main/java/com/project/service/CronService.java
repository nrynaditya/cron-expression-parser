package com.project.service;

import com.project.exceptions.InvalidInputException;
import com.project.exceptions.VoidInputException;
import com.project.util.CronFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CronService {

    LinkedHashMap<String, String> cronMap = new LinkedHashMap<>();

    /**
     * Initialize the CronService with the input cron expression
     *
     * @param cron Cron expression
     * @throws VoidInputException if input is void or null
     */
    public CronService(String cron) throws VoidInputException {
        if (cron == null || cron.length() == 0) {
            throw new VoidInputException("Cron expression cannot be null or void");
        }
        String[] cronString = cron.split("\\s+");
        this.cronMap.put("minute", cronString[0]);
        this.cronMap.put("hour", cronString[1]);
        this.cronMap.put("day of month", cronString[2]);
        this.cronMap.put("month", cronString[3]);
        this.cronMap.put("day of week", cronString[4]);
        this.cronMap.put("command", cronString[5]);
    }

    /**
     * This prints the expanded time values for each time field in the input cron expression
     */
    public void expandExpression() {
        expandMinute();
        expandHour();
        expandDayOfMonth();
        expandMonth();
        expandDayOfWeek();
        expandCommand();
    }

    /**
     * This prints the expanded values for the "minute" field in the provided cron expression
     */
    public void expandMinute() {
        try {
            CronFormatter.format("minute", parse(0, 59, this.cronMap.get("minute")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This prints the expanded values for the "hour" field in the provided cron expression
     */
    public void expandHour() {
        try {
            CronFormatter.format("hour", parse(0, 23, this.cronMap.get("hour")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This prints the expanded values for the "day of month" field in the provided cron expression
     */
    public void expandDayOfMonth() {
        try {
            CronFormatter.format("dayOfMonth", parse(1, 31, this.cronMap.get("day of month")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This prints the expanded values for the "month" field in the provided cron expression
     */
    public void expandMonth() {
        try {
            CronFormatter.format("month", parse(1, 12, this.cronMap.get("month")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This prints the expanded values for the "day of week" field in the provided cron expression
     */
    public void expandDayOfWeek() {
        try {
            CronFormatter.format("day of week", parse(0, 6, this.cronMap.get("day of week")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This prints the value for the "command" field in the provided cron expression
     */
    public void expandCommand() {
        try {
            CronFormatter.format("command", parse(0, 0, this.cronMap.get("command")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This parses the cron expression for all fields ( minute, hour, day of month, month, day of week, command)
     * and returns a list of expanded values
     *
     * @param min The minimum valid value for a given time field, 0 for command field
     * @param max The maximum valid value for a given time field, 0 for command field
     * @param exp Cron sub-expression for the given time field
     * @return List of expanded times for the given time field
     * @throws InvalidInputException on invalid inputs
     */
    public List<String> parse(int min, int max, String exp) throws InvalidInputException {
        List<String> values = new ArrayList<>();
        try {
            if (min == 0 && max == 0) {
                values.add(exp);
            } else if (exp.contains("*/")) {
                String[] tokens = exp.split("/");
                for (int i = min; i <= max; i++) {
                    if (i % Integer.parseInt(tokens[1]) == 0) {
                        values.add(Integer.toString(i));
                    }
                }
            }else if(exp.contains("/")){
                String[] tokens = exp.split("/");
                min = Integer.parseInt(tokens[0]);
                for(int i=min;i<=max;i++){
                    if(i%(Integer.parseInt(tokens[1])) == min){
                        values.add(Integer.toString(i));
                    }
                }
            }
            else if (exp.contains(",")) {
                String[] tokens = exp.split(",");
                values = Arrays.stream(tokens).collect(Collectors.toList());
            } else if (exp.contains("*")) {
                for (int i = min; i <= max; i++) {
                    values.add(Integer.toString(i));
                }
            } else if (exp.contains("-")) {
                String[] limits = exp.split("-");
                min = Integer.parseInt(limits[0]);
                max = Integer.parseInt(limits[1]);
                for (int i = min; i <= max; i++) {
                    values.add(Integer.toString(i));
                }
            } else {
                values.add(exp);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidInputException("Cron expression is invalid.");
        }

        return values;
    }
}
