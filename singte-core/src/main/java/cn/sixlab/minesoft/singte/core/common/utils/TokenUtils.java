package cn.sixlab.minesoft.singte.core.common.utils;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.UUID;

public class TokenUtils {

    /**
     * 根据UserDetail生成Token
     */
    public static String generateToken(UserDetails userDetails) {
        String token = UUID.randomUUID() + userDetails.getUsername() + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        return SecureUtil.md5(token);
    }

}
