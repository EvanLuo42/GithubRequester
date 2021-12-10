package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event

class EventBus {
    private val listeners = HashMap<Event, Listener>()

    fun post(event: Event): Boolean {
        listeners
            .entries
            .stream()
            .filter {listener -> listener.key.javaClass.name == event.javaClass.name }
            .forEach { listener -> listener.value.action(event) }

        return true
    }

    fun registerListener(type: Event, listener: Listener): Boolean {
        listeners[type] = listener
        return true
    }
}