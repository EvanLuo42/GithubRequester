package cn.phakel.githubrequester.listener

import cn.phakel.githubrequester.event.Event
import kotlin.reflect.KClass

/**
 * Annotation Class for @Subscribe
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: KClass<out TestEvent>)