package zafu.edu.cn.coreutil;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 15:51
 */
public class ManifestUtil {
    /**
     * 获得包名
     * @param context 上下文
     * @return 包名
     */
    public static String getPackageName(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获得版本名
     * @param context 上下文
     * @return 版本名
     */
    public static String getVersionName(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得版本号
     * @param context 上下文
     * @return 版本号
     */
    public static int getVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取application层级的metadata
     * @param context 上下文
     * @param key key
     * @return value
     */
    public static String getApplicationMetaData(Context context,String key){
        try {
            Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            return metaData.get(key).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
