package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StLang extends BaseModel {

    private String langCode;

    private String langText;

    private String langIcon;

    private String intro;

}