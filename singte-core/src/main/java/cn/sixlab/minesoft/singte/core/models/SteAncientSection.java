package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SteAncientSection extends BaseModel {

    private String ancientSet;

    private String ancientCategory;

    private String bookName;

    private String sectionName;

    private String author;

    private String contentHtml;

    private String contentText;

    private Integer weight;

    private Integer viewCount;

    private Integer thumbCount;

    private String intro;

}