package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author JBJ
 * @date 2022-07-21 16:11
 */
@Service //业务组件
public class AlphaService  {
    /*
        1、在service中调用dao的方法
         */
    @Autowired
    private AlphaDao alphaDao; //将默认的alphaDao（MyBatis）注入到对象中


    public String find() {
        return alphaDao.select();//返回MyBatis
    }


    //构造器
    public AlphaService() {
        System.out.println("构建AlphaService");
    }

    //初始化方法
    @PostConstruct //使该方法在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");

    }

    @PreDestroy //在对象销毁之前调用
    public void destory() {
        System.out.println("销毁AlphaService");
    }


}
