package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
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

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private ArticleService service;

    @Autowired
    private ConfigUtils configUtils;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(ModelMap modelMap) {
        service.listList(modelMap, 1, 10);
        modelMap.put("title", configUtils.getConfig("st_site_name"));
        return "list";
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

}
