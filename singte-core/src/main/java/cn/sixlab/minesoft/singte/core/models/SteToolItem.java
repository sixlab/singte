package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SteToolItem extends BaseModel {

    @StColumn(placeholder = "分类", order = 1, cssClass = "required")
    private String category;

    @StColumn(placeholder = "工具名称", order = 2, cssClass = "required")
    private String toolName;

    @StColumn(placeholder = "编号", order = 3, cssClass = "required")
    private String toolCode;

    @StColumn(type = "hidden", defaultVal = "0")
    private Integer viewCount;

    @StColumn(type = "hidden", defaultVal = "0")
    private Integer thumbCount;

    @StColumn(placeholder = "顺序", order = 4, cssClass = "required")
    private Integer weight;

    @StColumn(placeholder = "简介", order = 5)
    private String intro;

}