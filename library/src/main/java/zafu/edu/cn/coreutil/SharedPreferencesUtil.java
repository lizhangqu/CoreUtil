package zafu.edu.cn.coreutil;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 17:54
 */
public class SharedPreferencesUtil {
    /**
     * SharedPreferences兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

    /**
     * 保存键值对
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @param value    value
     */
    public static void set(Context context, String fileName, String key,
                           String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 获取键对应的值
     *
     * @param context      上下文
     * @param fileName     文件名
     * @param key          key
     * @param defaultValue 默认值，无对应value时返回
     * @return value
     */
    public static String get(Context context, String fileName, String key,
                             String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defaultValue);// 第二个参数为默认值
        return value;
    }

    /**
     * 获取键对应的值，找不到则返回""
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @return value
     */
    public static String get(Context context, String fileName, String key) {
        return get(context, fileName, key, "");
    }

    /**
     * 移除key对应的项
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     */
    public static void remove(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context  上下文
     * @param fileName 文件名
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key对应的项是否存在
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @return 是否存在
     */
    public static boolean contatins(Context context, String fileName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有键值对
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return Map组成的键值对
     */
    public static Map<String, ?> getAll(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

}
