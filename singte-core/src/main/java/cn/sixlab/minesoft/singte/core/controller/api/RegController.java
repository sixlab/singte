package cn.sixlab.minesoft.singte.core.controller.api;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.CaptchaUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/reg")
@Api(tags = "注册", description = "/reg 注册")
public class RegController extends BaseController {

    @Autowired
    private StUserDao userMapper;

    @PostMapping(value = "/submit")
    @ApiOperation(value = "注册用户", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp addUser(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password,
            @ApiParam(name = "key", value = "图片唯一标示符", required = true) @RequestParam String key,
            @ApiParam(name = "code", value = "用户输入的验证码", required = true) @RequestParam String code
    ) {
        logger.info(" username:" + username + " begin reg ");

        if (CaptchaUtils.verify(key, code)) {
            StUser stUser = userMapper.selectByUsername(username);

            if (null == stUser) {
                stUser = new StUser();
                stUser.setUsername(username);
                stUser.setShowName(username);
                stUser.setPassword(new BCryptPasswordEncoder().encode(password));
                stUser.setStatus("1");
                stUser.setRole(StConst.ROLE_USER);
                stUser.setCreateTime(new Date());

                userMapper.save(stUser);

                return ModelResp.success();
            } else {
                return ModelResp.error(StErr.EXIST_SAME, "用户已存在");
            }
        } else {
            return ModelResp.error(StErr.VERIFY_ERROR, "验证码错误");
        }
    }
}
