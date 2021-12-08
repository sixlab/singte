package cn.sixlab.minesoft.singte.core.common.utils;

import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 请求URL，不修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param method {@code RequestMethod.POST}，{@code RequestMethod.GET} 等
     * @param data   请求的参数
     * @return 请求结果
     */
    public static String sendRequest(String url, RequestMethod method, Map<String, String> data) {
        return sendRequest(url, method, data, null);
    }

    /**
     * 请求URL，需要修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param method {@code RequestMethod.POST}，{@code RequestMethod.GET} 等
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return 请求结果
     */
    public static String sendRequest(String url, RequestMethod method, Map<String, String> data, Map<String, String> header) {
        if (RequestMethod.POST.equals(method)) {
            return sendPost(url, data, header);
        } else {
            return sendGet(url, data, header);
        }
    }

    /**
     * POST 请求URL，参数是 Form 提交，不需要修改Header的值。
     *
     * @param url  请求的链接
     * @param data 请求的参数
     * @return 请求结果
     */
    public static String sendPost(String url, Map<String, String> data) {
        return sendPost(url, data, null);
    }

    /**
     * POST 请求URL，参数是 Form 提交，需要修改Header的值。
     *
     * @param url    请求的链接
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return 请求结果
     */
    public static String sendPost(String url, Map<String, String> data, Map<String, String> header) {
        logger.info("POST：" + url);
        logger.info("参数：" + makeGetParam(data));

        FormBody.Builder builder = new FormBody.Builder();
        if (MapUtils.isNotEmpty(data)) {
            for (String key : data.keySet()) {
                if (StringUtils.hasLength(data.get(key))) {
                    builder.add(key, data.get(key));
                }
            }
        }

        Headers.Builder headersBuilder = new Headers.Builder();
        if (MapUtils.isNotEmpty(header)) {
            for (String key : header.keySet()) {
                if (StringUtils.hasLength(header.get(key))) {
                    headersBuilder.add(key, header.get(key));
                }
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .post(builder.build())
                .build();

        Response response = null;
        Date date = new Date();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("error:" + url);
        }
        logger.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * GET 请求URL，不需要修改Header的值，含有参数。
     *
     * @param url  请求的链接
     * @param data 请求的参数
     * @return 请求结果
     */
    public static String sendGet(String url, Map<String, String> data) {
        return sendGet(url, data, null);
    }

    /**
     * GET 请求URL，需要修改Header的值，含有参数。
     *
     * @param url    请求的链接
     * @param data   请求的参数
     * @param header 请求的 header 参数
     * @return 请求结果
     */
    public static String sendGet(String url, Map<String, String> data, Map<String, String> header) {
        logger.info("GET：" + url);
        logger.info("参数：" + makeGetParam(data));

        Headers.Builder headersBuilder = new Headers.Builder();
        if (MapUtils.isNotEmpty(header)) {
            for (String key : header.keySet()) {
                headersBuilder.add(key, header.get(key));
            }
        }

        if (MapUtils.isNotEmpty(data)) {
            url = makeGetUrl(url, data);
        }

        Response response = null;
        Date date = new Date();

        Request request = new Request.Builder()
                .url(url)
                .headers(headersBuilder.build())
                .build();

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("error:" + url);
        }
        logger.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * POST 请求URL，提交是POST提交，不需要修改Header的值。
     *
     * @param url  请求的链接
     * @param json 请求的参数
     * @return 请求结果
     */
    public static String sendPostBody(String url, String json) {
        return sendPostBody(url, json, null);
    }

    /**
     * POST 请求URL，提交是POST提交，需要修改Header的值。
     *
     * @param url    请求的链接
     * @param json   请求的参数
     * @param header 请求的 header 参数
     * @return 请求结果
     */
    public static String sendPostBody(String url, String json, Map<String, String> header) {
        logger.info("POST JSON：" + url);
        logger.info("参数：" + json);

        Headers.Builder builder = new Headers.Builder();
        if (MapUtils.isNotEmpty(header)) {
            for (String key : header.keySet()) {
                builder.add(key, header.get(key));
            }
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(builder.build())
                .build();
        Date date = new Date();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("error:" + url);
        }
        logger.info(url + " 时间 " + (new Date().getTime() - date.getTime()));
        return responseHandler(response);
    }

    /**
     * 转换 map 为 get 请求的的参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data 要转换的map
     * @return 转换结果
     */
    public static String makeGetParam(Map<String, String> data) {
        return makeGetParam(data, false);
    }

    /**
     * 转换 map 为 get 请求的的参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data      要转换的map
     * @param urlEncode 是否将 val 转码
     * @return 转换结果
     */
    public static String makeGetParam(Map<String, String> data, boolean urlEncode) {
        StringBuilder sb = new StringBuilder();
        if (MapUtils.isNotEmpty(data)) {
            Iterator<String> iterator = data.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String val = data.get(key);
                sb.append(key);
                sb.append("=");
                if (urlEncode) {
                    sb.append(URLEncoder.encode(val));
                } else {
                    sb.append(val);
                }
                if (iterator.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    public static String responseHandler(Response response) {
        if (response != null) {
            if (response.isSuccessful() && response.body() != null) {
                try {
                    String text = response.body().string();
                    logger.info("返回url_fragment:" + response.request().url().fragment());
                    logger.info("返回：" + text);
                    return text;
                } catch (IOException e) {
                    logger.error("请求错误：" + response);
                }
            } else {
                try {
                    logger.error("请求失败：" + (response.body() != null ? response.body().string() : "null") + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 转换 map 为 get 请求的的参数，key=val&k1=v1&k2=v2 格式，不含 ? 符号。
     *
     * @param data 要转换的map
     * @return 转换结果
     */
    public static String makeGetUrl(String url, Map<String, String> data) {
        return url + "?" + makeGetParam(data, true);
    }

    /**
     * 将Get字符串转换为 LinkedHashMap
     *
     * @param param 字符串参数
     * @return 转换结果
     */
    public static Map<String, String> param2map(String param) {
        Map<String, String> map = new LinkedHashMap<>();

        if (null != param) {
            String[] parameters = param.split("&");
            if (parameters.length > 0) {
                for (String parameter : parameters) {
                    String[] kv = parameter.split("=");
                    if (kv.length == 2) {
                        String key = kv[0];
                        String val = kv[1];
                        if (StringUtils.hasLength(key) && StringUtils.hasLength(val)) {
                            map.put(key, val);
                        }
                    }
                }
            }
        }

        return map;
    }

}
