package cn.sixlab.minesoft.singte.core.common.init;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Order(5)
public class SysInit implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        Map<String, BaseInitComponent> beans = SpringUtil.getBeansOfType(BaseInitComponent.class);
        for (BaseInitComponent component : beans.values()) {
            component.init();
        }
    }
}
