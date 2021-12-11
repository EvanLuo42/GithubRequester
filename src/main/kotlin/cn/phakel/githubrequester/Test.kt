package cn.phakel.githubrequester

import cn.phakel.githubrequester.event.TestEvent
import cn.phakel.githubrequester.listener.Listener
import cn.phakel.githubrequester.listener.Subscribe

class Test: Listener {
    @Subscribe("TestEvent")
    fun action(event: TestEvent) {
        println("A")
    }
}