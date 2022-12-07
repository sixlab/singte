package cn.sixlab.minesoft.singte.core.controller.api;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.dao.StUserMetaDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
import cn.sixlab.minesoft.singte.core.service.ApiDingTalkService;
import cn.sixlab.minesoft.singte.core.service.DingTalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/dingtalk")
@Api(tags = "钉钉机器人回调", description = "/api/dingtalk 钉钉机器人回调")
public class ApiDingTalkController extends BaseController {

    @Autowired
    private StUserDao userDao;

    @Autowired
    private StUserMetaDao userMetaDao;

    @Autowired
    private DingTalkService dingTalkService;

    @Autowired
    private ApiDingTalkService service;

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
                String content = text.getStr("content");

                // 以下第一部分是完全匹配内容的
                if (StrUtil.equalsAny(content, "h", "help")) {
                    service.help(dingUserId, stUser);
                } else if (StrUtil.equalsAny(content, "更新")) {
                    service.update(dingUserId, stUser);
                } else if (StrUtil.equalsAny(content, "重启")) {
                    service.restart(dingUserId, stUser);
                } else if (StrUtil.equalsAny(content, "l", "list")) {
                    service.listTodo(dingUserId, stUser);
                } else if (StrUtil.equalsAny(content, "ll")) {
                    service.listTask(dingUserId, stUser);
                    // 以下是内容为数字的
                } else if (NumberUtil.isNumber(content)) {
                    Integer indexNo = Integer.valueOf(content);
                    service.status(dingUserId, stUser, indexNo);

                    service.listTask(dingUserId, stUser);
                    // 以下是以具体内容开头的
                } else if (StrUtil.startWithAny(content, "a", "add", "添加")) {
                    String[] params = StrUtil.splitToArray(content, "\n");
                    if (params.length >= 2) {
                        service.addTask(dingUserId, stUser, params);
                    } else {
                        dingTalkService.sendSampleText(dingUserId, "参数无效，长度小于2");
                    }
                } else if (StrUtil.startWithAny(content, "b", "batch", "批量")) {
                    String[] params = StrUtil.splitToArray(content, " ");
                    if (params.length >= 2) {
                        for (int i = 1; i < params.length; i++) {
                            if (NumberUtil.isNumber(params[i])) {
                                Integer indexNo = Integer.valueOf(params[i]);
                                service.status(dingUserId, stUser, indexNo);
                            }
                        }

                        service.listTask(dingUserId, stUser);
                    }
                } else if (StrUtil.startWithAny(content, "d", "delete", "删除")) {
                    String[] params = StrUtil.splitToArray(content, " ");
                    if (params.length >= 2) {
                        for (int i = 1; i < params.length; i++) {
                            if (NumberUtil.isNumber(params[i])) {
                                Integer indexNo = Integer.valueOf(params[i]);
                                service.delete(dingUserId, stUser, indexNo);
                            }
                        }

                        service.listTask(dingUserId, stUser);
                    }
                } else if (StrUtil.startWithAny(content, "t", "tips", "提示")) {
                    String[] params = StrUtil.splitToArray(content, "\n");
                    if (params.length >= 2) {
                        service.tips(dingUserId, stUser, params);
                    } else {
                        dingTalkService.sendSampleText(dingUserId, "参数无效，长度小于2");
                    }
                }
            }
        }

        return result;
    }
}
