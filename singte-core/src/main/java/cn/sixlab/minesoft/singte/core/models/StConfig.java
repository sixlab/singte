package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class StConfig extends BaseModel {

    private String configKey;

    private String configVal;

    private String configGroup;

    private String intro;

    private String status;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigVal() {
        return configVal;
    }

    public void setConfigVal(String configVal) {
        this.configVal = configVal == null ? null : configVal.trim();
    }

    public String getConfigGroup() {
        return configGroup;
    }

    public void setConfigGroup(String configGroup) {
        this.configGroup = configGroup == null ? null : configGroup.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}