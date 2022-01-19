package cn.sixlab.minesoft.singte.core.poetry;

import cn.sixlab.minesoft.singte.core.models.SteAncientBook;
import cn.sixlab.minesoft.singte.core.models.SteAncientSection;

public interface PoetryImportApi {

    int parseAncient(String type, String resp, SteAncientBook book, SteAncientSection param);

}
