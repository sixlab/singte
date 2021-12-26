package cn.sixlab.minesoft.singte.core.common.vo;

import cn.sixlab.minesoft.singte.core.common.utils.I18nUtils;

public class StException extends RuntimeException {
    private Integer status;
    private String message;

    public StException(Integer status, String message) {
        super(message);
        setStatus(status);
        setMessage(message);
    }

    public StException(String message) {
        super(message);
        setStatus(5001);
        setMessage(message);
        this.status = 5001;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = I18nUtils.get(message);
    }
}
