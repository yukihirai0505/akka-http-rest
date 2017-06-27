package models.db

import models.TokenEntity
import utils.DatabaseService

trait TokenEntityTable extends UserEntityTable {

  protected val databaseService: DatabaseService

  import databaseService.driver.api._
  import slick.jdbc.{GetResult => GR}

  class Tokens(_tableTag: Tag) extends Table[TokenEntity](_tableTag, "tokens") {
    def * = (userId, token, Rep.Some(id)) <> (TokenEntity.tupled, TokenEntity.unapply)
    def ? = (Rep.Some(userId), Rep.Some(token), Rep.Some(id)).shaped.<>({r=>import r._; _1.map(_=> TokenEntity.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    val userId: Rep[Long] = column[Long]("user_id")
    val token: Rep[String] = column[String]("token", O.Length(500,varying=true))
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
  }

  lazy val tokens = new TableQuery(tag => new Tokens(tag))

  implicit def GetResultTokensRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]]): GR[TokenEntity] = GR{
    prs => import prs._
      val r = (<<?[Long], <<[Long], <<[String])
      import r._
      TokenEntity.tupled((_2, _3, _1)) // putting AutoInc last
  }
}
