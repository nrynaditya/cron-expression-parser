package com.project;

import com.project.exceptions.InvalidInputException;
import com.project.exceptions.VoidInputException;
import com.project.service.CronService;

/**
 * @author adityanarayan
 * @apiNote A cron expression parser that takes a cron expression as input string
 *          and expands each field to show the times at which it will run.
 */
public class CronExpressionParser {
    /**
     * This is the main driver method
     *
     * @param args cron expression
     */
    public static void main(String[] args) {
        CronService cronService;
        if(args.length != 1) {
            throw new InvalidInputException("Single argument should be passed");
        }
        String exp = args[0];
        try {
            cronService = new CronService(exp);
            cronService.expandExpression();
        } catch (VoidInputException voidInputException) {
            voidInputException.printStackTrace();
        }
    }
}
