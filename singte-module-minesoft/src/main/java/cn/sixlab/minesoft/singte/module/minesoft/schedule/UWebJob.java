package cn.sixlab.minesoft.singte.module.minesoft.schedule;

import cn.sixlab.minesoft.singte.core.common.utils.HttpUtils;
import cn.sixlab.minesoft.singte.module.minesoft.dao.SeoDataDao;
import cn.sixlab.minesoft.singte.module.minesoft.dao.SeoItemDao;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoData;
import cn.sixlab.minesoft.singte.module.minesoft.models.SeoItem;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UWebJob {

    @Autowired
    private SeoDataDao dataMapper;

    @Autowired
    private SeoItemDao itemMapper;

    @Scheduled(cron = "0 1 0 * * ?")
    public void job() {
        String date = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");

        List<SeoItem> itemList = itemMapper.selectItems("cnzz");

        for (SeoItem item : itemList) {
            SeoData data = new SeoData();
            data.setUser(item.getUser());
            data.setUid(item.getUid());
            data.setCategory(item.getCategory());

            data.setDate(date);
            data.setCreateTime(new Date());

            try {
                uWebData(item.getDataUrl(), data);

                dataMapper.insert(data);
            }catch (Exception e){
            }
        }
    }

    public void uWebData(String dataUrl, SeoData data) {
//        https://online.cnzz.com/online/online_v3.php?id=1279968362&h=z3.cnzz.com&on=1&s=    李蔚
//        https://online.cnzz.com/online/online_v3.php?id=1279968358&h=z12.cnzz.com&on=1&s=   罗嘉豪
        String resp = HttpUtils.sendGet(dataUrl, null);
        Pattern pattern = Pattern.compile(".*昨日IP\\[([0-9]*)]");
        Matcher matcher = pattern.matcher(resp);
        if (matcher.find()) {
            data.setIp(Integer.valueOf(matcher.group(1)));
        }

        pattern = Pattern.compile(".*昨日PV\\[([0-9]*)]");
        matcher = pattern.matcher(resp);
        if (matcher.find()) {
            data.setPv(Integer.valueOf(matcher.group(1)));
        }
    }

}
