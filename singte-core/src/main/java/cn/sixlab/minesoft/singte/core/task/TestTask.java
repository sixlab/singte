package cn.sixlab.minesoft.singte.core.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseTask;
import cn.sixlab.minesoft.singte.core.dao.StTaskDao;
import cn.sixlab.minesoft.singte.core.models.StTask;

import java.util.Date;

public class TestTask extends BaseTask<StTask> {
    public TestTask(StTask param) {
        super(param);
    }

    @Override
    public void execute() {
        StTaskDao bean = SpringUtil.getBean(StTaskDao.class);
        System.out.println(bean.selectById(param.getId()).getTaskName());
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + param.getId());
    }
}
