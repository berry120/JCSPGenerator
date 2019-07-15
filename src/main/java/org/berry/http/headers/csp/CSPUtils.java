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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.experimental.UtilityClass;

/**
 *
 * @author Michael
 */
@UtilityClass
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

}
