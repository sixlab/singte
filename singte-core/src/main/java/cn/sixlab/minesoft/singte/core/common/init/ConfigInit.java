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
    public void run(ApplicationArguments args) throws Exception {
        item(StConst.ST_WX_NAME, "满茵科技", "微信名字");
        item(StConst.ST_WX_QR, "/static/images/wx/mp.jpg", "微信公号二维码");
        item(StConst.ST_WBO_ID, "314566975", "微博ID");
        item(StConst.ST_WBO_QR, "/static/logo.png", "微博二维码");
        item(StConst.ST_SITE_NAME, "singte", "站点名字");
        item(StConst.ST_SITE_LOGO, "/static/logo-h.png", "站点logo");
        item(StConst.ST_COPY_YEAR, "2021", "版权年份");
        item(StConst.ST_ICP, "", "ICP备案号");
    }

    public void item(String key, String val, String intro) {
        StConst.ST_CONFIGS.put(key, intro);

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
