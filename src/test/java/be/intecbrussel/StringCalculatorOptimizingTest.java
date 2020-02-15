package be.intecbrussel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCalculatorOptimizingTest {

    StringCalculatorOptimizing sco = new StringCalculatorOptimizing();

    @Test
    void testEmptyString () {
        Assertions.assertEquals(0, sco.add(""));
    }

    @Test
    void testSingleNumber () {
        Assertions.assertEquals(1, sco.add("1"));
    }

    @Test
    void testMultipleNumbers () {
        Assertions.assertEquals(3, sco.add("1,2"));
    }

    @Test
    void testNewLineSeperator () {
        Assertions.assertEquals(6, sco.add("1,2\n3"));
    }

    @Test
    void testCustomDelimiter () {
        Assertions.assertEquals(3, sco.add("//;\n1;2"));
    }

    @Test
    void testNegativeNumberError () {
        Assertions.assertDoesNotThrow(() -> sco.add("-15, 20"));
    }

    @Test
    void testMultipleNegativeNumbers () {
        Assertions.assertDoesNotThrow(() -> sco.add("-15,20,-5,-20"));
    }

    @Test
    void testGetCalledCount () {
        sco.add("50");
        sco.add("30,12,1");
        Assertions.assertNotNull(sco.callCount());
    }

    @Test
    void testIgnoreThousandUp () {
        Assertions.assertEquals(2, sco.add("2,1001"));
    }

    @Test
    void testMultipleCharacterLongDelimiters() {
        Assertions.assertEquals(6, sco.add("//[***]\n1***2***3"));
    }

    @Test
    void testMultipleSingleCharacterDelimiters () {
        Assertions.assertEquals(6, sco.add("//[*][%]\n1*2%3"));
    }
    /*
    Allow multiple delimiters like this:
    “//[delim1][delim2]\n”
    for example
    “//[*][%]\n1*2%3” == 6
     */

    @Test
    void testMultipleMultipleCharacterDelimiters () {
        Assertions.assertEquals(74, sco.add("//[**][%%]\n1**20%%53"));
    }

}