package zafu.edu.cn.coreutil;

import android.util.Log;

/**
 * 日志工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-30
 * Time: 10:06
 */
public class LogUtil {
    private static final String DEFAULT_TAG = "CoreUtil";
    private static final boolean DEFAULT_DEBUGABLE = true;
    public static String LOG_TAG = DEFAULT_TAG;
    private static boolean debugable = DEFAULT_DEBUGABLE;

    public static String getLogTag() {
        return LOG_TAG;
    }

    public static void setLogTag(String logTag) {
        LOG_TAG = logTag;
    }

    public static String getDefaultTag() {
        return DEFAULT_TAG;
    }

    public static void i(String tag, String msg) {
        if (!debugable)
            return;
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!debugable)
            return;
        Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (!debugable)
            return;
        Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (!debugable)
            return;
        Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!debugable)
            return;
        Log.w(tag, msg);
    }

    public static void println(int priority, String tag, String msg) {
        if (!debugable)
            return;
        Log.println(priority, tag, msg);
    }

    public static void i(String msg) {
        if (!debugable)
            return;
        Log.i(LOG_TAG, msg);
    }

    public static void e(String msg) {
        if (!debugable)
            return;
        Log.e(LOG_TAG, msg);
    }

    public static void d(String msg) {
        if (!debugable)
            return;
        Log.d(LOG_TAG, msg);
    }

    public static void v(String msg) {
        if (!debugable)
            return;
        Log.v(LOG_TAG, msg);
    }

    public static void w(String msg) {
        if (!debugable)
            return;
        Log.w(LOG_TAG, msg);
    }

    public static void println(int priority, String msg) {
        if (!debugable)
            return;
        Log.println(priority, LOG_TAG, msg);
    }
}
