# Top-n email domains

## Description
Write   a   program   in   a   language   of   your   choice   that   given   standard   input   with   one   email   address   per   line. eg.
```
joeblogs@hotmail.com
andrew.smith@gmail.com       
...
```
outputs   the   10   domains   (or   less   if   there   aren't   that   many)   that   appear   the   most   often   with   a   count   of   the number   of   times   it   appears   after   each   domain.   eg.
```
hotmail.com   2553
yahoo.com   1315
and   up   to   8   more   lines.
```
## How to start

### Via console
You can simply run the project via console
```
cd path/to/src/main/java
javac com/kahoot/Main.java
java com/kahoot/Main "domain@aaa.de\ndomain@aaa.de\ndomain@aaa.de\ndomain@bbb.de"
```
### Proove functionality via tests

There are four unittests that are covering the code:
 1. Testing the happy path with a list of more then 10 unique domains
 2. Testing the happy path with a list of less then 10 unique domains
 3. Testing with a null String
 4. Testing with a malformed list of no emails
  
You can simply run the tests via gradle
```
./gradlew clean build
```

## F.A.Q

### Why gradle?
Gradle is used for the comfortable use of JUnit5. It adds not other dependencies to the project