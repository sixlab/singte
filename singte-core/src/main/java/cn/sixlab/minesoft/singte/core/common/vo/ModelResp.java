package cn.sixlab.minesoft.singte.core.common.vo;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModelResp extends LinkedHashMap<String, Object> {

//    private Integer status = 200; // 状态码
//    private String error = "";    // 代码错误信息
//    private String message = "";  // 用户信息提醒

    public ModelResp() {
        this(200, "操作成功", "");
    }

    public ModelResp(Integer code) {
        this(code, "", "");
    }

    public ModelResp(Integer code, String message) {
        this(code, message, "");
    }

    /**
     * 初始化信息
     *
     * @param code 编号，200是正确返回，其他的都是错误
     * @param message 信息提示，给用户的提示
     * @param error 错误信息，给代码的信息
     */
    public ModelResp(Integer code, String message, String error) {
        super();
        put("status", code);
        put("message", message);
        put("error", error);
    }

    public static ModelResp resp() {
        return new ModelResp();
    }

    public static ModelResp error(Integer code) {
        return new ModelResp(code);
    }

    public static ModelResp error(Integer code, String message) {
        return new ModelResp(code, message);
    }

    public static ModelResp error(Integer code, String message, String error) {
        return new ModelResp(code, message, error);
    }

    public static ModelResp success() {
        return new ModelResp();
    }

    public static ModelResp success(Object data, String message) {
        return new ModelResp().setMessage(message).setData(data);
    }

    public static ModelResp success(Object data) {
        return new ModelResp().setData(data);
    }

    public ModelResp add(String key, Object val) {
        Map<String, Object> data = this.getData(Map.class);

        if (null == data) {
            data = new HashMap<>();
            this.setData(data);
        }

        data.put(key, val);

        return this;
    }

    public Integer getStatus() {
        return MapUtils.getInteger(this, "status");
    }

    public ModelResp setStatus(Integer status) {
        put("status", status);
        return this;
    }

    public String getError() {
        return MapUtils.getString(this, "error");
    }

    public ModelResp setError(String error) {
        put("error", error);
        return this;
    }

    public String getMessage() {
        return MapUtils.getString(this, "message");
    }

    public ModelResp setMessage(String message) {
        put("message", message);
        return this;
    }

    public Object getData() {
        return get("data");
    }

    public <T> T getData(Class<T> clz) {
        Object data = getData();

        if (clz.isInstance(data)) {
            return (T) data;
        }

        return null;
    }

    public ModelResp setData(Object obj) {
        put("data", obj);
        return this;
    }

    public ModelResp set(String key, Object val) {
        put(key, val);
        return this;
    }

    public <T> T get(String key, Class<T> clz) {
        Object data = get(key);

        if (clz.isInstance(data)) {
            return (T) data;
        }

        return null;
    }
}
