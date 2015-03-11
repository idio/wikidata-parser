package org.idio.wikidata.dump.element

import org.json4s._
import org.json4s.jackson.JsonMethods._

/*
* A Claim in Wikidata represents many relationships via a PropertyType
* i.e:
*
* Entity: Batman
* Claim: P123(citizen_of):
*      argument: Qid_USA
*      argument: Qid_Gotham
*      argument: Qid_earth
*      ....
*
* */

class Claim(jsonClaim: JArray, val relationshipType:String){

  /*
  * Get all the main Snaks within a claim
  * */
  private def getMainSnaks() = {
    val mainSnaks = jsonClaim.arr.flatMap{
      jvalue =>
        val snak = new Snak(jvalue)
        snak.argumentId match {
          case Some(qid) => Some(snak)
          case _ => None
        }
    }

    val validMainSnaks = mainSnaks.flatMap{
      snak =>
        snak.dataType match{
          case Some(dataType) if dataType.equals("wikibase-item") => Some(snak)
          case _=> None
        }
    }
    validMainSnaks
  }

  /*
   Returns all the arguments Qid's part of a mainSnak
   in the example: [Qid_USA, Qid_Gotham, Qid_earth]
  */
  lazy val argumentQids ={
    val mainSnaks = getMainSnaks()
    if (mainSnaks.isEmpty)
       List[String]()
    else
      mainSnaks.flatMap(_.argumentId)
  }

}
