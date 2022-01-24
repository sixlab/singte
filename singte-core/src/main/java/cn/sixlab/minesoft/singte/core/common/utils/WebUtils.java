package cn.sixlab.minesoft.singte.core.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.sixlab.minesoft.singte.core.common.vo.StUserDetails;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class WebUtils {

    private final static Set<String> PublicSuffixSet = new HashSet<String>(
            Arrays.asList("tech|top|xyz|com|org|net|gov|edu|co|tv|mobi|info|asia|xxx|onion|cn|com.cn|edu.cn|gov.cn|net.cn|org.cn|jp|kr|tw|com.hk|hk|com.hk|org.hk|se|com.se|org.se"
                    .split("\\|")));

    private static final Pattern IP_PATTERN = Pattern
            .compile("(\\d{1,3}\\.){3}(\\d{1,3})");

    /**
     * 获取url的顶级域名
     *
     * @return
     */
    public static String getDomainName(String host) {
        if (host.endsWith(".")) {
            host = host.substring(0, host.length() - 1);
        }

        if (IP_PATTERN.matcher(host).matches()) {
            return host;
        }
        int index = 0;
        String candidate = host;
        while (index >= 0) {
            index = candidate.indexOf('.');
            String subCandidate = candidate.substring(index + 1);
            if (PublicSuffixSet.contains(subCandidate)) {
                return candidate;
            }
            candidate = subCandidate;
        }
        return candidate;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (null != requestAttributes) {
                return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getRequest();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpServletResponse getResponse() {
        try {
            ServletRequestAttributes requestAttributes = getRequestAttributes();

            if (null != requestAttributes) {
                return requestAttributes.getResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUA() {
        HttpServletRequest req = getRequest();

        String ua = req.getHeader("user-agent");
        if (ua != null) {
            ua = ua.toLowerCase();
        }

        return ua;
    }

    public static String getIP() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static void addCookie(String key, String val) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(30 * 60); // 30min
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static void addCookie(String key, String val, int expiry) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(expiry / 1000);
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    /**
     * 添加cookie 时间单位为秒
     *
     * @param key
     * @param val
     * @param expirySecond
     */
    public static void addCookieBySecond(String key, String val, int expirySecond) {
        Cookie cookie = new Cookie(key.trim(), val.trim());
        cookie.setMaxAge(expirySecond);
        cookie.setPath("/");
        getResponse().addCookie(cookie);
    }

    public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }

    public static String getCookie(String key) {
        Cookie[] cookies = getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取域名,例子  192.168.1.100   www.a.com
     */
    public static String getDomain() {
        return getDomain(getRequest(), true);
    }

    public static String getDomain(HttpServletRequest request, boolean excludePort) {
        String domain = request.getHeader("HOST");
        if (StrUtil.isNotEmpty(domain) && excludePort) {
            int endIdx = domain.indexOf(":");
            if (endIdx >= 0) {
                domain = domain.substring(0, endIdx);
            }
        }
        if (StrUtil.isNotEmpty(domain)) {
            return domain;
        }
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        int endIndex = requestURL.length() - requestURI.length();
        int beginIndex = requestURL.indexOf("://");
        if (beginIndex < 0) {
            beginIndex = 0;
        } else {
            beginIndex += "://".length();
        }
        domain = requestURL.substring(beginIndex, endIndex);
        if (StrUtil.isEmpty(domain)) {
            domain = "";
        }
        return domain;
    }

    public static String requestBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

//    以下为新增的方法，质量更高，上面的方面逐步删掉

    public static ModelAndView requestModel(String viewName) throws IOException {
        ModelAndView model;

        String method = getRequest().getMethod();
        if (HttpMethod.GET.matches(method)) {
            model = new ModelAndView(viewName);
        }else{
            model = new ModelAndView(new MappingJackson2JsonView());
        }

        return model;
    }

    public static ModelAndView requestModel(HttpServletRequest request, String viewName) throws IOException {
        ModelAndView model;

        String method = request.getMethod();
        if (HttpMethod.GET.matches(method)) {
            model = new ModelAndView(viewName);
        }else{
            model = new ModelAndView(new MappingJackson2JsonView());
        }

        return model;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter("Authorization");
        if (StrUtil.isEmpty(token)) {
            token = request.getHeader("Authorization");
        }
        if (StrUtil.isEmpty(token)) {
            token = getCookie("Authorization");
        }
        return token;
    }

    public static String getToken() {
        return getToken(WebUtils.getRequest());
    }

    public static StUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getPrincipal();
        if (details instanceof StUserDetails) {
            StUserDetails userDetails = (StUserDetails) details;
            userDetails.eraseCredentials();
            return userDetails.getStUser();
        }
        return null;
    }

}
