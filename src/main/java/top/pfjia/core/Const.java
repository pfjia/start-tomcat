package top.pfjia.core;

import java.io.File;

/**
 * @author pfjia
 * @since 2018/9/26 17:17
 */
public class Const {
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = CR + LF;
    /**
     * WEB_ROOT is the directory where our HTML and other files reside.
     * For this package, WEB_ROOT is the "webroot" directory under the working
     * directory.
     * The working directory is the location in the file system
     * from where the java command was invoked.
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "webapp";
    /**
     * WEB_ROOT is the directory where our HTML and other files reside.
     * For this package, WEB_ROOT is the "webroot" directory under the working
     * directory.
     * The working directory is the location in the file system
     * from where the java command was invoked.
     */
    // shutdown command
    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    public static final String SERVLET = "/servlet/";
}
