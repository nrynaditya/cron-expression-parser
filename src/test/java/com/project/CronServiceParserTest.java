package com.project;

import com.project.exceptions.VoidInputException;
import com.project.exceptions.InvalidInputException;
import com.project.service.CronService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CronServiceParserTest {
    /**
     * Test to verify that a valid cron expression throws no exceptions
     */
    @Test
    public void TestValidCron() {
        String args = "*/15 0 1,15 * 1-5 /usr/bin/find";

        assertDoesNotThrow(() -> {
            new CronService(args);
        });
    }

    /**
     * Test to verify that an empty cron expression throws VoidInputException
     */
    @Test
    public void TestVoidCron() {
        String args = null;

        Exception exception = assertThrows(VoidInputException.class, () -> {
            new CronService(args);
        });
        String expectedMessage = "Cron expression cannot be null or void";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that an invalid cron expression throws InvalidInputException
     */
    @Test
    public void TestInvalidCron() {
        String args = "*/15 a-b 1,15 * 1-5 /usr/bin/find";

        CronService cronService = null;
        try {
            cronService = new CronService(args);
        } catch (VoidInputException voidInputException) {
            voidInputException.printStackTrace();
        }

        final CronService finalCronService = cronService;
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            finalCronService.parse(0, 23, "a-b");
        });
        String expectedMessage = "Cron expression is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
