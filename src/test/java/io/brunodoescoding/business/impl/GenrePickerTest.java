package io.brunodoescoding.business.impl;

import io.brunodoescoding.business.GenreStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenrePickerTest {

    @Test
    public void testWhenApplyingSummerTemperature() {
        GenreStrategy found = GenrePicker.pick(33.0);

        assertNotNull(found);
        assertEquals("party", found.getGenre());
    }

    @Test
    public void testWhenApplyingSpringTemperature() {
        GenreStrategy found = GenrePicker.pick(28.0);

        assertNotNull(found);
        assertEquals("pop", found.getGenre());
    }

    @Test
    public void testWhenApplyingChillTemperature() {
        GenreStrategy found = GenrePicker.pick(13.0);

        assertNotNull(found);
        assertEquals("rock", found.getGenre());
    }

    @Test
    public void testWhenApplyingFreezingTemperature() {
        GenreStrategy found = GenrePicker.pick(-2.0);

        assertNotNull(found);
        assertEquals("classical", found.getGenre());
    }

}
