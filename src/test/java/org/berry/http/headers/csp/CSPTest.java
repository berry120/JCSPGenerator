/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Base64;
import org.berry.http.headers.csp.exception.DuplicateDirectivesException;
import org.berry.http.headers.csp.exception.NotBase64Exception;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Michael
 */
public class CSPTest {


    @Test
    public void testNonce() {
        System.out.println("nonce");
        String nonce = CSP.nonce("dGVzdA==");
        assertEquals(nonce, "'nonce-dGVzdA=='");
    }

    @Test
    public void testShaError() {
        System.out.println("sha256-error");
        assertThrows(NotBase64Exception.class, () -> {
            CSP.sha256("VdkzWZUNW5N1sO6w8ENtu/2JMSlJXF88dICyE=");
        });
    }

    @Test
    public void testSha256() {
        System.out.println("sha256");
        String val = CSP.sha256("VdkzWZUNW5N1sO6w8ENtuq00L17/2JMSlJXF88dICyE=");
        assertEquals(val, "'sha256-VdkzWZUNW5N1sO6w8ENtuq00L17/2JMSlJXF88dICyE='");
    }

    @Test
    public void testSha384() {
        System.out.println("sha384");
        String val = CSP.sha384("6LSRUCDXvwgdyptakzhqAFOXil+cPcFj1Leeb6y02jJKjtOhx9d9jy7zGNGTANG+");
        assertEquals(val, "'sha384-6LSRUCDXvwgdyptakzhqAFOXil+cPcFj1Leeb6y02jJKjtOhx9d9jy7zGNGTANG+'");
    }

    @Test
    public void testSha512() {
        System.out.println("sha512");
        String val = CSP.sha512("HxDcS8kNShzP9cw+7CJMOOPMShEUKnxeml0e51641kciPLZ9J7eRo53tSqW0iO+oazVjtdysN/HkElrBX92VCw==");
        assertEquals(val, "'sha512-HxDcS8kNShzP9cw+7CJMOOPMShEUKnxeml0e51641kciPLZ9J7eRo53tSqW0iO+oazVjtdysN/HkElrBX92VCw=='");
    }
    
    @Test
    public void testBaseUri() {
        System.out.println("baseUri");
        assertEquals(new CSPDirective("base-uri", "https://test.com"), CSP.baseUri("https://test.com"));
    }

    @Test
    public void testBlockAllMixedContent() {
        System.out.println("blockAllMixedContent");
        assertEquals(new CSPDirective("block-all-mixed-content"), CSP.blockAllMixedContent());
    }

    @Test
    public void testChildSrc() {
        System.out.println("childSrc");
        assertEquals(new CSPDirective("child-src", "https://test.com"), CSP.childSrc("https://test.com"));
    }

    @Test
    public void testConnectSrc() {
        System.out.println("connectSrc");
        assertEquals(new CSPDirective("connect-src", "https://test.com"), CSP.connectSrc("https://test.com"));
    }

    @Test
    public void testDefaultSrc() {
        System.out.println("defaultSrc");
        assertEquals(new CSPDirective("default-src", "https://test.com"), CSP.defaultSrc("https://test.com"));
    }

    @Test
    public void testFontSrc() {
        System.out.println("fontSrc");
        assertEquals(new CSPDirective("font-src", "https://test.com"), CSP.fontSrc("https://test.com"));
    }

    @Test
    public void testFormAction() {
        System.out.println("formAction");
        assertEquals(new CSPDirective("form-action", "https://test.com"), CSP.formAction("https://test.com"));
    }

    @Test
    public void testFrameAncestors() {
        System.out.println("frameAncestors");
        assertEquals(new CSPDirective("frame-ancestors", "https://test.com"), CSP.frameAncestors("https://test.com"));
    }

    @Test
    public void testFrameSrc() {
        System.out.println("frameSrc");
        assertEquals(new CSPDirective("frame-src", "https://test.com"), CSP.frameSrc("https://test.com"));
    }

    @Test
    public void testImgSrc() {
        System.out.println("imgSrc");
        assertEquals(new CSPDirective("img-src", "https://test.com"), CSP.imgSrc("https://test.com"));
    }

    @Test
    public void testManifestSrc() {
        System.out.println("minfestSrc");
        assertEquals(new CSPDirective("manifest-src", "https://test.com"), CSP.manifestSrc("https://test.com"));
    }

    @Test
    public void testMediaSrc() {
        System.out.println("mediaSrc");
        assertEquals(new CSPDirective("media-src", "https://test.com"), CSP.mediaSrc("https://test.com"));
    }

    @Test
    public void testObjectSrc() {
        System.out.println("objectSrc");
        assertEquals(new CSPDirective("object-src", "https://test.com"), CSP.objectSrc("https://test.com"));
    }

    @Test
    public void testPluginTypes() {
        System.out.println("pluginTypes");
        assertEquals(new CSPDirective("plugin-types", "https://test.com"), CSP.pluginTypes("https://test.com"));
    }

    @Test
    public void testReferrer() {
        System.out.println("referrer");
        assertEquals(new CSPDirective("referrer", "https://test.com"), CSP.referrer("https://test.com"));
    }

    @Test
    public void testReportTo() {
        System.out.println("reportTo");
        assertEquals(new CSPDirective("report-to", "https://test.com"), CSP.reportTo("https://test.com"));
    }

    @Test
    public void testReportUri() {
        System.out.println("reportUri");
        assertEquals(new CSPDirective("report-uri", "https://test.com"), CSP.reportUri("https://test.com"));
    }

    @Test
    public void testRequireSriFor() {
        System.out.println("requireSriFor");
        assertEquals(new CSPDirective("require-sri-for", "https://test.com"), CSP.requireSriFor("https://test.com"));
    }

    @Test
    public void testSandbox() {
        System.out.println("sandbox");
        assertEquals(new CSPDirective("sandbox", "https://test.com"), CSP.sandbox("https://test.com"));
    }

    @Test
    public void testScriptSrc() {
        System.out.println("scriptSrc");
        assertEquals(new CSPDirective("script-src", "https://test.com"), CSP.scriptSrc("https://test.com"));
    }

    @Test
    public void testStyleSrc() {
        System.out.println("styleSrc");
        assertEquals(new CSPDirective("style-src", "https://test.com"), CSP.styleSrc("https://test.com"));
    }

    @Test
    public void testUpgradeInsecureRequests() {
        System.out.println("upgradeInsecureRequests");
        assertEquals(new CSPDirective("upgrade-insecure-requests"), CSP.upgradeInsecureRequests());
    }

    @Test
    public void testWorkerSrc() {
        System.out.println("workerSrc");
        assertEquals(new CSPDirective("worker-src", "https://test.com"), CSP.workerSrc("https://test.com"));
    }

}
