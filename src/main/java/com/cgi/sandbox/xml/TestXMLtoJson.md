# JSON-java

[https://github.com/stleary/JSON-java](https://github.com/stleary/JSON-java "https://github.com/stleary/JSON-java")

Maintenu par Sean Leary (Cisco Systems, Inc)

> A reference implementation of a JSON package in Java.
> 
> XML.java: XML provides support for converting between JSON and XML.

- 187 commits
- 6 branches
- 3 releases
- 15 contributors
- Latest commit 6 days ago

## Remarques

- 2 lignes de codes pour la conversion XML -> XML (String -> String)
- page de tu : [https://github.com/stleary/JSON-Java-unit-test](https://github.com/stleary/JSON-Java-unit-test "https://github.com/stleary/JSON-Java-unit-test")
- taille 49ko

## Dep

	json-20160212.jar -> C:\apps\jdk-1.8\jre\lib\rt.jar
	   org.json (json-20160212.jar)
	      -> java.io
	      -> java.lang
	      -> java.lang.reflect
	      -> java.math
	      -> java.util


## Maven

	<dependency>
	    <groupId>org.json</groupId>
	    <artifactId>json</artifactId>
	    <version>20160212</version>
	</dependency>

## Test

	<?xml version="1.0"?>
	<customer>
	    <first-name>Jane</first-name>
	    <last-name>Doe</last-name>
	    <address>
	        <street>123 A Street</street>
	    </address>
	    <phone-number type="work">555-1111</phone-number>
	    <phone-number type="cell">555-2222</phone-number>
	</customer>

## Result

	{
		"customer": {
			"phone-number": [{
				"type": "work",
				"content": "555-1111"
			}, {
				"type": "cell",
				"content": "555-2222"
			}],
			"address": {
				"street": "123 A Street"
			},
			"first-name": "Jane",
			"last-name": "Doe"
		}
	}

# Staxon

[https://github.com/beckchr/staxon/](https://github.com/beckchr/staxon/ "https://github.com/beckchr/staxon/")

Maintenu par Christoph Beck (?)

> using the Java Streaming API for XML (javax.xml.stream)
> 
> mapping convention [http://www.sklar.com/badgerfish/](http://www.sklar.com/badgerfish/ "http://www.sklar.com/badgerfish/")

- 346 commits
- 1 branch
- 22 releases
- 2 contributors
- Latest commit on 15 Oct 2015

## Remarques

- bench : [https://github.com/beckchr/staxon/wiki/Benchmark](https://github.com/beckchr/staxon/wiki/Benchmark "https://github.com/beckchr/staxon/wiki/Benchmark")
- Semble plus complet mais plus compliqué : Support for JAXB and JAX-RS, Full XML namespace support, Support the JSON Processing API (JSR-353), Jackson
- doc [https://github.com/beckchr/staxon/wiki/](https://github.com/beckchr/staxon/wiki/ "https://github.com/beckchr/staxon/wiki/")
- pas fan de la convention badgerfish ... (des @  pour les attributs XML et $ pour les contenus ...)
- taille 147ko

## Dep

	staxon-1.3.jar -> C:\apps\jdk-1.8\jre\lib\rt.jar
	staxon-1.3.jar -> staxon-1.3.jar
	   de.odysseus.staxon.base (staxon-1.3.jar)
	      -> java.io
	      -> java.lang
	      -> java.net
	      -> java.util
	      -> javax.xml.namespace
	      -> javax.xml.stream
	      -> javax.xml.stream.util
	      -> javax.xml.transform
	      -> javax.xml.transform.stream
	   de.odysseus.staxon.event (staxon-1.3.jar)
	      -> java.io
	      -> java.lang
	      -> java.util
	      -> javax.xml.namespace
	      -> javax.xml.stream
	      -> javax.xml.stream.events
	      -> javax.xml.stream.util
	   de.odysseus.staxon.json (staxon-1.3.jar)
	      -> de.odysseus.staxon.base                            staxon-1.3.jar
	      -> de.odysseus.staxon.event                           staxon-1.3.jar
	      -> de.odysseus.staxon.json.stream                     staxon-1.3.jar
	      -> de.odysseus.staxon.json.stream.util                staxon-1.3.jar
	      -> java.io
	      -> java.lang
	      -> java.util
	      -> javax.xml.namespace
	      -> javax.xml.stream
	      -> javax.xml.stream.util
	   de.odysseus.staxon.json.jaxb (staxon-1.3.jar)
	      -> de.odysseus.staxon.json                            staxon-1.3.jar
	      -> de.odysseus.staxon.json.util                       staxon-1.3.jar
	      -> java.io
	      -> java.lang
	      -> java.lang.annotation
	      -> java.lang.reflect
	      -> java.nio.charset
	      -> java.util
	      -> javax.xml.bind
	      -> javax.xml.bind.annotation
	      -> javax.xml.namespace
	      -> javax.xml.stream
	   de.odysseus.staxon.json.stream (staxon-1.3.jar)
	      -> java.io
	      -> java.lang
	      -> java.util
	      -> javax.xml.stream
	   de.odysseus.staxon.json.stream.impl (staxon-1.3.jar)
	      -> de.odysseus.staxon.json.stream                     staxon-1.3.jar
	      -> java.io
	      -> java.lang
	      -> java.math
	   de.odysseus.staxon.json.stream.util (staxon-1.3.jar)
	      -> de.odysseus.staxon.json.stream                     staxon-1.3.jar
	      -> java.io
	      -> java.lang
	      -> java.math
	      -> java.util
	      -> java.util.regex
	      -> javax.xml.namespace
	   de.odysseus.staxon.json.util (staxon-1.3.jar)
	      -> de.odysseus.staxon.event                           staxon-1.3.jar
	      -> de.odysseus.staxon.util                            staxon-1.3.jar
	      -> java.lang
	      -> java.util
	      -> java.util.regex
	      -> javax.xml.namespace
	      -> javax.xml.stream
	      -> javax.xml.stream.events
	   de.odysseus.staxon.util (staxon-1.3.jar)
	      -> java.lang
	      -> java.util
	      -> javax.xml.namespace
	      -> javax.xml.parsers
	      -> javax.xml.stream
	      -> javax.xml.stream.events
	      -> javax.xml.stream.util
	      -> org.w3c.dom
	   de.odysseus.staxon.xml.util (staxon-1.3.jar)
	      -> de.odysseus.staxon.event                           staxon-1.3.jar
	      -> de.odysseus.staxon.util                            staxon-1.3.jar
	      -> java.lang
	      -> javax.xml.stream
	      -> javax.xml.stream.events


## Maven

	<dependency>
	    <groupId>de.odysseus.staxon</groupId>
	    <artifactId>staxon</artifactId>
	    <version>1.3</version>
    </dependency>

## Test

	<?xml version="1.0"?>
	<customer>
	    <first-name>Jane</first-name>
	    <last-name>Doe</last-name>
	    <address>
	        <street>123 A Street</street>
	    </address>
	    <phone-number type="work">555-1111</phone-number>
	    <phone-number type="cell">555-2222</phone-number>
	</customer>

## Result

	{
		"customer": {
			"first-name": "Jane",
			"last-name": "Doe",
			"address": {
				"street": "123 A Street"
			},
			"phone-number": [{
				"@type": "work",
				"$": "555-1111"
			}, {
				"@type": "cell",
				"$": "555-2222"
			}]
		}
	}