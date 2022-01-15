package cn.sixlab.minesoft.singte.core.common.init;

import cn.sixlab.minesoft.singte.core.common.utils.CtxHolder;
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
        Map<String, BaseInitComponent> beans = CtxHolder.getBeans(BaseInitComponent.class);
        for (BaseInitComponent component : beans.values()) {
            component.init();
        }
    }
}
