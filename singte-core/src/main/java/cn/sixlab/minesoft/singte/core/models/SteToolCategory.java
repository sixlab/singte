
package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SteToolCategory extends BaseModel {

    private String category;

    private Integer count;

    private Integer weight;

    private String intro;

}