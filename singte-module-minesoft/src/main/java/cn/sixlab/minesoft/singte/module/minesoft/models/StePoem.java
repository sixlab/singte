package cn.sixlab.minesoft.singte.module.minesoft.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class StePoem extends BaseModel {

    private String poemName;

    private String poemAuthor;

    private String poemContent;

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName == null ? null : poemName.trim();
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