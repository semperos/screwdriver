package com.semperos.screwdriver.js;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler {
    private RhinoCompiler rhinoCompiler;

    public CoffeeScriptCompiler() {
        rhinoCompiler = new RhinoCompiler();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("coffee-script.js", "com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        rhinoCompiler.addDependencies(deps);
    }

    /**
     * Compile CoffeeScript to JavaScript
     *
     * @todo Research why the registerCompiler method has to go here.
     *
     * @param coffeeScriptSource The CoffeeScript source code to be compiled
     * @return The resultant JavaScript
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    public String compile(String coffeeScriptSource, File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("CoffeeScriptCompiler", "com/semperos/screwdriver/js/extension/compile-coffeescript.js");
        rhinoCompiler.addScriptFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.compilerArgs(coffeeScriptSource);
        return rhinoCompiler.compile();
    }
}
