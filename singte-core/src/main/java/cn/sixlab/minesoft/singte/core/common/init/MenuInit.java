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
        checkMenu(true, "menu.system.manage", "menu.st-level1", "fas fa-th", "", 800, "系统管理");

        checkMenu(false, "menu.index", "menu.st-level1", "fas fa-tachometer-alt", "/admin/index", 100, "首页");

        checkMenu(false, "menu.article.list", "menu.article.manage", "far fa-circle", "/admin/article/list", 200100, "文章列表");
        checkMenu(false, "menu.article.category", "menu.article.manage", "far fa-circle", "/admin/category/list", 200200, "分类列表");
        checkMenu(false, "menu.article.keyword", "menu.article.manage", "far fa-circle", "/admin/keyword/list", 200300, "关键词列表");

        checkMenu(false, "menu.spider.list", "menu.spider.manage", "far fa-circle", "/admin/spider/list", 500100, "爬虫列表");

        checkMenu(false, "menu.config.list", "menu.system.manage", "far fa-circle", "/admin/config/list", 800100, "800100");
        checkMenu(false, "menu.menu.list", "menu.system.manage", "far fa-circle", "/admin/menu/list", 800200, "800200");
        checkMenu(false, "menu.user.list", "menu.system.manage", "far fa-circle", "/admin/user/list", 800300, "800300");
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
