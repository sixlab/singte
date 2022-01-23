package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class StArticle extends BaseModel {

    private String alias;

    private String sourceUrl;

    private String title;

    private String author;

    private List<String> keywords;

    private String summary;

    private String category;

    private Integer viewCount;

    private Integer thumbCount;

    private Date publishTime;

    private String content;

}