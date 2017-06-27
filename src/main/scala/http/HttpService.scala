package http

import akka.http.scaladsl.server.Directives._
import http.routes.{AuthServiceRoute, UsersServiceRoute}
import services.{AuthService, UsersService}
import utils.CorsSupport

import scala.concurrent.ExecutionContext

class HttpService(usersService: UsersService,
                  authService: AuthService
                 )(implicit executionContext: ExecutionContext) extends CorsSupport {

  val usersRouter = new UsersServiceRoute(authService, usersService)
  val authRouter = new AuthServiceRoute(authService)

  val routes =
    pathPrefix("v1") {
      corsHandler {
        usersRouter.route ~
        authRouter.route
      }
    }

}
