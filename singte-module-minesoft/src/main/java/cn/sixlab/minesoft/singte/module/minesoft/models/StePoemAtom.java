package cn.sixlab.minesoft.singte.module.minesoft.models;

import java.io.Serializable;
import java.util.Date;

public class StePoemAtom implements Serializable {
    private Integer id;

    private Integer poemId;

    private String poemName;

    private String atomContent;

    private Integer atomOrder;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoemId() {
        return poemId;
    }

    public void setPoemId(Integer poemId) {
        this.poemId = poemId;
    }

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName == null ? null : poemName.trim();
    }

    public String getAtomContent() {
        return atomContent;
    }

    public void setAtomContent(String atomContent) {
        this.atomContent = atomContent == null ? null : atomContent.trim();
    }

    public Integer getAtomOrder() {
        return atomOrder;
    }

    public void setAtomOrder(Integer atomOrder) {
        this.atomOrder = atomOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}