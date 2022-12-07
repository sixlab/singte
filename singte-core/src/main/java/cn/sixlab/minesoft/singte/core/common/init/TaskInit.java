package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.cron.CronUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseTask;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StTaskDao;
import cn.sixlab.minesoft.singte.core.models.StTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
@Order(10)
@Slf4j
public class TaskInit implements ApplicationRunner {

    @Autowired
    private StTaskDao taskDao;

    @Override
    public void run(ApplicationArguments args) {

        List<StTask> taskList = taskDao.selectStatus(StConst.YES);
        for (StTask stTask : taskList) {
            String id = "task" + stTask.getId();
            try {
                Class<BaseTask> clz = (Class<BaseTask>) Class.forName(stTask.getTaskBean());
                BaseTask task = clz.getConstructor(Object.class).newInstance(stTask.getId());
                CronUtil.schedule(id, stTask.getTaskCron(), task);
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

}
