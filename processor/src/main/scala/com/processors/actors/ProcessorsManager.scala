package com.processors.actors

import javax.inject.Inject

import akka.actor.{Actor, ActorLogging}
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.{Subscribe, SubscribeAck}
import com.commons.actors.names.Topics
import com.commons.messages.StartProcessor
import play.api.libs.concurrent.InjectedActorSupport

/**
  * https://www.playframework.com/documentation/2.5.x/ScalaAkka#Dependency-injecting-child-actors
  *
  * @param factory
  */
class ProcessorsManager @Inject()(factory: Processor.Factory) extends Actor with InjectedActorSupport with ActorLogging {

  lazy val mediator = DistributedPubSub(context.system).mediator


  override def preStart() = {
    log.debug(" ProcessorsManager started")

    // subscribe to the topic named "ProcessorsInBox"
    mediator ! Subscribe(Topics.ProcessorsInBox, self)
  }

  def receive = {


    case StartProcessor(task) =>

      context.child(task) match {
        case Some(_) => {
          log.info(s"sorry, the task $task is already started!")
        }

        case None =>
          log.debug(s"preparing task $task")

          //we will use the task as unique task for actor processor
          injectedChild(factory(task), task)

      }

    case SubscribeAck(Subscribe(Topics.ProcessorsInBox, None, `self`)) ⇒
      log.info("subscribing")

    case other =>
      log.error(s"unhandled message: ${other.getClass.getCanonicalName}  from ${sender.path}")

  }
}

