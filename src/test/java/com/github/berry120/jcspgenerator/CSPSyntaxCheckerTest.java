/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.berry120.jcspgenerator;

import com.github.berry120.jcspgenerator.CSPSyntaxChecker;
import com.github.berry120.jcspgenerator.CSPDirective;
import com.github.berry120.jcspgenerator.exception.DuplicateDirectivesException;
import com.github.berry120.jcspgenerator.exception.InvalidDirectiveNameException;
import com.github.berry120.jcspgenerator.exception.InvalidDirectiveValueException;
import com.github.berry120.jcspgenerator.exception.NotBase64Exception;
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
        assertThrows(InvalidDirectiveValueException.class, () -> {
            CSPSyntaxChecker.checkNameAndValues("testnameok", new String[]{"valueok", "value notok (contains space)"});
        });
        assertThrows(InvalidDirectiveNameException.class, () -> {
            CSPSyntaxChecker.checkNameAndValues("testname;notok", new String[]{"valueok", "valueok2"});
        });
    }

    @Test
    public void testCheckValues() {
        System.out.println("checkValues");
        CSPSyntaxChecker.checkValues(new String[] {"valueok"});
        CSPSyntaxChecker.checkValues(new String[] {"valueok", "anothervalueok"});
        CSPSyntaxChecker.checkValues(new String[] {"valueok", "specialsymbolok123!\"" });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            CSPSyntaxChecker.checkValues(new String[]{"valueok", "value notok (contains space)"});
        });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            CSPSyntaxChecker.checkValues(new String[]{"valu;eok", "value notok (contains space)"});
        });
        assertThrows(InvalidDirectiveValueException.class, () -> {
            CSPSyntaxChecker.checkValues(new String[]{"value;notok"});
        });
    }

    @Test
    public void testCheckName() {
        System.out.println("checkName");
        CSPSyntaxChecker.checkName("normalname");
        assertThrows(InvalidDirectiveNameException.class, () -> {
            CSPSyntaxChecker.checkName("asd;asd");
        });
        assertThrows(InvalidDirectiveNameException.class, () -> {
            CSPSyntaxChecker.checkName("asd asd");
        });
    }

    @Test
    public void testCheckDuplicateDirectives() {
        System.out.println("checkDuplicateDirectives");
        CSPSyntaxChecker.checkDuplicateDirectives(new CSPDirective("onename"), new CSPDirective("anothername"));
        assertThrows(DuplicateDirectivesException.class, () -> {
            CSPSyntaxChecker.checkDuplicateDirectives(new CSPDirective("onename"), new CSPDirective("anothername"), new CSPDirective("anothername"));
        });
    }

    @Test
    public void testCheckBase64() {
        System.out.println("checkBase64");
        CSPSyntaxChecker.checkBase64("MTIz", 3);
        CSPSyntaxChecker.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5", 36);
        CSPSyntaxChecker.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5");
        CSPSyntaxChecker.checkBase64("", 0);
        CSPSyntaxChecker.checkBase64("YQ==", 1);
        assertThrows(NotBase64Exception.class, () -> {
            CSPSyntaxChecker.checkBase64("MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5MTIzNDU2Nzg5", 1);
        });
        assertThrows(NotBase64Exception.class, () -> {
            CSPSyntaxChecker.checkBase64("$$", -1);
        });
        assertThrows(NotBase64Exception.class, () -> {
            CSPSyntaxChecker.checkBase64("notbase64atall!!!!");
        });
        assertThrows(NotBase64Exception.class, () -> {
            CSPSyntaxChecker.checkBase64("MTIz", 0);
        });
    }

}
