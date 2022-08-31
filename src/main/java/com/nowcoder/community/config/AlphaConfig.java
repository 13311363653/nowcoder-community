package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author JBJ
 * @date 2022-07-21 16:29
 */
@Configuration //表示这是一个普通的配置类
public class AlphaConfig {

    /**
     * @Description 将java的SimpleDateFormat封装到bean中
     * @author JBJ
     * @date 2022-07-21 16:31
     */
    @Bean //该方法返回的对象将会被装配到容器中 bean的名称为方法名
    public SimpleDateFormat simpleDateFormat() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
