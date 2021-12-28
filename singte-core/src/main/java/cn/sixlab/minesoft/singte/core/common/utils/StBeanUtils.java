package cn.sixlab.minesoft.singte.core.common.utils;

import org.springframework.beans.BeanUtils;

public class StBeanUtils {
    public static void copyProperties(Object source, Object target) {
        // TODO 待验证
        BeanUtils.copyProperties(source, target);
    }
}
