/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    /**
     * Based on the "frame-ancestors" property specified, attempt to get the 
     * value of the equivalent legacy "X-Frame-Options" header. This method 
     * will only return a result if the "frame-ancestors" directive is set to 
     * one of 'none', 'self', or a single http or https domain. In all other 
     * cases, and if the "frame-ancestors" property isn't specified, this method
     * will return an empty value.
     * 
     * Note that even in the cases where this method does return a value, 
     * equivalent functionality to the "frame-ancestors" directive cannot be
     * guaranteed.
     * 
     * @return The value of the equivalent legacy "X-Frame-Options" header, if
     * possible.
     */
    public Optional<String> getLegacyXFrameOptionsValue() {
        return Optional.ofNullable(directives.get("frame-ancestors"))
                .map(d -> d.getRawValues())
                .filter(l -> l.size() == 1)
                .map(l -> l.get(0))
                .map(s -> {
                    if (s.equals(CSP.NONE)) {
                        return "deny";
                    } else if (s.contains(CSP.SELF)) {
                        return "sameorigin";
                    } else if (s.startsWith("http://") || s.startsWith("https://")) {
                        return "allow-from " + s;
                    } else {
                        return null;
                    }
                });
    }

    public static void main(String[] args) {
        CSPHeader header = new CSPHeader(
                CSP.defaultSrc(CSP.SELF),
                CSP.connectSrc(CSP.SELF, "https://legacy.example.com"),
                CSP.fontSrc(CSP.SELF, "https://fonts.example.com"),
                CSP.frameSrc(CSP.SELF, "https://frame.paypal.com", "https://frame2.paypal.com", "https://analytics.provider.info"),
                CSP.imgSrc(CSP.SELF, "https://images.example.com", "https://cdn.example.com"),
                CSP.mediaSrc(CSP.NONE),
                CSP.objectSrc(CSP.NONE),
                CSP.scriptSrc(CSP.SELF, "https://script.example.org", "https://apis.example.org", "https://analytics.provider.info"),
                CSP.frameAncestors(CSP.NONE),
                CSP.blockAllMixedContent(),
                CSP.upgradeInsecureRequests()
        );
        System.out.println(header.getValue());
    }

}
