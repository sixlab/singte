package cn.sixlab.minesoft.singte.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface StTable {

    // 页面标题
    String title() default "";

    // 重载方法uri
    String reloadUri() default "";

}
