package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.linkDomain", reloadUri="/admin/reload/linkDomain")
public class StLinkDomain extends BaseModel {

    @StColumn(text = "label.linkDomain.name", placeholder = "label.linkDomain.name", order = 100, inputCss = "required")
    private String domainName;

    @StColumn(text = "label.linkDomain.domain", placeholder = "label.linkDomain.domain", order = 200, inputCss = "required")
    private String domain;

    @StColumn(text = "label.linkDomain.group", placeholder = "label.linkDomain.group", order = 300, inputCss = "required")
    private String multiDomainGroup;

    @StColumn(text = "label.linkDomain.title", placeholder = "label.linkDomain.title", order = 400, inputCss = "required")
    private String domainTitle;

    @StColumn(text = "label.linkDomain.keyword", placeholder = "label.linkDomain.keyword", order = 500, inputCss = "required")
    private String domainKeyword;

    @StColumn(text = "label.linkDomain.description", placeholder = "label.linkDomain.description", order = 600, inputCss = "required")
    private String domainDescription;

    @StColumn(text = "label.linkDomain.favicon", placeholder = "label.linkDomain.favicon", order = 700, inputCss = "required")
    private String domainFavicon;

    @StColumn(text = "label.common.remark", placeholder = "label.common.remark", order = 900)
    private String domainRemark;

}