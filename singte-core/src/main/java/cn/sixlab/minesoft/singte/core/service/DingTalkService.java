package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DingTalkService {

    @Autowired
    private ConfigUtils configUtils;

    public String accessToken() {
        Object o = StCacheHolder.CACHE_90m.get(StCacheHolder.KEY_DINGTALK_TOKEN);

        if (null == o) {
            o = requestAccessToken();
            if (null != o) {
                StCacheHolder.CACHE_90m.put(StCacheHolder.KEY_DINGTALK_TOKEN, o);
            } else {
                return "";
            }
        }
        return o.toString();
    }

    public String requestAccessToken() {
        String appKey = configUtils.getConfig(StConst.DT_APP_KEY);
        String appSecret = configUtils.getConfig(StConst.DT_APP_SECRET);

        Map<String, Object> param = ImmutableMap.of("appKey", appKey, "appSecret", appSecret);

        String resp = HttpUtil.post("https://api.dingtalk.com/v1.0/oauth2/accessToken", JSONUtil.toJsonStr(param));
        Map json = JSONUtil.toBean(resp, Map.class);

        return MapUtil.getStr(json, "accessToken");
    }

    public void sendSampleText(String userId, String text) {
        String accessToken = accessToken();
        if (StrUtil.isNotEmpty(accessToken)) {
            Map<String, Object> param = new HashMap<>();
            param.put("robotCode", configUtils.getConfig(StConst.DT_APP_KEY));
            param.put("userIds", new String[]{userId});
            param.put("msgKey", "sampleText");
            param.put("msgParam", JSONUtil.toJsonStr(ImmutableMap.of("content", text)));

            HttpUtil.createPost("https://api.dingtalk.com/v1.0/robot/oToMessages/batchSend")
                    .header("x-acs-dingtalk-access-token", accessToken)
                    .body(JSONUtil.toJsonStr(param)).execute().body();
        }
    }
}
