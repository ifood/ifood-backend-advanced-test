package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.exception;

/**
 * Exception class to represent an exception threw on the business layer
 * @author ffrazato
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructor without arguments
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructor that receives a message
     *
     * @param msg
     *            exception message
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * Constructor that receives an Exception
     *
     * @param ex
     *            exception object
     */
    public BusinessException(Exception ex) {
        super(ex);
    }

    /**
     * Constructor that receives a message and another exception object
     *
     * @param msg
     *            exception message
     * @param ex
     *            exception object
     */
    public BusinessException(String msg, Exception ex) {
        super(msg + "\n" + ex.getClass() + ": " + ex.getMessage(), ex);
    }
}