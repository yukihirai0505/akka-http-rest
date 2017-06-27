package models

case class UserEntity(username: String, password: String, id: Option[Long] = None) {
  require(!username.isEmpty, "username.empty")
  require(!password.isEmpty, "password.empty")
}

case class UserEntityUpdate(username: Option[String] = None, password: Option[String] = None) {
  def merge(user: UserEntity): UserEntity = {
    UserEntity(username.getOrElse(user.username), password.getOrElse(user.password), user.id)
  }
}