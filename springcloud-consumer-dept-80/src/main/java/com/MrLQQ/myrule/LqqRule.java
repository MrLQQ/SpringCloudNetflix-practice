package com.MrLQQ.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LqqRule {


    @Bean
    public IRule myRule(){
        return new LqqRandomRule(); //默认是轮询，现在是自定义的LqqRandomRUle
    }
}
