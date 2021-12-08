package cn.sixlab.minesoft.singte.init;

import cn.sixlab.minesoft.singte.core.common.config.StProperties;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.IoUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.mapper.StConfigMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(0)
public class TableInit implements ApplicationRunner {

    @Autowired
    private StProperties stProperties;

    @Autowired
    private ConfigUtils configUtils;

    @Autowired
    private StConfigMapper configMapper;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//      1. 先运行 StConfigMapper 建表，防止后边取 db version 报错
        configMapper.init();

//      2. 取 db version 的值
        String dbVersion = configUtils.getConfig(StConst.ST_VERSION);

//      3. 和 config version作对比
        Integer version = stProperties.getVersion();
        if (StringUtils.isEmpty(dbVersion)) {
//            3.1 db version 为空，完整初始化
            String sql = IoUtils.readAsserts("sql/all.sql");
            if (StringUtils.isNotEmpty(sql)) {
                configMapper.runSQL(sql);
            }
        } else {
            int oldVersion = Integer.parseInt(dbVersion);
            if (oldVersion < version) {
//                  3.2.1 db version 落后 config version
                for (int i = oldVersion; i < version; i++) {
                    String sql = IoUtils.readAsserts("sql/update" + i + ".sql");
                    if (StringUtils.isNotEmpty(sql)) {
                        configMapper.runSQL(sql);
                    }
                }
            }
        }

//        4. 更新 db version
        configUtils.setConfig(StConst.ST_VERSION, version + "", "系统数据库结构版本");
    }

}
