/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

/**
 * Contains static constants and convenience methods for forming CSP directives.
 * 
 * @author Michael
 */
public class CSP {
    
    public CSP() {
        throw new AssertionError("Not to be instantiated");
    }
    
    public static final String NONE = "'none'";
    public static final String ALL = "'all'";
    public static final String SELF = "'self'";
    public static final String UNSAFE_INLINE = "'unsafe-inline'";
    public static final String UNSAFE_EVAL = "'unsafe-eval'";
    public static final String REPORT_SAMPLE = "'report-sample'";
    public static final String STRICT_DYNAMIC = "'strict-dynamic'";
    
    public static String nonce(String nonce) {
        new CSPSyntaxChecker().checkBase64(nonce);
        return "'nonce-" + nonce + "'";
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
