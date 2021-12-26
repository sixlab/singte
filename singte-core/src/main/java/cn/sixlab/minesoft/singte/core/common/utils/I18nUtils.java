package cn.sixlab.minesoft.singte.core.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18nUtils {

    private static MessageSource messageSource;

    @Autowired
    public I18nUtils(MessageSource messageSource) {
        I18nUtils.messageSource = messageSource;
    }

    /**
     * 获取国际值
     */
    public static String get(String msgKey, Object... args) {
        try {
            return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }

}
