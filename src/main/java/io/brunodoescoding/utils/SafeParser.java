package io.brunodoescoding.utils;

import com.google.common.base.Strings;

public final class SafeParser {
    public static Double toDouble(String value) {
        if(Strings.isNullOrEmpty(value)) {
            return null;
        }

        Double convertedValue = null;

        try {
            convertedValue = Double.parseDouble(value);
        } catch(NumberFormatException nfe) {
            System.out.println(String.format("Problems while parsing value %s to double.", value));
        }

        return convertedValue;
    }

    public static boolean isDouble(String value) {
        return toDouble(value) != null;
    }

}
