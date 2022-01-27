package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.sixlab.minesoft.singte.core.common.utils.ConfigUtils;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.nlpcn.commons.lang.jianfan.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class MessageTranslate {
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired
    private ConfigUtils configUtils;

    @SneakyThrows
    public void trans(String lang, String baiduLang) {
        InputStream p1is = resourceLoader.getResource("i18n/message_zh.properties").getInputStream();
        InputStream p2is = resourceLoader.getResource("i18n/message_" + lang + ".properties").getInputStream();

        Properties p1 = new Properties();
        p1.load(p1is);

        Properties p2 = new Properties();
        p2.load(p2is);

        Properties p3 = trans(lang, p1, p2, baiduLang);
        p3.forEach((key, val) -> System.out.println(key + "=" + val));

        FileOutputStream out = new FileOutputStream("target.properties");
        p3.store(out, "");
        out.flush();
        out.close();

        p1is.close();
        p2is.close();
    }

    public Properties trans(String lang, Properties p1, Properties p2, String baiduLang) {
        String url = configUtils.getConfig("baidu_fanyi_url");
        String appId = configUtils.getConfig("baidu_fanyi_appid");
        String appKey = configUtils.getConfig("baidu_fanyi_key");

        Properties p3 = new Properties();
        p1.forEach((key, val) -> {
            if (!p2.containsKey(key)) {
                String text = val.toString();

                if ("zh_TW".equals(lang)) {
                    text = Converter.TRADITIONAL.convert(text);
                } else {
                    List<String> list = trans(text, baiduLang, url, appId, appKey);

                    if (list.size() < 1) {
                        System.out.println(key);
                        System.out.println("====================");
                        text = "====================";
                    } else if (list.size() > 1) {
                        System.out.println(key);
                        System.out.println(JsonUtils.toJson(list));
                        System.out.println("--------");
                        text = list.get(0);
                    } else {
                        text = list.get(0);
                    }
                }

                p3.put(key, text);
            }
        });
        return p3;
    }

    public List<String> trans(String text, String target, String url, String appId, String appKey) {
        System.out.println("translate:" + text);
        List<String> cns = new ArrayList<>();

        try {
            Thread.sleep(1000);

            String salt = String.valueOf(RandomUtils.nextInt(1000, 2000));

            Map<String, Object> param = new HashMap<>();
            param.put("q", text);
            param.put("from", "zh");
            param.put("to", target);
            param.put("appid", appId);
            param.put("salt", salt);
            param.put("sign", SecureUtil.md5(appId + text + salt + appKey));

            String json = HttpUtil.post(url, param);

            Map<String, Object> map = JsonUtils.toBean(json, Map.class);

            List<Map> result = (List) map.getOrDefault("trans_result", new ArrayList<>());

            for (Map item : result) {
                String dst = MapUtil.getStr(item, "dst");
                cns.add(dst);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cns;
    }

}
