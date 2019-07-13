/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Base64;
import org.berry.http.headers.csp.exception.InvalidDirectiveNameException;
import org.berry.http.headers.csp.exception.InvalidDirectiveValueException;
import org.berry.http.headers.csp.exception.NotBase64Exception;

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
    
    public void checkBase64(String b64, int checkLength) {
        if(!isBase64(b64)) {
            throw new NotBase64Exception(b64);
        }
        byte[] decoded = Base64.getDecoder().decode(b64);
        if(checkLength>0 && decoded.length!=checkLength) {
            throw new NotBase64Exception(b64, decoded.length, checkLength);
        }
    }
    
    private boolean isBase64(String str) {
        return str.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
    }
}
