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

@Component("importlistmaphuajianji")
public class ImportListMapHuaJianJi extends PoetryImportApi {

    @Override
    public List<PoetryModel> paths() {
        List<PoetryModel> list = new ArrayList<>();

        list.add(new PoetryModel("wudai/huajianji/huajianji-0-preface.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-1-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-2-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-3-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-4-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-5-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-6-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-7-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-8-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-9-juan.json","集部", "词曲类", "花间集", "赵崇祚"));
        list.add(new PoetryModel("wudai/huajianji/huajianji-x-juan.json","集部", "词曲类", "花间集", "赵崇祚"));

        return list;
    }

    /**
     * wudai/huajianji/huajianji-0-preface.json
     * wudai/huajianji/huajianji-1-juan.json
     * wudai/huajianji/huajianji-2-juan.json
     * wudai/huajianji/huajianji-3-juan.json
     * wudai/huajianji/huajianji-4-juan.json
     * wudai/huajianji/huajianji-5-juan.json
     * wudai/huajianji/huajianji-6-juan.json
     * wudai/huajianji/huajianji-7-juan.json
     * wudai/huajianji/huajianji-8-juan.json
     * wudai/huajianji/huajianji-9-juan.json
     * wudai/huajianji/huajianji-x-juan.json
     * wudai/nantang/poetrys.json
     * yuanqu/yuanqu.json
     * sishuwujing/mengzi.json
     * shijing/shijing.json
     * lunyu/lunyu.json
     * caocaoshiji/caocao.json
     * chuci/chuci.json
     *
     * quan_tang_shi/json/001.json
     * quan_tang_shi/json/900.json
     * ci.song.0.json
     * ci.song.1000.json
     * ci.song.9000.json
     * ci.song.10000.json
     * ci.song.19000.json
     * ci.song.20000.json
     * ci.song.21000.json
     * ci.song.2009y.json
     *
     */
    @Override
    public void parseJson(String resp, SteAncientSection param) {
        SteAncientSection ancientSection = new SteAncientSection();
        BeanUtil.copyProperties(param, ancientSection);

        int sectionCount = 1;
        List<Map> mapList = JsonUtils.toBean(resp, List.class);

        for (Map map : mapList) {

            ancientSection.setId(null);
            ancientSection.setWeight(sectionCount++);

            String title = MapUtil.getStr(map, "title");
            String chapter = MapUtil.getStr(map, "chapter");
            String section = MapUtil.getStr(map, "section");
            String volume = MapUtil.getStr(map, "volume");
            String rhythmic = MapUtil.getStr(map, "rhythmic");

            if (StrUtil.isEmpty(title) && StrUtil.isNotEmpty(chapter)) {
                title = chapter;
            }else if(StrUtil.isAllNotEmpty(title, chapter, section)){
                title = chapter + "·" + section + "·" + title;
            }else if(StrUtil.isAllNotEmpty(title, volume)){
                title = volume + "·" + title;
            }else if(StrUtil.isEmpty(title) && StrUtil.isNotEmpty(rhythmic)){
                title = rhythmic;
            }
            ancientSection.setSectionName(title);

            String author = MapUtil.getStr(map, "author");
            if(StrUtil.isNotEmpty(author)){
                ancientSection.setAuthor(author);
            }

            List<String> contentList = (List<String>) map.get("paragraphs");
            ancientSection.setContentHtml(StrUtil.join("<br />", contentList));
            ancientSection.setContentText(StrUtil.join("", contentList));

            ancientSectionDao.save(ancientSection);
        }
    }
}
