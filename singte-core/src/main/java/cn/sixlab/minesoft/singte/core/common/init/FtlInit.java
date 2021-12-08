package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.common.directive.ArticlesDirective;
import cn.sixlab.minesoft.singte.core.common.directive.ConfigDirective;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.mapper.StMenuMapper;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(10)
@Slf4j
public class FtlInit implements ApplicationRunner {

    @Autowired
    private ConfigUtils configUtils;

    @Autowired
    private ConfigDirective configDirective;

    @Autowired
    private ArticlesDirective articlesDirective;

    @Autowired
    private Configuration configuration;

    @Autowired
    private StMenuMapper menuMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configFtl();
    }

    public void configFtl() {
        try {
            configuration.setSharedVariable("gWxName", configUtils.getConfig(StConst.ST_WX_NAME));
            configuration.setSharedVariable("gWxQrcode", configUtils.getConfig(StConst.ST_WX_QR));

            configuration.setSharedVariable("gWeiboId", configUtils.getConfig(StConst.ST_WBO_ID));
            configuration.setSharedVariable("gWeiboQrcode", configUtils.getConfig(StConst.ST_WBO_QR));

            configuration.setSharedVariable("gSiteName", configUtils.getConfig(StConst.ST_SITE_NAME));
            configuration.setSharedVariable("gLogo", configUtils.getConfig(StConst.ST_SITE_LOGO));
            configuration.setSharedVariable("gCopyYear", configUtils.getConfig(StConst.ST_COPY_YEAR));
            configuration.setSharedVariable("gICP", configUtils.getConfig(StConst.ST_ICP));


            configuration.setSharedVariable("gMenuGroups", menus());

            configuration.setSharedVariable("gHotKeywords", null);

            configuration.setSharedVariable("StConfig", configDirective);
            configuration.setSharedVariable("StArticles", articlesDirective);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }

    private Map<String, List<StMenu>> menus() {
        Map<String, List<StMenu>> result = new HashMap<>();

        result.put("nav", menuMapper.selectGroupMenus("nav"));
        result.put("sider", menuMapper.selectGroupMenus("sider"));
        result.put("footer", menuMapper.selectGroupMenus("footer"));

        return result;
    }

}
