package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Order(5)
public class ConfigInit implements ApplicationRunner {

    @Autowired
    private StConfigDao configMapper;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        item(StConst.ST_WX_NAME, "满茵科技", "微信公众号");
        item(StConst.ST_WX_QR, "/static/images/wx/mp.jpg", "微信公号二维码");
        item(StConst.ST_WX_SHOP_QR, "/static/images/wx/shop.jpg", "微信小店");

        item(StConst.ST_GITHUB_NAME, "minesoft", "Github 名字");
        item(StConst.ST_GITHUB_LINK, "https://github.com/minesoft", "Github 链接");
        item(StConst.ST_GITHUB_QR, "/static/images/github.jpg", "Github 链接二维码");

        item(StConst.ST_GITEE_NAME, "满茵科技", "码云名字");
        item(StConst.ST_GITEE_LINK, "https://gitee.com/minesoft", "码云链接");
        item(StConst.ST_GITEE_QR, "/static/images/gitee.jpg", "码云链接二维码");

        item(StConst.ST_SITE_NAME, "singte", "站点名字");
        item(StConst.ST_SITE_SUBTITLE, "基于MongoDB的完整单站程序", "站点副标题");

        item(StConst.ST_SITE_LOGO, "/static/logo-h.png", "站点logo-横版");
        item(StConst.ST_SITE_LOGO_BLOCK, "/static/logo.png", "站点logo-方版");

        item(StConst.ST_COPY_YEAR, "2021", "版权年份");
        item(StConst.ST_ICP, "", "ICP备案号");
    }

    public void item(String key, String val, String intro) {
        StConfig config = configMapper.selectByKey(key);
        if (config == null) {
            config = new StConfig();
            config.setConfigKey(key);
            config.setConfigVal(val);
            config.setIntro(intro);
            config.setStatus("1");
            config.setCreateTime(new Date());

            configMapper.save(config);
        }
    }
}
