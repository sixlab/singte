package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StConfig extends BaseModel {

    private String configKey;

    private String configVal;

    private String configGroup;

    private String intro;

}