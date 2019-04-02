package cn.itsource.aigou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.itsource.aigou.client","cn.itsource.aigou"}) //扫描fallbackfactory所在包和启动类所在包
@EnableFeignClients(basePackages = "cn.itsource.aigou.client") //扫描服务接口所在包
@MapperScan("cn.itsource.aigou.mapper")
public class ProductApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication8003.class);
    }
}
