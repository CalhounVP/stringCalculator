package be.intecbrussel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    StringCalculator sco = new StringCalculator();

//     1. In a test-first manner, create a simple class class StringCalculator
//    with a method public int Add(string numbers)
//        1. The method can take 0, 1 or 2 numbers, and will return their sum
//        (for an empty string it will return 0) for example:
//        “” == 0 , “1” == 1 , “1,2” == 3
//        2. Start with the simplest test case of an empty string and move to one & two numbers
//        3. Remember to solve things as simply as possible so that you force yourself to
//        write tests you did not think about
//        4. Remember to refactor after each passing test

    @Test
    void testEmptyString () {
        Assertions.assertEquals(0, sco.add(""));
    }

    @Test
    void testSingleNumber () {
        Assertions.assertEquals(1, sco.add("1"));
    }

    //2. Allow the Add method to handle an unknown amount of numbers

    @Test
    void testMultipleNumbers () {
        Assertions.assertEquals(3, sco.add("1,2"));
    }

//    3. Allow the Add method to handle new lines between numbers (instead of commas).
//            1. the following input is ok: “1\n2,3” == 6
//            2. the following is INVALID input so do not expect it : “1,\n” (not need to write a test for it)

    @Test
    void testNewLineSeperator () {
        Assertions.assertEquals(6, sco.add("1,2\n3"));
    }

//    4. Support different delimiters:
//    to change a delimiter, the beginning of the string will contain a separate line
//    that looks like this:
//            “//[delimiter]\n[numbers…]”
//            for example:
//             “//;\n1;2” == 3
//    since the default delimiter is ‘;’ .
//    Note: All existing scenarios and tests should still be supported

    @Test
    void testCustomDelimiter () {
        Assertions.assertEquals(3, sco.add("//;\n1;2"));
    }

//    5. Calling Add with a negative number will throw an exception “negatives not allowed” -
//    and the negative that was passed.

    @Test
    void testNegativeNumberError () {
        Assertions.assertDoesNotThrow(() -> sco.add("-15, 20"));
    }

//    6. If there are multiple negatives, show all of them in the exception message

    @Test
    void testMultipleNegativeNumbers () {
        Assertions.assertDoesNotThrow(() -> sco.add("-15,20,-5,-20"));
    }

//    7. Using TDD, Add a method to StringCalculator
//    called public int GetCalledCount()
//    that returns how many times Add() was invoked.
//    Remember - Start with a failing (or even non compiling) test.

    @Test
    void testGetCalledCount () {
        sco.add("50");
        sco.add("30,12,1");
        Assertions.assertNotNull(sco.callCount());
    }

//    9. Numbers bigger than 1000 should be ignored, for example:
//            2 + 1001 == 2


    @Test
    void testIgnoreThousandUp () {
        Assertions.assertEquals(2, sco.add("2,1001"));
    }

//    10. Delimiters can be of any length with the following format:
//            “//[delimiter]\n”
//            for example:
//            “//[***]\n1***2***3” ==

    @Test
    void testMultipleCharacterLongDelimiters() {
        Assertions.assertEquals(6, sco.add("//[***]\n1***2***3"));
    }

//    11. Allow multiple delimiters like this:
//            “//[delim1][delim2]\n”
//            for example
//            “//[*][%]\n1*2%3” == 6.

    @Test
    void testMultipleSingleCharacterDelimiters () {
        Assertions.assertEquals(6, sco.add("//[*][%]\n1*2%3"));
    }

//    12. make sure you can also handle multiple delimiters with length longer than one char
//    for example
//    “//[**][%%]\n1**2%%3” == 6.

    @Test
    void testMultipleMultipleCharacterDelimiters () {
        Assertions.assertEquals(74, sco.add("//[**][%%]\n1**20%%53"));
    }

}