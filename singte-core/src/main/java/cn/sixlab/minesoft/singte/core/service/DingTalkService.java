package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DingTalkService {

    @Autowired
    private ConfigUtils configUtils;

    public String accessToken() {
        Object token = StCacheHolder.CACHE_90m.get(StCacheHolder.KEY_DINGTALK_TOKEN);


        if (ObjectUtil.isEmpty(token)) {
            token = requestAccessToken();
            if (ObjectUtil.isNotEmpty(token)) {
                StCacheHolder.CACHE_90m.put(StCacheHolder.KEY_DINGTALK_TOKEN, token);
            } else {
                return "";
            }
        }
        return token.toString();
    }

    public String requestAccessToken() {
        String appKey = configUtils.getConfig(StConst.DT_APP_KEY);
        String appSecret = configUtils.getConfig(StConst.DT_APP_SECRET);

        Map<String, Object> param = ImmutableMap.of("appKey", appKey, "appSecret", appSecret);

        String resp = HttpUtil.post("https://api.dingtalk.com/v1.0/oauth2/accessToken", JSONUtil.toJsonStr(param));

        log.info("请求 Token 返回：" + resp);

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

            String resp = HttpUtil.createPost("https://api.dingtalk.com/v1.0/robot/oToMessages/batchSend")
                    .header("x-acs-dingtalk-access-token", accessToken)
                    .body(JSONUtil.toJsonStr(param)).execute().body();

            log.info("请求返回：" + resp);
        }
    }
}
