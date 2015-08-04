package zafu.edu.cn.coreutil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 输入法操作工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-08-04
 * Time: 11:29
 */
public class KeyboardUtil {
    /**
     * 隐藏键盘
     *
     * @param context 上下文
     * @param view    The currently focused view
     */
    public static void hideInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示输入键盘
     *
     * @param context 上下文
     * @param view    The currently focused view, which would like to receive soft keyboard input
     */
    public static void showInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏输入法
     *
     * @param mAct activity
     */
    public static void hideInputMethod(Activity mAct) {
        try {// hide keybord anyway
            View v = mAct.getWindow().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) mAct.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 显示输入法
     *
     * @param mAct activity
     */
    public static void showInputMethod(final Activity mAct) {
        View v = mAct.getCurrentFocus();
        if (null == v) {
            return;
        }
        ((InputMethodManager) mAct.getSystemService(Activity.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
    }
}
