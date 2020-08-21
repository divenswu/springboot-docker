package com.divenswu.spring.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDockerApplication {
    private static Logger logger = LoggerFactory.getLogger(SpringDockerApplication.class);

    public static void main(String[] args) {
        logger.info("running SpringDockerApplication");
        SpringApplication.run(SpringDockerApplication.class, args);
    }

}
