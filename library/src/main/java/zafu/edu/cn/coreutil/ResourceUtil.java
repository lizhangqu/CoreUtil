package zafu.edu.cn.coreutil;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 17:33
 */
public class ResourceUtil {
    /**
     * 从assets目录下读取文件内容
     * @param context 上下文
     * @param fileName 文件名
     * @return 文件字节流
     */
    public static byte[] readBytesFromAssets(Context context,String fileName){
        InputStream is=null;
        byte[] buffer=null;
        try{
            is=context.getAssets().open(fileName);
            int size=is.available();
            buffer=new byte[size];
            is.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                    is=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从res/raw目录下读取文件内容
     * @param context 上下文
     * @param rawId rawId
     * @return 文件字节流
     */
    public static byte[] readBytesFromRaw(Context context,int rawId){
        InputStream is=null;
        byte[] buffer=null;
        try{
            is=context.getResources().openRawResource(rawId);
            int size=is.available();
            buffer=new byte[size];
            is.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                    is=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return buffer;
    }

    /**
     * 从assets目录读取文本
     * @param context 上下文
     * @param fileName 文件内容
     * @return 文本内容
     */
    public static String readStringFromAssets(Context context,String fileName){
        String result=null;
        byte[] buffer = readBytesFromAssets(context, fileName);
        try{
            result=new String(buffer,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return result;
    }

    public static String readStringFromRaw(Context context,int rawId){
        String result=null;
        byte[] buffer=readBytesFromRaw(context,rawId);
        try {
            result=new String(buffer,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
