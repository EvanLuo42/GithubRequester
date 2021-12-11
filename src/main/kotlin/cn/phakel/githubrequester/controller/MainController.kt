package cn.phakel.githubrequester.controller

import cn.phakel.githubrequester.GithubRequesterApplication
import cn.phakel.githubrequester.event.CommitCommentEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@RestController
class MainController {

    @Autowired
    private lateinit var server: GithubRequesterApplication

    @RequestMapping("/")
    fun welcome() = "Welcome to GithubRequester Webhook API."

    @RequestMapping("/webhook", method = [RequestMethod.POST])
    fun webhook(@RequestBody body: String, @RequestHeader header: Map<String, String>): Map<String, Boolean> {
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(server.getSecret().toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKey)
        val signature = "sha256=" + sha256Hmac.doFinal(body.toByteArray())

        if (signature == header["HTTP_X_HUB_SIGNATURE_256"]) {
            val mapper = ObjectMapper()

            when (header["X-GitHub-Event"]) {
                "commit_comment" -> server.getEventBus().post(mapper.readValue(body, CommitCommentEvent::class.java))
            }

            return mapOf("success" to true)
        } else {
            return mapOf("success" to false)
        }
    }
}