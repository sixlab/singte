package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("importone")
public class ImportOne extends PoetryImportApi {

    /**
     * sishuwujing/daxue.json
     * sishuwujing/zhongyong.json
     * mengxue/zhuzijiaxun.json
     */
    @Override
    public void parseAncient(String type, String resp, SteAncientSection param) {
        int sectionCount = 1;
        Map map = JsonUtils.toBean(resp, Map.class);

        param.setId(null);
        param.setWeight(sectionCount);

        String title = MapUtil.getStr(map, "title");
        if (StrUtil.isEmpty(title)) {
            title = MapUtil.getStr(map, "chapter");
        }

        param.setSectionName(title);

        String author = MapUtil.getStr(map, "author");
        if (StrUtil.isNotEmpty(author)) {
            param.setAuthor(author);
        }

        List<String> contentList = (List<String>) map.get("paragraphs");
        param.setContentHtml(StrUtil.join("<br />", contentList));
        param.setContentText(StrUtil.join("", contentList));

        ancientSectionDao.save(param);
    }
}
