package cn.sixlab.minesoft.singte.core.common.vo;

import cn.hutool.core.map.MapUtil;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModelResp extends LinkedHashMap<String, Object> {

//    private Integer status = 200; // 状态码
//    private String message = "";  // 用户信息提醒

    public ModelResp() {
        this(200, "common.success");
    }

    public ModelResp(Integer status) {
        this(status, "");
    }

    /**
     * 初始化信息
     *
     * @param status 编号，200是正确返回，其他的都是错误
     * @param message 信息提示，给用户的提示
     */
    public ModelResp(Integer status, String message) {
        super();
        setStatus(status);
        setMessage(message);
    }

    public static ModelResp resp() {
        return new ModelResp();
    }

    public static ModelResp error(Integer status) {
        return new ModelResp(status);
    }

    public static ModelResp error(Integer status, String message) {
        return new ModelResp(status, message);
    }

    public static ModelResp success() {
        return new ModelResp();
    }

    public static ModelResp success(Object data) {
        return new ModelResp().setData(data);
    }

    public static ModelResp success(Object data, String message) {
        return new ModelResp().setMessage(message).setData(data);
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
        return MapUtil.getInt(this, "status");
    }

    public ModelResp setStatus(Integer status) {
        put("status", status);
        return this;
    }

    public String getMessage() {
        return MapUtil.getStr(this, "message");
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

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
