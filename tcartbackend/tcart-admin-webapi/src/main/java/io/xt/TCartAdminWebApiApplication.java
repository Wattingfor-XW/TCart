package io.xt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("io.xt.dao")
@EnableCaching
public class TCartAdminWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCartAdminWebApiApplication.class, args);
    }

}

