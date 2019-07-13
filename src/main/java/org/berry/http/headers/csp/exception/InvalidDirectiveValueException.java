/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp.exception;

/**
 *
 * @author Michael
 */
public class InvalidDirectiveValueException extends RuntimeException {
    
    public InvalidDirectiveValueException(String value) {
        super("\"" + value + "\" is an invalid directive value");
    }
    
}
