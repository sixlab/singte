package cn.sixlab.minesoft.singte.core.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StModelTable {
    private String tableName;
    private String title;

    private String reloadUri;

    private boolean reload;
    private boolean insert;

}
