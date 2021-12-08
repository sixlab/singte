package cn.sixlab.minesoft.singte.core.common.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Order(-1)
@Slf4j
public class DirsInit implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            File file = new File("st_templates");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File("st_static");
            if (!file.exists()) {
                file.mkdirs();
            }
        }catch (Exception e){
            log.error("初始化模板和静态文件目录失败。", e);
        }
    }

}
