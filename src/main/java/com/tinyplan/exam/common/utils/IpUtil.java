package com.tinyplan.exam.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    // 建议不要改变顺序
    private static final String[] IP_HEADERS = {
            "x-forwarded-for",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    public static final String IP_UNKNOWN = "unknown";

    /**
     * 获取发送请求的ip地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = null;
        for (String header : IP_HEADERS) {
            ip = request.getHeader(header);
            // 判断IP是否为空
            if (!isIPEmpty(ip)) {
                return ip;
            }
        }
        return isIPEmpty(ip) ? request.getRemoteAddr() : ip;
    }

    /**
     * 判断IP地址是否为空
     * @param ip ip地址
     */
    private static boolean isIPEmpty(String ip){
        return ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip);
    }

}
