package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.domain", reloadUri="/admin/reload/domain")
public class StDomain extends BaseModel {

    @StColumn(text = "label.domain.name", placeholder = "label.domain.name", order = 1, cssClass = "required")
    private String domainName;

    @StColumn(text = "label.domain.url", placeholder = "label.domain.url", order = 2, cssClass = "required")
    private String domainUrl;

    @StColumn(text = "label.domain.tplPath", placeholder = "label.domain.tplPath", order = 3, cssClass = "required")
    private String tplPath;

    @StColumn(text = "label.domain.bean", placeholder = "label.domain.bean", order = 4, cssClass = "required")
    private String domainBean;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 5, cssClass = "required number")
    private Integer weight;

    @StColumn(text = "label.common.remark", placeholder = "label.common.remark", order = 6)
    private String domainRemark;

}