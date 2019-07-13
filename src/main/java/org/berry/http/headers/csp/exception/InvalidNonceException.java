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
public class InvalidNonceException extends RuntimeException {
    
    public InvalidNonceException(String nonce) {
        super("\"" + nonce + "\" is not a valid nonce. Must be base64 encoded.");
    }
    
}
