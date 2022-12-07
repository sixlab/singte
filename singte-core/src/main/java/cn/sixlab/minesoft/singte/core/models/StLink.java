package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.link", reloadUri="/admin/reload/link")
public class StLink extends BaseModel {

    @StColumn(text = "label.link.domain", placeholder = "label.link.domain", order = 100, inputCss = "required")
    private String multiDomainGroup;

    @StColumn(text = "label.link.name", placeholder = "label.link.name", order = 200, inputCss = "required")
    private String linkName;

    @StColumn(text = "label.link.url", placeholder = "label.link.url", order = 300, inputCss = "required")
    private String linkUrl;

    @StColumn(text = "label.link.icon", placeholder = "label.link.icon", order = 400)
    private String linkIcon;

    @StColumn(text = "label.link.type", placeholder = "label.link.type", order = 500)
    private String linkType;

    @StColumn(text = "label.link.group", placeholder = "label.link.group", order = 600, inputCss = "required")
    private String linkGroup;

    @StColumn(text = "label.link.visit", placeholder = "label.link.visit", order = 700, inputCss = "required number")
    private Integer visit;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 800, inputCss = "required number")
    private Integer weight;

    @StColumn(text = "label.common.remark", placeholder = "label.common.remark", order = 900)
    private String linkRemark;

}