package cn.sixlab.minesoft.singte.core.common.config;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {
    @Id
    private String id;

    private Date updateTime;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
