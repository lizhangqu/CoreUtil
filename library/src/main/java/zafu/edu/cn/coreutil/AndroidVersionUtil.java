package zafu.edu.cn.coreutil;

import android.os.Build;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 16:37
 */
public class AndroidVersionUtil {
    /**
     * 是否在2.2版本及以上
     * @return 是否在2.2版本及以上
     */
    public static boolean isFroyo(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.FROYO;
    }

    /**
     * 是否在2.3版本及以上
     * @return 是否在2.3版本及以上
     */
    public static boolean isGingerbread(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD;
    }
    /**
     * 是否在3.0版本及以上
     *
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 是否在3.1版本及以上
     *
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * 是否在4.1版本及以上
     *
     * @return
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
}
