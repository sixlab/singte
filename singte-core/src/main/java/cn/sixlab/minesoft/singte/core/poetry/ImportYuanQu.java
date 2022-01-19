package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ImportYuanQu implements PoetryImportApi {

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @Override
    public int parseAncient(String type, String resp, SteAncientBook book, SteAncientSection param) {
        int sectionCount = 1;
        List<Map> mapList = JsonUtils.toBean(resp, List.class);

        for (Map map : mapList) {
            param.setId(null);
            param.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            String author = MapUtil.getStr(map, "author");

            param.setSectionName(title);
            param.setAuthor(author);

            List<String> contentList = (List<String>) map.get("paragraphs");
            param.setContentHtml(StrUtil.join("<br />", contentList));
            param.setContentText(StrUtil.join("", contentList));

            ancientSectionDao.save(param);
        }
        return sectionCount - 1;
    }
}
