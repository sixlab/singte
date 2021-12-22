package cn.sixlab.minesoft.singte;

import cn.sixlab.minesoft.singte.config.JwtUtils;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private StUserDao userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @ResponseBody
    @RequestMapping(value = "/login")
    public ModelResp userLogin(String username, String password) throws Exception {
        logger.info(" username:" + username + " begin login ");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = StConst.JWT_HEADER +  jwtUtils.generateToken(userDetails);

        return ModelResp.success().add("token", token);
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public ModelResp addUser(String username, String password) {
        logger.info(" username:" + username + " begin add ");

        StUser stUser = userMapper.selectByUsername(username);

        if(null== stUser){
            stUser = new StUser();
            stUser.setUsername(username);
            stUser.setShowName(username);
            stUser.setPassword(passwordEncoder.encode(password));
            stUser.setStatus("1");
            stUser.setCreateTime(new Date());

            userMapper.save(stUser);
        }else{
            return ModelResp.error(StErr.EXIST_SAME, "用户已存在");
        }

        return ModelResp.success();
    }
}
