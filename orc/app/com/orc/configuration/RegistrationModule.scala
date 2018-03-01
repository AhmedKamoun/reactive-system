package com.orc.configuration

import com.google.inject.AbstractModule
import com.orc.actors.ClientConnection
import play.api.libs.concurrent.AkkaGuiceSupport

/**
  * Guice module that provides actors.
  *
  * Registered in application.conf.
  */
class RegistrationModule extends AbstractModule with AkkaGuiceSupport {

  def configure() = {
    // Bind the client connection factory
    bindActorFactory[ClientConnection, ClientConnection.Factory]


  }


}



