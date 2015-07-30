package zafu.edu.cn.coreutil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 显示通知的工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-30
 * Time: 10:41
 */
public class ToastUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();

    private static Toast makeText(Context act, String msg, int len) {
        Toast result = new Toast(act);
        View tipsView = LayoutInflater.from(act).inflate(
                R.layout.layout_toast, null);
        TextView tips = (TextView) tipsView.findViewById(R.id.toast_content);
        tips.setText(msg);
        result.setDuration(len);
        result.setView(tipsView);

        return result;
    }


    private static void reMakeText(Context context, String msg, int len) {
        View tipsView = LayoutInflater.from(context).inflate(
                R.layout.layout_toast, null);
        TextView tips = (TextView) tipsView.findViewById(R.id.toast_content);
        tips.setText(msg);
        toast.setDuration(len);
        toast.setView(tipsView);
    }

    /**
     * 显示通知
     * @param context 上下文
     * @param messageId 字符串对应的资源id
     * @param length 显示时间
     */
    public static void show(final Context context, final int messageId,
                            final int length) {
        show(context, context.getResources().getString(messageId), length);
    }

    /**
     * 显示通知
     * @param context 上下文
     * @param message 显示的字符串
     * @param length 显示时间
     */
    public static void show(final Context context, final String message,
                            final int length) {
        int strSDKVersion = android.os.Build.VERSION.SDK_INT;
        if (strSDKVersion >= 14) {
            if (toast != null) {
                reMakeText(context, message, length);
            } else {
                toast = makeText(context, message, length);
            }
            toast.show();
        } else {
            new Thread(new Runnable() {
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (synObj) {
                                if (toast != null) {
                                    toast.cancel();
                                    reMakeText(context, message, length);
                                } else {
                                    toast = makeText(context, message, length);
                                }
                                toast.show();
                            }
                        }
                    });
                }
            }).start();
        }
    }
}
