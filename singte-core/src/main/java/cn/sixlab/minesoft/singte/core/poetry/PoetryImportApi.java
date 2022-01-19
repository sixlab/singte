package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.http.HttpUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class PoetryImportApi {

    @Autowired
    public SteAncientSectionDao ancientSectionDao;

    public abstract List<PoetryModel> paths();

    // TODO youmengying/youmengying.json
    public abstract void parseJson(String resp, SteAncientSection param);

    public void parsePoetry() {
        SteAncientSection section = new SteAncientSection();
        section.setViewCount(0);
        section.setThumbCount(0);
        section.setIntro("");
        section.setStatus(StConst.YES);
        section.setCreateTime(new Date());

        String parentPath = "https://raw.githubusercontent.com/chinese-poetry/chinese-poetry/master/";
        for (PoetryModel model : paths()) {
            section.setAncientSet(model.getAncientSet());
            section.setAncientCategory(model.getAncientCategory());
            section.setBookName(model.getBookName());
            section.setAuthor(model.getAuthor());
            String resp = HttpUtil.get(parentPath + model.getPath());

            parseJson(resp, section);
        }
    }

}
