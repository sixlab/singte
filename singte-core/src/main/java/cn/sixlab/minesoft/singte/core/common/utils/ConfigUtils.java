package cn.sixlab.minesoft.singte.core.common.utils;

import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ConfigUtils {

    @Autowired
    private StConfigDao configMapper;

    public String getConfig(String key) {
        StConfig config = configMapper.selectByKey(key);
        if (null != config && "1".equals(config.getStatus())) {
            return config.getConfigVal();
        }

        return null;
    }

    public StConfig setConfig(String key, String val) {
        return setConfig(key, val, "");
    }

    public StConfig setConfig(String key, String val, String intro) {
        StConfig config = configMapper.selectByKey(key);
        if (null == config) {
            config = new StConfig();
            config.setConfigKey(key);
            config.setConfigVal(val);
            config.setIntro(intro);
            config.setStatus("1");
            config.setCreateTime(new Date());

            configMapper.save(config);
        } else {
            config.setConfigVal(val);
            config.setIntro(intro);
            config.setUpdateTime(new Date());
            configMapper.save(config);
        }

        return config;
    }
}
