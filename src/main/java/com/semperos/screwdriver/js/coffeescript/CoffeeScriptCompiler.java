package com.semperos.screwdriver.js.coffeescript;

import com.semperos.screwdriver.js.RhinoEvaluator;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

import java.util.HashMap;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler {
    private RhinoEvaluator eva;

    public CoffeeScriptCompiler() {
        eva = new RhinoEvaluator();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("coffee-script.js", "com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        eva.loadDependencies(deps);
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
    public String compile(String coffeeScriptSource) throws RhinoEvaluatorException {
        eva.addField("coffeeScriptSource", coffeeScriptSource);
        return eva.execute(String.format("CoffeeScript.compile(__Screwdriver.coffeeScriptSource, %s);", "{}"),
                "CoffeeScriptCompiler",
                0,
                null);
    }
}
