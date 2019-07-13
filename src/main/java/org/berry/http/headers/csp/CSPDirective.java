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
import lombok.AccessLevel;
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
    @Getter(AccessLevel.PACKAGE)
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
    
}
