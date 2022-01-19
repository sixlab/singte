package cn.sixlab.minesoft.singte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"cn.sixlab", "cn.hutool.extra.spring"})
public class SingteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingteApplication.class, args);
    }

}
