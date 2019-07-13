/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import org.berry.http.headers.csp.exception.InvalidDirectiveNameException;
import org.berry.http.headers.csp.exception.InvalidDirectiveValueException;

/**
 *
 * @author Michael
 */
public class CSPSyntaxChecker {
    
    public void checkNameAndValues(String name, String... values) {
        checkName(name);
        checkValues(values);
    }
    
    public void checkValues(String... values) {
        for(String value : values) {
            checkValue(value);
        }
    }
    
    public void checkValue(String value) {
        if(!value.matches("[^\\;\\,]+")) {
            throw new InvalidDirectiveValueException(value);
        }
    }
    
    public void checkName(String name) {
        if (!name.matches("[a-zA-Z0-9\\-]+")) {
            throw new InvalidDirectiveNameException(name);
        }
    }
}
