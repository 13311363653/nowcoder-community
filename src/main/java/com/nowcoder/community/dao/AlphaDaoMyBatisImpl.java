package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author JBJ
 * @date 2022-07-21 15:59
 */
@Repository //数据库组件 使其可以被容器扫描
@Primary //当有多个符合反射机制的类时，优先选择这个
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
