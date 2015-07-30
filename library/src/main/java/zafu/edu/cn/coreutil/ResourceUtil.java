package zafu.edu.cn.coreutil;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 读取res目录下的内容的工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 17:33
 */
public class ResourceUtil {
    /**
     * 从assets目录下读取文件内容
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文件字节流
     */
    public static byte[] readBytesFromAssets(Context context, String fileName) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getAssets().open(fileName);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从res/raw目录下读取文件内容
     *
     * @param context 上下文
     * @param rawId   rawId
     * @return 文件字节流
     */
    public static byte[] readBytesFromRaw(Context context, int rawId) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = context.getResources().openRawResource(rawId);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从assets目录读取文本
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 文本内容
     */
    public static String readStringFromAssets(Context context, String fileName) {
        String result = null;
        byte[] buffer = readBytesFromAssets(context, fileName);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从raw目录读取文本
     *
     * @param context 上下文
     * @param rawId   id值
     * @return 文本内容
     */
    public static String readStringFromRaw(Context context, int rawId) {
        String result = null;
        byte[] buffer = readBytesFromRaw(context, rawId);
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获得字符串
     * @param context 上下文
     * @param strId 字符串id
     * @return 字符串
     */
    public static String getString(Context context,int strId){
        return context.getResources().getString(strId);
    }

    /**
     * 获得颜色
     * @param context 上下文
     * @param colorId 颜色id
     * @return 颜色
     */
    public static int getColor(Context context,int colorId){
        return context.getResources().getColor(colorId);
    }

    /**
     * 获得Drawable
     * @param context 上下文
     * @param drawableId Drawable的id
     * @return Drawable
     */
    public static Drawable getDrawable(Context context,int drawableId){
        return context.getResources().getDrawable(drawableId);
    }
}
