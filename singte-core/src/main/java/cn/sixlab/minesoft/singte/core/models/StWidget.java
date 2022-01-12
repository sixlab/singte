package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;

public class StWidget extends BaseModel {

    private String widgetName;

    private String widgetCode;

    private String widgetStatus;

    private String widgetIntro;

    private String widgetParam;

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getWidgetCode() {
        return widgetCode;
    }

    public void setWidgetCode(String widgetCode) {
        this.widgetCode = widgetCode;
    }

    public String getWidgetStatus() {
        return widgetStatus;
    }

    public void setWidgetStatus(String widgetStatus) {
        this.widgetStatus = widgetStatus;
    }

    public String getWidgetIntro() {
        return widgetIntro;
    }

    public void setWidgetIntro(String widgetIntro) {
        this.widgetIntro = widgetIntro;
    }

    public String getWidgetParam() {
        return widgetParam;
    }

    public void setWidgetParam(String widgetParam) {
        this.widgetParam = widgetParam;
    }
}