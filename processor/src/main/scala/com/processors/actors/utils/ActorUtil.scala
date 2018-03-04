package com.processors.actors.utils

import akka.actor.ActorRef
import com.google.inject.name.Names
import com.google.inject.{Injector, Key}

import scala.util.Try

object ActorUtil {

  implicit class ActorInjector(injector: Injector) {
    def getActor(name: String): Try[ActorRef] = Try {
      injector.getInstance(Key.get(classOf[ActorRef], Names.named(name)))
    }
  }

}
