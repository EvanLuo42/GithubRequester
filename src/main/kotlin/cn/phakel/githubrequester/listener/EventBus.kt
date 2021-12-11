package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event

class EventBus {
    private var listeners = mutableListOf<Listener>()

    fun post(event: Event) {
        println(event.javaClass.name)
        listeners
            .stream()
            .forEach { listener -> listener.javaClass.declaredMethods.filter{
                it.getAnnotation(Subscribe::class.java).event == event.javaClass.name
            }.forEach { it.invoke(listener.javaClass.newInstance(), event) } }
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }
}