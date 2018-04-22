package controllers

import akka.NotUsed
import akka.stream.scaladsl.Source
import javax.inject.{Inject, Singleton}
import models.{Customer, CustomerRepository}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

@Singleton
class CustomerController @Inject()(cc: ControllerComponents,
                                   customerRepository: CustomerRepository) extends AbstractController(cc) {
  implicit val writesCustomer = Json.writes[Customer]

  /**
    *
    * @return chunks of data as stream of json object, this is a reactive loading data from
    *         postgres database. As soon as a collection of rows result is prepared by postgres, it
    *         will pushed to application server to be consumed (by slick stream source/publisher).
    *         If application server is not able to consume data from postgres, back-pressure will be
    *         applied and postgres will retain data for later.
    */
  def customers: Action[AnyContent] = Action { implicit request =>

    val stream: Source[JsValue, NotUsed] =
      Source.fromPublisher(customerRepository.customers)
        .map(item => {
          // simulate a work to serialize message (100ms to serialize a message)
          Thread.sleep(100)
          Json.toJson(item)
        })


    Ok.chunked(stream)


  }

}
