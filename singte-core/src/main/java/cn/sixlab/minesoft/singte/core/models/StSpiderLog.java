package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@StTable(title = "page.title.spider.log",insertable = false)
public class StSpiderLog extends BaseModel {

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 10, cssClass = "required")
    private String spiderCode;

    @StColumn(text = "label.task.taskBean", placeholder = "label.task.taskBean", order = 20, cssClass = "required")
    private String spiderBean;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 30, cssClass = "required")
    private String spiderName;

    @StColumn(text = "label.spider.spiderLink", placeholder = "ph.spider.spiderLink", order = 40, cssClass = "required")
    private String spiderLink;

    private Date crawlerTime;

}