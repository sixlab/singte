package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.configs")
public class SteToolItem extends BaseModel {

    @StColumn(text = "label.common.category", placeholder = "分类", order = 1, cssClass = "required")
    private String category;

    @StColumn(text = "label.tool.toolName", placeholder = "工具名称", order = 2, cssClass = "required")
    private String toolName;

    @StColumn(text = "label.common.code", placeholder = "编号", order = 3, cssClass = "required")
    private String toolCode;

    @StColumn(text = "label.tool.viewCount", type = "hidden", order = 4, defaultVal = "0")
    private Integer viewCount;

    @StColumn(text = "label.tool.thumbCount", type = "hidden", order = 5, defaultVal = "0")
    private Integer thumbCount;

    @StColumn(text = "label.common.weight", placeholder = "顺序", order = 6, cssClass = "required")
    private Integer weight;

    @StColumn(text = "label.common.intro", placeholder = "简介", type = "text",order = 7)
    private String intro;

}