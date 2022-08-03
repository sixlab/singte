package cn.sixlab.minesoft.singte.core.controller.api;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StTodoDao;
import cn.sixlab.minesoft.singte.core.dao.StUserMetaDao;
import cn.sixlab.minesoft.singte.core.models.StTodo;
import cn.sixlab.minesoft.singte.core.models.StUserMeta;
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
    private StTodoDao todoDao;

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

        String msgType = param.getStr("msgtype");
        String dingUserId = param.getStr("senderStaffId");

        StringBuilder sb = new StringBuilder();
        if ("text".equals(msgType)) {
            JSONObject text = param.getJSONObject("text");
            if (null != text) {
                String content = text.getStr("content");
                if (NumberUtil.isNumber(content)) {
                    StUserMeta userMeta = userMetaDao.selectByMeta("dingTalk_UserId", dingUserId);
                    if (null != userMeta) {
                        String username = userMeta.getUsername();

                        StTodo stTodo = todoDao.selectByUserNo(username, content);
                        if (null != stTodo) {
                            stTodo.setStatus("0");
                            todoDao.save(stTodo);

                            sb.append("编号[").append(content).append("]任务完成：").append(stTodo.getTodoName());
                        }else{
                            sb.append("未发现任务编号：").append(content);
                        }
                    }else{
                        sb.append("未发现用户");
                    }
                }
            }
        }

        if(sb.length() > 0){
            dingTalkService.sendSampleText(dingUserId, sb.toString());
        }

        return result;
    }

}
