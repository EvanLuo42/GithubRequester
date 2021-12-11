package cn.phakel.githubrequester.event.repository

import cn.phakel.githubrequester.bean.Installation
import cn.phakel.githubrequester.bean.Organization
import cn.phakel.githubrequester.bean.Repository
import cn.phakel.githubrequester.bean.User
import cn.phakel.githubrequester.event.Event

class Fork(action: String,
           sender: User,
           repository: Repository,
           organization: Organization,
           installation: Installation,
           val forkee: Repository
): Event(action, sender, repository, organization, installation)