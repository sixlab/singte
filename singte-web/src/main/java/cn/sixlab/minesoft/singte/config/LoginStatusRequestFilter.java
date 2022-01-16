package cn.sixlab.minesoft.singte.config;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.service.StUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class LoginStatusRequestFilter extends OncePerRequestFilter {

    @Autowired
    private StUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = WebUtils.getToken(request);

        if (StrUtil.isNotEmpty(token)) {
            StUser stUser = userDetailsService.loadUserByToken(token);

            if (null != stUser) {
                if (!StConst.YES.equals(stUser.getStatus())) {
                    // 禁用
                    WebUtils.getResponse().getWriter().write(ModelResp.error(StErr.LOGIN_DISABLE, "login.user.disable").toString());
                    return;
                } else if (new Date().compareTo(stUser.getTokenValid()) > 0) {
                    // 过期
                    WebUtils.getResponse().getWriter().write(ModelResp.error(StErr.LOGIN_EXPIRED, "login.status.expired").toString());
                    return;
                } else {
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(stUser.getUsername());

                        userDetailsService.updateTokenValid(stUser.getUsername(), token);

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the
                        // Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}
