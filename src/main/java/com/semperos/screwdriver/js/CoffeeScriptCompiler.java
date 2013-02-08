package com.semperos.screwdriver.js;

import com.semperos.screwdriver.js.RhinoCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;

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
     * @param coffeeScriptSource The CoffeeScript source code to be compiled
     * @return The resultant JavaScript
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    public String compile(String coffeeScriptSource) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("CoffeeScriptCompiler", "com/semperos/screwdriver/js/extension/compile-coffeescript.js");
        rhinoCompiler.compilerArgs(coffeeScriptSource);
        return rhinoCompiler.compile();
    }
}
