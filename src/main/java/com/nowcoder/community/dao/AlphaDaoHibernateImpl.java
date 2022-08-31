package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author JBJ
 * @date 2022-07-21 15:53
 */
@Repository("alphaHibernate") //使其可以被容器扫描()中为bean的自定义名称，可不加
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
