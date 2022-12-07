package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StKeyword extends BaseModel {

    private String keyword;

    private Integer count;

    private Integer weight;

    private String intro;

    private String flag;

}