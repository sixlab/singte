package cn.sixlab.minesoft.singte.core.common.config;

import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StErr;
import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.common.vo.StException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class StControllerAdvice implements ResponseBodyAdvice<ModelResp>{

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getReturnType().equals(ModelResp.class);
    }

    @Override
    public ModelResp beforeBodyWrite(ModelResp modelResp, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return modelResp.setMessage(I18nUtils.get(modelResp.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handException(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();

        ModelAndView model = WebUtils.requestModel(request, "error");

        model.addObject("status", StErr.UNKNOWN);
        String message = e.getMessage();
        if(null!=message && message.length() < 100){
            message = I18nUtils.get(message);
        }
        model.addObject("message", message);

        return model;
    }

    @ExceptionHandler({StException.class})
    public ModelAndView handSelfException(HttpServletRequest request, StException e) throws Exception {
        e.printStackTrace();

        ModelAndView model = WebUtils.requestModel(request, "error");

        model.addObject("status", e.getStatus());
        model.addObject("message", e.getMessage());

        return model;
    }
}
