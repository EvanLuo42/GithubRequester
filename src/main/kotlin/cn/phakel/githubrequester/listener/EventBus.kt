package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event

/**
 * Implement Class of EventBus
 */
class EventBus {
    private var listeners = mutableListOf<Listener>()

    /**
     * Post an Event
     */
    fun post(event: Event) {
        println(event.javaClass.name)
        listeners
            .stream()
            .forEach { listener -> listener.javaClass.declaredMethods.filter{
                it.getAnnotation(Subscribe::class.java).event == event.javaClass.name
            }.forEach { it.invoke(listener.javaClass.newInstance(), event) } }
    }

    /**
     * Register a Listener.
     */
    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }
}