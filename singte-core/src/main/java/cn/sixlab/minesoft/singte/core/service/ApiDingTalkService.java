package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StTodoDao;
import cn.sixlab.minesoft.singte.core.dao.StUserMetaDao;
import cn.sixlab.minesoft.singte.core.models.StTodo;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
import cn.sixlab.minesoft.singte.core.schedule.DingTalkJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class ApiDingTalkService extends BaseController {

    @Autowired
    private StTodoDao todoDao;

    @Autowired
    private DingTalkJob dingTalkJob;

    @Autowired
    private StUserMetaDao userMetaDao;

    @Autowired
    private DingTalkService dingTalkService;

    @Autowired
    private AdminServerService adminServerService;

    public void help(String dingUserId, StUser stUser) {
        StringBuilder sb = new StringBuilder();
        if (StConst.ROLE_ADMIN.equals(stUser.getRole())) {
            sb.append("回复 重启: 重启服务器\n");
            sb.append("回复 更新: 更新服务器\n\n");
        }
        sb.append("回复 h/help: 返回帮助内容\n");
        sb.append("回复 l/list: 返回待办列表\n");
        sb.append("回复 ll: 返回所有任务列表\n");
        sb.append("回复 数字（大于零）: 将指定编号的待办完成\n");
        sb.append("回复 数字（小于零）: 将指定编号的任务置为待办\n");

        dingTalkService.sendSampleText(dingUserId, sb.toString());
        sb.setLength(0);

        sb.append("回复 以“d/delete/删除”开头的字符串: 删除任务，多个参数以空格分割，示例：\n");
        sb.append("    - 删除 1：删除序号是1的任务\n");
        sb.append("    - 删除 1 -2：删除序号是1、-2的任务\n\n");

        sb.append("回复 以“b/batch/批量”开头的字符串: 批量完成任务，示例：\n");
        sb.append("    - 批量 1 2 5：将序号是1/2/5的任务完成\n\n");
        sb.append("回复 以“t/tip/提示”开头的字符串: 设置提示，多个参数以换行符分割，示例：\n");
        sb.append("    - 提示 xxxxxxx：设置提示语，放置于待办结尾\n");

        dingTalkService.sendSampleText(dingUserId, sb.toString());
        sb.setLength(0);

        sb.append("回复 以“a/add/添加”开头的字符串: 添加任务，多个参数以换行符分割，示例：\n");
        sb.append("    - 添加 任务名称：添加一次性任务，并默认启用\n");
        sb.append("    - 添加 任务名称+cron表达式：添加循环任务，并默认不启用，等下次cron表达式生效才启用\n");
        sb.append("    - 添加 任务名称+cron表达式+1：添加循环任务，并默认启用\n\n");
        sb.append("    - 1 1 1 * * ?\n");
        sb.append("    - 1 1 1 ? * MON,TUE,WED,THU,FRI,SAT,SUN\n");

        dingTalkService.sendSampleText(dingUserId, sb.toString());
    }

    public void update(String dingUserId, StUser stUser) {
        if (StConst.ROLE_ADMIN.equals(stUser.getRole())) {
            dingTalkService.sendSampleText(dingUserId, "准备更新");
            adminServerService.runShell("update.sh");

            dingTalkService.sendSampleText(dingUserId, "更新完毕，准备重启");
            adminServerService.runShell("kill.sh");
        }
    }

    public void restart(String dingUserId, StUser stUser) {
        if (StConst.ROLE_ADMIN.equals(stUser.getRole())) {
            dingTalkService.sendSampleText(dingUserId, "准备重启");
            adminServerService.runShell("kill.sh");
        }
    }

    public void listTodo(String dingUserId, StUser stUser) {
        String todo = dingTalkJob.listTodo(stUser);
        dingTalkService.sendSampleText(dingUserId, todo);
    }

    public void listTask(String dingUserId, StUser stUser) {
        String taskMessage = dingTalkJob.listAllTask(stUser);
        String todoMessage = dingTalkJob.listTodo(stUser);

        dingTalkService.sendSampleText(dingUserId, taskMessage + "\n\n" + todoMessage);
    }

    public void status(String dingUserId, StUser stUser, Integer indexNo) {
        StTodo stTodo = todoDao.selectByUserNo(stUser.getUsername(), indexNo);

        if (null != stTodo) {
            stTodo.setIndexNo(null);

            StringBuilder sb = new StringBuilder();
            if (indexNo > 0) {
                stTodo.setStatus(StConst.NO);
                todoDao.save(stTodo);

                sb.append("编号[").append(indexNo).append("]任务完成：").append(stTodo.getTodoName());
            } else {
                stTodo.setStatus(StConst.YES);
                todoDao.save(stTodo);

                sb.append("编号[").append(indexNo).append("]任务已启用：").append(stTodo.getTodoName());
            }
            dingTalkService.sendSampleText(dingUserId, sb.toString());
        } else {
            dingTalkService.sendSampleText(dingUserId, "未发现任务编号：" + indexNo);
        }
    }

    public void addTask(String dingUserId, StUser stUser, String[] params) {
        StTodo todo = new StTodo();

        todo.setTodoCode(dingUserId + new Date().getTime());
        todo.setUsername(stUser.getUsername());

        // 先设置为默认启用
        todo.setStatus(StConst.YES);
        todo.setTodoName(params[1]);

        if (params.length >= 3) {
            todo.setTodoCron(params[2]);
            // 如果长度超过2，先设置为禁用
            todo.setStatus(StConst.NO);
        }
        if (params.length >= 4 && "1".equals(params[3])) {
            // 第四个参数是1的，再启用
            todo.setStatus(StConst.YES);
        }
        todo.setTodoType("1");
        todo.setWeight(1);
        todo.setIntro("");
        todo.setUpdateTime(new Date());
        todo.setCreateTime(new Date());

        todoDao.save(todo);

        String taskMessage = dingTalkJob.listAllTask(stUser);
        String todoMessage = dingTalkJob.listTodo(stUser);

        String sb = "任务添加完成：" + todo.getTodoName() +
                "\n\n" + taskMessage +
                "\n\n" + todoMessage;

        dingTalkService.sendSampleText(dingUserId, sb);
    }

    public void delete(String dingUserId, StUser stUser, Integer indexNo) {
        StTodo stTodo = todoDao.selectByUserNo(stUser.getUsername(), indexNo);

        if (null != stTodo) {
            todoDao.deleteById(stTodo.getId());

            dingTalkService.sendSampleText(dingUserId, "编号[" + indexNo + "]任务已删除：" + stTodo.getTodoName());
        } else {
            dingTalkService.sendSampleText(dingUserId, "未发现任务编号：" + indexNo);
        }
    }

    public void tips(String dingUserId, StUser stUser, String[] params) {
        StUserMeta talkUserTips = userMetaDao.selectUserMeta(stUser.getUsername(), "dingTalk_UserTips");
        if(null== talkUserTips){
            talkUserTips = new StUserMeta();
            talkUserTips.setUsername(stUser.getUsername());
            talkUserTips.setStatus(StConst.YES);
            talkUserTips.setWeight(1);
            talkUserTips.setIntro("");
            talkUserTips.setMetaKey("dingTalk_UserTips");
            talkUserTips.setCreateTime(new Date());
        }else{
            talkUserTips.setUpdateTime(new Date());
        }
        talkUserTips.setMetaVal(params[1]);
        userMetaDao.save(talkUserTips);
    }
}
