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

import lombok.experimental.UtilityClass;

/**
 * Contains static constants and convenience methods for forming CSP directives.
 * 
 * @author Michael
 */
@UtilityClass
public class CSP {
    
    public static final String NONE = "'none'";
    public static final String ALL = "'all'";
    public static final String SELF = "'self'";
    public static final String UNSAFE_INLINE = "'unsafe-inline'";
    public static final String UNSAFE_EVAL = "'unsafe-eval'";
    public static final String REPORT_SAMPLE = "'report-sample'";
    public static final String STRICT_DYNAMIC = "'strict-dynamic'";
    
    public static String nonce(String nonce) {
        return rawB64Val("nonce", nonce, -1);
    }
    
    public static String sha256(String val) {
        return rawB64Val("sha256", val, 256/8);
    }
    
    public static String sha384(String val) {
        return rawB64Val("sha384", val, 384/8);
    }
    
    public static String sha512(String val) {
        return rawB64Val("sha512", val, 512/8);
    }
    
    private static String rawB64Val(String name, String val, int lengthCheck) {
        new CSPSyntaxChecker().checkBase64(val, lengthCheck);
        return "'" + name + "-" + val + "'";
    }
    
    public static CSPDirective baseUri(String... values) {
        return new CSPDirective("base-uri", values);
    }

    public static CSPDirective blockAllMixedContent() {
        return new CSPDirective("block-all-mixed-content");
    }

    public static CSPDirective childSrc(String... values) {
        return new CSPDirective("child-src", values);
    }

    public static CSPDirective connectSrc(String... values) {
        return new CSPDirective("connect-src", values);
    }

    public static CSPDirective defaultSrc(String... values) {
        return new CSPDirective("default-src", values);
    }

    public static CSPDirective fontSrc(String... values) {
        return new CSPDirective("font-src", values);
    }

    public static CSPDirective formAction(String... values) {
        return new CSPDirective("form-action", values);
    }

    public static CSPDirective frameAncestors(String... values) {
        return new CSPDirective("frame-ancestors", values);
    }

    public static CSPDirective frameSrc(String... values) {
        return new CSPDirective("frame-src", values);
    }

    public static CSPDirective imgSrc(String... values) {
        return new CSPDirective("img-src", values);
    }

    public static CSPDirective manifestSrc(String... values) {
        return new CSPDirective("manifest-src", values);
    }

    public static CSPDirective mediaSrc(String... values) {
        return new CSPDirective("media-src", values);
    }

    public static CSPDirective objectSrc(String... values) {
        return new CSPDirective("object-src", values);
    }

    public static CSPDirective pluginTypes(String... values) {
        return new CSPDirective("plugin-types", values);
    }

    public static CSPDirective referrer(String referrerPolicy) {
        return new CSPDirective("referrer", referrerPolicy);
    }

    public static CSPDirective reportTo(String... values) {
        return new CSPDirective("report-to", values);
    }

    public static CSPDirective reportUri(String... values) {
        return new CSPDirective("report-uri", values);
    }

    public static CSPDirective requireSriFor(String... values) {
        return new CSPDirective("require-sri-for", values);
    }

    public static CSPDirective sandbox(String... values) {
        return new CSPDirective("sandbox", values);
    }

    public static CSPDirective scriptSrc(String... values) {
        return new CSPDirective("script-src", values);
    }

    public static CSPDirective styleSrc(String... values) {
        return new CSPDirective("style-src", values);
    }

    public static CSPDirective upgradeInsecureRequests() {
        return new CSPDirective("upgrade-insecure-requests");
    }

    public static CSPDirective workerSrc(String... values) {
        return new CSPDirective("worker-src", values);
    }
}
