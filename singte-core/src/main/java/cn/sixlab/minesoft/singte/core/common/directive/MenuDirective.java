package cn.sixlab.minesoft.singte.core.common.directive;

import cn.sixlab.minesoft.singte.core.common.utils.WebUtils;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.collections4.CollectionUtils;
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
        String requestURI = WebUtils.getRequest().getRequestURI();
        List<StMenu> menuList = menuMapper.selectGroupMenus(group);

        if ("st-level1".equals(group)) {
            env.setVariable("requestUri", ObjectWrapper.DEFAULT_WRAPPER.wrap(requestURI));
            env.setVariable("stMenuGroupList", ObjectWrapper.DEFAULT_WRAPPER.wrap(menuList));
        } else {
            env.setVariable("stMenuGroup", ObjectWrapper.DEFAULT_WRAPPER.wrap(menuList));

            boolean hasMenu = CollectionUtils.isNotEmpty(menuList);
            env.setVariable("stHasMenu", ObjectWrapper.DEFAULT_WRAPPER.wrap(hasMenu));

            if (hasMenu) {
                boolean folderOpen = false;
                for (StMenu stMenu : menuList) {
                    if (stMenu.getMenuLink().equals(requestURI)) {
                        folderOpen = true;
                        break;
                    }
                }
                env.setVariable("stFolderOpen", ObjectWrapper.DEFAULT_WRAPPER.wrap(folderOpen));
            }
        }

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
