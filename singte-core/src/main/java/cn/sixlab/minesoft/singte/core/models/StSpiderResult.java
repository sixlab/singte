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

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 1, cssClass = "required")
    private String spiderCode;

    @StColumn(text = "label.common.type", placeholder = "label.common.type", order = 2, cssClass = "required")
    private String spiderType;

    @StColumn(text = "label.common.name", placeholder = "label.common.name", order = 3, cssClass = "required")
    private String spiderName;

    @StColumn(text = "label.spider.spiderLink", placeholder = "ph.spider.spiderLink", order = 4, cssClass = "required")
    private String spiderLink;

    @StColumn(text = "label.common.title", placeholder = "ph.spider.title", order = 5, cssClass = "required")
    private String title;

    @StColumn(text = "label.common.content", placeholder = "ph.spider.content", order = 6, cssClass = "required", viewable = false)
    private String content;

    @StColumn(text = "label.common.summary", placeholder = "ph.spider.summary", order = 7, cssClass = "required", viewable = false)
    private String summary;

    @StColumn(text = "label.common.category", placeholder = "ph.spider.category", order = 8, cssClass = "required", viewable = false)
    private String category;

    @StColumn(text = "label.common.keyword", placeholder = "ph.spider.keyword", order = 9, cssClass = "required", viewable = false)
    private String keyword;

    private Date crawlerTime;

}