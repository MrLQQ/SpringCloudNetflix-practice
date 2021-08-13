package com.MrLQQ.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Dept 实体类
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)    // 链式写法
public class Dept implements Serializable {

    private Long deptno;
    private  String dname;

    // 这个数据库是存在哪个数据库的字段。
    // 微服务，一个服务对应一个数据库，同一个信息可能在不同的数据库
    private String db_source;

    public Dept(String dname){
        this.dname = dname;
    }

}
