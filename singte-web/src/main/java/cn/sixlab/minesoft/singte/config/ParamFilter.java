package cn.sixlab.minesoft.singte.config;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.service.LangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(1)
@Slf4j
public class ParamFilter implements Filter {

    @Autowired
    private LangService langService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logParam(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void logParam(ServletRequest servletRequest) {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            //输出参数
            String result = "\n";

            String method = request.getMethod();
            result += method;

            result += " | ";

            String inComeUrI = request.getRequestURI();
            result += inComeUrI + "\n";

            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String element = parameterNames.nextElement();
                log.info("param:" + element + "=" + request.getParameter(element));
            }

            log.info(WebUtils.getIP());

            log.info(result);
        }

        String flag = WebUtils.getCookie("lang_flag");
        if(StrUtil.isEmpty(flag)){
            WebUtils.addCookie("lang_flag", "1", (int) StConst.SECONDS_YEAR_1);
            WebUtils.addCookie("lang", langService.getLang(), (int) StConst.SECONDS_YEAR_1);
        }
    }
}
