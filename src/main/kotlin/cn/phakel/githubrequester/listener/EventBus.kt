package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event
import org.slf4j.LoggerFactory

/**
 * Implement Class of EventBus
 */
class EventBus {

    companion object {

        /**
         * Get an EventBus object
         */
        val get = EventBus()
    }

    private var listeners = mutableListOf<Listener>()
    private val logger = LoggerFactory.getLogger(EventBus::class.java)

    /**
     * Post an Event
     */
    fun post(event: Event) {
        listeners
            .stream()
            .forEach { listener -> listener.javaClass.declaredMethods.filter {
                it.getAnnotation(Subscribe::class.java).event == event::class
            }.forEach { it.invoke(listener, event) } }
        logger.info("Posted ${event.javaClass.name}.")
    }

    /**
     * Register a Listener.
     */
    fun registerListener(listener: Listener) {
        listeners.add(listener)
        logger.info("Registered Listener ${listener.javaClass.name}.")
    }
}