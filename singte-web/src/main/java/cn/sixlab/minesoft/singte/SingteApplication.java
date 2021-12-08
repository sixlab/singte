package cn.sixlab.minesoft.singte;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("cn.sixlab.minesoft.singte.core.mapper")
@SpringBootApplication
public class SingteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingteApplication.class, args);
    }

}
