package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.common.directive.ArticlesDirective;
import cn.sixlab.minesoft.singte.core.common.directive.ConfigDirective;
import cn.sixlab.minesoft.singte.core.common.directive.KeywordDirective;
import cn.sixlab.minesoft.singte.core.common.directive.MenuDirective;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
@Slf4j
public class FtlInit implements ApplicationRunner {

    @Autowired
    private Configuration configuration;

    @Autowired
    private MenuDirective menuDirective;

    @Autowired
    private ConfigDirective configDirective;

    @Autowired
    private KeywordDirective keywordDirective;

    @Autowired
    private ArticlesDirective articlesDirective;

    @Override
    public void run(ApplicationArguments args) {
        configFtl();
    }

    public void configFtl() {
        configuration.setSharedVariable("gHotKeywords", null);

        configuration.setSharedVariable("StMenu", menuDirective);
        configuration.setSharedVariable("StConfig", configDirective);
        configuration.setSharedVariable("StKeyword", keywordDirective);
        configuration.setSharedVariable("StArticles", articlesDirective);
    }

}
