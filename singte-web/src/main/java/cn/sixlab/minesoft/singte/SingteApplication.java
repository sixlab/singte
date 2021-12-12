package cn.sixlab.minesoft.singte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SingteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingteApplication.class, args);
    }

}
