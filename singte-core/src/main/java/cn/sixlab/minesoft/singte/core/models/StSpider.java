package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.spiders")
public class StSpider extends BaseModel {

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 10, cssClass = "required")
    private String spiderCode;

    @StColumn(text = "label.task.taskBean", placeholder = "label.task.taskBean", order = 20, cssClass = "required")
    private String spiderBean;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 30, cssClass = "required")
    private String spiderName;

    @StColumn(text = "label.task.taskCron", placeholder = "label.task.taskCron", order = 40, cssClass = "required")
    private String spiderCron;

    @StColumn(text = "label.spider.threadNum", placeholder = "label.spider.threadNum", order = 50, cssClass = "required number")
    private Integer threadNum;

    @StColumn(text = "label.spider.startUrl", placeholder = "ph.spider.startUrl", order = 60, cssClass = "required", viewable = false)
    private String startUrl;

    @StColumn(text = "label.spider.pagerRule", placeholder = "ph.spider.pagerRule", order = 70, cssClass = "required", viewable = false)
    private String pagerRule;

    @StColumn(text = "label.spider.linkRule", placeholder = "ph.spider.linkRule", order = 80, cssClass = "required", viewable = false)
    private String linkRule;

    @StColumn(text = "label.spider.titleRule", placeholder = "ph.spider.titleRule", order = 90, cssClass = "required", viewable = false)
    private String titleRule;

    @StColumn(text = "label.spider.contentRule", placeholder = "ph.spider.contentRule", order = 100, cssClass = "required", viewable = false)
    private String contentRule;

    @StColumn(text = "label.spider.summaryRule", placeholder = "ph.spider.summaryRule", order = 110, cssClass = "required", viewable = false)
    private String summaryRule;

    @StColumn(text = "label.spider.categoryRule", placeholder = "ph.spider.categoryRule", order = 120, cssClass = "required", viewable = false)
    private String categoryRule;

    @StColumn(text = "label.spider.keywordRule", placeholder = "ph.spider.keywordRule", order = 130, cssClass = "required", viewable = false)
    private String keywordRule;

    @StColumn(text = "label.spider.waitTimes", placeholder = "ph.spider.waitTimes", order = 140, cssClass = "number")
    private Integer waitTimes;

    @StColumn(text = "label.spider.spiderParam", placeholder = "ph.spider.spiderParam", order = 150, cssClass = "required", viewable = false)
    private String spiderParam;

}