# String Reformat
[![Build Status](https://travis-ci.com/brajjan79/RegexFormat.svg?branch=main)](https://travis-ci.com/brajjan79/RegexFormat) 
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6121fcdf61bc464eb9d8b604b5514ab2)](https://www.codacy.com/gh/brajjan79/RegexFormat/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=brajjan79/RegexFormat&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/6121fcdf61bc464eb9d8b604b5514ab2)](https://www.codacy.com/gh/brajjan79/RegexFormat/dashboard?utm_source=github.com&utm_medium=referral&utm_content=brajjan79/RegexFormat&utm_campaign=Badge_Coverage)
[![Jitpack Url](https://jitpack.io/v/brajjan79/RegexFormat.svg)](https://jitpack.io/#brajjan79/RegexFormat)
## Description

A simple yet powerful tool to reconstruct parts of a string based an a regex
and a pattern the found part should be reformated to.

### Usage

The class works by taking an input string to reformat, locate a part of the
string using a regex, then splitting the found part into a list och characters
that can then be injected as a replacement to the found string. This also
includes any provided characters

-   **Input string:** Can be anything except contain "$xx" where xx is random number.

-   **Regex:** Any kind of regex, class throws exception of no match was found.

-   **Reformat pattern:** Characters found by regex is represented as "$" and a number
    that matches the position of the found char. Any other provided characters will be
    injected as well.

#### *Import the class*

``` Java
import com.github.stringreformat.RegexFormat;
```

#### *Methods*

The RegexFormat class has 2 public methods:
RegexFormat.reformat(string, regex, pattern) - reformats first occurrence of the regex.
RegexFormat.reformatAll(string, regex, pattern) - reformats all occurrences of the regex.
RegexFormat.hasMatch(string, regex) - returns a boolean if match exist.

#### *Input data*

Class requires:

-   Input string to reformat
-   Regex as String to locate what to reformat
-   Reformat pattern to know how to reformat

**Examples:**

``` Java
# reformat()
String string = "20120210_125954";
String regex = "[0-9]{8}_[0-9]{6}";
String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "2012-02-10 12:59:54"

String string = "20120210_125954, 19561122_223344";
String regex = "[0-9]{8}_[0-9]{6}";
String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "2012-02-10 12:59:54, 19561122_223344"

String string = "20120210_125954";
String regex = "[0-9]{8}_[0-9]{6}";
String pattern = "$4$5-$6$7 $9$10:$11$12";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "02-10 12:59"

String string = "This is some text [20120210_125954] that contains a date!";
String regex = "[0-9]{8}_[0-9]{6}";
String pattern = "$4$5-$6$7 $9$10:$11$12";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "This is some text [2012-02-10 12:59:54] that contains a date!"

String string = "Reformat this asap.";
String regex = "asap";
String pattern = "$0.$1.$2.$3";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "Reformat this a.s.a.p."

String string = "Reformat this asap.";
String regex = "this|asap";
String pattern = "$0.$1.$2.$3";
String reformattedString = RegexFormat.reformat(string, regex, pattern);
--> "Reformat t.h.i.s asap."

# reformatAll()
String string = "20120210_125954, 19561122_223344";
String regex = "[0-9]{8}_[0-9]{6}";
String pattern = "$0$1$2$3-$4$5-$6$7 $9$10:$11$12:$13$14";
String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
--> "2012-02-10 12:59:54, 1956-11-22 22:33:44"

String string = "Reformat this asap.";
String regex = "this|asap";
String pattern = "$0.$1.$2.$3";
String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
--> "Reformat t.h.i.s a.s.a.p."

String string = "Reformat this asap.";
String regex = "Reformat|this|asap";
String pattern = "$7$6$5$4$3$2$1$0";
String reformattedString = RegexFormat.reformatAll(string, regex, pattern);
--> "tamrofeR siht pasa."

# hasMatch()
String string = "20120210_125954";
String regex = "[0-9]{8}_[0-9]{6}";
boolean matches = RegexFormat.hasMatch(string, regex);
--> True

# hasMatch()
String string = "20120210_125954";
String regex = "Some-words";
boolean matches = RegexFormat.hasMatch(string, regex);
--> False
```

**Do nots:**
Locating $ signs in a regex is currently not supported. Since $n is part
of the inject pattern all existing $ in the input string will be removed
and re added in the end.

### Installation

Recommended is to install the tool via a dependency manager like Maven or
Gradle.
The source data can be downloaded here:
<https://github.com/brajjan79/RegexFormat/tags>

#### Maven, Gradle etc

See Jitpack: <https://jitpack.io/#brajjan79/RegexFormat>
