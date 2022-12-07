package cn.sixlab.minesoft.singte.core.common.utils;

public class CaptchaUtils {

    public static boolean verify(String key, String code) {
        key = "captcha_" + key;
        Object cached = StCacheHolder.CACHE_5m.get(key);

        if (code.equals(cached)) {
            StCacheHolder.CACHE_5m.remove(key);
            return true;
        }

        return false;
    }
}
