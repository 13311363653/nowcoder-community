package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //测试类采用和正式代码相同的配置类
//一个类实现ApplicationContextAware接口，获取Spring容器
public class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    //实现setApplicationContext方法,Spring容器会将自身的容器传给applicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;//将Spring容器保存到applicationContext中
    }

    /**
     * @Description Spring 容器获取bean
     * @author JBJ
     * @date 2022-07-21 16:27
     */
    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext); //org.springframework.web.context.support.GenericWebApplicationContext@239105a8
        //从容器中获取自动装配的bean
        AlphaDao bean = applicationContext.getBean(AlphaDao.class);//通过反射类的方式获取数据
        System.out.println(bean.select());//MyBatis(设置了高优先级)
        System.out.println(bean); //com.nowcoder.community.dao.AlphaDaoMyBatisImpl@57e388c3

        AlphaDao bean1 = applicationContext.getBean("alphaHibernate", AlphaDao.class);//通过bean的名称获取(“bean名称”，强制转型的类型)
        System.out.println(bean1.select());//Hibernate（指定名称获得bean）
    }


    /**
     * @Description Spring 容器管理bean
     * @author JBJ
     * @date 2022-07-21 16:27
     */
    @Test
    public void testBeanManagement(){
        /*
        被Spring容器管理的bean，默认是单例的，只会实例化一次。加入@Scope（“prototype”）可以实现每次调用都会实例化一次，很多用
         */
        AlphaService alphaServiceBean = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaServiceBean); //在控制台可以看到执行顺序为：实例化--初始化--销毁
    }

    /**
     * @Description Spring 容器装配第三方的bean
     * @author JBJ
     * @date 2022-07-21 16:28
     */

    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));//在BeanConfig中封装了一个格式化类
    }

    /**
     * @Description 测试依赖注入
     * @author JBJ
     * @date 2022-07-21 16:37
     */

    @Autowired //令Spring容器自动将bean赋值给以下变量
    private AlphaDao alphaDao;
    /*
    等价于这个过程,且外部类不需实现ApplicationContextAware接口
    AlphaDao bean = applicationContext.getBean(AlphaDao.class);//通过反射类的方式获取数据
    bean.select()
     */

    //令Spring容器自动将名称为（）中的bean赋值给以下变量
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao2;

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI(){
        System.out.println(alphaDao); //com.nowcoder.community.dao.AlphaDaoMyBatisImpl@3cd26422
        System.out.println(alphaDao2); //com.nowcoder.community.dao.AlphaDaoHibernateImpl@3b83459e
        System.out.println(alphaService); //com.nowcoder.community.service.AlphaService@2eda072
        System.out.println(simpleDateFormat); //java.text.SimpleDateFormat@4f76f1a0

    }

}
