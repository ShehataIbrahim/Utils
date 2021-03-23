/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

/**
 *
 * @author Shehata.Ibrahim
 */
public class MissingCompanyCodeException extends Exception{
    String code;

    public MissingCompanyCodeException(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
}
