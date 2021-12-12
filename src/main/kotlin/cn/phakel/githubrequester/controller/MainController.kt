package cn.phakel.githubrequester.controller

import cn.phakel.githubrequester.GithubRequesterApplication
import cn.phakel.githubrequester.event.CommitCommentEvent
import cn.phakel.githubrequester.event.Event
import cn.phakel.githubrequester.event.repository.branch.Create
import cn.phakel.githubrequester.event.repository.branch.Delete
import cn.phakel.githubrequester.listener.EventBus
import cn.phakel.githubrequester.listener.TestEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@RestController
class MainController {

    @Autowired
    private lateinit var eventBus: EventBus

    @Value("\${github.secret}")
    private lateinit var secret: String

    @RequestMapping("/")
    fun welcome() = "Welcome to GithubRequester Webhook API."

    @RequestMapping("/test")
    fun test(): Boolean {
        eventBus.testPost(TestEvent("Success"))
        return true
    }

    @RequestMapping("/webhook", method = [RequestMethod.POST])
    fun webhook(@RequestBody body: String, @RequestHeader header: Map<String, String>): Map<String, Boolean> {
        /**
         * Construct the Signature.
         */
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKey)
        val signature = "sha256=" + sha256Hmac.doFinal(body.toByteArray())

        /**
         * Verify the Signature.
         */
        if (signature == header["HTTP_X_HUB_SIGNATURE_256"]) {
            val mapper = ObjectMapper()

            /**
             * Post Events.
             */
            when (header["X-GitHub-Event"]) {
                "commit_comment" -> eventBus.post(mapper.readValue(body, CommitCommentEvent::class.java))
                "create" -> eventBus.post(mapper.readValue(body, Create::class.java))
                "delete" -> eventBus.post(mapper.readValue(body, Delete::class.java))
            }

            return mapOf("success" to true)
        } else {
            return mapOf("success" to false)
        }
    }
}