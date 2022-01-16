package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.core.util.RandomUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.SteToolItemDao;
import cn.sixlab.minesoft.singte.core.models.SteToolItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InitTool implements BaseInitComponent {

    @Autowired
    private SteToolItemDao toolItemDao;

    @Override
    public void init() {
        checkToolItem("tool-ancient-name", "古意取名", "工具");
    }

    public void checkToolItem(String toolCode, String toolName, String category) {
        SteToolItem toolItem = toolItemDao.selectByCode(toolCode);

        if (null == toolItem) {
            toolItem = new SteToolItem();
            toolItem.setCategory(category);
            toolItem.setToolName(toolName);
            toolItem.setToolCode(toolCode);
            toolItem.setViewCount(0);
            toolItem.setThumbCount(0);
            toolItem.setWeight(RandomUtil.randomInt(100));
            toolItem.setIntro(toolName);
            toolItem.setStatus(StConst.YES);
            toolItem.setCreateTime(new Date());

            toolItemDao.save(toolItem);
        }
    }
}
