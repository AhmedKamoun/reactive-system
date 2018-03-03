package com.processors.actors

import javax.inject.Inject

import akka.actor.{Actor, ActorLogging}
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.Publish
import com.commons.actors.names.Topics
import com.commons.messages.ProcessingResult
import com.google.inject.assistedinject.Assisted

object Processor {

  trait Factory {
    def apply(task: String): Actor
  }

}

/**
  * Represents processor
  *
  * @param task The unique task identifier
  */
class Processor @Inject()(@Assisted task: String) extends Actor with ActorLogging {

  // activate the extension
  val mediator = DistributedPubSub(context.system).mediator


  override def preStart() = {
    log.info(s"starting the task of $task ...")

    //publish to general topic
    mediator ! Publish(Topics.GENERAL, ProcessingResult(s"task: $task is started..."))
  }


  def receive = {

    case other =>
      log.error(s"unhandled message: ${other.getClass.getCanonicalName}  from ${sender.path}")


  }
}

