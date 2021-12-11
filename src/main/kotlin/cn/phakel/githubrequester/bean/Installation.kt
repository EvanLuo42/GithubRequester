package cn.phakel.githubrequester.bean

class Installation(
    val access_tokens_url: String,
    val account: User,
    val app_id: Int,
    val created_at: Int,
    val events: List<String>,
    val html_url: String,
    val id: Int,
    val permissions: Permissions,
    val repositories_url: String,
    val repository_selection: String,
    val single_file_name: String,
    val target_id: Int,
    val target_type: String,
    val updated_at: Int
)

class Permissions(
    val contents: String,
    val issues: String,
    val metadata: String
)