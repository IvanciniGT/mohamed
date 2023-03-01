JavaScript is a programming language.
This started with a diferent name. 
It was called Mocha. This was created in 10 days.
It was created by Netscape (it was a web browser)

Time after that the ECMA (European Consortium of MAnufacturers)defined a standard, to manage how this language (Javascript) should evolve.

Javascript's actual name nowadays is ECMAScript.
- ES7
- ES6
- ES8

Javascript and Java are completly different programming languages.
Java was developed by Sun Microsystems.
That company bougth Netscape. They decided to change the name of Mocha to JAVAScript to promote Java.

# Node?

Javascript is an interpreted programming language, so... it requires an INTERPRETER to be installed wherever we ant to execute a JS program.

Time ago, web browsers came with a JS interpreter inside.
There is an opensource web browser called CHROMIUM.
This project is mantained by GOOGLE.
The code of CHROMIUM is used nowadays as the core of:
- CHROME
- EDGE
- SAFARI
- OPERA
Only firefox has its own core.

In that Code (inside chromium) there was a JS Interpreter, so that all those web browsers can execute JS programs.

GOOGLE decided to extract the JS Interpreter inside CHROMIUM and ship it as an standalone software project: NODEJS

NODEJS is the same concept as the JAVA VIRTUAL MACHINE.
To execute a JAVA program we need a JVM  (as JAVA is an interpreted programming language)
To execute a JAVASCRIPT program we need a JS INTERPRETER (as JS is an interpreted programming language):
- If we want to execute a JS program inside a web browser, each web browser comes with a bundled JS Interpreter
- But nowadays we can execute JS Programs outside a web browser, thank to NODEJS, which is a JS Interpreter that doesn't requires a web browser.

In order to EXECUTE a web frontend created in JS we don't require and we don't use NODE... as each web comes with a bundled JS Interpreter.

When we create a JS frontend, we need to store that JS program inside a WEB SERVER.

Browser is going to download the JS from the WEB SERVER.

When we DEVELOP a web frontend, we need a development web server, so we can upload the JS programs that we are creating... so that I can access them from my Web Browser.

Usually NODEJS is used in development environments to produce a development web server to upload the JS programs being created... so we can test them from a Web browser.

A different thing is that we want to CREATE A BACKEND is JS.
In that case, we need a PRODUCTION WEB SERVER. We can use NODEJS for creating that web server... and use JAVASCRIPT to program my backend. JS programs which will be running inside NODEJS in the server.


# Angular

Is a JS Framework to produce WEB Frontends.
It uses node to create a development web server.
BUT once the JS code is created... we just need to package the project as a JS file and copy (upload) that file to any web server (Apache, Weblogic, Websphere, Nginx, it doesn't matter)... even NodeJS+express.

----

Programming languages can be classified by different taxonomies.
Such as:
- Interpreted vs compiled
- Static typed vs dynamic typed programming languages
- By its paradigmns

Programs are executed by the Operating System.
But the no Operating System knows about JAVA or PYTHON nor JS

We create programs using a programming language.
But that program (writen in that programming language)
needs to be translated to the OS native language.

In order to do that we use 2 different strategies:
- Compilation: 
  This is a static translation of the code

    Code in c language -> compile -> get a executable binary file

- Interpretation
  In this case the translation is done in real time.

Example:
  I am writing a Paella recipe... and I want to send that to you
  so, you can "execute" the paella when you get back to Egypt.

  The problem is that you don't know spanish.

    We have 2 options:
    1. I will translate the recipe before actually giving it to you. Maybe I'm going to use google translator. 
        COMPILATION
        Problem with compilation: I I want to send that same recipe to a friend that I have in China... I need to 
        translate the recipe also to chinese. ANOTHER COMPILATION
        So now I have:
        - the original recipe (in spanish)
        - a compiled version in arabic
        - a compiled version in chinese
        For me... maybe that is too much work... To manage all those different versions of the recipe
    2. I will send to you the recipe in Spanish... Now it is your problem ... not mine !
        Now I don't have to main all those versions... Just 1
        But ... you don't know spanish... nor my chinese friend

        Both of you can get an INTERPRETER so that whenever you want to "execute" the recipe, that guy can transte on demand the recipe.

    For example python is an interpreted programming language

---
Static typed vs dynamic typed programming languages

# Variable?

Depending on the programming language you are using, a var is a different thing. It could be:
- A place in our RAM memory where we stote data.
- A reference to an object we have in the computer's RAM memory

What is a var in JAVA and in JAVASCRIPT? 

A var is A reference to an object we have in the computer's RAM memory

```js
var text = "Hi!"; // This is what we call A STATEMENT

text = "Bye";
```
How many parts this statement has? 3
In which order are they executed:
1. "Hi!"      This stores a new STRING OBJECT in memory
2. var text   This creates a var called text
3. =          text points to "Hi!"
              "Hi!" is NOT stored into text

---
1. "Bye!"   This stores a new STRING OBJECT in a different
            place in memory.
            At this time we have 2 different things in memory:
            "Hi!" and "Bye"
2. text =   This moves the var text to point to "Bye"


Each prommaning language allows to work with different DATA TYPES.

For example, in JS:
 3         ->          number
 3.9       ->          number
 "hi"      ->          string
 []        ->          array
 {}        ->          object (In python we call this a dictionary)

For example, in JAVA:
 3         ->          int
 "hi"      ->          string
 3.9       ->          double
 f8.3      ->          float
 Array
 Map

Depending on the programming language, vars are also typed.
That means vars are assigned to a data type.

```java
// JAVA is a static typed language.
// That means vars are defined with a datatype
// And therefore they can only POINT to values of that type
int number = 3;
number = "HELLO"; // This line fails in JAVA!
As the VAR was defined as INT
```

In Dynamic typed languages (such as JAVASCRIPT) vars don't have a DATATYPE

```js
var number = 3; 
number = "Bye"; // It works... as JS vars don't have a preassigned type
number = true;
```

That is a good or a bad thing?

It depends.... 
For small projects, dynamic typed languages are more confortable.
In big projects, we cannot use dynamic typed languages... PERIOD

Imagine I define a function:

```js
prepareReport(title, pdf, file, data)
```
What sould I supply to this function if I want to call it?
I need to supply:
- title. What is this? STRING?
          Maybe is just a boolean saying wether or not we want a title
In other word... I have NO IDEA AT ALL!!!!!!
That is a problem... specially if 'm using someone else function.

```java
void prepareReport(String title, boolean pdf, Path file, Map<String, String> data)
// So now I knows this function does not return nothing
```
No I know how to talk / call that function.
I have no problems !

In JS I have a huge problem !

In addition.... if I have a static typed language, such as JAVA,
when I compile a project (because JAVA is an interpreted language but also a compiled language), the compiler can check that all the calls are suppling the adecuate data types.

In JS we have NO datatypes... and we have NO COMPILATION....
That is NOT GOOD AT ALL !
Only for small projects... like REALLY SMALL PROJECTS.

So... what they did in the JS world to solve this issue?
To create a new LANGUAGE on top of JS -> TYPESCRIPT
That is called TYPEscript because is a STRONGLY TYPED programming language, such as JAVA.

BUT in JS we don't compile the code....

Well. They also created a new term: "transpile".
Transpilation is the process to convert from one language to another. Such as from TYPESCRIPT to traditional JAVASCRIPT (which is what is actually being executed inside a browser).

During that transpilation, Datatypes are gonna be checked!
Same as in JAVA while we compile the code.

Angular decided to use TYPESCRIPT as language and forget about JAVASCRIPT, because JS is a dynamic typed programming language!


Imagine you are at your Ministry developing some apps.

- You have to create an app to manage citizen requests
- But also you are creating a second app to manage your partner holidays

Probably in both apps you will need to expose your partners information:
- In the first app, maybe a request is managed by a person.
  And in the screen you may want to show its information: email, pic, name, phone
- But in the second one (holidays) it will be the same.
  A holidays request needs to be approved by someone... 
  and maybe in the sreen you also want to show that person inform,
  Exactly the same information.

It would be great to have a <user id="182736743"> html tag that you could use in both projects.

So that this tag, renders to a div which includes the pic, name, email... all the person infromation.

You want a reusable WEB COMPONENT. And you can do that with Angular.

If each component has its own CSS (collection of styles) you just need to include that component... (import the module where that component is defined). Styles will be automatically imported.
You don't need to include styles (the same styles) in both apps.
We want the styles to be associated (included) within the web component.

