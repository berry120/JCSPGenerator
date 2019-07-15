/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.http.headers.csp;

import org.berry.http.headers.csp.exception.DuplicateDirectivesException;
import org.berry.http.headers.csp.exception.InvalidDirectiveNameException;
import org.berry.http.headers.csp.exception.InvalidDirectiveValueException;
import org.berry.http.headers.csp.exception.NotBase64Exception;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Michael
 */
public class CSPSyntaxCheckerTest {

    @Test
    public void testCheckNameAndValues() {
        System.out.println("checkNameAndValues");
        CSPSyntaxChecker instance = new CSPSyntaxChecker();
        assertThrows(InvalidDirectiveValueException.class, () -> {
            instance.checkNameAndValues("testnameok", new String[]{"valueok", "value notok (contains space)"});
        });
    }

    @Test
    public void testCheckValues() {
        System.out.println("checkValues");
        CSPSyntaxChecker instance = new CSPSyntaxChecker();
        instance.checkValues(new String[] {"valueok"});
        instance.checkValues(new String[] {"valueok", "anothervalueok"});
        instance.checkValues(new String[] {"valueok", "specialsymbolok123!\"" });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            instance.checkValues(new String[]{"valueok", "value notok (contains space)"});
        });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            instance.checkValues(new String[]{"valu;eok", "value notok (contains space)"});
        });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            instance.checkValues(new String[]{"value;notok"});
        });
    }

    @Test
    public void testCheckName() {
        System.out.println("checkName");
        CSPSyntaxChecker instance = new CSPSyntaxChecker();
        instance.checkName("normalname");
        assertThrows(InvalidDirectiveNameException.class, () -> {
            instance.checkName("asd;asd");
        });
        assertThrows(InvalidDirectiveNameException.class, () -> {
            instance.checkName("asd asd");
        });
    }

    @Test
    public void testCheckDuplicateDirectives() {
        System.out.println("checkDuplicateDirectives");
        CSPSyntaxChecker instance = new CSPSyntaxChecker();
        instance.checkDuplicateDirectives(new CSPDirective("onename"), new CSPDirective("anothername"));
        assertThrows(DuplicateDirectivesException.class, () -> {
            instance.checkDuplicateDirectives(new CSPDirective("onename"), new CSPDirective("anothername"), new CSPDirective("anothername"));
        });
    }

    @Test
    public void testCheckBase64() {
        System.out.println("checkBase64");
        CSPSyntaxChecker instance = new CSPSyntaxChecker();
        instance.checkBase64("MTIz", 3);
        instance.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5", 36);
        instance.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5");
        assertThrows(NotBase64Exception.class, () -> {
            instance.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5", 1);
            instance.checkBase64("$$", -1);
        });
        assertThrows(NotBase64Exception.class, () -> {
            instance.checkBase64("notbase64atall!!!!");
            instance.checkBase64("$$", -1);
        });
    }

}
