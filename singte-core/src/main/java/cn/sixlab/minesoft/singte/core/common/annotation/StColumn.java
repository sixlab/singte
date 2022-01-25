package cn.sixlab.minesoft.singte.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface StColumn {

    // 字段显示值
    String text() default "";

    // 字段提示
    String placeholder() default "";

    // 字段类型
    String type() default "input";

    // 字段排序
    int order() default 0;

    // 字段样式
    String cssClass() default "";

    // 默认值
    String defaultVal() default "";

    // 可编辑不显示 edit
    // 可编辑可显示 all
    // 锁编辑不显示 none
    // 锁编辑可显示 view

    // 是否可见
    boolean viewable() default true;

    // 是否可编辑
    boolean editable() default true;
}
