<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/eo-hamcrest)](http://www.rultor.com/p/objectionary/eo-hamcrest)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Maven Central](https://img.shields.io/maven-central/v/org.eolang/eo-hamcrest.svg)](https://maven-badges.herokuapp.com/maven-central/org.eolang/eo-hamcrest)
[![mvn](https://github.com/objectionary/eo-hamcrest/actions/workflows/mvn.yml/badge.svg)](https://github.com/objectionary/eo-hamcrest/actions/workflows/mvn.yml)
[![codecov](https://codecov.io/gh/cqfn/eo/branch/master/graph/badge.svg)](https://codecov.io/gh/cqfn/eo)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Graur/eo-tests/blob/master/LICENSE.txt)

[![Maintainability](https://api.codeclimate.com/v1/badges/b8b59692f3c8c973ac54/maintainability)](https://codeclimate.com/github/objectionary/eo-hamcrest/maintainability)
[![Hits-of-Code](https://hitsofcode.com/github/graur/eo-hamcrest)](https://hitsofcode.com/github/graur/eo-hamcrest/view)
![Code-Size](https://img.shields.io/github/languages/code-size/objectionary/eo-hamcrest)
![Lines of code](https://img.shields.io/tokei/lines/github/objectionary/eo-hamcrest)

This is a collection of test matchers for [EO](https://www.eolang.org) in [Hamcrest](http://hamcrest.org) style, which allow you to make unit tests declarative and elegant. Here is an example of a unit test:

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit1

[] > my-first-test
  assert-that > @
    4.plus 4
    $.all-of
      $.equal-to 8
      $.less-than 100
    "sum of two numbers"
```

The ```assert-that``` object is a stylized sentence for making a test assertion.
In this example, the subject of the assertion is the object ```4.plus 4``` that is the first method parameter. The second method parameter is a `matcher` for the first parameter, here a matcher that checks one object is equal and less than to another objects. The third parameter is an optional string description of the test case (```sum of two numbers```). The test passes since all conditions are ```TRUE```.

### Matchers

Hamcrest comes with a library of useful matchers. Here are some of the most important ones:

###### Core

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > core-matchers-test
  assert-that > @
    5.times 4
    $.is
      $.anything
    "multiply of two numbers"
```

```.anything``` - always matches, useful if you don’t care what the object under test is

```.described-as``` - decorator to adding custom failure description

```.is``` - decorator to improve readability

###### Logical
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > logical-matchers-test
  assert-that > @
    50.minus 10
    $.all-of
      $.not 
        $.equal-to 5
      $.any-of
        $.less-than 100
        $.greater-than 1
    "substract one number from another"
```

```.all-of``` - matches if all matchers match, short circuits (like Java &&)

```.any-of``` - matches if any matchers match, short circuits (like Java ||)

```.not``` - matches if the wrapped matcher doesn’t match and vice versa

###### Object
```.equal-to``` - the subject of the assertion is equal to some object

###### Collections
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > collections-test
  assert-that > @
    * 50 "t" "smth"
    $.array-each
      $.equal-to 50
      $.equal-to "t"
      $.equal-to "smth"
```
```array-each``` - test an array’s elements against an array of matchers

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > collections-test
  assert-that > @
    * 1 5 "f" 12
    $.has-item
      $.less-than 4
```

```.has-item```, ```.has-items``` - test a collection contains elements

###### Number

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > numbers-matchers-test
  assert-that > @
    31.div 3
    $.any-of
      $.close-to 10.0 0.1
      $.less-than 20
      $.greater-than 355
    "number of floating point number matchers"
```

```.close-to``` - test floating point values are close to a given value

```.greater-than```, ```.less-than``` - test ordering

###### Text

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > text-matchers-test
  assert-that > @
    "Vice versa!"
    $.all-of
      $.equal-to-ignoring-case "vice versa!"
      $.starts-with "Vice"
      $.ends-with "versa!"
      $.contains-string "ver"
    "text matchers test"
```

```.equal-to-ignoring-case``` - test string equality ignoring case

```.equal-to-ignoring-white-space``` - test string equality ignoring differences in runs of whitespace

```.contains-string,``` ```.ends-with```, ```.starts-with``` - test string matching


More examples are [here](https://github.com/Graur/eo-hamcrest/tree/main/src/test/eo/org/rolang/hamcrest)

## Custom matchers

You can also implement your own matcher by passing parameter to the ```assert-that``` object:

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > my-custom-matcher-test
  assert-that > @
    my-object
    my-custom-matcher obj

[obj] > my-custom-matcher
  [x] > match
    eq. > @
      x
      obj    
      
  [] > describe-mismatch
    "mismatch description message" > @

  [] > description-of
    "expected value description" > @
```

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
