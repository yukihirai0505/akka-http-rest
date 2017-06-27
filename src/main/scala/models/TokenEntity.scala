package models

import java.util.UUID

case class TokenEntity(userId: Long, token: String = UUID.randomUUID().toString.replaceAll("-", ""), id: Option[Long] = None)
