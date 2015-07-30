package zafu.edu.cn.coreutil;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 內部存儲卡工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-30
 * Time: 17:26
 */
public class InternalStorageUtil {
    /**
     * 在原文件后追加内容
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param content  追加的内容
     * @return 是否追加成功
     */
    public static boolean append(Context context, String fileName,
                                 String content) {
        return writeBytes(context, fileName, content.getBytes(), true);
    }

    /**
     * 写入文件，文件存在则覆盖
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param content  文件内容
     * @return 是否写入成功
     */
    public static boolean write(Context context, String fileName, String content) {
        return writeBytes(context, fileName, content.getBytes(), false);
    }

    /**
     * 写入字节
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param content  写入的字节
     * @return 是否写入成功
     */
    public static boolean writeBytes(Context context, String fileName,
                                     byte[] content) {
        return writeBytes(context, fileName, content, false);
    }

    /**
     * 写入文件，文件存在时根据参数isAppend判断是否覆盖
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param content  写入的字节
     * @param isAppend 是否追加
     * @return 是否写入成功
     */
    public static boolean writeBytes(Context context, String fileName,
                                     byte[] content, boolean isAppend) {
        FileOutputStream fout = null;
        boolean flag = false;
        try {
            if (isAppend) {
                fout = context.openFileOutput(fileName, Context.MODE_APPEND);
            } else {

                fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            }
            fout.write(content);
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                    fout = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 读取文件
     *
     * @param context  上下文
     * @param fileName 文件内容
     * @return 文件内容的字符串
     */
    public static String read(Context context, String fileName) {
        byte[] buffer = readBytes(context, fileName);
        String result = null;
        try {
            result = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取字节
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 字节数组
     */
    public static byte[] readBytes(Context context, String fileName) {
        FileInputStream fin = null;
        byte[] buffer = null;
        try {
            fin = context.openFileInput(fileName);
            int length = fin.available();
            buffer = new byte[length];
            fin.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                    fin = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    /**
     * 清除所有文件，当有一个文件未清除时返回false
     *
     * @param context 上下文
     * @return 是否清除成功
     */
    public static boolean clear(Context context) {
        boolean flag = true;
        String[] files = context.fileList();
        for (String fileName : files) {
            boolean result = context.deleteFile(fileName);
            if (result == false) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean delete(Context context, String fileName) {
        return context.deleteFile(fileName);
    }

    /**
     * 返回内部存储的绝对路径
     *
     * @param context 上下文
     * @return 内部存储的绝对路径
     */
    public static String getFileDir(Context context) {
        File filesDir = context.getFilesDir();
        return filesDir.getAbsolutePath();
    }
}
