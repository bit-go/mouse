package com.mouse;

import com.mouse.core.config.RedisTemplateConfig;
import com.mouse.core.config.ThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */

@EnableAsync
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    /**
     * redisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTemplateConfig(redisConnectionFactory).getRedisTemplate();
    }

    /**
     * 自定义Async线程池
     *
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        return new ThreadPoolConfig(50, 100, 1000, 10,
                "rotor-admin-thread-pool").getThreadPool();
    }
}
