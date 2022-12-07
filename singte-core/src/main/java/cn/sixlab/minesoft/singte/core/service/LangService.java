package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StLangDao;
import cn.sixlab.minesoft.singte.core.models.StLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LangService {

    @Autowired
    private StLangDao langDao;

    public String getLang() {
        String lang = LocaleContextHolder.getLocale().toString();

        StLang stLang = langDao.selectByLang(lang);
        if (null == stLang && lang.contains("_")) {
            lang = lang.substring(0, lang.indexOf("_"));
            stLang = langDao.selectByLang(lang);
        }

        if (null == stLang) {
            lang = StConst.DEFAULT_LOCALE.toString();
        }

        return lang;
    }
}
