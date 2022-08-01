package cn.sixlab.minesoft.singte.core.controller.api;

import cn.hutool.json.JSONObject;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.common.vo.ModelResp;
import cn.sixlab.minesoft.singte.core.dao.StArticleDao;
import cn.sixlab.minesoft.singte.core.service.ArticleService;
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

    @ResponseBody
    @PostMapping(value = "/callback")
    @ApiOperation(value = "钉钉机器人回调", consumes = "application/json", produces = "application/json")
    public ModelResp callback(@RequestBody JSONObject param) {
        ModelResp result = ModelResp.success();

        log.info(param.toStringPretty());

        return result;
    }

}
