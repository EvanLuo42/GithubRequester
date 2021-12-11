package cn.phakel.githubrequester.bean

import com.fasterxml.jackson.annotation.JsonProperty

class Comment(
    @JsonProperty("author_association")
    val authorAssociation: String,
    val body: String,
    @JsonProperty("commit_id")
    val commitId: String,
    @JsonProperty("created_at")
    val createdAt: String,
    @JsonProperty("html_url")
    htmlUrl: String,
    val id: Int,
    val line: Any,
    @JsonProperty("node_id")
    val nodeId: String,
    val path: Any,
    val user: User,
    val position: Any,
    @JsonProperty("update_at")
    val updatedAt: String,
    val url: String
)