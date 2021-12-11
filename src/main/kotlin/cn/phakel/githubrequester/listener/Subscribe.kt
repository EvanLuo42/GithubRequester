package cn.phakel.githubrequester.listener

/**
 * Annotation Class for @Subscribe
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val event: String)