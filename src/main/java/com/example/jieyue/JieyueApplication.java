package com.example.jieyue;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.jieyue.common.mapper")// mapper扫描
@SpringBootApplication
@ServletComponentScan
@EnableScheduling// 定时任务
public class JieyueApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JieyueApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JieyueApplication.class);
    }

    /*
     * 处理文件过大上传失败的问题
     */
    @Bean
    public TomcatServletWebServerFactory containerFactory() {
        return new TomcatServletWebServerFactory() {
            protected void customizeConnector(Connector connector) {
                int maxSize = 50000000;
                super.customizeConnector(connector);
                connector.setMaxPostSize(maxSize);
                connector.setMaxSavePostSize(maxSize);
                if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {

                    ((AbstractHttp11Protocol <?>) connector.getProtocolHandler()).setMaxSwallowSize(maxSize);
                    logger.info("Set MaxSwallowSize "+ maxSize);
                }
            }
        };

    }
}
