/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Michael
 */
@Data
public class LegacyHeader {

    @NonNull
    private final String headerName;
    @NonNull
    private final String equivalentCspDirective;
    @NonNull
    private final Map<Predicate<String>, Function<String, String>> transformerMap;
    
}
