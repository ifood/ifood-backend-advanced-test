package com.ifood.businessrules;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CategoryConversionTest {
    private static final int THIRTY_IN_CELSIUS = 86; //fahrenheit
    private static final int FIFTEEN_IN_CELSIUS = 59;
    private static final int TEN_IN_CELSIUS = 50;
    private static final int ZERO_IN_CELSIUS = 32;
    private static CategoryConversion categoryConversion;

    @BeforeClass
    public static void init() {
        categoryConversion = new CategoryConversion();
    }

    @Test public void
    assureTemperatureAndCategory() throws Exception {
        assertTemperatureAndCategory(THIRTY_IN_CELSIUS, CategoryConversion.PARTY);
        assertTemperatureAndCategory(FIFTEEN_IN_CELSIUS, CategoryConversion.POP);
        assertTemperatureAndCategory(TEN_IN_CELSIUS, CategoryConversion.ROCK);
        assertTemperatureAndCategory(ZERO_IN_CELSIUS, CategoryConversion.CLASSICAL);
    }

    private void assertTemperatureAndCategory(int temperature, String category) {
        assertThat(categoryConversion.getCategory(temperature), is(category));
    }
}
