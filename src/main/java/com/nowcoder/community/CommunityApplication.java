package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //这是一个springboot应用
public class CommunityApplication {
    public static void main(String[] args) {

		SpringApplication.run(CommunityApplication.class, args);//创建了一个容器和tomcat
    }

}
