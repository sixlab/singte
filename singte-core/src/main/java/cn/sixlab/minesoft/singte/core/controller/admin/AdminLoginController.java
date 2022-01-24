package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.*;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AdminLoginController extends BaseController {

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StUserDetailsService userDetailsService;

    @GetMapping(value = "/login")
    public String login() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken){
            return "admin/login";
        }

        return "redirect:/admin/index";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        StUser loginUser = WebUtils.getLoginUser();

        StUser preUser = userDao.selectByUsername(loginUser.getUsername());
        preUser.setToken(null);
        preUser.setTokenValid(null);
        userDao.save(preUser);

        WebUtils.addCookie("Authorization", "", 0);

        SecurityContextHolder.clearContext();

        return "redirect:/admin/login";
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
            if (stream.findAny().isPresent()) {
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
        } else {
            return ModelResp.error(StErr.LOGIN_CAPTCHA, "login.captcha.diff");
        }
    }

}
