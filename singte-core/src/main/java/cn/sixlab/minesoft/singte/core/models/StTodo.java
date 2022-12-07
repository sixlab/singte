package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.todo", reloadUri="/admin/reload/todo")
public class StTodo extends BaseModel {

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 100, inputCss = "required")
    private String todoCode;

    @StColumn(text = "label.common.no", placeholder = "label.common.no", order = 150, inputCss = "required number")
    private Integer indexNo;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 200, inputCss = "required")
    private String todoName;

    @StColumn(text = "label.user.username", placeholder = "label.user.username", order = 300, inputCss = "required")
    private String username;

    @StColumn(text = "label.todo.todoType", placeholder = "ph.todo.todoType", order = 400, inputCss = "required", defaultVal = "1")
    private String todoType;

    @StColumn(text = "label.task.taskCron", placeholder = "label.task.taskCron", order = 500)
    private String todoCron;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 600, inputCss = "required number")
    private Integer weight;

    @StColumn(text = "label.common.intro", placeholder = "label.common.intro", order = 700, type = "text", viewable = false)
    private String intro;

}