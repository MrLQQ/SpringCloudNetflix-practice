package com.MrLQQ.springcloud.controller;

import com.MrLQQ.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptCustomerController {

    // RestTemplate 供我们直接调用就可以了！ 注册到Spring中
    // (url, 实体:Map, Class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    // 通过Ribbon实现， 我们这里的地址，应该是一个变量，通过服务来访问
    // private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/customer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
    }

    @RequestMapping("/customer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept,Boolean.class);
    }

    @RequestMapping("/customer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
    }

}
