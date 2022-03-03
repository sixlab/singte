package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.StCacheHolder;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxService {

    @Autowired
    private ConfigUtils configUtils;

    public String wxToken() {
        Object o = StCacheHolder.CACHE_90m.get(StCacheHolder.KEY_WX_TOKEN);

        if (null == o) {
            o = requestToken();
            if (null != o) {
                StCacheHolder.CACHE_90m.put(StCacheHolder.KEY_WX_TOKEN, o);
            } else {
                return "";
            }
        }
        return o.toString();
    }

    public String requestToken() {
        String appId = configUtils.getConfig(StConst.WX_APP_ID);
        String secret = configUtils.getConfig(StConst.WX_APP_SECRET);
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;

        String resp = HttpUtil.get(url);
        Map json = JSONUtil.toBean(resp, Map.class);

        return MapUtil.getStr(json, "access_token");
    }
}
