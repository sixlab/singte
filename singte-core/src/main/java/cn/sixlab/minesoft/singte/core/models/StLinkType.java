package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.linkType", reloadUri="/admin/reload/linkType")
public class StLinkType extends BaseModel {

    @StColumn(text = "label.link.domain", placeholder = "label.link.domain", order = 100, inputCss = "required")
    private String multiDomainGroup;

    @StColumn(text = "label.link.type", placeholder = "label.link.type", order = 200)
    private String linkType;

    @StColumn(text = "label.linkType.hasSub", placeholder = "ph.linkType.hasSub", order = 300, inputCss = "required number")
    private Integer hasSub;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 800, inputCss = "required number")
    private Integer weight;

    @StColumn(text = "label.common.remark", placeholder = "label.common.remark", order = 900)
    private String typeRemark;

}