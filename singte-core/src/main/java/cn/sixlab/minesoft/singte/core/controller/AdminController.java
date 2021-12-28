package cn.sixlab.minesoft.singte.core.controller;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.TokenUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private StUserDetailsService userDetailsService;

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ModelResp login(String username, String password, String captchaKey, String captchaCode) {
        captchaKey = "captcha_" + captchaKey;
        Object cached = StCacheHolder.CACHE_5m.get(captchaKey);

        if (captchaCode.equals(cached)) {
            StCacheHolder.CACHE_5m.remove(captchaKey);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (null == userDetails) {
                return ModelResp.error(StErr.NOT_EXIST, "login.user.none");
            }

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            Stream<? extends GrantedAuthority> stream = authorities.stream().filter((Predicate<GrantedAuthority>) grantedAuthority -> grantedAuthority.getAuthority().equals(StConst.ROLE_USER));
            if(stream.findAny().isPresent()){
                return ModelResp.error(StErr.NOT_EXIST, "login.user.none");
            }

            if (!BCrypt.checkpw(password, userDetails.getPassword())) {
                return ModelResp.error(StErr.LOGIN_PWD_ERR, "login.pwd.error");
            }

            if (!userDetails.isEnabled()) {
                return ModelResp.error(StErr.LOGIN_DISABLE, "login.user.disable");
            }

            final String token = TokenUtils.generateToken(userDetails);
            userDetailsService.updateToken(username, token);

            return ModelResp.success().add("token", token);
        } else {
            return ModelResp.error(StErr.LOGIN_CAPTCHA, "login.captcha.diff");
        }
    }

}
