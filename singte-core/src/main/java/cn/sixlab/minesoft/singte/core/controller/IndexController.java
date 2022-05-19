package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.TokenUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StPageDao;
import cn.sixlab.minesoft.singte.core.models.StPage;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private StPageDao pageDao;

    @Autowired
    private StUserDetailsService userDetailsService;

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
    public ModelResp captchaVerify(String key, String code) {
        key = "captcha_" + key;
        Object cached = StCacheHolder.CACHE_5m.get(key);

        if (code.equals(cached)) {
            StCacheHolder.CACHE_5m.remove(key);
            return ModelResp.success();
        } else {
            return ModelResp.error(0);
        }
    }

    @ResponseBody
    @GetMapping(value = "/initTest")
    public String initTest() {

        return I18nUtils.get("login.forbidden");
    }

    @ResponseBody
    @PostMapping(value = "/user/login")
    public ModelResp login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails) {
            return ModelResp.error(StErr.NOT_EXIST, "login.user.none");
        }

        if (!BCrypt.checkpw(password, userDetails.getPassword())) {
            return ModelResp.error(StErr.LOGIN_PWD_ERR, "login.err.pwd");
        }

        if (!userDetails.isEnabled()) {
            return ModelResp.error(StErr.LOGIN_DISABLE, "login.err.disable");
        }

        final String token = TokenUtils.generateToken(userDetails);
        userDetailsService.updateToken(username, token);

        return ModelResp.success().add("Authorization", token);
    }
}
