package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@SpringBootApplication

/*
Sets up default configuratyion
starts spring application context
performs class path scan
starts Tomcat server
 */
public class ProductappJwaApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ProductappJwaApplication.class, args);
        String beans[] = ctx.getBeanDefinitionNames();
        Arrays.sort(beans);
        for(String beanNames:beans){
            System.out.println(beanNames);
        }
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
