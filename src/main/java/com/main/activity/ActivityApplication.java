package com.main.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
//修改springboot的启动类, 在类上加如下注解，关闭security功能。主要是因为activiti的依赖中含有spring security的jar包，所以springboot会自动配置安全功能，访问时就需要输入密码。
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        //org.activiti.spring.boot.SecurityAutoConfiguration.class,
})
public class ActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }

}
