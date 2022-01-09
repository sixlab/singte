package cn.sixlab.minesoft.singte.module.minesoft.service;

import cn.sixlab.minesoft.singte.module.minesoft.dao.StePoemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.StePoem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PoemService {

    @Autowired
    private StePoemDao poemMapper;


    public void addPoem(StePoem poem) {
        poem.setCreateTime(new Date());
        poemMapper.save(poem);
    }

}
