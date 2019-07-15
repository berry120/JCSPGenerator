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

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateNonce() {
        byte[] bytes = new byte[16];
        SECURE_RANDOM.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static String hashSha256(String toHash) {
        return hash(toHash, "SHA-256");
    }

    public static String hashSha384(String toHash) {
        return hash(toHash, "SHA-384");
    }

    public static String hashSha512(String toHash) {
        return hash(toHash, "SHA-512");
    }

    public static String hash(String toHash, String algorithm) {
        try {
            return new String(
                    Base64.getEncoder().encode(MessageDigest.getInstance(algorithm).digest(toHash.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException("Error locating " + algorithm + " algorithm", ex);
        }
    }

    private CSPUtils() {
        throw new AssertionError("Not for instantiation");
    }

}
