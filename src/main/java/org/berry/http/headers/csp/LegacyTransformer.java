/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 *
 * @author Michael
 */
@AllArgsConstructor
public class LegacyTransformer {
    
    @NonNull
    private final CSPDirective cspDirective;
    @NonNull
    private final Map<Predicate<String>, Function<String, String>> transformer;
    
    public Optional<String> transform() {
        return Optional.of(cspDirective)
                .map(directive -> directive.getValueList())
                .filter(list -> list.size() == 1)
                .map(list -> list.get(0))
                .flatMap(
                        str -> transformer.keySet().stream()
                                .filter(p -> p.test(str))
                                .findFirst()
                                .map(transformer::get)
                                .map(f -> f.apply(str))
                );
    }
    
}