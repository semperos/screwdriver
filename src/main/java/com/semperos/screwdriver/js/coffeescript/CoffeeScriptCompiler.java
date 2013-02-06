package com.semperos.screwdriver.js.coffeescript;

import com.semperos.screwdriver.js.RhinoCompiler;
import com.semperos.screwdriver.js.RhinoEvaluator;
import com.semperos.screwdriver.js.RhinoEvaluatorException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler {
    private RhinoCompiler eva;

    public CoffeeScriptCompiler() {
        eva = new RhinoCompiler();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("coffee-script.js", "com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        eva.addDependencies(deps);
    }

    /**
     * Compile CoffeeScript to JavaScript
     *
     * @todo Provide facility to pass custom options to CoffeeScript compiler (e.g., --bare)
     *
     * @param coffeeScriptSource The CoffeeScript source code to be compiled
     * @return The resultant JavaScript
     * @throws CoffeeScriptCompileException
     */
    public String compile(String coffeeScriptSource) throws IOException, RhinoEvaluatorException {
        eva.registerCompiler("CoffeeScriptCompiler", "com/semperos/screwdriver/js/extension/compile-coffeescript.js");
        eva.compilerArgs(coffeeScriptSource);
        return eva.compile();
    }
}
