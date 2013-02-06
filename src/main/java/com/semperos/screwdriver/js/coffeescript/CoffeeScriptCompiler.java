/**
 * Copyright 2013 Daniel Gregoire, David Yeung
 *
 * Copied heavily from https://github.com/yeungda/jcoffeescript. Given
 * different design needs and goals, this code will depart further
 * from Mr. Yeung's implementation as development continues.
 *
 */

package com.semperos.screwdriver.js.coffeescript;

import com.semperos.screwdriver.js.RhinoEvaluator;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

import java.io.*;
import java.util.HashMap;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler extends RhinoEvaluator {
    public CoffeeScriptCompiler() {}

    @Override
    public String execute(String coffeeScriptSource) throws CoffeeScriptCompileException {
        Context context = Context.enter();
        try {
            Scriptable scope = this.getGlobalScope();
            Scriptable compileScope = context.newObject(scope);
            compileScope.setParentScope(scope);
            compileScope.put("coffeeScriptSource", compileScope, coffeeScriptSource);
            try {
                return (String)context.evaluateString(compileScope,
                        String.format("CoffeeScript.compile(coffeeScriptSource, %s);", "{}"),
                        "CoffeeScriptCompiler",
                        0,
                        null);
            } catch (JavaScriptException e) {
                throw new CoffeeScriptCompileException(e);
            }
        } finally {
            Context.exit();
        }
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
    public String compile(String coffeeScriptSource) throws CoffeeScriptCompileException {
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("coffee-script.js", "com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        this.loadDependencies(deps);
        return this.execute(coffeeScriptSource);
    }
}
