package com.commons.messages

import play.api.libs.json._

sealed trait InEvent

object InEvent {
  implicit lazy val inEventFormat: Format[InEvent] = Json.format[InEvent]
}

// SUB CLASS OF InEvent

/**
  *
  * @param task identifier
  */
case class StartProcessor(task: String) extends InEvent

object StartProcessor {
  implicit lazy val startProcessorFormat: Format[StartProcessor] = Json.format[StartProcessor]
}

