package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.crypto.digest.BCrypt;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitAdmin implements BaseInitComponent {

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StConfigDao configDao;

    @Override
    public void init() {
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
}
