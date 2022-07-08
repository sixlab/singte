package cn.sixlab.minesoft.singte.core.controller.api;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.TokenUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.common.vo.StException;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/user")
@Api(tags = "登录接口", description = "/user 用于登录")
public class ApiLoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StUserDetailsService userDetailsService;

    @Autowired
    private StUserDao userMapper;

    @ResponseBody
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", notes = "用户登录 - 说明", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp userLogin(
            @ApiParam(name = "用户名", value = "username") @RequestParam(value = "username", required = false) String username,
            @ApiParam(name = "密码", value = "password") @RequestParam(value = "password", required = false) String password) {
        logger.info(" username:" + username + " begin login ");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new StException(StErr.LOGIN_DISABLE, "login.err.disable");
        } catch (BadCredentialsException e) {
            throw new StException(StErr.LOGIN_PWD_ERR, "login.err.pwd");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = TokenUtils.generateToken(userDetails);
        userDetailsService.updateToken(username, token);

        return ModelResp.success().add("Authorization", token);
    }

    @ResponseBody
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增用户", notes = "用户登录 - 说明", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp addUser(
            @ApiParam(name = "用户名", value = "username") @RequestParam(value = "username", required = false) String username,
            @ApiParam(name = "密码", value = "password") @RequestParam(value = "password", required = false) String password) {
        logger.info(" username:" + username + " begin add ");

        StUser stUser = userMapper.selectByUsername(username);

        if(null == stUser){
            stUser = new StUser();
            stUser.setUsername(username);
            stUser.setShowName(username);
            stUser.setPassword(new BCryptPasswordEncoder().encode(password));
            stUser.setStatus("1");
            stUser.setCreateTime(new Date());

            userMapper.save(stUser);
        }else{
            return ModelResp.error(StErr.EXIST_SAME, "用户已存在");
        }

        return ModelResp.success();
    }
}
