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
        checkMenu(true, "menu.stack.manage", "menu.st-level1", "fas fa-th", "", 710, "工具管理");
        checkMenu(true, "menu.system.manage", "menu.st-level1", "fas fa-th", "", 800, "系统管理");

        checkMenu(false, "menu.article.list", "menu.article.manage", "far fa-circle", "/admin/article/list", 200100, "文章列表");
        checkMenu(false, "menu.page.list", "menu.article.manage", "far fa-circle", "/admin/page/list", 200130, "页面列表");
        checkMenu(false, "menu.article.category", "menu.article.manage", "far fa-circle", "/admin/category/list", 200200, "分类列表");
        checkMenu(false, "menu.article.keyword", "menu.article.manage", "far fa-circle", "/admin/keyword/list", 200300, "关键词列表");

        checkMenu(false, "menu.spider.list", "menu.spider.manage", "far fa-circle", "/admin/table/StSpider/list", 500100, "爬虫列表");
        checkMenu(false, "menu.spider.log", "menu.spider.manage", "far fa-circle", "/admin/table/StSpiderLog/list", 500200, "爬虫记录");
        checkMenu(false, "menu.spider.result", "menu.spider.manage", "far fa-circle", "/admin/table/StSpiderResult/list", 500300, "爬虫结果");
        checkMenu(false, "menu.spider.push", "menu.spider.manage", "far fa-circle", "/admin/table/StSpiderPush/list", 500400, "推送规则");

        checkMenu(false, "menu.ancient.book", "menu.ancient.manage", "far fa-circle", "/admin/ancient/book/list", 600100, "古文列表");
        checkMenu(false, "menu.ancient.section", "menu.ancient.manage", "far fa-circle", "/admin/ancient/section/list", 600200, "章节列表");
        checkMenu(false, "menu.ancient.set", "menu.ancient.manage", "far fa-circle", "/admin/ancient/set/list", 600300, "古文部属");
        checkMenu(false, "menu.ancient.category", "menu.ancient.manage", "far fa-circle", "/admin/ancient/category/list", 600400, "古文分类");

        checkMenu(false, "menu.tool.item", "menu.tool.manage", "far fa-circle", "/admin/table/SteToolItem/list", 700100, "工具列表");
        checkMenu(false, "menu.tool.category", "menu.tool.manage", "far fa-circle", "/admin/tool/category/list", 700200, "工具分类");

        checkMenu(false, "menu.stack.domain", "menu.stack.manage", "far fa-circle", "/admin/table/StLinkDomain/list", 710100, "导航站点");
        checkMenu(false, "menu.stack.type", "menu.stack.manage", "far fa-circle", "/admin/table/StLinkType/list", 710150, "导航分类");
        checkMenu(false, "menu.stack.link", "menu.stack.manage", "far fa-circle", "/admin/table/StLink/list", 710200, "导航网址");

        checkMenu(false, "menu.user.list", "menu.system.manage", "far fa-circle", "/admin/user/list", 800100, "用户列表");
        checkMenu(false, "menu.domain.list", "menu.system.manage", "far fa-circle", "/admin/table/StDomain/list", 800150, "域名列表");
        checkMenu(false, "menu.config.list", "menu.system.manage", "far fa-circle", "/admin/config/list", 800200, "系统参数");
        checkMenu(false, "menu.menu.list", "menu.system.manage", "far fa-circle", "/admin/menu/list", 800300, "菜单管理");
        checkMenu(false, "menu.lang.list", "menu.system.manage", "far fa-circle", "/admin/lang/list", 800400, "语言管理");
        checkMenu(false, "menu.widget.list", "menu.system.manage", "far fa-circle", "/admin/widget/list", 800500, "组件列表");
        checkMenu(false, "menu.task.list", "menu.system.manage", "far fa-circle", "/admin/table/StTask/list", 800600, "定时任务管理");
        checkMenu(false, "menu.server.info", "menu.system.manage", "far fa-circle", "/admin/server/info", 800900, "服务器信息");

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
