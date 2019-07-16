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

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class CSPHeader {

    private final Map<String, CSPDirective> directives;

    /**
     * Create a CSP Header from one or more CSPDirectives. An empty CSP header
     * is also supported. Duplicate directive names are not allowed.
     * @param values CSPDirectives in this CSP Header.
     */
    public CSPHeader(CSPDirective... values) {
        CSPSyntaxChecker.checkDuplicateDirectives(values);
        this.directives = Collections.unmodifiableMap(
                Arrays.stream(values)
                        .collect(Collectors.toMap(a -> a.getName(), b -> b))
        );
    }

    /**
     * Get the full value of this header. The value can either be used for a 
     * "Content-Security-Policy" header or "Content-Security-Policy-Report-Only"
     * header.
     * @return the header value.
     */
    public String getValue() {
        if (directives.isEmpty()) {
            return "";
        } else {
            return String.join("; ",
                    directives.values().stream()
                            .map(d -> d.getValue())
                            .collect(Collectors.toList())) + ";";
        }
    }

    /**
     * Based on the "frame-ancestors" property specified, attempt to get the
     * value of the equivalent legacy "X-Frame-Options" header. This method will
     * only return a result if the "frame-ancestors" directive is set to one of
     * 'none', 'self', or a single http or https domain. In all other cases, and
     * if the "frame-ancestors" property isn't specified, this method will
     * return an empty value.
     *
     * Note that even in the cases where this method does return a value,
     * equivalent functionality to the "frame-ancestors" directive cannot be
     * guaranteed.
     *
     * @return The value of the equivalent legacy "X-Frame-Options" header, if
     * possible.
     */
    public Optional<String> getLegacyXFrameOptionsValue() {
        return getLegacyValue(new LegacyXFrameOptionsHeader());
    }

    /**
     * Generic support for returning a legacy header.
     * @param legacyHeader the legacy header to generate from this CSP header.
     * @return the optional value of the legacy header, if it can be meaningfully
     * generated.
     */
    private Optional<String> getLegacyValue(LegacyHeader legacyHeader) {
        return Optional.ofNullable(directives.get(legacyHeader.getEquivalentCspDirective()))
                .flatMap(
                        d -> new LegacyTransformer(d, legacyHeader.getTransformerMap()).transform()
                );
    }

}
