package com.project.exceptions;

/**
 * This exception should be thrown when the cron expression is invalid
 */
public class InvalidInputException extends RuntimeException {
    /**
     * Initialize the exception
     *
     * @param message Custom message can be passed to add more info to the exception
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
