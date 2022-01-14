package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientSetDao;
import cn.sixlab.minesoft.singte.core.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ancient")
public class AncientController extends BaseController {

    @Autowired
    private SteAncientSetDao ancientSetDao;

    @Autowired
    private SteAncientCategoryDao ancientCategoryDao;

    @Autowired
    private SteAncientBookDao ancientBookDao;

    @Autowired
    private SteAncientSectionDao ancientSectionDao;

    @GetMapping(value = "/set")
    public String listSet(ModelMap modelMap) {

        modelMap.put("result", ancientSetDao.list());

        return "ancient/set";
    }

    @GetMapping(value = "/category/{setId}")
    public String listCategory(ModelMap modelMap, @PathVariable String setId) {
        SteAncientSet ancientSet = ancientSetDao.selectById(setId);

        String setAncientText = ancientSet.getAncientSet();

        modelMap.put("title", setAncientText);
        modelMap.put("result", ancientCategoryDao.listSetCategory(setAncientText));

        return "ancient/category";
    }

    @GetMapping(value = "/book/{categoryId}")
    public String listBook(ModelMap modelMap, @PathVariable String categoryId) {
        SteAncientCategory ancientCategory = ancientCategoryDao.selectById(categoryId);

        String category = ancientCategory.getAncientCategory();
        modelMap.put("father", ancientCategory.getAncientSet());
        modelMap.put("title", category);
        modelMap.put("result", ancientBookDao.listCategoryBook(category));

        return "ancient/book";
    }

    @GetMapping(value = "/section/{bookId}")
    public String listSection(ModelMap modelMap, @PathVariable String bookId) {
        SteAncientBook ancientBook = ancientBookDao.selectById(bookId);

        String bookName = ancientBook.getBookName();
        modelMap.put("grandFather", ancientBook.getAncientSet());
        modelMap.put("father", ancientBook.getAncientCategory());
        modelMap.put("title", bookName);
        modelMap.put("result", ancientSectionDao.listBookSections(bookName));

        return "ancient/section";
    }

    @GetMapping(value = "/content/{sectionId}")
    public String content(ModelMap modelMap, @PathVariable String sectionId) {
        SteAncientSection ancientSection = ancientSectionDao.selectById(sectionId);

        modelMap.put("title", ancientSection.getSectionName());
        modelMap.put("ancientSection", ancientSection);

        return "ancient/content";
    }

    @ResponseBody
    @PostMapping(value = "/thumb")
    public ModelResp thumb(String sectionId) {
        SteAncientSection ancientSection = ancientSectionDao.selectById(sectionId);
        ancientSection.setThumbCount(ancientSection.getThumbCount() + 1);
        ancientSectionDao.save(ancientSection);
        return ModelResp.success(ancientSection.getThumbCount());
    }

//    @ResponseBody
//    @PostMapping(value = "/importAncient")
//    public ModelResp importAncient(String path) throws IOException {
//        path = "D:\\workspace\\projects\\chinese-poetry-master\\chuci";
//        System.out.println("-----------------------------------");
//        Path filePath = Paths.get(path);
//
//        readFolder(filePath);
//
//        return ModelResp.success();
//    }
//
//    public void readFolder(Path folderPath) throws IOException {
//        Stream<Path> pathStream = Files.list(folderPath);
//        pathStream.forEach(pathItem -> {
//            try {
//                if (pathItem.toFile().isDirectory()) {
//                    readFolder(pathItem);
//                } else if (pathItem.toString().endsWith(".json")){
//                    List list = JsonUtils.readJson(pathItem.toFile(), List.class);
//                    for (Object obj : list) {
//                        Map map = (Map) obj;
//                        String title = MapUtils.getString(map, "title");
//                        String author = MapUtils.getString(map, "author");
//                        String section = MapUtils.getString(map, "section");
//                        List<String> contentList = (List<String>) map.get("content");
//
//                        SteAncient steAncient = new SteAncient();
//                        steAncient.setAncientName(title);
//                        steAncient.setAncientCategory("楚辞");
//                        steAncient.setAncientAuthor(author);
//                        steAncient.setContentHtml(StrUtil.join("<br />", contentList));
//                        steAncient.setContentText(StrUtil.join("", contentList));
//
//                        steAncientDao.addAncient(steAncient);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
