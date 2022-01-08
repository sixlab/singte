package cn.sixlab.minesoft.singte.module.minesoft.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class StePoem extends BaseModel {

    private String poemName;

    private String poemType;

    private String poemCategory;

    private String poemSection;

    private String poemAuthor;

    private String poemContent;

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName == null ? null : poemName.trim();
    }

    public String getPoemType() {
        return poemType;
    }

    public void setPoemType(String poemType) {
        this.poemType = poemType;
    }

    public String getPoemCategory() {
        return poemCategory;
    }

    public void setPoemCategory(String poemCategory) {
        this.poemCategory = poemCategory;
    }

    public String getPoemSection() {
        return poemSection;
    }

    public void setPoemSection(String poemSection) {
        this.poemSection = poemSection;
    }

    public String getPoemAuthor() {
        return poemAuthor;
    }

    public void setPoemAuthor(String poemAuthor) {
        this.poemAuthor = poemAuthor == null ? null : poemAuthor.trim();
    }

    public String getPoemContent() {
        return poemContent;
    }

    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent == null ? null : poemContent.trim();
    }
}