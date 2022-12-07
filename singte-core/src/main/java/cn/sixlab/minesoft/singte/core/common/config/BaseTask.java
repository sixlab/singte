package cn.sixlab.minesoft.singte.core.common.config;

import cn.hutool.cron.task.Task;

public abstract class BaseTask<T> implements Task {
    protected T param;

    public BaseTask(T param) {
        this.param = param;
    }

}
