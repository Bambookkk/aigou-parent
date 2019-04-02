package cn.itsource.aigou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //表明是eureka的客户端
public class CommonApplication8005 {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication8005.class);
    }
}
