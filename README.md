# Screwdriver #

Web assetSpec management for CI builds and local development using a lightweight Java stack.

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
