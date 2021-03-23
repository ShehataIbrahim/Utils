/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

/**
 *
 * @author Shehata.Ibrahim
 */
public class MissingConfigurationException extends Exception{

    public MissingConfigurationException(String message) {
        super(message);
    }

    public MissingConfigurationException(Throwable cause) {
        super(cause);
    }

    public MissingConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
