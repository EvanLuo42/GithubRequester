package cn.phakel.githubrequester.event

import cn.phakel.githubrequester.bean.Comment
import cn.phakel.githubrequester.bean.Organization
import cn.phakel.githubrequester.bean.Repository
import cn.phakel.githubrequester.bean.User

class CommitCommentEvent(action: String,
                         sender: User,
                         repository: Repository,
                         organization: Organization,
                         val comment: Comment
): Event(action, sender, repository, organization)