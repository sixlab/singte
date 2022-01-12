package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.SteAncientCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteAncientDao;
import cn.sixlab.minesoft.singte.core.models.SteAncient;
import cn.sixlab.minesoft.singte.core.models.SteAncientCategory;
import cn.sixlab.minesoft.singte.core.service.AncientService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequestMapping("/ancient")
public class AncientController extends BaseController {

    @Autowired
    private SteAncientDao steAncientDao;

    @Autowired
    private SteAncientCategoryDao categoryDao;

    @Autowired
    private AncientService ancientService;

    @GetMapping(value = "/list")
    public String index(ModelMap modelMap) {

        modelMap.put("ancientCategory", categoryDao.listCategory());

        return "ancient/list";
    }

    @GetMapping(value = "/category/{categoryId}")
    public String category(ModelMap modelMap, @PathVariable String categoryId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        SteAncientCategory ancientCategory = categoryDao.selectById(categoryId);

        String category = ancientCategory.getAncientCategory();
        modelMap.put("title", category);
        modelMap.put("ancientList", steAncientDao.selectByCategory(category));

        return "ancient/category";
    }

    @GetMapping(value = "/item/{ancientId}")
    public String item(ModelMap modelMap, @PathVariable String ancientId) {
        SteAncient steAncient = steAncientDao.selectById(ancientId);

        if (null == steAncient) {
            return "redirect:/404";
        } else {
            modelMap.put("steAncient", steAncient);
            return "ancient/item";
        }
    }

    @ResponseBody
    @PostMapping(value = "/importAncient")
    public ModelResp importAncient(String path) throws IOException {
        path = "D:\\workspace\\projects\\chinese-poetry-master\\chuci";
        System.out.println("-----------------------------------");
        Path filePath = Paths.get(path);

        readFolder(filePath);

        return ModelResp.success();
    }

    public void readFolder(Path folderPath) throws IOException {
        Stream<Path> pathStream = Files.list(folderPath);

        pathStream.forEach(pathItem -> {
            try {
                if (pathItem.toFile().isDirectory()) {
                    readFolder(pathItem);
                } else if (pathItem.toString().endsWith(".json")){
                    List list = JsonUtils.readJson(pathItem.toFile(), List.class);
                    for (Object obj : list) {
                        Map map = (Map) obj;
                        String title = MapUtils.getString(map, "title");
                        String author = MapUtils.getString(map, "author");
                        String section = MapUtils.getString(map, "section");
                        List<String> contentList = (List<String>) map.get("content");

                        SteAncient steAncient = new SteAncient();
                        steAncient.setAncientName(title);
                        steAncient.setAncientType("楚辞");
                        steAncient.setAncientCategory("楚辞");
                        steAncient.setAncientSection(section);
                        steAncient.setAncientAuthor(author);
                        steAncient.setAncientLines(contentList);

                        ancientService.addAncient(steAncient);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
