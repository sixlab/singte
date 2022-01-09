package cn.sixlab.minesoft.singte.module.minesoft.service;

import cn.sixlab.minesoft.singte.module.minesoft.dao.SteAncientDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.SteAncient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AncientService {

    @Autowired
    private SteAncientDao ancientDao;

    public void addAncient(SteAncient ancient) {
        ancient.setCreateTime(new Date());
        ancientDao.save(ancient);
    }

}
