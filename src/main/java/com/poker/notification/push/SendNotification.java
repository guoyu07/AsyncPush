package com.poker.notification.push;

import com.poker.notification.util.threadpool.support.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 推送服务
 *
 * @author run
 * @create 2017-11-08
 **/
public class SendNotification {

    protected static final Logger logger = LoggerFactory.getLogger(SendNotification.class);

    protected static ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("SendNotification", false));

    public final static SendNotification instance = new SendNotification();

    public final static SendNotification getInstance() {
        return instance;
    }

    private SendNotification() {
    }

    public void send(Map content) {
        checkExecutor();
        try {
            executor.execute(new PushPool(content));
        } catch (Throwable t) {
            logger.error(t.getMessage());
        }
    }

    private static void checkExecutor() {
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newCachedThreadPool(new NamedThreadFactory("SendNotification", false));
        }
    }
}
