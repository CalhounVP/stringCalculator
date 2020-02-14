package be.intecbrussel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator sc = new StringCalculator();

    @Test
    void testEmptyString () {
        Assertions.assertEquals(0, sc.add(""));
    }

    @Test
    void testSingleNumber () {
        Assertions.assertEquals(1, sc.add("1"));
    }

    @Test
    void testMultipleNumbers () {
        Assertions.assertEquals(3, sc.add("1,2"));
    }

    @Test
    void testNewLineSeperator () {
        Assertions.assertEquals(6, sc.add("1,2\n3"));
    }

    @Test
    void testCustomDelimiter () {
        Assertions.assertEquals(3, sc.add("//;\n1;2"));
    }

    @Test
    void testNegativeNumberError () {
        Assertions.assertDoesNotThrow(() -> sc.add("-15, 20"));
    }

    @Test
    void testMultipleNegativeNumbers () {
        Assertions.assertDoesNotThrow(() -> sc.add("-15,20,-5,-20"));
    }

    @Test
    void testGetCalledCount () {
        sc.add("50");
        sc.add("30,12,1");
        Assertions.assertNotNull(sc.callCount());
    }

    @Test
    void testIgnoreThousandUp () {
        Assertions.assertEquals(2, sc.add("2,1001"));
    }

    @Test
    void testMultipleDelimiters() {
        Assertions.assertEquals(6, sc.add("//[***]\n1***2***3"));
    }

    @Test
    void testMultipleCharacterDelimiters () {
        Assertions.assertEquals(6, sc.add("//[*][%]\n1*2%3"));
    }
    /*
    Allow multiple delimiters like this:
    “//[delim1][delim2]\n”
    for example
    “//[*][%]\n1*2%3” == 6
     */

    //@Test
    void testMultipleMultipleCharacterDelimiters () {

    }
    /*
    make sure you can also handle multiple delimiters with length longer than one char
    for example
    “//[**][%%]\n1**2%%3” =
     */

}