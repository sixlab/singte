package cn.sixlab.minesoft.singte.core.common.utils;

import org.springframework.beans.BeanUtils;

public class StBeanUtils {
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
