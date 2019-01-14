package io.xt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.xt.dao")
public class TCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TCartServiceApplication.class, args);
    }

}

