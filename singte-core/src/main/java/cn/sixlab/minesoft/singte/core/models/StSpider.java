package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StSpider extends BaseModel {

    private String spiderType;

    private String spiderName;

    private String startUrl;

    private String pagerRule;

    private String linkRule;

    private String titleRule;

    private String contentRule;

    private String summaryRule;

    private String categoryRule;

    private String keywordRule;

    private Integer waitTimes;

    private String urlParam;

}