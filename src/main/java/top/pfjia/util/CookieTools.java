package top.pfjia.util;

/**
 * @author pfjia
 * @since 2018/9/26 20:17
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import javax.servlet.http.Cookie;
import java.text.FieldPosition;
import java.util.Date;

public class CookieTools {
    private static final String tspecials = "()<>@,;:\\\"/[]?={} \t";

    public CookieTools() {
    }

    public static String getCookieHeaderName(Cookie cookie) {
        int version = cookie.getVersion();
        return version == 1 ? "Set-Cookie2" : "Set-Cookie";
    }

    /**
     * @deprecated
     */
    public static String getCookieHeaderValue(Cookie cookie) {
        StringBuffer buf = new StringBuffer();
        getCookieHeaderValue(cookie, buf);
        return buf.toString();
    }

    public static void getCookieHeaderValue(Cookie cookie, StringBuffer buf) {
        int version = cookie.getVersion();
        String name = cookie.getName();
        if (name == null) {
            name = "";
        }

        String value = cookie.getValue();
        if (value == null) {
            value = "";
        }

        buf.append(name);
        buf.append("=");
        maybeQuote(version, buf, value);
        if (version == 1) {
            buf.append(";Version=1");
            if (cookie.getComment() != null) {
                buf.append(";Comment=");
                maybeQuote(version, buf, cookie.getComment());
            }
        }

        if (cookie.getDomain() != null) {
            buf.append(";Domain=");
            maybeQuote(version, buf, cookie.getDomain());
        }

        if (cookie.getMaxAge() >= 0) {
            if (version == 0) {
                buf.append(";Expires=");
                if (cookie.getMaxAge() == 0) {
                    DateTool.oldCookieFormat.format(new Date(10000L), buf, new FieldPosition(0));
                } else {
                    DateTool.oldCookieFormat.format(new Date(System.currentTimeMillis() + (long) cookie.getMaxAge() * 1000L), buf, new FieldPosition(0));
                }
            } else {
                buf.append(";Max-Age=");
                buf.append(cookie.getMaxAge());
            }
        } else if (version == 1) {
            buf.append(";Discard");
        }

        if (cookie.getPath() != null) {
            buf.append(";Path=");
            maybeQuote(version, buf, cookie.getPath());
        }

        if (cookie.getSecure()) {
            buf.append(";Secure");
        }

    }

    private static boolean isToken(String value) {
        int len = value.length();

        for (int i = 0; i < len; ++i) {
            char c = value.charAt(i);
            if (c < ' ' || c >= 127 || "()<>@,;:\\\"/[]?={} \t".indexOf(c) != -1) {
                return false;
            }
        }

        return true;
    }

    static void maybeQuote(int version, StringBuffer buf, String value) {
        if (version != 0 && !isToken(value)) {
            buf.append('"');
            buf.append(value);
            buf.append('"');
        } else {
            buf.append(value);
        }

    }
}
