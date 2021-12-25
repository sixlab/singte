package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class StKeyword extends BaseModel {

    private String keyword;

    private Integer weight;

    private String intro;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}