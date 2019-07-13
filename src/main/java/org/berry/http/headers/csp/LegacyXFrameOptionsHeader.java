/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Michael
 */
public class LegacyXFrameOptionsHeader extends LegacyHeader {
    
    public LegacyXFrameOptionsHeader() {
        super("X-Frame-Options", "frame-ancestors", getMap());
    }
    
    private static Map<Predicate<String>, Function<String, String>> getMap() {
        Map<Predicate<String>, Function<String, String>> ret = new LinkedHashMap<>();
        ret.put(s -> s.equals(CSP.NONE), s -> "deny");
        ret.put(s -> s.equals(CSP.SELF), s -> "sameorigin");
        ret.put(s -> s.startsWith("http://") || s.startsWith("https://"), s -> "allow-from " + s);
        return ret;
    }
    
}
