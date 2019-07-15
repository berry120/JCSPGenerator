/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author Michael
 */
public class CSPUtils {
    
    private static final SecureRandom secureRandom = new SecureRandom();
    
    public static String generateNonce() {
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }
    
    public static String hashSha256(String toHash) {
        return encode("SHA-256", toHash);
    }
    
    public static String hashSha384(String toHash) {
        return encode("SHA-384", toHash);
    }
    
    public static String hashSha512(String toHash) {
        return encode("SHA-512", toHash);
    }
    
    private static String encode(String algorithm, String toHash) {
        try {
        return new String(
                Base64.getEncoder().encode(MessageDigest.getInstance(algorithm).digest(toHash.getBytes(StandardCharsets.UTF_8))));
        }
        catch(NoSuchAlgorithmException ex) {
            throw new AssertionError("Error locating " + algorithm + " algorithm", ex);
        }
    }
    
}
