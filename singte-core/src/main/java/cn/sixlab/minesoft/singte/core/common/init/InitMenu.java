package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitMenu implements BaseInitComponent {

    @Autowired
    private StMenuDao menuDao;

    @Override
    public void init() {
        checkMenu(false, "menu.index", "menu.st-level1", "fas fa-tachometer-alt", "/admin/index", 100, "首页");

        checkMenu(true, "menu.article.manage", "menu.st-level1", "fas fa-th", "", 200, "文章管理");
        checkMenu(true, "menu.spider.manage", "menu.st-level1", "fas fa-th", "", 500, "爬虫管理");
        checkMenu(true, "menu.ancient.manage", "menu.st-level1", "fas fa-th", "", 600, "古文管理");
        checkMenu(true, "menu.tool.manage", "menu.st-level1", "fas fa-th", "", 700, "工具管理");
        checkMenu(true, "menu.system.manage", "menu.st-level1", "fas fa-th", "", 800, "系统管理");

        checkMenu(false, "menu.article.list", "menu.article.manage", "far fa-circle", "/admin/article/list", 200100, "文章列表");
        checkMenu(false, "menu.page.list", "menu.article.manage", "far fa-circle", "/admin/page/list", 200130, "页面列表");
        checkMenu(false, "menu.article.category", "menu.article.manage", "far fa-circle", "/admin/category/list", 200200, "分类列表");
        checkMenu(false, "menu.article.keyword", "menu.article.manage", "far fa-circle", "/admin/keyword/list", 200300, "关键词列表");

        checkMenu(false, "menu.spider.list", "menu.spider.manage", "far fa-circle", "/admin/spider/list", 500100, "爬虫列表");

        checkMenu(false, "menu.ancient.book", "menu.ancient.manage", "far fa-circle", "/admin/ancient/book/list", 600100, "古文列表");
        checkMenu(false, "menu.ancient.section", "menu.ancient.manage", "far fa-circle", "/admin/ancient/section/list", 600200, "章节列表");
        checkMenu(false, "menu.ancient.set", "menu.ancient.manage", "far fa-circle", "/admin/ancient/set/list", 600300, "古文部属");
        checkMenu(false, "menu.ancient.category", "menu.ancient.manage", "far fa-circle", "/admin/ancient/category/list", 600400, "古文分类");

        checkMenu(false, "menu.tool.item", "menu.tool.manage", "far fa-circle", "/admin/tool/list", 700100, "工具列表");
        checkMenu(false, "menu.tool.category", "menu.tool.manage", "far fa-circle", "/admin/tool/category/list", 700200, "工具分类");

        checkMenu(false, "menu.config.list", "menu.system.manage", "far fa-circle", "/admin/config/list", 800100, "系统参数");
        checkMenu(false, "menu.menu.list", "menu.system.manage", "far fa-circle", "/admin/menu/list", 800200, "菜单管理");
        checkMenu(false, "menu.lang.list", "menu.system.manage", "far fa-circle", "/admin/lang/list", 800200, "语言管理");
        checkMenu(false, "menu.widget.list", "menu.system.manage", "far fa-circle", "/admin/widget/list", 800250, "组件列表");
        checkMenu(false, "menu.user.list", "menu.system.manage", "far fa-circle", "/admin/user/list", 800300, "用户列表");

        checkMenu(false, "nav.index", "nav", "far fa-circle", "/", 10, "首页");
        checkMenu(false, "nav.articles", "nav", "far fa-circle", "/article/list", 15, "文章");
        checkMenu(false, "nav.ancient", "nav", "far fa-circle", "/ancient/set", 20, "古文");
        checkMenu(false, "nav.tools", "nav", "far fa-circle", "/tool/list", 30, "工具");
        checkMenu(false, "nav.about", "nav", "far fa-circle", "/about", 99, "关于");

        checkMenu(false, "footer.index", "footer", "icon-shouye", "/", 10, "首页");
        checkMenu(false, "footer.ancient", "footer", "icon-navicon-wzgl", "/ancient/set", 20, "古文");
        checkMenu(false, "footer.tools", "footer", "icon-fenlei", "/tool/list", 30, "工具");
        checkMenu(false, "footer.about", "footer", "icon-My", "/about", 99, "关于");
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
