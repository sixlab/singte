package cn.sixlab.minesoft.singte.core.controller;

import cn.hutool.crypto.digest.DigestUtil;
import cn.sixlab.minesoft.singte.core.common.config.BaseController;
import cn.sixlab.minesoft.singte.core.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController {

    @Autowired
    private WxService service;

    @ResponseBody
    @GetMapping(value = "/push")
    public String push(String signature, String timestamp, String nonce, String echostr) {
        String token = service.wxToken();

        String[] paramArr = new String[]{token, timestamp, nonce};
        Arrays.sort(paramArr);

        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
        String ciphertext = DigestUtil.sha1Hex(content);

        if(signature.equals(ciphertext)){
            return echostr;
        }else{
            return "验签失败";
        }
    }
}
