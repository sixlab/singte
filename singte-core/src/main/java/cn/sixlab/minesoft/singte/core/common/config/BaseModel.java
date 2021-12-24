package cn.sixlab.minesoft.singte.core.common.config;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {
    @Id
    private String id;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
