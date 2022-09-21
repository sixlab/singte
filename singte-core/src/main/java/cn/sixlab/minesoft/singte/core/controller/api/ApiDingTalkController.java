package cn.sixlab.minesoft.singte.core.controller.api;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StTodoDao;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.dao.StUserMetaDao;
import cn.sixlab.minesoft.singte.core.models.StTodo;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
import cn.sixlab.minesoft.singte.core.schedule.DingTalkJob;
import cn.sixlab.minesoft.singte.core.service.AdminServerService;
import cn.sixlab.minesoft.singte.core.service.DingTalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/dingtalk")
@Api(tags = "钉钉机器人回调", description = "/api/dingtalk 钉钉机器人回调")
public class ApiDingTalkController extends BaseController {

    @Autowired
    private StTodoDao todoDao;

    @Autowired
    private StUserDao userDao;

    @Autowired
    private DingTalkJob dingTalkJob;

    @Autowired
    private StUserMetaDao userMetaDao;

    @Autowired
    private DingTalkService dingTalkService;

    @Autowired
    private AdminServerService adminServerService;

    @ResponseBody
    @PostMapping(value = "/callback")
    @ApiOperation(value = "钉钉机器人回调", consumes = "application/json", produces = "application/json")
    public ModelResp callback(@RequestBody JSONObject param) {
        log.info(param.toStringPretty());
        ModelResp result = ModelResp.success();
        String dingUserId = param.getStr("senderStaffId");
        StUserMeta userMeta = userMetaDao.selectByMeta("dingTalk_UserId", dingUserId);
        if (null == userMeta) {
            return ModelResp.error(404);
        }
        String username = userMeta.getUsername();
        StUser stUser = userDao.selectByUsername(username);

        String msgType = param.getStr("msgtype");
        if ("text".equals(msgType)) {
            JSONObject text = param.getJSONObject("text");
            if (null != text) {
                StringBuilder sb = new StringBuilder();
                String content = text.getStr("content");

                // 以下第一部分是完全匹配内容的
                if (StrUtil.equalsAny(content, "h", "help")) {
                    if(StConst.ROLE_ADMIN.equals(stUser.getRole())){
                        sb.append("回复 重启: 重启服务器\n");
                        sb.append("回复 更新: 更新服务器\n");

                        dingTalkService.sendSampleText(dingUserId, sb.toString());
                        sb.setLength(0);
                    }
                    sb.append("回复 h/help: 返回帮助内容\n");
                    sb.append("回复 l/list: 返回待办列表\n");
                    sb.append("回复 ll: 返回所有任务列表\n");
                    sb.append("回复 数字: 完成待办列表里的指定序号的任务\n");

                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);

                    sb.append("回复 以“d/delete/删除”开头的字符串: 删除任务，多个参数以空格分割，示例：\n");
                    sb.append("    - 删除 1：删除序号是1的任务\n");

                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);

                    sb.append("回复 以“s/status/状态”开头的字符串: 将未开启的任务置为待办，示例：\n");
                    sb.append("    - 状态 1：将序号是1的任务启用[ll里边的顺序]\n");

                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);

                    sb.append("回复 以“b/batch/批量”开头的字符串: 批量完成任务，示例：\n");
                    sb.append("    - 批量 1 2 5：将序号是1/2/5的任务完成\n");

                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);

                    sb.append("回复 以“a/add/添加”开头的字符串: 添加任务，多个参数以换行符分割，示例：\n");
                    sb.append("    - 添加 任务名称：添加一次性任务，并默认启用\n");
                    sb.append("    - 添加 任务名称+cron表达式：添加循环任务，并默认不启用，等下次cron表达式生效才启用\n");
                    sb.append("    - 添加 任务名称+cron表达式+1：添加循环任务，并默认启用\n");
                    sb.append("    - MON,TUE,WED,THU,FRI,SAT,SUN\n");

                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);
                } else if (StrUtil.equalsAny(content, "更新")) {
                    if (StConst.ROLE_ADMIN.equals(stUser.getRole())) {
                        dingTalkService.sendSampleText(dingUserId, "准备更新");
                        sb.setLength(0);
                        adminServerService.runShell("update.sh");

                        dingTalkService.sendSampleText(dingUserId, "更新完毕，准备重启");
                        sb.setLength(0);
                        adminServerService.runShell("kill.sh");
                    }
                } else if (StrUtil.equalsAny(content, "重启")) {
                    if (StConst.ROLE_ADMIN.equals(stUser.getRole())) {
                        dingTalkService.sendSampleText(dingUserId, "准备重启");
                        sb.setLength(0);
                        adminServerService.runShell("update.sh");
                    }
                } else if (StrUtil.equalsAny(content, "l", "list")) {
                    sb.append(dingTalkJob.listTodo(stUser));
                } else if (StrUtil.equalsAny(content, "ll")) {
                    sb.append(dingTalkJob.listAllTask(stUser));
                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);

                    sb.append(dingTalkJob.listTodo(stUser));
                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                    sb.setLength(0);
                // 以下是内容为数字的
                } else if (NumberUtil.isNumber(content)) {
                    StTodo stTodo = todoDao.selectByUserNo(username, Integer.valueOf(content));
                    if (null != stTodo) {
                        stTodo.setStatus(StConst.NO);
                        stTodo.setIndexNo(null);
                        todoDao.save(stTodo);

                        sb.append("编号[").append(content).append("]任务完成：").append(stTodo.getTodoName());
                    } else {
                        sb.append("未发现任务编号：").append(content);
                    }
                // 以下是以具体内容开头的
                } else if (StrUtil.startWithAny(content, "a", "add", "添加")) {
                    String[] strings = StrUtil.splitToArray(content, "\n");
                    if (strings.length >= 2) {
                        StTodo todo = new StTodo();

                        todo.setTodoCode(dingUserId + new Date().getTime());
                        todo.setUsername(username);

                        // 先设置为默认启用
                        todo.setStatus(StConst.YES);
                        todo.setTodoName(strings[1]);

                        if (strings.length >= 3) {
                            todo.setTodoCron(strings[2]);
                            // 如果长度超过2，先设置为禁用
                            todo.setStatus(StConst.NO);
                        }
                        if (strings.length >= 4 && "1".equals(strings[3])) {
                            // 第四个参数是1的，再启用
                            todo.setStatus(StConst.YES);
                        }
                        todo.setTodoType("1");
                        todo.setWeight(1);
                        todo.setIntro("");
                        todo.setUpdateTime(new Date());
                        todo.setCreateTime(new Date());

                        todoDao.save(todo);

                        String message = dingTalkJob.listTodo(stUser);
                        sb.append("任务添加完成：").append(todo.getTodoName()).append("\n\n").append(message);
                    } else {
                        sb.append("参数无效，长度小于2");
                    }
                } else if (StrUtil.startWithAny(content, "b", "batch", "批量")) {
                    String[] strings = StrUtil.splitToArray(content, " ");
                    if (strings.length >= 2) {
                        for (int i = 1; i < strings.length; i++) {
                            if (NumberUtil.isNumber(strings[1])) {
                                Integer indexNo = Integer.valueOf(strings[1]);
                                StTodo stTodo = todoDao.selectByUserNo(username, indexNo);
                                if (null != stTodo) {
                                    stTodo.setIndexNo(null);
                                    stTodo.setStatus(StConst.NO);
                                    todoDao.save(stTodo);

                                    sb.append("编号[").append(content).append("]任务完成：").append(stTodo.getTodoName()).append("\n");
                                } else {
                                    sb.append("未发现任务编号：").append(indexNo);
                                }
                            }
                        }
                    }
                } else if (StrUtil.startWithAny(content, "d", "delete", "删除")) {
                    String[] strings = StrUtil.splitToArray(content, " ");
                    if (strings.length == 2 && NumberUtil.isNumber(strings[1])) {
                        Integer indexNo = Integer.valueOf(strings[1]);
                        StTodo stTodo = todoDao.selectByUserNo(username, indexNo);
                        if (null != stTodo) {
                            todoDao.deleteById(stTodo.getId());

                            sb.append("编号[").append(indexNo).append("]任务已删除：").append(stTodo.getTodoName());

                            String message = dingTalkJob.listTodo(stUser);
                            sb.append("\n\n").append(message);
                        } else {
                            sb.append("未发现任务编号：").append(indexNo);
                        }
                    }
                } else if (StrUtil.startWithAny(content, "s", "status", "状态")) {
                    String[] strings = StrUtil.splitToArray(content, " ");
                    if (strings.length == 2 && NumberUtil.isNumber(strings[1])) {
                        Integer indexNo = Integer.valueOf(strings[1]);
                        StTodo stTodo = todoDao.selectByUserNo(username, indexNo);
                        if (null != stTodo) {
                            stTodo.setIndexNo(null);
                            stTodo.setStatus(StConst.YES);
                            todoDao.save(stTodo);

                            sb.append("编号[").append(indexNo).append("]任务已启用：").append(stTodo.getTodoName());

                            String message = dingTalkJob.listTodo(stUser);
                            sb.append("\n\n").append(message);
                        } else {
                            sb.append("未发现任务编号：").append(indexNo);
                        }
                    }
                }

                if (sb.length() > 0) {
                    dingTalkService.sendSampleText(dingUserId, sb.toString());
                }
            }
        }

        return result;
    }
}
