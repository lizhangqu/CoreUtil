package zafu.edu.cn.coreutil;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-08-04
 * Time: 11:20
 */
public class ValidateUtil {
    /**
     * 是否包含数字
     *
     * @param str 待验证的字符串
     * @return 是否含数字
     */
    public static boolean hasNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        boolean bFind = pattern.matcher(str).find();
        return bFind;
    }

    /**
     * 是否含有特殊符号
     *
     * @param str 待验证的字符串
     * @return 是否含有特殊符号
     */
    public static boolean hasSpecialCharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 是否是英语
     *
     * @param str 待验证的字符串
     * @return 是否是英语
     */
    public static boolean isEnglish(String str) {
        byte[] bytes = str.getBytes();
        int i = bytes.length;
        int j = str.length();
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否为重复字符串
     *
     * @param str 待验证的字符串
     * @return 是否为重复字符串
     */
    public static boolean isRepeatedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int len = str.length();
        if (len <= 1) {
            return false;
        } else {
            char firstChar = str.charAt(0);// 第一个字符
            for (int i = 1; i < len; i++) {
                char nextChar = str.charAt(i);// 第i个字符
                if (firstChar != nextChar) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断是否为纯数字
     *
     * @param str 待验证的字符串
     * @return 是否为纯数字
     */
    public static boolean isNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[0-9]+$");// 从开头到结尾必须全部为数字
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 判断字符串是否为连续数字 45678901等
     *
     * @param str 待验证的字符串
     * @return 是否为连续数字
     */
    public static boolean isContinuousNum(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        if (!isNumbericString(str))
            return true;
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            char curChar = str.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == '9')
                verifyChar = '0';
            char nextChar = str.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为空
     *
     * @param str 待验证的字符串
     * @return 是否为空
     */
    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() <= 0)
            return true;
        else
            return false;
    }

    /**
     * 是否是电话号码
     *
     * @param num 待验证的字符串
     * @return 是否是手机号
     */
    public static boolean isPhoneNum(String num) {
        // modified by liyang
        // 确保每一位都是数字
        return !TextUtils.isEmpty(num) && num.matches("1[0-9]{10}")
                && !isRepeatedString(num) && !isContinuousNum(num);
    }

    /**
     * 是否是合法的电话号码
     *
     * @param str 待验证的字符串
     * @return 是否是合法的电话号码
     */
    public static boolean isValidTelephoneNumber(String str) {
        Pattern pattern = Pattern.compile("^(\\(\\d{0,4}\\)|\\d{0,4}-)?(\\(\\d{0,4}\\)|\\d{0,4}-)?\\d{7,}$");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是合法的手机号码
     *
     * @param str 待验证的字符串
     * @return 是否是合法的手机号码
     */
    public static boolean isValidPhoneNumber(String str) {
        Pattern pattern = Pattern.compile("(^0?(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$)|(^\\d{3,4}-\\d{7,8}(-\\d{1,4})?$)");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是合法的邮箱
     *
     * @param str 待验证的字符串
     * @return 是否是合法的邮箱
     */
    public static boolean isValidEmail(String str) {
        Pattern pattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        Matcher isValid = pattern.matcher(str);
        if (!isValid.matches()) {
            return false;
        }
        return true;

    }

    /**
     * 是否是纯字母
     *
     * @param str 待验证的字符串
     * @return 是否是纯字母
     */
    public static boolean isAlphaBetaString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z]+$");// 从开头到结尾必须全部为字母或者数字
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 判断字符串是否为连续字母 xyZaBcd等
     *
     * @param str 待验证的字符串
     * @return 是否为连续字母
     */
    public static boolean isContinuousWord(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        if (!isAlphaBetaString(str))
            return true;
        int len = str.length();
        String local = str.toLowerCase();
        for (int i = 0; i < len - 1; i++) {
            char curChar = local.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == 'z')
                verifyChar = 'a';
            char nextChar = local.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为纯字母或数字
     *
     * @param str 待验证的字符串
     * @return 是否为纯字母或数字
     */
    public static boolean isAlphaBetaOrNumbericString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");// 从开头到结尾必须全部为字母或者数字
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 是否包含中文
     *
     * @param str 待验证的字符串
     * @return 是否包含中文
     */
    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * 是否是日期
     * 20120506 共八位，前四位-年，中间两位-月，最后两位-日
     *
     * @param date    待验证的字符串
     * @param yearlen yearlength
     * @return 是否是真实的日期
     */
    public static boolean isRealDate(String date, int yearlen) {
        int len = 4 + yearlen;
        if (date == null || date.length() != len)
            return false;

        if (!date.matches("[0-9]+"))
            return false;

        int year = Integer.parseInt(date.substring(0, yearlen));
        int month = Integer.parseInt(date.substring(yearlen, yearlen + 2));
        int day = Integer.parseInt(date.substring(yearlen + 2, yearlen + 4));

        if (year <= 0)
            return false;
        if (month <= 0 || month > 12)
            return false;
        if (day <= 0 || day > 31)
            return false;

        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return day > 30 ? false : true;
            case 2:
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                    return day > 29 ? false : true;
                return day > 28 ? false : true;
            default:
                return true;
        }
    }


}
