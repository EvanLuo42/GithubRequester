package cn.phakel.githubrequester.event

import cn.phakel.githubrequester.bean.*

class CommitCommentEvent(action: String,
                         sender: User,
                         repository: Repository,
                         organization: Organization,
                         val comment: Comment, installation: Installation
): Event(action, sender, repository, organization, installation)