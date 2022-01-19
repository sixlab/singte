package cn.sixlab.minesoft.singte.core.poetry;

import cn.sixlab.minesoft.singte.core.dao.SteAncientSectionDao;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PoetryImportApi {

    @Autowired
    public SteAncientSectionDao ancientSectionDao;

    // youmengying/youmengying.json
    public abstract void parseAncient(String type, String resp, SteAncientSection param);

}
