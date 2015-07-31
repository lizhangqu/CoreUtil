package zafu.edu.cn.coreutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

/**
 * 网络状态工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-07-31
 * Time: 13:41
 */
public class NetworkUtil {


    public static final int CHINA_MOBILE = 1; // 中国移动
    public static final int CHINA_UNICOM = 2; // 中国联通
    public static final int CHINA_TELECOM = 3; // 中国电信


    public static final int SIM_OK = 0;
    public static final int SIM_NO = -1;
    public static final int SIM_UNKNOW = -2;

    public static final String CONN_TYPE_WIFI = "wifi";
    public static final String CONN_TYPE_GPRS = "gprs";
    public static final String CONN_TYPE_NONE = "none";

    /**
     * 判断网络连接有效
     *
     * @return 判断网络连接有效
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前网络是否是wifi网络
     *
     * @param context 上下文
     * @return boolean 是否是wifi网络
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前网络是否是3g网络
     *
     * @param context 上下文
     * @return 是否是3g网络
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 取得网络类型，wifi 2G 3G
     *
     * @param context 上下文
     * @return WF 2G 3G 4G，或空 如果没网
     */
    public static String getWifiOr2gOr3G(Context context) {
        String networkType = "";
        if (context != null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
                if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) { // 有网
                    networkType = activeNetInfo.getTypeName().toLowerCase();
                    if (networkType.equals("wifi")) {
                        networkType = "WF";
                    } else { // 移动网络
                        // //如果使用移动网络，则取得apn
                        // apn = activeNetInfo.getExtraInfo();
                        // 将移动网络具体类型归一化到2G 3G 4G
                        networkType = "2G"; // 默认是2G
                        int subType = activeNetInfo.getSubtype();
                        switch (subType) {
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_CDMA: // IS95
                                break;
                            case TelephonyManager.NETWORK_TYPE_EDGE: // 2.75
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_GPRS: // 2.5
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSDPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                                networkType = "3G";
                                break; // ~ 1-2 Mbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                networkType = "3G";
                                break; // ~ 5 Mbps
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                networkType = "3G";
                                break; // ~ 10-20 Mbps
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                break; // ~25 kbps
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                networkType = "4G";
                                break; // ~ 10+ Mbps
                            default:
                                break;
                        }
                    } // end 移动网络if
                } // end 有网的if
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
        }
        return networkType;
    }

    /**
     * 程序启动时判断活动状态的网络类型
     *
     * @param context 上下文
     * @return 网络类型
     */
    public static String getNetworkType(Context context) {
        String result = null;


        ConnectivityManager connectivity = (ConnectivityManager) (context.getSystemService(Context.CONNECTIVITY_SERVICE));

        if (connectivity == null) {
            result = null;
        } else {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i] != null) {
                        State tem = info[i].getState();
                        if ((tem == State.CONNECTED || tem == State.CONNECTING)) {
                            String temp = info[i].getExtraInfo();
                            result = info[i].getTypeName() + " "
                                    + info[i].getSubtypeName() + temp;
                            break;
                        }
                    }
                }
            }

        }

        return result;
    }

    /**
     * 获得网络连接类型
     *
     * @param context 上下文
     * @return 网络连接类型
     */
    public static String getNetConnectType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == connManager) {
            return CONN_TYPE_NONE;
        }

        NetworkInfo info = null;
        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != info) {
            State wifiState = info.getState();
            if (State.CONNECTED == wifiState) {
                return CONN_TYPE_WIFI;
            }
        } else {
        }

        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != info) {
            State mobileState = info.getState();
            if (State.CONNECTED == mobileState) {
                return CONN_TYPE_GPRS;
            }
        } else {
        }
        return CONN_TYPE_NONE;
    }

    /**
     * 获得Proxy地址
     *
     * @param context 上下文
     * @return Proxy地址
     */
    public static String getProxy(Context context) {
        String proxy = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isAvailable()) {
                String stringExtraInfo = networkinfo.getExtraInfo();
                if (stringExtraInfo != null && ("cmwap".equals(stringExtraInfo) || "uniwap".equals(stringExtraInfo))) {
                    proxy = "10.0.0.172:80";
                } else if (stringExtraInfo != null && "ctwap".equals(stringExtraInfo)) {
                    proxy = "10.0.0.200:80";
                }
            }
        }

        return proxy;
    }

    /*
     * 获取SIM卡状态
     */

    /**
     * 获取SIM卡状态
     *
     * @param context 上下文
     * @return SIM卡状态
     */
    public static int getSimState(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        if (simState == TelephonyManager.SIM_STATE_READY) {
            return SIM_OK;
        } else if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            return SIM_NO;
        } else {
            return SIM_UNKNOW;
        }
    }
    
    /*	获取运营商类型
     * 	此方法判断不是很全
     * */

    /**
     * 获取运营商类型,此方法判断不是很全
     *
     * @param context 上下文
     * @param nsp     StringBuffer
     * @return 营商类型
     */
    public static int getNSP(Context context, StringBuffer nsp) {

        if (getSimState(context) == SIM_OK) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String operator = tm.getNetworkOperatorName();

            //String numeric = tm.getNetworkOperator();

            if (operator == null || operator.length() == 0) {
                return SIM_UNKNOW;
            }
            if (nsp != null) {
                nsp.delete(0, nsp.length());
                nsp.append(operator);
            }
            if (operator.compareToIgnoreCase("中国移动") == 0
                    || operator.compareToIgnoreCase("CMCC") == 0
                    || operator.compareToIgnoreCase("China Mobile") == 0
                    || operator.compareToIgnoreCase("46000") == 0
                    || operator.compareToIgnoreCase("46002") == 0) {
                return CHINA_MOBILE;
            } else if (operator.compareToIgnoreCase("中国电信") == 0
                    || operator.compareToIgnoreCase("China Telecom") == 0
                    || operator.compareToIgnoreCase("46003") == 0
                    || operator.compareToIgnoreCase("China Telcom") == 0) {
                return CHINA_TELECOM;
            } else if (operator.compareToIgnoreCase("中国联通") == 0
                    || operator.compareToIgnoreCase("China Unicom") == 0
                    || operator.compareToIgnoreCase("46001") == 0
                    || operator.compareToIgnoreCase("CU-GSM") == 0) {
                return CHINA_UNICOM;
            } else {
                return SIM_UNKNOW;
            }
        } else {
            return SIM_NO;
        }
    }
}
