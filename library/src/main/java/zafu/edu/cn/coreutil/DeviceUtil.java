package zafu.edu.cn.coreutil;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-29
 * Time: 16:42
 */
public class DeviceUtil {
    /**
     * 获得手机名称
     * @return 手机名称
     */
    public static String getMobileName(){
        return  Build.MANUFACTURER+" "+ Build.MODEL;
    }

    /**
     * 获得手机类型
     * @return 手机类型
     */
    public static String getPhoneType(){
        String phoneType=Build.MODEL;
        return phoneType;
    }

    /**
     * 获得系统版本号
     * @return 系统版本号
     */
    public static int getOSVersion(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机号，几乎获取不到
     * @param context 上下文
     * @return 手机号，几乎获取不到
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getApplicationContext().getSystemService(
                        Context.TELEPHONY_SERVICE);
        String phoneNum = mTelephonyMgr.getLine1Number();
        return TextUtils.isEmpty(phoneNum) ? "" : phoneNum;
    }

    /**
     * 获得IMEI号
     * @param context 上下文
     * @return IMEI号
     */
    public static String getPhoneIMEI(Context context){
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getApplicationContext().getSystemService(
                        Context.TELEPHONY_SERVICE);
        String phoneImei = mTelephonyMgr.getDeviceId();
        return TextUtils.isEmpty(phoneImei) ? "" : phoneImei;
    }

    /**
     * 获取imsi号
     * @param context 上下文
     * @return IMSI号
     */
    public static String getPhoneIMSI(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context
                .getApplicationContext().getSystemService(
                        Context.TELEPHONY_SERVICE);
        String phoneImsi = mTelephonyMgr.getSubscriberId();

        return TextUtils.isEmpty(phoneImsi) ? "" : phoneImsi;
    }

    /**
     * 获得ipv4类型的ip地址
     * @return ip地址
     */
    public static String getLocalIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&& inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getLocalMacAddress() {
        String Mac = null;
        try {
            String path = "sys/class/net/wlan0/address";
            if ((new File(path)).exists()) {
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer = new byte[8192];
                int byteCount = fis.read(buffer);
                if (byteCount > 0) {
                    Mac = new String(buffer, 0, byteCount, "utf-8");
                }
                fis.close();
            }

            if (Mac == null || Mac.length() == 0) {
                path = "sys/class/net/eth0/address";
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis.read(buffer_name);
                if (byteCount_name > 0) {
                    Mac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
                fis.close();
            }

            if (Mac == null || Mac.length() == 0) {
                return "";
            } else if (Mac.endsWith("\n")) {
                Mac = Mac.substring(0, Mac.length() - 1);
            }
        } catch (Exception io) {
        }

        return TextUtils.isEmpty(Mac) ? "" : Mac;
    }

    /**
     * 获得序列号
     * @return 序列号
     */
    public static String getSerialNum() {
        String serialNum = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNum = (String) (get.invoke(c, "ro.serialno", "unknown"));
        } catch (Exception ignored) {
        }

        return serialNum;
    }

    /**
     * 获得AndroidId
     * @param context 上下文
     * @return AndroidId
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}
