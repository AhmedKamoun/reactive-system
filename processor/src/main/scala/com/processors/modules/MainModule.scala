package com.processors.modules

import javax.inject.Singleton

import akka.actor.ActorSystem
import com.commons.actors.names.ProcessorsManager
import com.google.inject.{AbstractModule, Injector, Provides}
import com.processors.actors.{Processor, ProcessorsManager}
import com.typesafe.config.ConfigFactory
import play.api.inject.guice.GuiceInjector
import play.api.libs.concurrent.AkkaGuiceSupport
import play.api.{Configuration, inject}

class MainModule extends AbstractModule with AkkaGuiceSupport {


  override def configure(): Unit = {

    // load config from application.conf
    val config = ConfigFactory.load
    val configuration = Configuration(config)
    bind(classOf[Configuration]).toInstance(configuration)

    bind(classOf[ActorSystem]).toInstance(ActorSystem(configuration.get[String]("akka.actor-system"), config))

    bind(classOf[inject.Injector]).to(classOf[GuiceInjector])

    bindActorFactory[Processor, Processor.Factory]

    bindActor[ProcessorsManager](ProcessorsManager.NAME)


  }


  @Provides
  @Singleton
  def playInjector(injector: Injector): GuiceInjector = new GuiceInjector(injector)
}
