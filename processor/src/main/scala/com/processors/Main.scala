package com.processors

import com.google.inject.Guice
import com.processors.modules.MainModule
import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {

  logger.debug("Main application started ...")
  val injector = Guice.createInjector(new MainModule())


}
