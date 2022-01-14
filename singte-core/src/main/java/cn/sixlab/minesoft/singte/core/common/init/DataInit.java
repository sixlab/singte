package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.dao.StMenuDao;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.dao.StWidgetDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import cn.sixlab.minesoft.singte.core.models.StMenu;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.models.StWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Order(5)
public class DataInit implements ApplicationRunner {

    @Autowired
    private StMenuDao menuDao;

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StConfigDao configDao;

    @Autowired
    private StWidgetDao widgetDao;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        configInit();

        menuInit();

        widgetInit();

        initAdmin();
    }

    private void widgetInit() {
        checkWidget("widget-hot", "热门文章", "热门文章");
        checkWidget("widget-new", "最新文章", "最新文章");
        checkWidget("widget-random", "随机文章", "随机文章");

        checkWidget("widget-view", "点击排行", "点击排行");
        checkWidget("widget-tags", "标签云", "标签云");

        checkWidget("widget-adv", "侧边广告", "侧边广告");
    }

    private void initAdmin() {
        StConfig config = configDao.selectByKey(StConst.ST_INIT);
        if (config == null) {
            config = new StConfig();
            config.setConfigKey(StConst.ST_INIT);
            config.setConfigVal("1");
            config.setIntro("系统初始化");
            config.setStatus("1");
            config.setCreateTime(new Date());

            configDao.save(config);

            StUser stUser = new StUser();
            stUser.setUsername("singte");
            stUser.setShowName("singte");
            stUser.setPassword(BCrypt.hashpw("123456"));
            stUser.setStatus(StConst.YES);
            stUser.setRole(StConst.ROLE_ADMIN);
            stUser.setCreateTime(new Date());

            userDao.save(stUser);
        }
    }

    public void menuInit() {
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

        checkMenu(false, "menu.ancient.book", "menu.ancient.manage", "far fa-circle", "/admin/ancient/book/list", 600100, "古文列表");
        checkMenu(false, "menu.ancient.section", "menu.ancient.manage", "far fa-circle", "/admin/ancient/section/list", 600200, "章节列表");
        checkMenu(false, "menu.ancient.set", "menu.ancient.manage", "far fa-circle", "/admin/ancient/set/list", 600300, "古文部属");
        checkMenu(false, "menu.ancient.category", "menu.ancient.manage", "far fa-circle", "/admin/ancient/category/list", 600400, "古文分类");

        checkMenu(false, "menu.config.list", "menu.system.manage", "far fa-circle", "/admin/config/list", 800100, "800100");
        checkMenu(false, "menu.menu.list", "menu.system.manage", "far fa-circle", "/admin/menu/list", 800200, "800200");
        checkMenu(false, "menu.widget.list", "menu.system.manage", "far fa-circle", "/admin/widget/list", 800250, "800250");
        checkMenu(false, "menu.user.list", "menu.system.manage", "far fa-circle", "/admin/user/list", 800300, "800300");

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

    private void configInit() {
        checkConfig(StConst.ST_WX_NAME, "满茵科技", "微信公众号");
        checkConfig(StConst.ST_WX_QR, "/static/images/wx/mp.jpg", "微信公号二维码");
        checkConfig(StConst.ST_WX_SHOP_QR, "/static/images/wx/shop.jpg", "微信小店");

        checkConfig(StConst.ST_GITHUB_NAME, "minesoft", "Github 名字");
        checkConfig(StConst.ST_GITHUB_LINK, "https://github.com/minesoft", "Github 链接");
        checkConfig(StConst.ST_GITHUB_QR, "/static/images/github.jpg", "Github 链接二维码");

        checkConfig(StConst.ST_GITEE_NAME, "满茵科技", "码云名字");
        checkConfig(StConst.ST_GITEE_LINK, "https://gitee.com/minesoft", "码云链接");
        checkConfig(StConst.ST_GITEE_QR, "/static/images/gitee.jpg", "码云链接二维码");

        checkConfig(StConst.ST_SITE_NAME, "singte", "站点名字");
        checkConfig(StConst.ST_SITE_SUBTITLE, "基于MongoDB的完整单站程序", "站点副标题");

        checkConfig(StConst.ST_SITE_LOGO, "/static/logo-h.png", "站点logo-横版");
        checkConfig(StConst.ST_SITE_LOGO_BLOCK, "/static/logo.png", "站点logo-方版");

        checkConfig(StConst.ST_COPY_YEAR, "2021", "版权年份");
        checkConfig(StConst.ST_ICP, "", "ICP备案号");
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

    public void checkConfig(String key, String val, String intro) {
        StConfig config = configDao.selectByKey(key);
        if (config == null) {
            config = new StConfig();
            config.setConfigKey(key);
            config.setConfigVal(val);
            config.setIntro(intro);
            config.setStatus("1");
            config.setCreateTime(new Date());

            configDao.save(config);
        }
    }

    public void checkWidget(String widgetCode, String widgetName, String intro) {
        StWidget widget = widgetDao.selectByCode(widgetCode);
        if (widget == null) {
            widget = new StWidget();
            widget.setWidgetCode(widgetCode);
            widget.setWidgetName(widgetName);
            widget.setWidgetIntro(intro);
            widget.setStatus("1");
            widget.setWeight(RandomUtil.randomInt(100));
            widget.setCreateTime(new Date());

            widgetDao.save(widget);
        }
    }
}
