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
public class NotBase64Exception extends RuntimeException {
    
    public NotBase64Exception(String b64) {
        super("\"" + b64 + "\" must be base64 encoded.");
    }
    
    public NotBase64Exception(String b64, int length, int checkLength) {
        super("\"" + b64 + "\"'s decoded length must be " + checkLength + ", it was " + length);
    }
    
}
