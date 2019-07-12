/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class CSPDirective {
    
    @Getter
    private String name;
    private List<String> values;
    
    public String getValue() {
        return name + " " + String.join(" ", values);
    }
    
    public CSPDirective(String name) {
        this(name, new String[0]);
    }
    
    public CSPDirective(String name, Iterable<String> values) {
        this(name, StreamSupport.stream(values.spliterator(), false));
    }
    
    public CSPDirective(String name, Stream<String> values) {
        this(name, values.toArray(String[]::new));
    }
    
    public CSPDirective(String name, String... values) {
        this.name = name;
        this.values = Collections.unmodifiableList(Arrays.asList(values));
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
