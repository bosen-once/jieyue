package com.example.jieyue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>启动类</p>
 * @author Bosen
 * @date 2021/8/9 23:07
 */
@MapperScan("com.example.jieyue.common.mapper")
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class JieyueApplication {

    public static void main(String[] args) {
        SpringApplication.run(JieyueApplication.class, args);
    }

}
