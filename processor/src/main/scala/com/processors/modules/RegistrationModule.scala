package com.processors.modules

import com.commons.actors.names.ProcessorsManager
import com.google.inject.AbstractModule
import com.processors.actors.{Processor, ProcessorsManager}
import play.api.libs.concurrent.AkkaGuiceSupport

class RegistrationModule extends AbstractModule with AkkaGuiceSupport {


  override def configure(): Unit = {

    // Bind the client connection factory
    bindActorFactory[Processor, Processor.Factory]
    bindActor[ProcessorsManager](ProcessorsManager.NAME)

  }


}
