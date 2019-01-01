package io.xt;


@SpringBootApplication
@MapperScan("io.xt.tcart.dao")
public class TCartAdminWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JCartServiceApplication.class, args);
    }

}

