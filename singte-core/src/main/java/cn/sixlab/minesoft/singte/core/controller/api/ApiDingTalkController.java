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

                if (StrUtil.equalsAny(content, "h", "help")) {
                    sb.append("回复 h/help: 返回帮助内容\n");
                    sb.append("回复 l/list: 返回待办列表\n");
                    sb.append("回复 数字: 完成待办列表里的指定序号的任务\n");
                    sb.append("回复 d 数字: 删除待办列表里的指定序号的任务\n");
                    sb.append("回复 以“添加”开头的字符串: 添加任务，多个参数以空格分割，示例：\n");
                    sb.append("    - 添加 任务名称：添加一次性任务，并默认启用\n");
                    sb.append("    - 添加 任务名称 cron表达式：添加循环任务，并默认不启用，等下次cron表达式生效才启用\n");
                    sb.append("    - 添加 任务名称 cron表达式 1：添加循环任务，并默认启用\n");
                } else if (StrUtil.equalsAny(content, "l", "list")) {
                    sb.append(dingTalkJob.userMessage(stUser));
                } else if (NumberUtil.isNumber(content)) {
                    StTodo stTodo = todoDao.selectByUserNo(username, Integer.valueOf(content));
                    if (null != stTodo) {
                        stTodo.setStatus("0");
                        todoDao.save(stTodo);

                        sb.append("编号[").append(content).append("]任务完成：").append(stTodo.getTodoName());
                    } else {
                        sb.append("未发现任务编号：").append(content);
                    }
                } else if (content.startsWith("d")) {
                    String[] strings = StrUtil.splitToArray(content, " ");
                    if (strings.length == 2 && NumberUtil.isNumber(strings[1])) {
                        StTodo stTodo = todoDao.selectByUserNo(username, Integer.valueOf(strings[1]));
                        if (null != stTodo) {
                            todoDao.deleteById(stTodo.getId());

                            sb.append("编号[").append(content).append("]任务已删除：").append(stTodo.getTodoName());
                        } else {
                            sb.append("未发现任务编号：").append(content);
                        }
                    }
                } else if (content.startsWith("添加")) {
                    String[] strings = StrUtil.splitToArray(content, " ");
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
                            todo.setStatus(StConst.No);
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

                        String message = dingTalkJob.userMessage(stUser);
                        sb.append("任务添加完成：").append(todo.getTodoName()).append("\n").append(message);
                    }else{
                        sb.append("参数无效，长度小于2");
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
