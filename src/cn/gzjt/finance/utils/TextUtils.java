package cn.gzjt.finance.utils;

/**
 * @author jianwei.zhou
 * @date 2019/10/12 16:43
 */
public class TextUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }
}
