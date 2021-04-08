package eu.ark.creditark.services.creditarkservices.utils;

import java.text.MessageFormat;

public class StringGenUtils {
    public static String format(String template, String... values) {
        return MessageFormat.format(template, values);
    }

    public static String formatStringToDoubleFormat(String value) {
        if(null == value || value.length()==0) return "";
        if(value.endsWith("%")) return value;
        return DtoGenUtils.numberToStringUIFormated(DtoGenUtils.stringToDouble(value));

    }
}
