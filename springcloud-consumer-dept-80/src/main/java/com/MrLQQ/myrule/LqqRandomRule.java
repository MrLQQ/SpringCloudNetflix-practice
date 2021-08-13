
package com.MrLQQ.myrule;

import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import com.netflix.client.config.IClientConfig;

public class LqqRandomRule extends AbstractLoadBalancerRule {

    // 每个服务访问5次，换下一个服务
    // total=0，默认=0，如果=5，我们指向下一个服务节点
    // index=0，默认0，如果total=5，index+1

    private int total = 0;  //被调用的次数
    private int currentIndex=0; //当前式谁在提供服务

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers(); //获得活着的服务
            List<Server> allList = lb.getAllServers();  //获得全部的服务

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

//            int index = chooseRandomInt(serverCount);   // 生生产区间随机函数
//            server = upList.get(index); // 从活着的服务中，随机获得一个

            //-========================================

            if (total<5){
                server = upList.get(currentIndex);
                total++;
            }else {
                total=0;
                currentIndex++;
                if (currentIndex>=upList.size()){
                    currentIndex = 0;
                }
                server = upList.get(currentIndex);  // 从活着的服务中，获取只当的服务来进行操作
            }

            //-========================================

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(),key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
