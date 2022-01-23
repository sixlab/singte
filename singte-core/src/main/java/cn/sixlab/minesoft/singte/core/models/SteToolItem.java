package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SteToolItem extends BaseModel {

    private String category;

    private String toolName;

    private String toolCode;

    private Integer viewCount;

    private Integer thumbCount;

    private Integer weight;

    private String intro;

}