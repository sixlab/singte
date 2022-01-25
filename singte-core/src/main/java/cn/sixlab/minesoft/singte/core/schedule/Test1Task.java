package cn.sixlab.minesoft.singte.core.schedule;

import cn.hutool.cron.task.Task;
import org.springframework.stereotype.Component;

@Component
public class Test1Task implements Task {

    @Override
    public void execute() {
        System.out.println("task1 run");
    }
}
