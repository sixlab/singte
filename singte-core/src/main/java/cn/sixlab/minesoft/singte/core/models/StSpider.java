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

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 1, cssClass = "required")
    private String spiderCode;

    @StColumn(text = "label.common.type", placeholder = "label.common.type", order = 2, cssClass = "required")
    private String spiderType;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 3, cssClass = "required")
    private String spiderName;

    @StColumn(text = "label.spider.startUrl", placeholder = "ph.spider.startUrl", order = 4, cssClass = "required", view = false)
    private String startUrl;

    @StColumn(text = "label.spider.pagerRule", placeholder = "ph.spider.pagerRule", order = 5, cssClass = "required", view = false)
    private String pagerRule;

    @StColumn(text = "label.spider.linkRule", placeholder = "ph.spider.linkRule", order = 6, cssClass = "required", view = false)
    private String linkRule;

    @StColumn(text = "label.spider.titleRule", placeholder = "ph.spider.titleRule", order = 7, cssClass = "required", view = false)
    private String titleRule;

    @StColumn(text = "label.spider.contentRule", placeholder = "ph.spider.contentRule", order = 8, cssClass = "required", view = false)
    private String contentRule;

    @StColumn(text = "label.spider.summaryRule", placeholder = "ph.spider.summaryRule", order = 9, cssClass = "required", view = false)
    private String summaryRule;

    @StColumn(text = "label.spider.categoryRule", placeholder = "ph.spider.categoryRule", order = 10, cssClass = "required", view = false)
    private String categoryRule;

    @StColumn(text = "label.spider.keywordRule", placeholder = "ph.spider.keywordRule", order = 11, cssClass = "required", view = false)
    private String keywordRule;

    @StColumn(text = "label.spider.waitTimes", placeholder = "ph.spider.waitTimes", order = 12, cssClass = "number")
    private Integer waitTimes;

    @StColumn(text = "label.spider.spiderParam", placeholder = "ph.spider.spiderParam", order = 13, cssClass = "required", view = false)
    private String spiderParam;

}