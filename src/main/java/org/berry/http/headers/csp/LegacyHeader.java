/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import com.sun.istack.internal.NotNull;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Data;

/**
 *
 * @author Michael
 */
@Data
public class LegacyHeader {

    @NotNull
    private final String headerName;
    @NotNull
    private final String equivalentCspDirective;
    @NotNull
    private final Map<Predicate<String>, Function<String, String>> transformerMap;
    
}
