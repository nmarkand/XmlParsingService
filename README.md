# XmlParsingService

## APPLICATION STARTER - 

#### XmlParsingServiceApplication.java class

## DESIGN PATTERN -

The project is implemented as a micro-service using Springboot 2.
Combination of Creational and Structural design patterns, is used to implement the project. Factory (creational) design pattern (Dependency Inversion - SOLID principle) and Bridge structural design pattern (Composition - Single responsibility SOLID principle) are used to provide object oriented design.

## STRUCTURE -

#### src/main/java - It contains project code.
#### src/main/resource - It contains project resources (Merck_TaskToSolve.docx which is provided), index.html page which is loaded when application is up and running.

#### src/main/test - It contains unit test cases according the project structure.
#### src/test/resources - It contains projcet test resources to perform unit test cases and evaluation of results.

## FUNCTIONALITIES -  
There are two functionalities are implemented -

###  GET - parseXml/byX2ElementXpath
This is the challenge which is required to implemented i.e. to find out the values of nodes with name "x2" and id="a1" that are direct children of nodes with name "x1".

As xml file is already provided which is stored in src/main/resources folder structure to perform xpath parsing.

#### JSON response - 	
	{
		"elementValues": [
		    "a",
		    "c",
		    "h"
		],
		"count": 3
	}

### POST - parseXml/byXpath
Additionally one generic function which can be used to parse xml using xpath. It take a Multipart file and xpath in request object. Please note it was not included in requirement and implemented it just for a demo purpose. 

## IMPLEMENTATION -

Simply Merck_TaskToSolve.docx is loaded from src/main/resources and transformed to Dom Document. The compiled xpath "/root/x1/x2[@id='a1']" (XPathExpression) evaluate Dom Document to get NodeList. The NodeList is further transformed to List of element's values using Java 8 stream api.

## USER INTERFACE

index.html file is available under src/main/resources/static.
As soon as the application is up and running, it is automatically gets loaded.
Run http://localhost:8080 (I have default port 8080).

Click on button and you will get expected result.  

![Alt text](/src/test/resources/UserInterface1.jpg?raw=true "UserInterface1")

![Alt text](/src/test/resources/UserInterface2.jpg?raw=true "UserInterface2")

## TESTING - 

## Unit testing -
#### src/main/test

##### Controller unit tests
For unit testing in controller Mockito is used to provide mock. Unit testing is performed layer by layer (bottom to up) which means, once service layer unit testing is performed,  service layered is mocked, and mock service is used in controller to evaluate desired outcome.

##### Service unit test 
##### Utils unit tests 

# POM.XML

1) Spring boot jpa, starter, tomcat, devtools etc.

# PROJECT BUILD -

Maven used as a build tool.

Do the following -
a) mvn package OR mvn clean install.
It will create XmlParsingService.jar in target folder.

b) java -jar XmlParsingService.jar
Execute it using command prompt. OR a)It is a SpringBoot project and can be simply executed as a java project from XmlParsingServiceApplication.java class.