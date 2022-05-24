package cn.sixlab.minesoft.singte.core.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StModelColumn {
    private String columnName;
    private String text;
    private String type;
    private String inputCss;
    private String placeholder;
    private String defaultVal;
    private int order;
    private boolean view;
}
