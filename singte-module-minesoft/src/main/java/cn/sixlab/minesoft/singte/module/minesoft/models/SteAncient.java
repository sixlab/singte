package cn.sixlab.minesoft.singte.module.minesoft.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

import java.util.List;

public class SteAncient extends BaseModel {

    private String ancientName; // 文章名称

    private String ancientType; // 文章类型

    private String ancientAuthor; // 文章作者

    private String ancientCategory; // 文章类别

    private String ancientSection; // 文章所属文集

    private List<String> ancientLines; // 文章内容

    public String getAncientName() {
        return ancientName;
    }

    public void setAncientName(String ancientName) {
        this.ancientName = ancientName;
    }

    public String getAncientType() {
        return ancientType;
    }

    public void setAncientType(String ancientType) {
        this.ancientType = ancientType;
    }

    public String getAncientAuthor() {
        return ancientAuthor;
    }

    public void setAncientAuthor(String ancientAuthor) {
        this.ancientAuthor = ancientAuthor;
    }

    public String getAncientCategory() {
        return ancientCategory;
    }

    public void setAncientCategory(String ancientCategory) {
        this.ancientCategory = ancientCategory;
    }

    public String getAncientSection() {
        return ancientSection;
    }

    public void setAncientSection(String ancientSection) {
        this.ancientSection = ancientSection;
    }

    public List<String> getAncientLines() {
        return ancientLines;
    }

    public void setAncientLines(List<String> ancientLines) {
        this.ancientLines = ancientLines;
    }
}