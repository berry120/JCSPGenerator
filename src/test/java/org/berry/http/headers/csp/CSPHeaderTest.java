/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Optional;
import org.berry.http.headers.csp.exception.DuplicateDirectivesException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Michael
 */
public class CSPHeaderTest {

    @Test
    public void testConstructor() {
        System.out.println("constructor");
        assertThrows(DuplicateDirectivesException.class, () -> {
            new CSPHeader(
                    CSP.defaultSrc(CSP.SELF),
                    CSP.defaultSrc(CSP.SELF)
            );
        });
    }

    @Test
    public void testGetValue() {
        System.out.println("getValue");
        CSPHeader header = new CSPHeader(
                CSP.defaultSrc(CSP.SELF)
        );
        assertEquals("default-src 'self';", header.getValue());
        CSPHeader header2 = new CSPHeader(
                CSP.defaultSrc(CSP.SELF),
                CSP.connectSrc(CSP.SELF, "https://legacy.example.com"),
                CSP.blockAllMixedContent()
        );
        assertEquals("block-all-mixed-content; connect-src 'self' https://legacy.example.com; default-src 'self';", header2.getValue());
        CSPHeader header3 = new CSPHeader(
                CSP.blockAllMixedContent()
        );
        assertEquals("block-all-mixed-content;", header3.getValue());
        CSPHeader header4 = new CSPHeader();
        assertEquals("", header4.getValue());
    }

    @Test
    public void testGetLegacyXFrameOptionsValue() {
        System.out.println("getLegacyXFrameOptionsValue");
        CSPHeader header1 = new CSPHeader(
                CSP.blockAllMixedContent()
        );
        assertEquals(Optional.empty(), header1.getLegacyXFrameOptionsValue());
        CSPHeader header2 = new CSPHeader(
                CSP.frameAncestors("https://test1.com", "https://test2.com")
        );
        assertEquals(Optional.empty(), header2.getLegacyXFrameOptionsValue());
        CSPHeader header3 = new CSPHeader(
                CSP.frameAncestors(CSP.SELF)
        );
        assertEquals(Optional.of("sameorigin"), header3.getLegacyXFrameOptionsValue());
        CSPHeader header4 = new CSPHeader(
                CSP.frameAncestors(CSP.NONE)
        );
        assertEquals(Optional.of("deny"), header4.getLegacyXFrameOptionsValue());
        CSPHeader header5 = new CSPHeader(
                CSP.frameAncestors("https://test.com")
        );
        assertEquals(Optional.of("allow-from https://test.com"), header5.getLegacyXFrameOptionsValue());
        CSPHeader header6 = new CSPHeader(
                CSP.frameAncestors("http://test.com")
        );
        assertEquals(Optional.of("allow-from http://test.com"), header6.getLegacyXFrameOptionsValue());
    }

}
