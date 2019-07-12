/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

/**
 *
 * @author Michael
 */
public final class CSPConstants {
    
    private CSPConstants() {
        throw new AssertionError("Not to be instantiated");
    }

    public static final String NONE             = "'none'";
    public static final String ALL              = "'all'";
    public static final String SELF             = "'self'";
    public static final String UNSAFE_INLINE    = "'unsafe-inline'";
    public static final String UNSAFE_EVAL      = "'unsafe-eval'";
    public static final String REPORT_SAMPLE    = "'report-sample'";
    public static final String STRICT_DYNAMIC   = "'strict-dynamic'";
    
}
