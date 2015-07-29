package zafu.edu.cn.coreutil;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;

import java.lang.reflect.Field;


/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 16:17
 */
public class ScreenUtil {
    /**
     * 获得屏幕宽度，单位px
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return  dm.heightPixels;
    }

    /**
     * 获取通知栏高度
     * @param context 上下文
     * @return 通知栏高度
     */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight=0;
        try {
            Class<?> clazz=Class.forName("com.android.internal.R$dimen");
            Object obj=clazz.newInstance();
            Field field=clazz.getField("status_bar_height");
            int temp= Integer.parseInt(field.get(obj).toString());
            statusBarHeight=context.getResources().getDimensionPixelSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获得标题栏高度
     * @param context 上下文，为Activity对象
     * @return 标题栏高度
     */
    public static int getTitleBarHeight(Activity context){
        int contentTop=context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return contentTop-getStatusBarHeight(context);
    }
}
