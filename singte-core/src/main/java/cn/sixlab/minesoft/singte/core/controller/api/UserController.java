package cn.sixlab.minesoft.singte.core.controller.api;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.TokenUtils;
import cn.sixlab.minesoft.singte.core.common.utils.UserUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.common.vo.StException;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
@Api(tags = "用户操作", description = "/user 用户操作")
public class UserController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StUserDetailsService userDetailsService;

    @ResponseBody
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp login(
            @ApiParam(name = "username", value = "用户名") @RequestParam(required = false) String username,
            @ApiParam(name = "password", value = "密码") @RequestParam(required = false) String password
    ) {
        log.info(" username:" + username + " begin login ");
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
    @PostMapping(value = "/info")
    @ApiOperation(value = "用户信息", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public ModelResp info() {
        StUser stUser = userDetailsService.loadUser(UserUtils.getUsername());

        return ModelResp.success(stUser);
    }

}
