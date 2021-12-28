package cn.sixlab.minesoft.singte.core.common.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

public class StCacheHolder {
    public static final TimedCache<String, Object> CACHE_5m = CacheUtil.newTimedCache(StConst.SECONDS_MIN_5 * 1000);
    public static final TimedCache<String, Object> CACHE_30m = CacheUtil.newTimedCache(StConst.SECONDS_MIN_30 * 1000);

    static {
        CACHE_5m.schedulePrune(StConst.SECONDS_1 * 1000);
        CACHE_30m.schedulePrune(StConst.SECONDS_1 * 1000);
    }
}
