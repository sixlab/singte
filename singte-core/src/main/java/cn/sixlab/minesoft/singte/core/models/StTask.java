package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.task")
public class StTask extends BaseModel {

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 1, cssClass = "required")
    private String taskCode;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 2, cssClass = "required")
    private String taskName;

    @StColumn(text = "label.task.taskBean", placeholder = "ph.task.taskBean", order = 3, cssClass = "required")
    private String taskBean;

    @StColumn(text = "label.task.taskCron", placeholder = "ph.task.taskCron", order = 4, cssClass = "required")
    private String taskCron;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 6, cssClass = "required number")
    private Integer weight;

    @StColumn(text = "label.common.intro", placeholder = "label.common.intro", order = 6, type = "text", viewable = false)
    private String intro;

}