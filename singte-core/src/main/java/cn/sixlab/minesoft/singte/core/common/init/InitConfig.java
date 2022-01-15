package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitConfig implements BaseInitComponent {

    @Autowired
    private StConfigDao configDao;

    @Override
    public void init() {
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
}
