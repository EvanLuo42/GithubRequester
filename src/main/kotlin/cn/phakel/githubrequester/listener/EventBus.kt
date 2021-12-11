package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event

class EventBus {
    private var listeners = mutableListOf<Listener>()

    fun post(event: Event) {
        listeners
            .stream()
            //.filter { listener -> listener.javaClass.annotations.contains(Subscribe(event.javaClass.name)) }
            .forEach { listener -> listener.javaClass.declaredMethods.forEach { it.invoke(listener.javaClass.newInstance(), event) } }
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }
}