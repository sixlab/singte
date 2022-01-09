package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Order(5)
public class MenuInit implements ApplicationRunner {

    @Autowired
    private StMenuDao menuDao;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        checkMenu(true, "menu.article.manage", "menu.st-level1", "fas fa-th", "", 200, "文章管理");
        checkMenu(true, "menu.spider.manage", "menu.st-level1", "fas fa-th", "", 500, "爬虫管理");
        checkMenu(true, "menu.ancient.manage", "menu.st-level1", "fas fa-th", "", 600, "古文管理");
        checkMenu(true, "menu.system.manage", "menu.st-level1", "fas fa-th", "", 800, "系统管理");

        checkMenu(false, "menu.index", "menu.st-level1", "fas fa-tachometer-alt", "/admin/index", 100, "首页");

        checkMenu(false, "menu.article.list", "menu.article.manage", "far fa-circle", "/admin/article/list", 200100, "文章列表");
        checkMenu(false, "menu.page.list", "menu.article.manage", "far fa-circle", "/admin/page/list", 200130, "页面列表");
        checkMenu(false, "menu.article.category", "menu.article.manage", "far fa-circle", "/admin/category/list", 200200, "分类列表");
        checkMenu(false, "menu.article.keyword", "menu.article.manage", "far fa-circle", "/admin/keyword/list", 200300, "关键词列表");

        checkMenu(false, "menu.spider.list", "menu.spider.manage", "far fa-circle", "/admin/spider/list", 500100, "爬虫列表");

        checkMenu(false, "menu.ancient.list", "menu.ancient.manage", "far fa-circle", "/admin/ancient/list", 600100, "古文列表");
        checkMenu(false, "menu.ancient.category", "menu.ancient.manage", "far fa-circle", "/admin/ancientCategory/list", 600200, "古文分类");

        checkMenu(false, "menu.config.list", "menu.system.manage", "far fa-circle", "/admin/config/list", 800100, "800100");
        checkMenu(false, "menu.menu.list", "menu.system.manage", "far fa-circle", "/admin/menu/list", 800200, "800200");
        checkMenu(false, "menu.user.list", "menu.system.manage", "far fa-circle", "/admin/user/list", 800300, "800300");


        checkMenu(false, "nav.index", "nav", "far fa-circle", "/", 10, "首页");
        checkMenu(false, "nav.ancient", "nav", "far fa-circle", "/ancient", 20, "古文");
        checkMenu(false, "nav.tools", "nav", "far fa-circle", "/tools", 30, "工具");
        checkMenu(false, "nav.about", "nav", "far fa-circle", "/about", 99, "关于");


        checkMenu(false, "sider.ancient", "sider", "far fa-circle", "/ancient", 20, "古文");
        checkMenu(false, "sider.tools", "sider", "far fa-circle", "/tools", 30, "工具");

        checkMenu(false, "footer.ancient", "footer", "far fa-circle", "/ancient", 20, "古文");
        checkMenu(false, "footer.tools", "footer", "far fa-circle", "/tools", 30, "工具");
        checkMenu(false, "sixlab", "footer", "far fa-circle", "http://sixlab.cn", 90, "sixalb");
        checkMenu(false, "minesoft", "footer", "far fa-circle", "http://minesoft.tech", 95, "minesoft");
        checkMenu(false, "footer.about", "footer", "far fa-circle", "/about", 99, "关于");
    }

    private void checkMenu(boolean folderMenu, String menuCode, String menuGroup, String menuIcon, String menuLink, int weight, String intro) {
        StMenu menu = menuDao.selectByMenuCode(menuCode);
        if (menu == null) {
            menu = new StMenu();
            menu.setMenuCode(menuCode);
            menu.setMenuGroup(menuGroup);
            menu.setMenuIcon(menuIcon);
            menu.setMenuLink(menuLink);
            menu.setFolderMenu(folderMenu);
            menu.setStatus("1");
            menu.setWeight(weight);
            menu.setIntro(intro);
            menu.setCreateTime(new Date());

            menuDao.save(menu);
        }
    }
}
