package zafu.edu.cn.coreutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 截屏工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-08-04
 * Time: 12:40
 */
public class ScreenShotUtil {

    /**
     * / 获取指定Activity的截屏，保存到png文件
     *
     * @param activity activity
     * @return 截屏Bitmap
     */
    private static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    /**
     * 保存bitmap
     *
     * @param b           bitmap
     * @param strFileName 文件名
     * @return 是否保存成功
     */
    private static boolean savePic(Bitmap b, String strFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFileName);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 截取webView快照(webView加载的整个内容的大小)
     *
     * @param webView webview
     * @return 截屏bitmap
     */
    private static Bitmap captureWebView(WebView webView) {
        Picture snapShot = webView.capturePicture();

        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(), snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }

    /**
     * 根据毫秒获得格式化日期
     *
     * @param time   毫秒数
     * @param format 格式化字符串
     * @return 格式化后的字符串
     */
    private static String getDate(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String daystr = formatter.format(date);
        return daystr;
    }

    /**
     * 是否存在sd卡
     *
     * @return 是否存在sd卡
     */
    private static Boolean isExistsSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    /**
     * 获得文件名
     *
     * @param context 上下文
     * @return 文件名
     */
    private static String getFileName(Context context) {
        String fileName = getDate(System.currentTimeMillis(), "yyyyMMddHHmmss") + ".png";
        final String localPath;
        if (isExistsSD()) {
            localPath = context.getExternalCacheDir() + File.separator + fileName;
        } else {
            localPath = context.getFilesDir() + fileName;
        }

        return localPath;
    }

    /**
     * 截屏并保存
     *
     * @param a activity
     * @return 保存的路径
     */
    public static String shoot(Activity a) {
        String localPath = getFileName(a);
        boolean ret = ScreenShotUtil.savePic(ScreenShotUtil.takeScreenShot(a), localPath);
        if (ret) {
            return localPath;
        } else {
            return "";
        }
    }

    /**
     * 截屏并保存
     *
     * @param context 上下文
     * @param webView webview
     * @return 保存的路径
     */
    public static String shootWebView(Context context, WebView webView) {
        String localPath = getFileName(context);
        boolean ret = ScreenShotUtil.savePic(ScreenShotUtil.captureWebView(webView), localPath);
        if (ret) {
            return localPath;
        } else {
            return "";
        }
    }
}
