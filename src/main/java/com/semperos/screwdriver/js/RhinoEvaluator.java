/**
 * Copyright 2013 Daniel Gregoire, David Yeung
 *
 * Copied heavily from https://github.com/yeungda/jcoffeescript. Given
 * different design needs and goals, this code will depart further
 * from Mr. Yeung's implementation as development continues.
 *
 */
package com.semperos.screwdriver.js;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.*;
import java.util.Map;

/**
 * Compile CoffeeScript via Rhino
 */
public abstract class RhinoEvaluator {
    private Scriptable globalScope;

    public Scriptable getGlobalScope() {
        return this.globalScope;
    }

    public RhinoEvaluator() {}

    public void loadDependencies(Map<String,String> jsDeps) {
        ClassLoader classLoader = getClass().getClassLoader();
        for (Map.Entry<String,String> dep : jsDeps.entrySet()) {
            InputStream inputStream = classLoader.getResourceAsStream(dep.getValue());
            try {
                try {
                    Reader reader = new InputStreamReader(inputStream, "UTF-8");
                    try {
                        Context context = Context.enter();
                        context.setOptimizationLevel(-1); // needed to prevent 64k bytecode limit in Rhino
                        try {
                            globalScope = context.initStandardObjects();
                            context.evaluateReader(globalScope, reader, dep.getKey(), 0, null);
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
    }

    protected abstract String execute(String scriptSource) throws RhinoEvaluatorException;

}
