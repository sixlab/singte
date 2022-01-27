package cn.sixlab.minesoft.singte.core.controller.admin;

import cn.hutool.cron.CronUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.config.BaseTask;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StSpiderDao;
import cn.sixlab.minesoft.singte.core.dao.StSpiderPushDao;
import cn.sixlab.minesoft.singte.core.dao.StTaskDao;
import cn.sixlab.minesoft.singte.core.models.StSpider;
import cn.sixlab.minesoft.singte.core.models.StTask;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/reload")
public class AdminReloadController extends BaseController {

    @Autowired
    private StTaskDao taskDao;

    @Autowired
    private StSpiderDao spiderDao;

    @Autowired
    private StSpiderPushDao pushDao;

    @SneakyThrows
    @ResponseBody
    @PostMapping(value = "/task")
    public ModelResp task() {
        List<StTask> taskList = taskDao.selectStatus(StConst.YES);
        for (StTask stTask : taskList) {
            String id = "task" + stTask.getId();
            CronUtil.remove(id);

            Class<BaseTask> clz = (Class<BaseTask>) Class.forName(stTask.getTaskBean());
            BaseTask task = clz.getConstructor(Object.class).newInstance(stTask.getId());

            CronUtil.schedule(id, stTask.getTaskCron(), task);
        }

        return ModelResp.success();
    }

    @SneakyThrows
    @ResponseBody
    @PostMapping(value = "/spider")
    public ModelResp spider() {
        List<StSpider> spiderList = spiderDao.selectByStatus(StConst.YES);
        for (StSpider spider : spiderList) {
            String id = "spider" + spider.getId();
            CronUtil.remove(id);

            Class<BaseTask> clz = (Class<BaseTask>) Class.forName(spider.getSpiderBean());
            BaseTask task = clz.getConstructor(Object.class).newInstance(spider.getId());

            CronUtil.schedule(id, spider.getSpiderCron(), task);
        }

        return ModelResp.success();
    }

    @ResponseBody
    @PostMapping(value = "/push")
    public ModelResp push() {

        

        return ModelResp.success();
    }

}

