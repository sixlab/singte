package cn.sixlab.minesoft.singte.core.controller.user;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/captcha")
@Api(tags = "验证码", description = "/captcha 验证码")
public class CaptchaController extends BaseController {

    @GetMapping(value = "/image")
    @ApiOperation(value = "显示图片验证码", notes = "显示图片版本验证码", produces = "image/gif")
    public void captcha(
            @ApiParam(name = "key", value = "图片唯一标示符", required = true) @RequestParam String key,
            @ApiParam(name = "width", value = "图片宽", required = true) @RequestParam int width,
            @ApiParam(name = "height", value = "图片高", required = true) @RequestParam int height,
            HttpServletResponse response) throws IOException {
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
    @PostMapping(value = "/verify")
    @ApiOperation(value = "验证验证码-用于测试", notes = "验证验证码-用于测试", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp verify(
            @ApiParam(name = "key", value = "图片唯一标示符", required = true) @RequestParam String key,
            @ApiParam(name = "code", value = "用户输入的验证码", required = true) @RequestParam String code
    ) {
        key = "captcha_" + key;
        Object cached = StCacheHolder.CACHE_5m.get(key);

        if (code.equals(cached)) {
            StCacheHolder.CACHE_5m.remove(key);
            return ModelResp.success();
        } else {
            return ModelResp.error(0);
        }
    }

}
