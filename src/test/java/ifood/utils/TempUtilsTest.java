package ifood.utils;

import ifood.config.MvcTest;
import org.junit.Assert;
import org.junit.Test;

public class TempUtilsTest extends MvcTest {

    @Test
    public void absoluteZeroTest() {
        final double expected = -273.15;
        final double input = -459.67;

        final double actual = TempUtils.convertFahrenheitToCelcius(input);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void zeroCelsiusTest() {
        final double expected = 0;
        final double input = 32;

        final double actual = TempUtils.convertFahrenheitToCelcius(input);
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void hundredCelsiusTest() {
        final double expected = 100;
        final double input = 212;

        final double actual = TempUtils.convertFahrenheitToCelcius(input);
        Assert.assertEquals(expected, actual, 0);
    }
}
