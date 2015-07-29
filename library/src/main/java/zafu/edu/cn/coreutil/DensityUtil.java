package zafu.edu.cn.coreutil;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 16:06
 */
public class DensityUtil {
    /**
     * 将px值转化为dip值
     * @param context 上下文
     * @param pxValue px的值
     * @return dip的值
     */
    public static int px2dip(Context context,float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip的值转化为px值
     * @param context 上下文
     * @param dipValue dip的值
     * @return px的值
     */
    public static int dip2px(Context context,float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px的值转化为sp的值
     * @param context 上下文
     * @param pxValue px的值
     * @return sp的值
     */
    public static int px2sp(Context context,float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp的值转化为px
     * @param context 上下文
     * @param spValue sp的值
     * @return px的值
     */
    public static int sp2px(Context context,float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕像素密度
     * @param context 上下文
     * @return 屏幕像素密度
     */
    public static float getDensity(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return dm.density;
    }

    /**
     * 获取scaledDensity
     * @param context 上下文
     * @return scaledDensity
     */
    public static float getScaledDensity(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return dm.scaledDensity;
    }
}
