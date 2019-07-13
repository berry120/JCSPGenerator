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
public class InvalidDirectiveNameException extends RuntimeException {
    
    public InvalidDirectiveNameException(String name) {
        super("\"" + name + "\" is an invalid directive name");
    }
    
}
