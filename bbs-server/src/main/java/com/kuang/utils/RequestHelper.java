package com.kuang.utils;


import com.kuang.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.*;

/**
 * @author: dobell
 * @link: http://www.dobell.me/
 * @description:
 */
public class RequestHelper {

    public static final String REQUEST_HEADER_USER_AGENT = "user-agent";
    public static final String REQUEST_HEADER_REFERER = "referer";


    private static final String protocol = "Sec-WebSocket-Protocol";


    public static String getProtocol() {
        return protocol;
    }

    public static String getWebSocketProtocol(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(protocol);
    }


    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取session中的用户,添加全局对象
     *
     * @return
     */
    public static User getSessionUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
         User attribute = ( User) requestAttributes.getAttribute( "loginUser", RequestAttributes.SCOPE_REQUEST);
        return attribute;
    }

    /**
     * 判断请求是否为Ajax请求.
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }


    public static String getRequestIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为 真实 ip
            return StringUtils.split(ip, ",")[0].trim();
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        ip = request.getHeader("X-Cluster-Client-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        return request.getRemoteAddr();
    }


    public static boolean isApplicationJsonHeader(HttpServletRequest request) {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        return contentType != null && StringUtils.replaceAll(
                contentType.trim(),
                StringUtils.SPACE,
                StringUtils.EMPTY
        ).contains(MediaType.APPLICATION_JSON_VALUE);
    }


    public static String getRequestHeader(String headerName) {
        return getRequest().getHeader(headerName);
    }


    public static String getUserAgentHeader() {
        return getRequestHeader(REQUEST_HEADER_USER_AGENT);
    }


    public static String getRequestMessage(HttpServletRequest request) throws IOException {
        StringBuilder parameters = new StringBuilder();
        return getRequestMessage(request, parameters);
    }


    private static String getRequestMessage(HttpServletRequest request, StringBuilder parameters) throws
            IOException {
        parameters.append("\n请求URL : ")
                .append(request.getRequestURI())
                .append("\n请求URI : ")
                .append(request.getRequestURL())
                .append("\n请求方式 : ")
                .append(request.getMethod())
                .append(isAjaxRequest(request) ? "\tajax请求" : "\t同步请求")
                .append("\n请求者IP : ")
                .append(request.getRemoteAddr())
                .append("\nSESSION_ID : ")
                .append(request.getSession().getId())
                .append("\n请求时间 : ")
                .append(Instant.now());
        // 请求头
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String element = headerNames.nextElement();
            if (null != element) {
                String header = request.getHeader(element);
                parameters.append("\n请求头内容 : ").append(element).append("=").append(header);
            }
        }
        parameters.append("\n请求参数 : ").append(getRequestBody(request));
        // 请求Session内容
        final Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            parameters.append("\nSession内容 : ").append(sessionAttributeNames.nextElement());
        }
        return parameters.toString();
    }


    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 得到请求的根目录
     *
     * @return
     */
    public static String getBasePath() {
        HttpServletRequest request = getHttpServletRequest();
        String path = getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;
        return basePath;
    }

    /**
     * 得到结构目录
     *
     * @return
     */
    public static String getContextPath() {
        HttpServletRequest request = getHttpServletRequest();
        String path = request.getContextPath();
        return path;
    }


    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        String str = sb.toString();
        return str;
    }

    public static String getRequestBody() throws IOException {
        HttpServletRequest request = getHttpServletRequest();
        ServletInputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        String str = sb.toString();
        return str;
    }

    public static Map getRequestHeaders(String type) {
        Map heads = new HashMap();
        List<String> list = new ArrayList<>();
        list.add(type);
        heads.put("type", list);
        return heads;
    }

}