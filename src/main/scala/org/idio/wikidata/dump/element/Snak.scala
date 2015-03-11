package org.idio.wikidata.dump.element

import scala.util.{Success, Try}

import org.json4s._
import org.json4s.jackson.JsonMethods._


class Snak(json: JValue){

  lazy val dataType = {
    Try((json \ "mainsnak" \ "datatype" ).asInstanceOf[JString].s) match{
      case Success(s) => Some(s)
      case _ => None
    }
  }

  lazy val argumentId = {
    Try((json \ "mainsnak" \ "datavalue" \ "value" \ "numeric-id" ).asInstanceOf[JInt].num) match{
      case Success(s) => Some("Q"+ s.toString())
      case _ => None
    }
  }

}
