package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StMenu extends BaseModel {

    private String menuCode;

    private String menuLink;

    private String menuIcon;

    private Boolean folderMenu;

    private String menuGroup;

    private Integer weight;

    private String intro;

}