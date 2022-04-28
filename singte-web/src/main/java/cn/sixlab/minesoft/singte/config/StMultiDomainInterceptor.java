package cn.sixlab.minesoft.singte.config;

import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.dao.StDomainDao;
import cn.sixlab.minesoft.singte.core.models.StDomain;
import cn.sixlab.minesoft.singte.core.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StMultiDomainInterceptor implements HandlerInterceptor {

    @Autowired
    private DomainService domainService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (null != modelAndView) {
            String viewName = modelAndView.getViewName();
            if (null != viewName && viewName.startsWith("/") && !viewName.endsWith("/")) {
                StDomain activeDomain = domainService.activeDomain();

                if (null != activeDomain) {
                    String tplPath = activeDomain.getTplPath();
                    modelAndView.setViewName(tplPath + viewName);
                }
            }
        }

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
