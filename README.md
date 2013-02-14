# Screwdriver #

Web assetSpec management for CI builds and local development using a lightweight Java stack.

## JavaScript Interactive Prompt (REPL) ##

You can run a Rhino-powered JavaScript REPL using the following command:

```
mvn exec:java -Dexec.mainClass="org.mozilla.javascript.tools.shell.Main"
```

When the Rhino shell is run in this manner, the project's classes are automatically added to the classpath for Rhino's classloader, which makes them available via LiveConnect, etc.

(Note that the experience will be greatly enhanced by running this with readline, ledit, or equivalent library available for your platform.)

## Running the RequireJS Optimizer ##

Extraordinarily verbose, but works:

```
java -classpath ~/.m2/repository/org/mozilla/rhino/1.7R4/rhino-1.7R4.jar:~/.m2/repository/com/google/javascript/closure-compiler/rr2079.1/closure-compiler-rr2079.1.jar: org.mozilla.javascript.tools.shell.Main src/main/resources/com/semperos/screwdriver/js/vendor/r-2.1.4.js -o src/test/resources/com/semperos/screwdriver/sample/hardcoded-build.js
```

The next step is to call RequireJS from Java via Rhino, in which case the Google Closure compiler JAR will already be on the classpath and all of this will be hidden behind a nice API.

## Related Projects ##

 * https://github.com/yeungda/jcoffeescript
 * https://github.com/remear/java-assetSpec-pipeline
 * https://github.com/sstephenson/sprockets
