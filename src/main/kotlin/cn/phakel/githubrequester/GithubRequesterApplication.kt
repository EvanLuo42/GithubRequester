package cn.phakel.githubrequester

import cn.phakel.githubrequester.event.Test
import cn.phakel.githubrequester.listener.EventBus
import cn.phakel.githubrequester.listener.Listener
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.util.*

@SpringBootApplication
@ComponentScan(basePackages = ["cn.phakel.githubrequester.config"])
class GithubRequesterApplication {

    private var port: Int = 8080
    private val app = SpringApplication(GithubRequesterApplication::class.java)
    private lateinit var args: Array<String>
    private val listeners = mutableListOf<Listener>()
    private val logger = LoggerFactory.getLogger(GithubRequesterApplication::class.java)
    private var secret: String = ""

    /**
     * Set Webhook Secret.
     */
    fun setSecret(secret: String): GithubRequesterApplication {
        this.secret = secret
        return this
    }

    /**
     * Register a Listener
     * At Least Add One Listener
     */
    fun registerListener(listener: Listener): GithubRequesterApplication {
        listeners.add(listener)
        return this
    }

    /**
     * Set the Compiling Arguments.
     * Force Setting
     */
    fun setArgs(args: Array<String>): GithubRequesterApplication {
        this.args = args
        return this
    }

    /**
     * Set Server Running Port.
     * Optional Setting
     */
    fun setPort(port: Int): GithubRequesterApplication {
        this.port = port
        return this
    }

    /**
     * Initialize the Webhook Server.
     */
    fun build(): GithubRequesterApplication {
        app.setDefaultProperties(Collections.singletonMap("server.port", port) as Map<String, Int>)
        if (secret.isNotEmpty()) app.setDefaultProperties(Collections.singletonMap("github.secret", secret) as Map<String, String>)
        app.run(*args)
        logger.info("Initialized the Webhook Server.")

        listeners
            .stream()
            .forEach { EventBus.getEventBus().registerListener(it) }
        logger.info("Registered the Listeners.")

        return this
    }
}

fun main(args: Array<String>) {
    val server = GithubRequesterApplication()
        .setPort(8000)
        .setArgs(args)
        .setSecret("ASdf")
        .registerListener(Test())
        .build()
}
