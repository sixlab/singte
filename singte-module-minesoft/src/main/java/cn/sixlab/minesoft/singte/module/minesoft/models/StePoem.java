package cn.sixlab.minesoft.singte.module.minesoft.models;

import java.io.Serializable;
import java.util.Date;

public class StePoem implements Serializable {
    private Integer id;

    private String poemName;

    private String poemAuthor;

    private Date createTime;

    private String poemContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPoemContent() {
        return poemContent;
    }

    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent == null ? null : poemContent.trim();
    }
}