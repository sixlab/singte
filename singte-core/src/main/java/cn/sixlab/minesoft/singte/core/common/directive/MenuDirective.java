package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class MenuDirective implements TemplateDirectiveModel {

    @Autowired
    private StMenuDao menuMapper;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String group = MapUtils.getString(params, "group");
        if ("stAdmin".equals(group)) {
            List<StMenu> menuList = menuMapper.selectGroupMenus(group);

            env.setVariable("stMenuGroupList", ObjectWrapper.DEFAULT_WRAPPER.wrap(menuList));
        } else {
            List<StMenu> menuList = menuMapper.selectGroupMenus(group);

            env.setVariable("stMenuGroup", ObjectWrapper.DEFAULT_WRAPPER.wrap(menuList));
            env.setVariable("stHasMenu", ObjectWrapper.DEFAULT_WRAPPER.wrap(menuList.size() > 0));

            if (body != null) {
                body.render(env.getOut());
            }
        }
    }
}
