package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.dao.SteAncientBookDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AncientService {

    @Autowired
    private SteAncientBookDao ancientDao;

    public void addAncient(SteAncientBook ancient) {
        ancient.setCreateTime(new Date());
        ancientDao.save(ancient);
    }

}
