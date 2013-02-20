__Screwdriver.result = 'COMPILATION FAILED';

var parser = new less.Parser();
parser.parse(__Screwdriver.scriptSource, function(e, tree) {
    if (e instanceof Object) {
        throw e;
    }
    /**
     * Imports don't work via Rhino by default
     * See https://github.com/marceloverdijk/lesscss-java/blob/master/src/main/java/org/lesscss/LessSource.java
     * for a Java-side approach to solving this problem. Uses regular expressions to parse LESS
     * source code, which is a bit more brittle than what we would like to do here, using the actual LESS
     * AST to edit the @import statements.
     *
     * NOTE: This isn't possible, because the LESS *parser* actually resolves @import
     * statements as it goes. Which means a file with an @import statement that points to a non-existent file
     * causes a parser failure before the parser has completed its work. We'll have to
     * go the route of lesscss-java until the LESS maintainers remove this operation
     * from the parsing phase.
     */

//    if (tree.rules != null && tree.rules.length > 0) {
//        for(var i = 0; i < tree.rules.length; i++) {
//            if (tree.rules[i].path != null && tree.rules[i].rootpath === '') {
//                tree.rules[i].rootpath = __Screwdriver.scriptFilePath;
//            }
//        }
//    }
    try {
        __Screwdriver.result = tree.toCSS(__Screwdriver.compilerOptions);
    } catch (e) {
        print("ERROR: LESS compilation error.");
        __ScrewdriverGlobal.printObject(e);
        throw e;
        // var msg = 'Error: LESS code could not be compiled.\n';
        // msg += formatObject(e);
        // throw new LessCompilerException(msg);
    }

});

__Screwdriver.result;
