package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.core.util.RandomUtil;
import cn.sixlab.minesoft.singte.core.dao.StWidgetDao;
import cn.sixlab.minesoft.singte.core.models.StWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitWidget implements BaseInitComponent {

    @Autowired
    private StWidgetDao widgetDao;

    @Override
    public void init() {
        checkWidget("widget-hot", "热门文章", "热门文章");
        checkWidget("widget-new", "最新文章", "最新文章");
        checkWidget("widget-random", "随机文章", "随机文章");

        checkWidget("widget-view", "点击排行", "点击排行");
        checkWidget("widget-tags", "标签云", "标签云");

        checkWidget("widget-adv", "侧边广告", "侧边广告");
    }

    public void checkWidget(String widgetCode, String widgetName, String intro) {
        StWidget widget = widgetDao.selectByCode(widgetCode);
        if (widget == null) {
            widget = new StWidget();
            widget.setWidgetCode(widgetCode);
            widget.setWidgetName(widgetName);
            widget.setWidgetIntro(intro);
            widget.setStatus("1");
            widget.setWeight(RandomUtil.randomInt(100));
            widget.setCreateTime(new Date());

            widgetDao.save(widget);
        }
    }
}
