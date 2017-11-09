package com.poker.notification.config;

import com.poker.notification.util.threadpool.support.ThreadPoolConfig;

/**
 * 线程池配置类
 *
 * @author run
 * @create 2017-11-08
 **/
public class PushThreadConfig implements ThreadPoolConfig {
    @Override
    public int core() {
        return 1;
    }

    @Override
    public int maxThreads() {
        return 10;
    }

    @Override
    public int queueSize() {
        return 10;
    }

    @Override
    public int alive() {
        return 0;
    }

    @Override
    public String threadName() {
        return "SendNotificationThread";
    }
}
