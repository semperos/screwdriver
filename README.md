# Screwdriver #

Web asset management for CI builds and local development using a lightweight Java stack.

## Clojure REPL ##

You can run a Clojure REPL with all of this project's dependencies on the classpath by running the following:

```
mvn -PclojureScripting clojure:repl
```

Note the use of the `clojureScripting` profile, which makes the use of Clojure completely optional for the normal build of the project.

(Note that the experience will be greatly improved by running this with readline, ledit, or equivalent library available for your platform.)

## JavaScript REPL ##

You can run a Rhino-powered JavaScript REPL using the following command:

```
mvn exec:java -Dexec.mainClass="org.mozilla.javascript.tools.shell.Main"
```

When the Rhino shell is run in this manner, the project's classes are automatically added to the classpath for Rhino's classloader, which makes them available via LiveConnect, etc.

(Note that the experience will be greatly improved by running this with readline, ledit, or equivalent library available for your platform.)

## Related Projects ##

 * https://github.com/yeungda/jcoffeescript
 * https://github.com/remear/java-assetSpec-pipeline
 * https://github.com/sstephenson/sprockets
