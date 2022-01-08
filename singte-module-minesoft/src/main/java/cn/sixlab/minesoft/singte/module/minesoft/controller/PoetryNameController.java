package cn.sixlab.minesoft.singte.module.minesoft.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemAtomDao;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemDao;
import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemNameDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemAtom;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoemName;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/poetry/name")
public class PoetryNameController extends BaseController {

    @Autowired
    private StePoemAtomDao poemAtomMapper;

    @Autowired
    private StePoemNameDao poemNameMapper;

    @GetMapping(value = "/names")
    public String names(ModelMap modelMap) {

        return poemNames(modelMap, 1);
    }

    @GetMapping(value = "/names/{page}")
    public String names(ModelMap modelMap, @PathVariable Integer page) {

        return poemNames(modelMap, page);
    }

    private String poemNames(ModelMap modelMap, Integer page) {
        List<StePoemName> poemNameList = poemNameMapper.selectPoemNames(page, 100);

        modelMap.put("pageInfo", poemNameList);

        return "poetry/name/names";
    }

    @GetMapping(value = "/keywords")
    public String keywords(ModelMap modelMap, String keywords) {
        String[] keywordList = StringUtils.split(keywords, " ");

        List<StePoemAtom> poemAtomList = poemAtomMapper.selectByKeywords(keywordList);

        modelMap.put("poemAtomList", poemAtomList);

        return "poetry/name/keywords";
    }

}
