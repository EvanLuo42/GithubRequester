package cn.phakel.githubrequester.event

import cn.phakel.githubrequester.bean.Installation
import cn.phakel.githubrequester.bean.Organization
import cn.phakel.githubrequester.bean.Repository
import cn.phakel.githubrequester.bean.User

open class Event(
    val action: String,
    val sender: User,
    val repository: Repository,
    val organization: Organization,
    val installation: Installation)