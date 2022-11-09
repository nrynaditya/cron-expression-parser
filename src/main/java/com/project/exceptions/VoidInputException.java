package com.project.exceptions;

/**
 * This exception should be thrown when the cron expression is empty or null
 */
public class VoidInputException extends RuntimeException {
    /**
     * Initialize the exception
     *
     * @param message Custom message can be passed to add more info to the exception
     */
    public VoidInputException(String message) {
        super(message);
    }
}

