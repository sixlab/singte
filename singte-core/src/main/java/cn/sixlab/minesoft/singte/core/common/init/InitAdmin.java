package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StConfigDao;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StConfig;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitAdmin implements BaseInitComponent {

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StConfigDao configDao;

    @Value("${initPWD}")
    private String initPWD;

    @Override
    public void init() {
        StConfig config = configDao.selectByKey(StConst.ST_INIT);
        if (config == null) {
            // 系统初始化，创建管理用户
            config = new StConfig();
            config.setConfigKey(StConst.ST_INIT);
            config.setConfigVal("1");
            config.setIntro("系统初始化");
            config.setStatus("1");
            config.setCreateTime(new Date());

            configDao.save(config);

            initAdmin();
        }else{
            if (StrUtil.isNotEmpty(initPWD)) {
                initAdmin();
            }
        }
    }

    private void initAdmin(){
        StUser stUser = userDao.selectByUsername("singte");

        if (stUser == null) {
            stUser = new StUser();
            stUser.setUsername("singte");
            stUser.setShowName("singte");
            stUser.setCreateTime(new Date());
        }

        if (StrUtil.isEmpty(initPWD)) {
            initPWD = "123456";
        }
        stUser.setPassword(BCrypt.hashpw(initPWD));
        stUser.setRole(StConst.ROLE_ADMIN);
        stUser.setStatus(StConst.YES);

        userDao.save(stUser);
    }
}
