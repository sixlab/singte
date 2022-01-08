package cn.sixlab.minesoft.singte.module.minesoft.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import cn.sixlab.minesoft.singte.module.minesoft.service.PoemService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/poem")
public class PoemController extends BaseController {

    @Autowired
    private StePoemDao stePoemDao;

    @Autowired
    private PoemService poemService;

    @GetMapping(value = "/item/{poemId}")
    public String item(ModelMap modelMap, @PathVariable String poemId) {
        StePoem stePoem = stePoemDao.selectById(poemId);

        if (null == stePoem) {
            return "redirect:/404";
        } else {
            modelMap.put("stePoem", stePoem);
            return "poem/item";
        }
    }

    @ResponseBody
    @PostMapping(value = "/importPoem")
    public ModelResp importPoem(String path) throws IOException {
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

                        StePoem stePoem = new StePoem();
                        stePoem.setPoemName(title);
                        stePoem.setPoemType("楚辞");
                        stePoem.setPoemCategory("楚辞");
                        stePoem.setPoemSection(section);
                        stePoem.setPoemAuthor(author);
                        stePoem.setPoemContent(StringUtils.join(contentList, ""));

                        poemService.addPoem(stePoem);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
