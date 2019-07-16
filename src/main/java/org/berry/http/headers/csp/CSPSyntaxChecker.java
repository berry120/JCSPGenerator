/*
 * Copyright (c) 2019 Michael Berry
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package org.berry.http.headers.csp;

import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.berry.http.headers.csp.exception.DuplicateDirectivesException;
import org.berry.http.headers.csp.exception.InvalidDirectiveNameException;
import org.berry.http.headers.csp.exception.InvalidDirectiveValueException;
import org.berry.http.headers.csp.exception.NotBase64Exception;

/**
 * Convenience methods for checking the syntax of CSP directives.
 * @author Michael
 */
@UtilityClass
public class CSPSyntaxChecker {
    
    public static void checkNameAndValues(String name, String... values) {
        checkName(name);
        checkValues(values);
    }
    
    public static void checkValues(String... values) {
        for(String value : values) {
            checkValue(value);
        }
    }
    
    public static void checkValue(String value) {
        if(!value.matches("[^\\;\\,\\s]+")) {
            throw new InvalidDirectiveValueException(value);
        }
    }
    
    public static void checkName(String name) {
        if (!name.matches("[a-zA-Z0-9\\-]+")) {
            throw new InvalidDirectiveNameException(name);
        }
    }
    
    public static void checkDuplicateDirectives(CSPDirective... directives) {
        if(Arrays.stream(directives).map(d -> d.getName()).collect(Collectors.toSet()).size()!=directives.length) {
            throw new DuplicateDirectivesException();
        }
    }
    
    public static void checkBase64(String b64) {
        checkBase64(b64, -1);
    }
    
    public static void checkBase64(String b64, int checkLength) {
        if(!isBase64(b64)) {
            throw new NotBase64Exception(b64);
        }
        byte[] decoded = Base64.getDecoder().decode(b64);
        if(checkLength>=0 && decoded.length!=checkLength) {
            throw new NotBase64Exception(b64, decoded.length, checkLength);
        }
    }
    
    private static boolean isBase64(String str) {
        return str.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
    }
}
