package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {
    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }

    @ResponseBody
    @GetMapping(value = "/test")
    public String test(String clzName, String method) {

        try {
            Class clz = Class.forName(clzName);
            Object bean = SpringUtil.getBean(clzName);
            Method clzMethod = clz.getMethod(method, null);
            clzMethod.invoke(bean, null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return "ok";
    }
}
