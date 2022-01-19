package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.dao.SteToolCategoryDao;
import cn.sixlab.minesoft.singte.core.dao.SteToolItemDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
import cn.sixlab.minesoft.singte.core.models.SteToolCategory;
import cn.sixlab.minesoft.singte.core.models.SteToolItem;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private ArticleService service;

    @Autowired
    private StPageDao pageDao;

    @Autowired
    private ConfigUtils configUtils;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(ModelMap modelMap) {
        service.listList(modelMap, 1, 10);
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "index";
    }

    @GetMapping(value = "/about")
    public String article(ModelMap modelMap) {
        StPage page = pageDao.selectByAlias("about");

        if (null == page) {
            return "redirect:/404";
        } else {
            pageDao.addView(page.getId());

            modelMap.put("page", page);
            return "page";
        }
    }

    @GetMapping(value = "/captcha")
    public void captcha(String key, int width, int height, HttpServletResponse response) throws IOException {
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(width, height);

        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        gifCaptcha.setGenerator(randomGenerator);

        gifCaptcha.createCode();
        String code = gifCaptcha.getCode();
        StCacheHolder.CACHE_5m.put("captcha_" + key, code);

        response.setContentType(MediaType.IMAGE_GIF_VALUE);
        gifCaptcha.write(response.getOutputStream());
    }

    @ResponseBody
    @RequestMapping(value = "/captchaVerify")
    public ModelResp captchaVerify(String key, String code)  {
        key = "captcha_" + key;
        Object cached = StCacheHolder.CACHE_5m.get(key);

        if(code.equals(cached)){
            StCacheHolder.CACHE_5m.remove(key);
            return ModelResp.success();
        }else{
            return ModelResp.error(0);
        }
    }

    @ResponseBody
    @GetMapping(value = "/initTest")
    public String initTest() {
        for (int i = 0; i < 20; i++) {
            SteToolCategory toolCategory = new SteToolCategory();
            toolCategory.setCategory("测试分类" + i);
            toolCategory.setCount(0);
            toolCategory.setStatus(StConst.YES);
            toolCategory.setWeight(i + 2);
            toolCategory.setIntro("简介" + i);
            toolCategory.setCreateTime(new Date());

            SpringUtil.getBean(SteToolCategoryDao.class).save(toolCategory);

            SteToolItem toolItem = new SteToolItem();

            toolItem.setCategory("测试分类0");
            toolItem.setToolName("卷" + i);
            toolItem.setWeight(i + 4);
            toolCategory.setStatus(StConst.YES);
            toolItem.setViewCount(RandomUtil.randomInt());
            toolItem.setThumbCount(RandomUtil.randomInt());
            toolItem.setIntro("简介" + i);
            toolItem.setCreateTime(new Date());

            SpringUtil.getBean(SteToolItemDao.class).save(toolItem);
        }

        return "success";
    }
}
