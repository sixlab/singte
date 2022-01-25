package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.tool.item")
public class SteToolItem extends BaseModel {

    @StColumn(text = "label.common.category", placeholder = "分类", order = 1, cssClass = "required")
    private String category;

    @StColumn(text = "label.tool.toolName", placeholder = "工具名称", order = 2, cssClass = "required")
    private String toolName;

    @StColumn(text = "label.common.code", placeholder = "编号", order = 3, cssClass = "required")
    private String toolCode;

    @StColumn(text = "label.tool.viewCount", editable = false, order = 4, defaultVal = "0")
    private Integer viewCount;

    @StColumn(text = "label.tool.thumbCount", editable = false, order = 5, defaultVal = "0")
    private Integer thumbCount;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 6, cssClass = "required number")
    private Integer weight;

    @StColumn(text = "label.common.intro", placeholder = "label.common.intro", order = 6, type = "text", viewable = false)
    private String intro;

}