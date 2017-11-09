/*
 * Copyright 1999-2011 Alibaba Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.poker.notification.util.threadpool.support.limited;

import com.poker.notification.util.threadpool.ThreadPool;
import com.poker.notification.util.threadpool.support.AbortPolicyWithReport;
import com.poker.notification.util.threadpool.support.NamedThreadFactory;
import com.poker.notification.util.threadpool.support.ThreadPoolConfig;

import java.util.concurrent.*;

/**
 * 此线程池一直增长，直到上限，增长后不收缩。
 * 
 * @author hunker
 */
public class LimitedThreadPool implements ThreadPool {

    public Executor getExecutor(ThreadPoolConfig threadConfig) {
        String name = threadConfig.threadName();
        int cores = threadConfig.core();
        int threads = threadConfig.maxThreads();
        int queues = threadConfig.queueSize();

        ThreadPoolExecutor executor =  new ThreadPoolExecutor(cores, threads, Long.MAX_VALUE, TimeUnit.MILLISECONDS,
        		queues == 0 ? new SynchronousQueue<Runnable>() : 
        			(queues < 0 ? new LinkedBlockingQueue<Runnable>() 
        					: new LinkedBlockingQueue<Runnable>(queues)),
        		new NamedThreadFactory(name, true), new AbortPolicyWithReport(name));

        executor.prestartAllCoreThreads();

        return executor;
    }

}
