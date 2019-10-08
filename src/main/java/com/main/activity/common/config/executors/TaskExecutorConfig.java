package com.main.activity.common.config.executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: fengguang xu
 * @Description: 线程池配置类
 * @Date: 2019/4/29 16:18
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig {

    @Value("${task-executor.core_pool_size}")
    private int corePoolSize;
    @Value("${task-executor.max_pool_size}")
    private int maxPoolSize;
    @Value("${task-executor.queue-capacity}")
    private int queueCapacity;
    @Value("${task-executor.name-prefix}")
    private String namePrefix;


    @Bean("asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix(namePrefix);
        taskExecutor.setKeepAliveSeconds(60);
        //对拒绝task的处理策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        taskExecutor.initialize();
        return taskExecutor;
    }





}
