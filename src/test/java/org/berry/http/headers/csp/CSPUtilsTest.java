/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import java.util.Base64;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Michael
 */
public class CSPUtilsTest {


    @Test
    public void testGenerateNonce() {
        System.out.println("generateNonce");
        String nonce = CSPUtils.generateNonce();
        String nonce2 = CSPUtils.generateNonce();
        assertEquals(128/8, Base64.getDecoder().decode(nonce).length);
        assertEquals(128/8, Base64.getDecoder().decode(nonce2).length);
        assertNotEquals(nonce, nonce2);
    }

    @Test
    public void testHashSha() {
        System.out.println("hashSha");
        assertEquals("VdkzWZUNW5N1sO6w8ENtuq00L17/2JMSlJXF88dICyE=", CSPUtils.hashSha256("randomstr"));
        assertEquals("6LSRUCDXvwgdyptakzhqAFOXil+cPcFj1Leeb6y02jJKjtOhx9d9jy7zGNGTANG+", CSPUtils.hashSha384("randomstr"));
        assertEquals("HxDcS8kNShzP9cw+7CJMOOPMShEUKnxeml0e51641kciPLZ9J7eRo53tSqW0iO+oazVjtdysN/HkElrBX92VCw==", CSPUtils.hashSha512("randomstr"));
    }

    @Test
    public void testHashInvalid() {
        System.out.println("hash (invalid)");
        assertThrows(IllegalArgumentException.class, () -> {
            CSPUtils.hash("randomstr", "this alogirhtm doesn't exist");
        });
    }

}
