package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event

interface Listener {
    fun action(event: Event)
}