package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StWidget extends BaseModel {

    private String widgetName;

    private String widgetCode;

    private String widgetIntro;

    private Integer weight;

    private String widgetParam;

}