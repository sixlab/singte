package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.dao.StLangDao;
import cn.sixlab.minesoft.singte.core.models.StLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitLang implements BaseInitComponent {

    @Autowired
    private StLangDao langDao;

    @Override
    public void init() {
        checkLang("zh", "简体中文", "cn");
        checkLang("zh_TW", "繁体中文", "tw");
        checkLang("en", "English", "us");
    }

    public void checkLang(String langCode, String langText, String langIcon) {
        StLang lang = langDao.selectByLang(langCode);
        if (lang == null) {
            lang = new StLang();
            lang.setLangCode(langCode);
            lang.setLangText(langText);
            lang.setLangIcon(langIcon);
            lang.setIntro("");
            lang.setStatus("1");
            lang.setCreateTime(new Date());

            langDao.save(lang);
        }
    }
}
