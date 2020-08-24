package com.divenswu.spring.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
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
        //处理任务
        long costTime = this.doWork(request);
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
        map.put("costTime", costTime);
        logger.info("get request for : "+ requestPath);
        return map;
    }

    private long doWork(HttpServletRequest request){
        long a = System.currentTimeMillis();
        String timeoutStr = request.getParameter("timeout");
        if(!StringUtils.isEmpty(timeoutStr)){
            int timeout = 0;
            try {
                timeout = Integer.parseInt(timeoutStr);
            } catch (Exception e){}
            if (timeout > 0){
                long runNum = 0L;
                for(int i=0; i< 1000000000L * timeout; i++){
                    runNum += i;
                }
                System.out.println("runNum="+runNum);
            }
        }
        long b = System.currentTimeMillis();
        return (b-a);
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        long num = 0;
        for(int i=0; i< 1000000000; i++){
            num += i;
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);
    }

}
