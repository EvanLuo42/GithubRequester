package cn.phakel.githubrequester

import cn.phakel.githubrequester.listener.Listener
import cn.phakel.githubrequester.listener.Subscribe
import cn.phakel.githubrequester.listener.TestEvent

class Test: Listener {
    @Subscribe(TestEvent::class)
    fun test(event: TestEvent) {
        println(event.a)
    }
}