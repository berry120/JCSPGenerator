/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public CSPHeader(CSPDirective... values) {
        new CSPSyntaxChecker().checkDuplicateDirectives(values);
        this.directives = Collections.unmodifiableMap(
                Arrays.stream(values)
                        .collect(Collectors.toMap(a -> a.getName(), b -> b))
        );
    }

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

    private Optional<String> getLegacyValue(LegacyHeader legacyHeader) {
        return Optional.ofNullable(directives.get(legacyHeader.getEquivalentCspDirective()))
                .flatMap(
                        d -> new LegacyTransformer(d, legacyHeader.getTransformerMap()).transform()
                );
    }

}
