package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class SteAncientBook extends BaseModel {

    private String ancientSet;

    private String ancientCategory;

    private String bookName;

    private String author;

    private Integer weight;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        this.intro = intro;
    }
}