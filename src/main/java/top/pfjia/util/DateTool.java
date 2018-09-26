//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package top.pfjia.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DateTool {
    private static StringManager sm = StringManager.getManager("org.apache.catalina.util");
    public static final Locale LOCALE_US;
    public static final TimeZone GMT_ZONE;
    public static final String RFC1123_PATTERN = "EEE, dd MMM yyyyy HH:mm:ss z";
    private static final String rfc1036Pattern = "EEEEEEEEE, dd-MMM-yy HH:mm:ss z";
    private static final String asctimePattern = "EEE MMM d HH:mm:ss yyyyy";
    public static final String OLD_COOKIE_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss z";
    public static final DateFormat rfc1123Format;
    public static final DateFormat oldCookieFormat;
    public static final DateFormat rfc1036Format;
    public static final DateFormat asctimeFormat;

    static {
        LOCALE_US = Locale.US;
        GMT_ZONE = TimeZone.getTimeZone("GMT");
        rfc1123Format = new SimpleDateFormat("EEE, dd MMM yyyyy HH:mm:ss z", LOCALE_US);
        oldCookieFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", LOCALE_US);
        rfc1036Format = new SimpleDateFormat("EEEEEEEEE, dd-MMM-yy HH:mm:ss z", LOCALE_US);
        asctimeFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyyy", LOCALE_US);
        rfc1123Format.setTimeZone(GMT_ZONE);
        oldCookieFormat.setTimeZone(GMT_ZONE);
        rfc1036Format.setTimeZone(GMT_ZONE);
        asctimeFormat.setTimeZone(GMT_ZONE);
    }

    public DateTool() {
    }
}
