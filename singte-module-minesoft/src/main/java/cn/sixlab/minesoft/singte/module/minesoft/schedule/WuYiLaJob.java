package cn.sixlab.minesoft.singte.module.minesoft.schedule;

import cn.sixlab.minesoft.singte.core.common.utils.HttpUtils;
import cn.sixlab.minesoft.singte.core.common.utils.JsonUtils;
import cn.sixlab.minesoft.singte.module.minesoft.dao.SeoDataDao;
import cn.sixlab.minesoft.singte.module.minesoft.dao.SeoItemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WuYiLaJob {

    public static String GS_BOT = "https://api.telegram.org/bot1811707782:AAGErMuj0nUJzUgYN7ZOeIpgDMYnLi4Dqus/";
    public static String GROUP = "-1001521805818";

    @Autowired
    private SeoDataDao dataMapper;

    @Autowired
    private SeoItemDao itemMapper;

    @Scheduled(cron = "0 2 0 * * ?")
    public void job() {
        String date = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");

        Map map = apiData(date);

        StringBuilder text = new StringBuilder(date);
        for (Object key : map.keySet()) {
            text.append("\n");
            text.append(key);
            text.append(":");
            text.append(map.get(key));
        }

        try {
            Map<String, String> param = new HashMap<>();
            param.put("chat_id", GROUP);
            param.put("text", text.toString());

            HttpUtils.sendPost(GS_BOT+"sendMessage", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map apiData(String date) {
        String json = HttpUtils.sendPostBody("https://v6-open.51.la/open/site/list", pubParam());
        Map result = JsonUtils.toBean(json, HashMap.class);

        if ("0000".equals(result.get("code"))) {
            Map<String, Integer> ipCount = new HashMap<>();

            ArrayList dataList = (ArrayList) result.get("data");
            for (Object data : dataList) {
                Map item = (Map) data;
                String maskId = MapUtils.getString(item, "maskId");

                SeoItem seoItem = itemMapper.selectUser("51la", maskId);
                if( null != seoItem){
                    SeoData seoData = new SeoData();
                    seoData.setUser(seoItem.getUser());
                    seoData.setUid(seoItem.getUid());
                    seoData.setCategory(seoItem.getCategory());

                    seoData.setDate(date);
                    seoData.setCreateTime(new Date());

                    try {
                        seoData.setIp(MapUtils.getInteger(item, "yesterdayIp"));
                        seoData.setPv(MapUtils.getInteger(item, "yesterdayPv"));

                        ipCount.put(seoData.getUser(), seoData.getIp());

                        dataMapper.insert(seoData);
                    }catch (Exception e){
                    }
                }
            }

            return ipCount;
        }

        return null;
    }

    public String pubParam(){
        int nonce = RandomUtils.nextInt(1000, 10000);
        long time = new Date().getTime();

        String data = "accessKey=vgGSzVgUeFgLQtsbRcyqVIDhEBvQuRtC&nonce=" +
                nonce + "&secretKey=i5UTGLsEqvlDDnf1Cj4u7V4WPgRIwbbG&timestamp=" + time;
        String sign = DigestUtils.sha256Hex(data);

        System.out.println(data);
        System.out.println(sign);

        Map<String, String> result = new HashMap<>();
        result.put("accessKey", "vgGSzVgUeFgLQtsbRcyqVIDhEBvQuRtC");
        result.put("nonce", nonce + "");
        result.put("timestamp", time + "");
        result.put("sign", sign);
        return JsonUtils.toJson(result);
    }
}
