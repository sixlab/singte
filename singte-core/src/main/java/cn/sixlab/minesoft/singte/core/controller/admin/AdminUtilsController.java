package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.HttpUtils;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import cn.sixlab.minesoft.singte.core.models.SteAncientSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/utils")
public class AdminUtilsController extends BaseController {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @ResponseBody
    @PostMapping(value = "/countAncient")
    public ModelResp countAncient() {

        List<SteAncientSet> ancientSetList = ancientSetDao.list();
        for (SteAncientSet ancientSet : ancientSetList) {

            String set = ancientSet.getAncientSet();
            int count = ancientBookDao.countSet(set);
            ancientSet.setCount(count);
            ancientSet.setStatus(StConst.YES);
            ancientSetDao.save(ancientSet);

            List<SteAncientCategory> ancientCategoryList = ancientCategoryDao.listSetCategory(set);

            for (SteAncientCategory ancientCategory : ancientCategoryList) {
                int cCount = ancientBookDao.countCategory(ancientCategory.getAncientCategory());
                ancientCategory.setCount(cCount);
                ancientCategory.setStatus(StConst.YES);
                ancientCategoryDao.save(ancientCategory);
            }
        }

        return ModelResp.success();
    }

    @ResponseBody
    @PostMapping(value = "/importAncient")
    public ModelResp importAncient(String path, String type, SteAncientSection param) throws IOException {
        String parentPath = "https://raw.githubusercontent.com/chinese-poetry/chinese-poetry/master/";

        String resp = HttpUtils.sendGet(parentPath + path, null);

        parseAncient(type, resp, param);

        // ci/author.song.json
        // mengxue/baijiaxing.json

        return ModelResp.success();
    }

    private void parseAncient(String type, String resp, SteAncientSection param) {
        SteAncientBook book = new SteAncientBook();
        book.setAncientSet(param.getAncientSet());
        book.setAncientCategory(param.getAncientCategory());
        book.setBookName(param.getBookName());
        book.setAuthor(param.getAuthor());

        book.setWeight(0);
        book.setIntro("");
        book.setStatus(StConst.YES);
        book.setCreateTime(new Date());

        param.setViewCount(0);
        param.setThumbCount(0);
        param.setIntro("");
        param.setStatus(StConst.YES);
        param.setCreateTime(new Date());

        int sectionCount = 1;
        switch (type){
            case "sanzijing-new":
                // mengxue/sanzijing-new.json
                Map sanzijinNewMap = JsonUtils.toBean(resp, Map.class);

                param.setId(null);
                param.setWeight(sectionCount);

                String sanzijinNewTitle = MapUtil.getStr(sanzijinNewMap, "title");
                String sanzijinNewAuthor = MapUtil.getStr(sanzijinNewMap, "author");

                param.setSectionName(sanzijinNewTitle);
                param.setAuthor(sanzijinNewAuthor);

                List<String> sanzijinNewContent = (List<String>) sanzijinNewMap.get("paragraphs");
                param.setContentHtml(StrUtil.join("<br />", sanzijinNewContent));
                param.setContentText(StrUtil.join("", sanzijinNewContent));

                ancientSectionDao.save(param);
                break;
            case "qianziwen":
                // mengxue/qianziwen.json
                Map qianziwenMap = JsonUtils.toBean(resp, Map.class);

                param.setId(null);
                param.setWeight(sectionCount);

                String qzwTitle = MapUtil.getStr(qianziwenMap, "title");
                String qzwAuthor = MapUtil.getStr(qianziwenMap, "author");

                param.setSectionName(qzwTitle);
                param.setAuthor(qzwAuthor);

                List<String> qianziwenContent = (List<String>) qianziwenMap.get("paragraphs");
                StringBuilder qianziwenSb = new StringBuilder();
                for (int i = 0; i < qianziwenContent.size(); i++) {
                    qianziwenSb.append(qianziwenContent.get(i));
                    if (i % 2 == 0) {
                        qianziwenSb.append("，");
                    }else{
                        qianziwenSb.append("。");
                    }
                }
                String qianziwen = qianziwenSb.toString();
                param.setContentHtml(StrUtil.replace(qianziwen, "。", "。<br />"));
                param.setContentText(qianziwen);

                ancientSectionDao.save(param);
                break;
            case "tangshisanbaishou":
                // mengxue/tangshisanbaishou.json
            case "shenglvqimeng":
                // mengxue/shenglvqimeng.json
            case "qianjiashi":
                // mengxue/qianjiashi.json
            case "guwenguanzhi":
                // mengxue/guwenguanzhi.json
                Map guwenguanzhiMap = JsonUtils.toBean(resp, Map.class);
                List<Map> guwenguanzhiContent = (List<Map>) guwenguanzhiMap.get("content");

                book.setBookName(MapUtil.getStr(guwenguanzhiMap, "title"));
                param.setBookName(MapUtil.getStr(guwenguanzhiMap, "title"));

                if(guwenguanzhiMap.containsKey("author")){
                    book.setAuthor(MapUtil.getStr(guwenguanzhiMap, "author"));
                    param.setAuthor(MapUtil.getStr(guwenguanzhiMap, "author"));
                }

                if(guwenguanzhiMap.containsKey("abstract")){
                    List<String> abstr = (List<String>) guwenguanzhiMap.get("abstract");
                    book.setIntro(StrUtil.join("", abstr));
                }

                for (Map map : guwenguanzhiContent) {
                    String gwgzTitle = MapUtil.getStr(map, "title");
                    List<Map> guwenguanzhi2Content = (List<Map>) map.get("content");

                    for (Map content2 : guwenguanzhi2Content) {
                        String chapter = MapUtil.getStr(map, "chapter");

                        param.setId(null);
                        param.setWeight(sectionCount++);

                        param.setSectionName(gwgzTitle + " - " +chapter);
                        if(map.containsKey("author")){
                            String gwgzAuthor = MapUtil.getStr(map, "author");
                            String source = MapUtil.getStr(map, "source");
                            if (StrUtil.isNotEmpty(source)) {
                                gwgzAuthor = gwgzAuthor + " - " + source;
                            }
                            param.setAuthor(gwgzAuthor);
                        }

                        List<String> contentList = (List<String>) content2.get("paragraphs");
                        param.setContentHtml(StrUtil.join("<br />", contentList));
                        param.setContentText(StrUtil.join("", contentList));

                        ancientSectionDao.save(param);
                    }
                }
                break;
            case "dizigui":
                // mengxue/dizigui.json
                Map diziguiMap = JsonUtils.toBean(resp, Map.class);
                List<Map> diziguiContent = (List<Map>) diziguiMap.get("content");


                book.setBookName(MapUtil.getStr(diziguiMap, "title"));
                param.setBookName(MapUtil.getStr(diziguiMap, "title"));

                book.setAuthor(MapUtil.getStr(diziguiMap, "author"));
                param.setAuthor(MapUtil.getStr(diziguiMap, "title"));

                for (Map map : diziguiContent) {
                    param.setId(null);
                    param.setWeight(sectionCount++);

                    String dzgTitle = MapUtil.getStr(map, "chapter");

                    param.setSectionName(dzgTitle);

                    List<String> contentList = (List<String>) map.get("paragraphs");
                    String contentHtml = StrUtil.join("。<br />", contentList);
                    contentHtml = StrUtil.replace(contentHtml, " ", "，");

                    param.setContentHtml(contentHtml);
                    param.setContentText(StrUtil.replace(contentHtml, "<br />", ""));

                    ancientSectionDao.save(param);
                }
                break;
            case "ci":
                // ci/ci.song.XXX.json
                List<Map> ciList = JsonUtils.toBean(resp, List.class);

                for (Map map : ciList) {
                    param.setId(null);
                    param.setWeight(sectionCount++);

                    String title = MapUtil.getStr(map, "rhythmic");
                    String author = MapUtil.getStr(map, "author");

                    param.setSectionName(title);
                    param.setAuthor(author);

                    List<String> contentList = (List<String>) map.get("paragraphs");
                    param.setContentHtml(StrUtil.join("<br />", contentList));
                    param.setContentText(StrUtil.join("", contentList));

                    ancientSectionDao.save(param);
                }
                break;
            case "caocao":
                // caocaoshiji/caocao.json
            case "lunyu":
                // lunyu/lunyu.json
                List<Map> caocaoList = JsonUtils.toBean(resp, List.class);

                for (Map map : caocaoList) {
                    param.setId(null);
                    param.setWeight(sectionCount++);

                    String title = MapUtil.getStr(map, "title");
                    if(StrUtil.isEmpty(title)){
                        title = MapUtil.getStr(map, "chapter");
                    }
                    param.setSectionName(title);

                    List<String> contentList = (List<String>) map.get("paragraphs");
                    param.setContentHtml(StrUtil.join("<br />", contentList));
                    param.setContentText(StrUtil.join("", contentList));

                    ancientSectionDao.save(param);
                }
                break;
            case "chuci":
                // chuci/chuci.json
                List<Map> chuciList = JsonUtils.toBean(resp, List.class);

                for (Map map : chuciList) {
                    param.setId(null);
                    param.setWeight(sectionCount++);

                    String title = MapUtil.getStr(map, "title");
                    String section = MapUtil.getStr(map, "section");
                    String author = MapUtil.getStr(map, "author");

                    param.setSectionName(section + " - " + title);
                    param.setAuthor(author);

                    List<String> contentList = (List<String>) map.get("content");
                    param.setContentHtml(StrUtil.join("。<br />", contentList));
                    param.setContentText(StrUtil.join("。", contentList));

                    ancientSectionDao.save(param);
                }
                break;
        }

        book.setCount(sectionCount-1);
        ancientBookDao.save(book);
    }

}
