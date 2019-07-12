/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class CSPHeader {

    private final Map<String, CSPDirective> directives;

    public CSPHeader(Iterable<CSPDirective> values) {
        this(StreamSupport.stream(values.spliterator(), false));
    }

    public CSPHeader(Stream<CSPDirective> values) {
        this(values.toArray(CSPDirective[]::new));
    }

    public CSPHeader(CSPDirective... values) {
        this.directives = Collections.unmodifiableMap(
                Arrays.stream(values)
                        .collect(Collectors.toMap(a -> a.getName(), b -> b))
        );
    }

    public String getValue() {
        return String.join("; ",
                directives.values().stream()
                        .map(d -> d.getValue())
                        .collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        CSPHeader header = new CSPHeader(
                CSPDirective.defaultSrc(CSPConstants.SELF),
                CSPDirective.connectSrc(CSPConstants.SELF, "https://legacy.example.com"),
                CSPDirective.fontSrc(CSPConstants.SELF, "https://fonts.example.com"),
                CSPDirective.frameSrc(CSPConstants.SELF, "https://frame.paypal.com", "https://frame2.paypal.com", "https://analytics.provider.info"),
                CSPDirective.imgSrc(CSPConstants.SELF, "https://images.example.com", "https://cdn.example.com"),
                CSPDirective.mediaSrc(CSPConstants.NONE),
                CSPDirective.objectSrc(CSPConstants.NONE),
                CSPDirective.scriptSrc(CSPConstants.SELF, "https://script.example.org", "https://apis.example.org", "https://analytics.provider.info"),
                CSPDirective.frameAncestors(CSPConstants.NONE),
                CSPDirective.blockAllMixedContent(),
                CSPDirective.upgradeInsecureRequests()
        );
        System.out.println(header.getValue());
    }

}
