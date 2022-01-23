package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StPage extends BaseModel {

    private String alias;

    private String title;

    private String author;

    private Integer viewCount;

    private Date publishTime;

    private String content;

}