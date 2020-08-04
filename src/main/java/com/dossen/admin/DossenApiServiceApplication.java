package com.dossen.admin;

import com.dossen.util.BeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.dossen")
public class DossenApiServiceApplication {

    public static void main(String[] args) {
        //run方法的返回值ConfigurableApplicationContext继承了ApplicationContext上下文接口
        ConfigurableApplicationContext applicationContext = SpringApplication.run( DossenApiServiceApplication.class,args );
        //将run方法的返回值赋值给工具类中的静态变量
        BeanUtil.applicationContext = applicationContext;
    }

}
