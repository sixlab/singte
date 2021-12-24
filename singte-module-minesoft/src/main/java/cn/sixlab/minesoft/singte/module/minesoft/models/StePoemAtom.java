package cn.sixlab.minesoft.singte.module.minesoft.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class StePoemAtom extends BaseModel {

    private String poemId;

    private String poemName;

    private String atomContent;

    private Integer atomOrder;

    public String getPoemId() {
        return poemId;
    }

    public void setPoemId(String poemId) {
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
}