package cn.sixlab.minesoft.singte.core.common.config;

import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.common.vo.StException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class StControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ModelAndView handException(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();

        ModelAndView model = WebUtils.requestModel(request, "error");

        model.addObject("status", 5000);
        model.addObject("error", e.getMessage());
        model.addObject("message", e.getMessage());

        return model;
    }

    @ExceptionHandler({StException.class})
    public ModelAndView handSelfException(HttpServletRequest request, StException e) throws Exception {
        e.printStackTrace();

        ModelAndView model = WebUtils.requestModel(request, "error");

        model.addObject("status", e.getStatus());
        model.addObject("error", e.getMessage());
        model.addObject("message", e.getMessage());

        return model;
    }

}
