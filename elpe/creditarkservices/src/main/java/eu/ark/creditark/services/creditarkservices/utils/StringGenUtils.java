package eu.ark.creditark.services.creditarkservices.utils;

import java.text.MessageFormat;

public class StringGenUtils {
    public static String format(String template, String... values) {
        return MessageFormat.format(template, values);
    }
}
