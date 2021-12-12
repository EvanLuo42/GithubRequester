package cn.phakel.githubrequester.config

import cn.phakel.githubrequester.listener.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusConfig {

    /**
     * Bean of EventBus.
     */
    @Bean(name = ["eventBus"])
    fun eventBus(): EventBus {
        return EventBus()
    }
}