package com.commons.messages

import play.api.libs.json._

sealed trait OutEvent

object OutEvent {
  implicit val outFormat: Format[OutEvent] = Json.format[OutEvent]
}

case class ProcessingResult(response: String) extends OutEvent

object ProcessingResult {
  implicit val responseFormat: Format[ProcessingResult] = Json.format[ProcessingResult]
}

case class Welcome(response: String) extends OutEvent

object Welcome {
  implicit val welcomeFormat: Format[Welcome] = Json.format[Welcome]
}