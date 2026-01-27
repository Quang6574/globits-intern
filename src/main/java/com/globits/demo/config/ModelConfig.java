package com.globits.demo.config;

import com.globits.demo.model.Channel;
import com.globits.demo.model.Television;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean
    public Television televisionBean() {
        return new Television();
    }
    @Bean
    public Channel channelBean() {
        return new Channel();

    }
}
