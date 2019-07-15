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
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * An individual CSP directive. Multiple directives make up the full CSP header.
 * The directive usually consists of a name and then one or more parameters.
 * Some directives however, such as "upgrade-insecure-requests", take a name
 * only. Note that this class will perform no checking as to whether a name, or
 * a particular value, is valid in the CSP specification.
 * @author Michael
 */
@EqualsAndHashCode
public class CSPDirective {

    @Getter
    private String name;
    @Getter(AccessLevel.PACKAGE)
    private List<String> valueList;

    /**
     * Create a new CSP directive, just from a name (that doesn't contain a 
     * value.)
     * @param name the name of the CSP directive.
     */
    public CSPDirective(String name) {
        this(name, new String[0]);
    }

    /**
     * Create a new CSP directive from a name and a list of values.
     * @param name the name of the CSP directive.
     * @param values the list of values.
     */
    public CSPDirective(String name, String... values) {
        new CSPSyntaxChecker().checkNameAndValues(name, values);
        this.name = name;
        this.valueList = Collections.unmodifiableList(Arrays.asList(values));
    }
    
    /**
     * Get the value of this specific directive to be included in the whole
     * CSP header.
     * @return the value of this directive that will form a part of the entire
     * CSP header.
     */
    public String getValue() {
        if (valueList.isEmpty()) {
            return name;
        } else {
            return name + " " + String.join(" ", valueList);
        }
    }

}
