# Wikidata Parser

## Parser

Parsing one line (Wikidata Item & it's Claims) from the wikidata dump could be done using `DumpElement`

#### Parsing line from Dump
Example:

```scala

import org.idio.wikidata.dump.element._

val element = DumpElement.parseElement(dumpLine)

element match{
  case e:Item  = > println("It is a topic")
  case e:Property => println("It is a relationship type")
}
``` 

#### Playing with Topics

```scala
import org.idio.wikidata.dump.element._
val topic = DumpElement.parseElement(dumpLine).asInstanceOf[Item]

topic.getId() // the Qid of the topic
topic.getAliases("en") // aliases of the topic for english
topic.getRelationshipTuples // list of relationship tuples i.e (P130, Q123),(P30, Q01)....
```

- **getRelationshipTuples**: be aware it only extracts relationships out of main snaks where the argument of the relationship is another entity.

## Extractor

It is an utility to  extract relationships out of the wikidata dump for a given set of Qids.

- Input: 
	- Wikidata Dump
	- tsv containing list of Qids:
		- tsv must container a header
		- `qid` should be the first field

- Output: 
    - One triple per line : ( `startTopic`, `endTopic`, `relationshipType` )
    - `startTopic` and `endTopic` are referenced as lineIds within the given Qids map i.e: if the qid: `Q123` is in the line 10, then it will be referenced in the output as topic `10`
    - Relationships connecting topics which are not in the given list of Qids are ignored.
    - Relationship types are output as: `relationship label_Pid`

usage:
- 


## Compiling

- `sbt assembly`


