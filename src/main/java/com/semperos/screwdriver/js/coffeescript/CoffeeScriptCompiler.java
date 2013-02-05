/**
 * Copyright 2013 Daniel Gregoire, David Yeung
 *
 * Copied heavily from https://github.com/yeungda/jcoffeescript. Given
 * different design needs and goals, this code will depart further
 * from Mr. Yeung's implementation as development continues.
 *
 */

package com.semperos.screwdriver.js.coffeescript;

import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

import java.io.*;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler {
    private Scriptable globalScope;

    public CoffeeScriptCompiler() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        try {
            try {
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                try {
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1); // needed to prevent 64k bytecode limit in Rhino
                    try {
                        globalScope = context.initStandardObjects();
                        context.evaluateReader(globalScope, reader, "coffee-script.js", 0, null);
                    } finally {
                        Context.exit();
                    }
                } finally {
                    reader.close();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        Context context = Context.enter();
        try {
            Scriptable compileScope = context.newObject(globalScope);
            compileScope.setParentScope(globalScope);
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
}
