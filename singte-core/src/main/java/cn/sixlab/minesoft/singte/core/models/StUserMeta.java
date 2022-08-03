package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StTable(title = "page.title.userMeta", reloadable = false)
public class StUserMeta extends BaseModel {

    @StColumn(text = "label.user.username", placeholder = "label.user.username", order = 100, inputCss = "required")
    private String username;

    @StColumn(text = "label.common.code", placeholder = "label.common.code", order = 200, inputCss = "required")
    private String metaKey;

    @StColumn(text = "label.common.value", placeholder = "label.common.value", order = 300, type = "text", inputCss = "required", defaultVal = "1")
    private String metaVal;

    @StColumn(text = "label.common.weight", placeholder = "label.common.weight", order = 400, inputCss = "required number")
    private Integer weight;

    @StColumn(text = "label.common.intro", placeholder = "label.common.intro", order = 500, type = "text", viewable = false)
    private String intro;

}