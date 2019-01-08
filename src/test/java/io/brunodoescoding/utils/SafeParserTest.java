package io.brunodoescoding.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SafeParserTest {

    @Test
    public void testToDoubleWhenValueIsNull() {
        assertEquals(null, SafeParser.toDouble(null));
    }

    @Test
    public void testToDoubleWhenValueIsEmpty() {
        assertEquals(null, SafeParser.toDouble(""));
    }

    @Test
    public void testToDoubleWhenValueIsInvalid() {
        assertEquals(null, SafeParser.toDouble("invalid"));
    }

    @Test
    public void testToDoubleWhenValueIsValid() {
        assertEquals(1.0, SafeParser.toDouble("1.0"), 0.00001);
    }

    @Test
    public void testToDoubleWhenValueIsMaximumValue() {
        assertEquals(Double.MAX_VALUE, SafeParser.toDouble(String.valueOf(Double.MAX_VALUE)), 0.00001);
    }

    @Test
    public void testIsDoubleWhenValueIsNull() {
        assertEquals(false, SafeParser.isDouble(null));
    }

    @Test
    public void testIsDoubleWhenValueIsEmpty() {
        assertEquals(false, SafeParser.isDouble(""));
    }

    @Test
    public void testIsDoubleWhenValueIsInvalid() {
        assertEquals(false, SafeParser.isDouble("invalid"));
    }

    @Test
    public void testIsDoubleWhenValueIsValid() {
        assertEquals(true, SafeParser.isDouble("1.0"));
    }

    @Test
    public void testIsDoubleWhenValueIsMaximumValue() {
        assertEquals(true, SafeParser.isDouble(String.valueOf(Double.MAX_VALUE)));
    }

}
