package com.processors.modules

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import akka.cluster.client.ClusterClientReceptionist
import com.commons.actors.names.ProcessorsManager
import com.google.inject.AbstractModule
import com.processors.actors._
import play.api.libs.concurrent.AkkaGuiceSupport

class RegistrationModule extends AbstractModule with AkkaGuiceSupport {


  override def configure(): Unit = {


    // Bind the client connection factory
    bindActorFactory[Processor, Processor.Factory]
    bindActor[ProcessorsManager](ProcessorsManager.NAME)

    // Bind the cluster receptionist actors as an eager singleton
    bind(classOf[BackendClusterReceptionist]).asEagerSingleton()
  }


}

/**
  * Manages the creation of actors in the backend end.
  */
class BackendClusterReceptionist @Inject()(system: ActorSystem,
                                           @Named(ProcessorsManager.NAME) processorManager: ActorRef) {

  ClusterClientReceptionist(system).registerService(processorManager)

}