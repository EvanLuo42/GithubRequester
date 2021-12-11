package cn.phakel.githubrequester.event.repository.branch

import cn.phakel.githubrequester.bean.Installation
import cn.phakel.githubrequester.bean.Organization
import cn.phakel.githubrequester.bean.Repository
import cn.phakel.githubrequester.bean.User
import cn.phakel.githubrequester.event.Event
import com.fasterxml.jackson.annotation.JsonProperty

class Delete(action: String,
             sender: User,
             repository: Repository,
             organization: Organization,
             installation: Installation,
             val ref: String,
             @JsonProperty("ref_type")
             val refType: String,
             @JsonProperty("pusher_type")
             val pusherType: String
): Event(action, sender, repository, organization, installation)