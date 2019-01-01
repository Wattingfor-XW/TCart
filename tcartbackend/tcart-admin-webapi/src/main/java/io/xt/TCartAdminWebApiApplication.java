package io.xt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.xt.dao")
public class TCartAdminWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCartAdminWebApiApplication.class, args);
    }

}

