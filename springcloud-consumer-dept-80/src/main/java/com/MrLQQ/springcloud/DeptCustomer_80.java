package com.MrLQQ.springcloud;

import com.MrLQQ.myrule.LqqRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


// Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号
@SpringBootApplication
@EnableEurekaClient
// 在微服务开启的时候就能去加载我们自定义Ribbon类
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = LqqRule.class)
public class DeptCustomer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptCustomer_80.class, args);
    }
}
