# Screwdriver #

Web asset management for CI builds and local development using a lightweight Java stack.

## JavaScript REPL ##

You can run a Rhino-powered JavaScript REPL using the following command:

```
mvn exec:java -Dexec.mainClass="org.mozilla.javascript.tools.shell.Main"
```

When the Rhino shell is run in this manner, the project's classes are automatically added to the classpath for Rhino's classloader, which makes them available via LiveConnect, etc.

(Note that the experience will be greatly improved by running this with readline, ledit, or equivalent library available for your platform.)

## Performing a Release ##

```bash
# Prepare the release
mvn clean release:clean
mvn -Pintegration-tests release:prepare

# If all went well, push changes to codebase and version tag
git push origin master
git push --tags

# Perform release
mvn -Dgpg.passphrase="XXXX" release:perform

# Clean up afterwards too
mvn clean release:clean
```

## Related Projects ##

 * https://github.com/yeungda/jcoffeescript
 * https://github.com/remear/java-assetSpec-pipeline
 * https://github.com/sstephenson/sprockets

## License ##

Copyright 2013 Daniel Gregoire (semperos), Kirill Orlov (rheol,koberidze)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

See the LICENSE file for more details.
