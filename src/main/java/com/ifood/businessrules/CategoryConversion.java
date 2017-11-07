package com.ifood.businessrules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Category conversion: translate temperature to category.
 */
@Component
public class CategoryConversion {
    private static final float PARTY_TEMPERATURE = (float) 30;
    private static final float POP_TEMPERATURE = (float) 15;
    private static final float ROCK_TEMPERATURE = (float) 10;
    public static final String PARTY = "party";
    public static final String POP = "pop";
    public static final String ROCK = "rock";
    public static final String CLASSICAL = "classical";

    private final Logger logger = LoggerFactory.getLogger(CategoryConversion.class);

    /**
     * Receives the temperature in Fahrenheit and returns the category.
     *
     * @param temperature temperature value
     * @return category name.
     */
    public String getCategory(float temperature) {
        final float t = convertFahrenheitToCelsius(temperature);
        logger.info("Convert {} F to {} C.", temperature, t);

        final String category = translateTemperatureToCategory(t);
        logger.info("{} is translated to {}", t, category);

        return category;
    }

    private float convertFahrenheitToCelsius(float temperature) {
        return (temperature - (float) 32) * (5 / (float) 9);
    }

    private String translateTemperatureToCategory(float temperature) {
        if (Float.compare(temperature, PARTY_TEMPERATURE) >= 0)
            return PARTY;
        else if (Float.compare(temperature, POP_TEMPERATURE) >= 0)
            return POP;
        else if (Float.compare(temperature, ROCK_TEMPERATURE) >= 0)
            return ROCK;

        return CLASSICAL;
    }
}
