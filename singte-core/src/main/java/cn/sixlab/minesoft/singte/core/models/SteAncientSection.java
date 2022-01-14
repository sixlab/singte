package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

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

    public String getAncientSet() {
        return ancientSet;
    }

    public void setAncientSet(String ancientSet) {
        this.ancientSet = ancientSet;
    }

    public String getAncientCategory() {
        return ancientCategory;
    }

    public void setAncientCategory(String ancientCategory) {
        this.ancientCategory = ancientCategory;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(Integer thumbCount) {
        this.thumbCount = thumbCount;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}