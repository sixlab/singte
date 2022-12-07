package cn.sixlab.minesoft.singte.core.common.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class InitDirs implements BaseInitComponent {

    @Override
    public void init() {
        try {
            File file = new File("st_templates");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File("st_static");
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            log.error("初始化模板和静态文件目录失败。", e);
        }
    }
}
