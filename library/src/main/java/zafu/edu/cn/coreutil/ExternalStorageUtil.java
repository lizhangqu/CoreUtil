package zafu.edu.cn.coreutil;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 外部存储工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-30
 * Time: 17:26
 */
public class ExternalStorageUtil {
    /**
     * 外部存储是否可写
     *
     * @return 外部存储是否可写
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 外部存储是否可读
     *
     * @return 外部存储是否可读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 获得外部存储根目录
     *
     * @return 外部存储根目录
     */
    public static String getExternalStoragePath() {
        if (isExternalStorageWritable())
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        else
            return null;
    }

    /**
     * 获得外部存储下载目录的路径
     *
     * @return 外部存储下载目录的路径
     */
    public static String getExternalDownloadPath() {
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * 向根目录写文件
     *
     * @param fileName 文件名
     * @param content  文件内容
     * @return 是否写入成功
     */
    public static boolean write(String fileName, String content) {
        return write("/", fileName, content);
    }

    /**
     * 向根目录写字节
     *
     * @param fileName 文件名
     * @param bytes    文件字节数组
     * @return 是否写入成功
     */
    public static boolean writeBytes(String fileName, byte[] bytes) {
        return writeBytes("/", fileName, bytes);
    }

    /**
     * 向指定目录的文件中写入字符串,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @param content  文件内容
     * @return 是否写入成功
     */
    public static boolean write(String path, String fileName, String content) {
        return writeBytes(path, fileName, content.getBytes());
    }

    /**
     * 向指定目录的文件写入字节数组,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @param bytes    字节数组
     * @return 是否写入成功
     */
    public static boolean writeBytes(String path, String fileName, byte bytes[]) {
        boolean flag = false;
        if (!path.equals("/")) {
            File dir = new File(getExternalStoragePath() + path);
            if (!dir.exists()) {
                if (!(dir.mkdir() || dir.isDirectory())) {
                    // 文件目录创建失败或者不是一个目录
                    return false;
                }
            }
        }
        File file = new File(getExternalStoragePath() + path + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, false);
            fos.write(bytes);
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return flag;
    }

    /**
     * 从根路径读字节
     *
     * @param fileName 文件名
     * @return 字节数组
     */
    public static byte[] readBytes(String fileName) {
        return readBytes("/", fileName);
    }

    /**
     * 从根路径读字节，路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 字节流
     */
    public static byte[] readBytes(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (!file.isFile()) {
            return null;
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int length = fis.available();
                byte[] buffer = new byte[length];
                fis.read(buffer);
                return buffer;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

    }

    /**
     * 从根目录读文本
     *
     * @param fileName 文件名
     * @return 文件中的文本
     */
    public static String read(String fileName) {
        return read("/", fileName);
    }

    /**
     * 从指定目录读文本,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 文件中的文本
     */
    public static String read(String path, String fileName) {
        try {
            byte[] readBytes = readBytes(path, fileName);
            if (readBytes == null) {
                return null;
            }
            return new String(readBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从根目录删除文件
     *
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean delete(String fileName) {
        return delete("/", fileName);
    }

    /**
     * 从指定目录删除,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean delete(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (file.exists())
            return file.delete();
        else
            return true;
    }
}
