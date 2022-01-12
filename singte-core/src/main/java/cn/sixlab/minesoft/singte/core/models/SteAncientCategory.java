package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class SteAncientCategory extends BaseModel {

    private String ancientCategory;

    private Integer categoryOrder;

    private String categoryIntro;

    public String getAncientCategory() {
        return ancientCategory;
    }

    public void setAncientCategory(String ancientCategory) {
        this.ancientCategory = ancientCategory;
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getCategoryIntro() {
        return categoryIntro;
    }

    public void setCategoryIntro(String categoryIntro) {
        this.categoryIntro = categoryIntro;
    }
}