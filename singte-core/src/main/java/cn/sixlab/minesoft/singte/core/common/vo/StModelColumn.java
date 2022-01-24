package cn.sixlab.minesoft.singte.core.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StModelColumn {
    private String name;
    private String type;
    private String cssClass;
    private String placeholder;
    private String defaultVal;
    private int order;
}
