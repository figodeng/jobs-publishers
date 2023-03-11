package figo.external.jobs.official.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");

    public static Date convert(String str) {
        try {
            return DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }
}
