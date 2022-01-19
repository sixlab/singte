package cn.sixlab.minesoft.singte.core.poetry;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("importone")
public class ImportOne extends PoetryImportApi {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        list.add(new PoetryModel("sishuwujing/daxue.json", "经部", "四书类", "大学", "四书类"));
        list.add(new PoetryModel("sishuwujing/zhongyong.json", "经部", "四书类", "中庸", "子思"));
        list.add(new PoetryModel("mengxue/zhuzijiaxun.json", "经部", "蒙学类", "朱子家训", "朱柏庐"));
        list.add(new PoetryModel("mengxue/sanzijing-new.json", "经部", "蒙学类", "三字经", "王应麟、章太炎等"));
        list.add(new PoetryModel("mengxue/baijiaxing.json", "经部", "蒙学类", "百家姓", "佚名"));

        return list;
    }

    /**
     * sishuwujing/daxue.json
     * sishuwujing/zhongyong.json
     * mengxue/zhuzijiaxun.json
     * mengxue/sanzijing-new.json
     * mengxue/baijiaxing.json
     */
    @Override
    public void parseJson(String resp, SteAncientSection param) {
        SteAncientSection ancientSection = new SteAncientSection();
        BeanUtil.copyProperties(param, ancientSection);

        int sectionCount = 1;
        Map map = JsonUtils.toBean(resp, Map.class);

        ancientSection.setId(null);
        ancientSection.setWeight(sectionCount);

        String title = MapUtil.getStr(map, "title");
        if (StrUtil.isEmpty(title)) {
            title = MapUtil.getStr(map, "chapter");
        }

        ancientSection.setSectionName(title);

        String author = MapUtil.getStr(map, "author");
        if (StrUtil.isNotEmpty(author)) {
            ancientSection.setAuthor(author);
        }

        List<String> contentList = (List<String>) map.get("paragraphs");
        ancientSection.setContentHtml(StrUtil.join("<br />", contentList));
        ancientSection.setContentText(StrUtil.join("", contentList));

        ancientSectionDao.save(ancientSection);
    }
}
