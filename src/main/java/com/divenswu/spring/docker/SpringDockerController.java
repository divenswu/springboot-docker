package com.divenswu.spring.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class SpringDockerController {

    private Logger logger = LoggerFactory.getLogger(getClass().toString());

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> home(HttpServletRequest request) {
        // 获取服务主机信息
        String ip = "unknown";
        String hostName = "unknown";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
            hostName=addr.getHostName();
        } catch (Exception e) {
            logger.info("get InetAddress error:", e);
        }
        // 获取请求链接地址
        String requestPath = "unknown";
        try {
            requestPath = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath();
            if (request.getQueryString() != null){
                requestPath += "?" + request.getQueryString();
            }
        } catch (Exception e) {
            logger.info("get requestPath error:", e);
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("hostIp", ip);
        map.put("hostName", hostName);
        map.put("requestPath", requestPath);
        logger.info("get request for : "+ requestPath);
        return map;
    }

}
