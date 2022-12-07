package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@StTable(title = "page.title.spider.result", insertable = false)
public class StSpiderResult extends BaseModel {

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 10, inputCss = "required")
    private String spiderCode;

    @StColumn(text = "label.task.taskBean", placeholder = "label.task.taskBean", order = 20, inputCss = "required")
    private String spiderBean;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 30, inputCss = "required")
    private String spiderName;

    @StColumn(text = "label.spider.spiderLink", placeholder = "ph.spider.spiderLink", order = 40, inputCss = "required")
    private String spiderLink;

    @StColumn(text = "label.common.title", placeholder = "ph.spider.title", order = 50, inputCss = "required")
    private String title;

    @StColumn(text = "label.common.content", placeholder = "ph.spider.content", order = 60, inputCss = "required", viewable = false)
    private String content;

    @StColumn(text = "label.common.summary", placeholder = "ph.spider.summary", order = 70, inputCss = "required", viewable = false)
    private String summary;

    @StColumn(text = "label.common.category", placeholder = "ph.spider.category", order = 80, inputCss = "required", viewable = false)
    private String category;

    @StColumn(text = "label.common.keyword", placeholder = "ph.spider.keyword", order = 90, inputCss = "required", viewable = false)
    private String keyword;

    private Date crawlerTime;

}