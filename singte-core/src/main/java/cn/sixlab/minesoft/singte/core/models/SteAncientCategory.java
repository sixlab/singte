package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SteAncientCategory extends BaseModel {

    private String ancientSet;

    private String ancientCategory;

    private Integer count;

    private Integer weight;

    private String intro;

}