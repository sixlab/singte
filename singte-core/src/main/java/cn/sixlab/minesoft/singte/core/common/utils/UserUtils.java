package cn.sixlab.minesoft.singte.core.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            String name = authentication.getName();
            if (!StringUtils.equalsIgnoreCase(name, "anonymousUser")) {
                return name;
            }
        }
        return null;
    }

    public static boolean isLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth) {
            return !(auth instanceof AnonymousAuthenticationToken);
        }
        return false;
    }
}
