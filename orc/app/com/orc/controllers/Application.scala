package com.orc.controllers

import javax.inject.Inject

import akka.actor.{ActorSystem, Props}
import akka.stream.Materializer
import com.commons.messages.{InEvent, OutEvent}
import com.orc.actors.ClientConnection
import play.api.Logger
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc.{AbstractController, ControllerComponents, WebSocket}

import scala.concurrent.ExecutionContext

class Application @Inject()(cc: ControllerComponents,
                            clientConnectionFactory: ClientConnection.Factory)
                           (implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext)
  extends AbstractController(cc) {

  implicit val messageFlowTransformer = MessageFlowTransformer.jsonMessageFlowTransformer[InEvent, OutEvent]

  /**
    * establish web socket connection with a token parameter
    *
    * @return
    */

  def socket(username: String) = WebSocket.accept[InEvent, OutEvent] {
    implicit request =>

      Logger.info(s"socket connexion for user: $username")

      ActorFlow.actorRef { upstream =>
        Props(clientConnectionFactory(username, upstream))
      }

  }


}