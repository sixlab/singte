package cn.sixlab.minesoft.singte.core.schedule;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StTodoDao;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.dao.StUserMetaDao;
import cn.sixlab.minesoft.singte.core.models.StTodo;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
import cn.sixlab.minesoft.singte.core.service.DingTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DingTalkJob {

    @Autowired
    private StTodoDao todoDao;

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StUserMetaDao userMetaDao;

    @Autowired
    private DingTalkService service;

    @Scheduled(cron = "1 0 0 * * ?")
    public void initToday() {
        LocalDateTime now = LocalDateTime.now();
        List<StTodo> todoList = todoDao.selectStatus(null, "0");
        for (StTodo stTodo : todoList) {
            if (StrUtil.isNotEmpty(stTodo.getTodoCron())) {
                CronExpression cronExpression = CronExpression.parse(stTodo.getTodoCron());
                LocalDateTime next = cronExpression.next(now);
                if (Duration.between(now, next).toDays() == 0) {
                    stTodo.setStatus(StConst.YES);
                    todoDao.save(stTodo);
                }
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void morning() {
        notifyTask();
    }


    @Scheduled(cron = "0 0 18 * * ?")
    public void afternoon() {
        notifyTask();
    }


    @Scheduled(cron = "0 0 22 * * ?")
    public void evening() {
        notifyTask();
    }

    private void notifyTask() {
        List<StUser> userList = userDao.selectStatus(StConst.YES);

        for (StUser stUser : userList) {
            notifyUser(stUser);
        }
    }

    private void notifyUser(StUser stUser) {
        StUserMeta talkUserId = userMetaDao.selectUserMeta(stUser.getUsername(), "dingTalk_UserId");
        if (null != talkUserId) {
            String msg = userMessage(stUser);
            service.sendSampleText(talkUserId.getMetaVal(), msg);
        }
    }

    public String userMessage(StUser stUser) {
        List<StTodo> todoList = todoDao.selectStatus(stUser.getUsername(), StConst.YES);
        int index = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("您好，").append(stUser.getShowName()).append("，您的待办清单：\n\n");
        for (StTodo stTodo : todoList) {
            stTodo.setIndexNo(++index);
            todoDao.save(stTodo);

            sb.append(index).append(". ").append(stTodo.getTodoName()).append("\n");
        }

        return sb.toString();
    }
}
