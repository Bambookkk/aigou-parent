package cn.itsource.aigou.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SpringConfigApplication1299 {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfigApplication1299.class);
    }
}