package be.intecbrussel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    public void testEmptyString () {
        StringCalculator sc = new StringCalculator();
        Assertions.assertEquals(0,sc.add(null));
    }

    @Test
    public void testAddAccept1Number () {
        StringCalculator sc = new StringCalculator();
        Assertions.assertEquals(1,sc.add("1"));
    }

    @Test
    public void testAddMultipleNumbers () {
        StringCalculator sc = new StringCalculator();
        Assertions.assertEquals(3,sc.add("1,2"));
    }

    @Test
    public void testNewLines () {
        StringCalculator sc = new StringCalculator();
        Assertions.assertEquals(3,sc.add("1 \n2"));
    }

    @Test
    public void testDelimiters() {
        StringCalculator sc = new StringCalculator();
        Assertions.assertEquals(3,sc.add("//;\n1;2"));
    }

    @Test
    public void testAddNegativeNumbers () {
        StringCalculator sc = new StringCalculator();
        Assertions.assertThrows(IllegalArgumentException.class, () -> sc.add("-15, 20") );
    }



}