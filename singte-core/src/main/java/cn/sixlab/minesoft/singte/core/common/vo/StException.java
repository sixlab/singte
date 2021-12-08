package cn.sixlab.minesoft.singte.core.common.vo;

public class StException extends RuntimeException {
    private Integer status;
    private String message;

    public StException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public StException(String message) {
        super(message);
        this.status = 5001;
        this.message = message;
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
        this.message = message;
    }
}
