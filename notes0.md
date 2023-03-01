
- Users
- Todo lists
- Tasks

In the database we are going to have 4 tables

Users -< Participate >- TodoLists -< Tasks

Project 1: Users

Project 2: Todo lists

    H2 database in memory.. for development purposes
    MariaDB
    Oracle
    MySQL
    DB2
----

Language -< Terms (words) -< Meanings

Website

Back-end:

The back end of this app has to provide methods for:
- Does a word exists in certain language?
- What are the meanings of THIS words?
- Could you suggest any words similar to THIS?

WEB SERVICES

URL: http://midictapp:8080/endpoint
                          /api/v1/endpoint


WEBSERVICE: http://midictapp:8080/api/v1/languages
This program is NOT going to return HTML, but JSON

In http protocol we have what we call METHODS: POST, PUT, GET, DELETE
An http method is just a metadata that is send within the request.

URL                                     METHOD
http://midictapp:8080/api/v1/languages  GET     We will return a list of the 
                                                languages with a defined diccionary

                                                ["spanish", "english", "arabic"]

http://midictapp:8080/api/v1/terms/{english}/{bottle}   GET

                                                {
                                                    "exists": true,
                                                    "meanings": [
                                                        "first meaning",
                                                        "second meaning"
                                                    ]
                                                }


http://midictapp:8080/api/v1/terms/{english}/{briak}   GET

                                                {
                                                    "exists": false,
                                                    "suggestions": [
                                                        { 
                                                            "term": "break",
                                                            "difference": 1
                                                        },
                                                        { 
                                                            "term": "bread",
                                                            "difference": 2
                                                        },
                                                        { 
                                                            "term": "brick",
                                                            "difference": 1
                                                        }
                                                    ]
                                                }


---

We may 2 different User Interaces (Frontends)
- Console App (from the terminal)
  $ dictionaryapp english briak

    The word "briak" does not exist in "english".
    Maybe you wanted to say: "break", "bread", or "brick"

- Web app (Angular)

---
Language > DB
    String name
Term     > DB
    String name
Meaning  > DB
    String name
Suggestion (Are calculated)
    String 

---
Repository (JPA)
LanguageRepository
TermRepository
Meaning Repository
---
Controller .    DEPENDENCY INYECTION
    In this class is where we are goig to define the ENDPOINTs:

    - /api/v1/languages
                        GET -> define a JAVA method to process the request 
            public HttpResult<List<String>> getLanguages(){

            }

            In our case, Spring is going to transform the List automatically to JSON
            In addition, Spring is going to help us associate the ENDPOINT to the function.

    - /api/v1/terms/{language}/{term}
                        GET -> define a JAVA method to process the request 
            public HttpResult<TermResponse> getTerm(String language, String term){

            }

            What is a Term Response... We have to define that.
            Automatically, Spring is going to transform that object to JSON
---
class TermResponse:
    boolean exists;
    List<String> meanings;
    List<Suggestion> suggestions;

class Utils
    List<Suggestion> calculateSuggestions(String term)

    Here inside, we will make use of the Levenstein Algorythm

--- 
In the training we will be making use of MAVEN

What is MAVEN?
This is a software which is going to help with:
- Dependencies: (kind of the same as npm in js)
  This is going to download all the dependencies of my project from the Internet
- In addition maven is going to:
  - Compile my code
  - Execute unitary tests
  - Package my app (generate .war, .ear, .jar)

Every single maven project requires a pom.xml configuration file.

In addition, maven requires a fixed (not that fixed... we can customize that... but we NEVER do that)folder structure

PROJECT
 |-src
 |  |-main                  My program
 |  |   |-java              My classes
 |  |   |-resources         Configuration files, images...
 |  |
 |  |-test
 |      |-java              My unit test classes
 |      |-resources         Configuration files, images... required by my tests
 |
 |-pom.xml                  Project configuration
                                Java version
                                Project Id
                                Dependencies
                                    Springboot
                                    Junit
                                    H2 in memory database -> MySQL
                                Packaging type (jar, war, ...)

MAVEN GOALS: Those are the tasks that we can ask maven for.
- compile       Compiles the files (classes and interfaces) in my src/main/java folder
- test-compile  Compiles the files (classes and interfaces) in my src/test/java folder
- test          Executes the compiled test files
- package       Packages the applicacion (generates the jar, war... ear file)


----
Test types:
- Static Tests: Are tests that doesn't require my program to be executed
                Quality test: SONARQUBE 
                This is what time ago was done by a Senior Developer
            Database structure review
- Dynamic Tests: Are tests that require my program to be executed
  - Functional Tests:       What to check how the app behaves
      - *** DEVELOPERS ^^
  - Non functional tests:   Other things
    - Performance tests
    - Stress tests
    - High Availability test

---
Test levels:
- Unit testing          Test an isolated component of the application
- Integration testing   Test the communication between components
- End2End testing       Test the behaviour of the whole app

I can test if every single function of my code returns what is supposed to be returned

Nowadays, we have a bunch of Frameworks that help with UNIT tests: 
- JUnit *******
- TestNG


Dependency invertion
Dependency inyection
Control inversion
