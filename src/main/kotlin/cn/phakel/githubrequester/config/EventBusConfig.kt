package cn.phakel.githubrequester.config

import cn.phakel.githubrequester.listener.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration

@Configuration
@Conditional
class EventBusConfig {

    /**
     * Bean of EventBus.
     */
    @Bean
    fun eventBus(): EventBus {
        return EventBus()
    }
}