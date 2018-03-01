package com.orc.actors

import javax.inject.Inject

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem}
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.{Publish, Subscribe, SubscribeAck}
import com.commons.actors.names.Topics
import com.commons.messages._
import com.google.inject.assistedinject.Assisted


class ClientConnection @Inject()(system: ActorSystem, @Assisted username: String, @Assisted upstream: ActorRef) extends Actor with ActorLogging {

  val mediator = DistributedPubSub(context.system).mediator


  override def preStart() = {
    upstream ! Welcome(s"Hi $username!")

    // subscribe to the topic named "general"
    mediator ! Subscribe(Topics.GENERAL, self)
  }

  def receive = {

    case in: InEvent =>
      log.info(s"receiving in-event $in , forward to processor..")
      mediator ! Publish(Topics.ProcessorsInBox, in)

    case out: OutEvent =>
      log.info(s"receiving out-event $out , forward to client web socket...")
      upstream ! out

    case SubscribeAck(Subscribe(Topics.GENERAL, None, `self`)) ⇒
      log.info("subscribing")

    case other =>
      log.error(s"unhandled message: ${other.getClass.getCanonicalName}  from ${sender.path}")

  }
}


object ClientConnection {

  /**
    * The factory interface for creating client connections
    */
  trait Factory {
    def apply(username: String, upstream: ActorRef): Actor
  }

}