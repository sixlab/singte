package cn.sixlab.minesoft.singte.core.models;

import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class StSpider extends BaseModel {

    private String spiderType;

    private String spiderName;

    private String startUrl;

    private String pagerRule;

    private String linkRule;

    private String titleRule;

    private String contentRule;

    private String summaryRule;

    private String categoryRule;

    private String keywordRule;

    private Integer waitTimes;

    private String spiderStatus;

    private String urlParam;

    public String getSpiderType() {
        return spiderType;
    }

    public void setSpiderType(String spiderType) {
        this.spiderType = spiderType == null ? null : spiderType.trim();
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName == null ? null : spiderName.trim();
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl == null ? null : startUrl.trim();
    }

    public String getPagerRule() {
        return pagerRule;
    }

    public void setPagerRule(String pagerRule) {
        this.pagerRule = pagerRule == null ? null : pagerRule.trim();
    }

    public String getLinkRule() {
        return linkRule;
    }

    public void setLinkRule(String linkRule) {
        this.linkRule = linkRule == null ? null : linkRule.trim();
    }

    public String getTitleRule() {
        return titleRule;
    }

    public void setTitleRule(String titleRule) {
        this.titleRule = titleRule == null ? null : titleRule.trim();
    }

    public String getContentRule() {
        return contentRule;
    }

    public void setContentRule(String contentRule) {
        this.contentRule = contentRule == null ? null : contentRule.trim();
    }

    public String getSummaryRule() {
        return summaryRule;
    }

    public void setSummaryRule(String summaryRule) {
        this.summaryRule = summaryRule == null ? null : summaryRule.trim();
    }

    public String getCategoryRule() {
        return categoryRule;
    }

    public void setCategoryRule(String categoryRule) {
        this.categoryRule = categoryRule == null ? null : categoryRule.trim();
    }

    public String getKeywordRule() {
        return keywordRule;
    }

    public void setKeywordRule(String keywordRule) {
        this.keywordRule = keywordRule == null ? null : keywordRule.trim();
    }

    public Integer getWaitTimes() {
        return waitTimes;
    }

    public void setWaitTimes(Integer waitTimes) {
        this.waitTimes = waitTimes;
    }

    public String getSpiderStatus() {
        return spiderStatus;
    }

    public void setSpiderStatus(String spiderStatus) {
        this.spiderStatus = spiderStatus == null ? null : spiderStatus.trim();
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam == null ? null : urlParam.trim();
    }
}