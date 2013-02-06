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
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

import java.io.*;
import java.util.Map;

/**
 * @todo Full-fledged embedding of JavaScript
 *
 * Expose methods to add keys to a globally-scoped JavaScript
 * object, which JavaScripts would have to know about and use.
 */

/**
 * Compile CoffeeScript via Rhino
 */
public class RhinoEvaluator {
    private Scriptable globalScope;
    private Scriptable scriptScope;
    private Scriptable screwdriver;

    public Scriptable getGlobalScope() {
        return this.globalScope;
    }

    public Scriptable getScriptScope() {
        return this.scriptScope;
    }

    public RhinoEvaluator() {
        Context context = Context.enter();
        try {
            globalScope = context.initStandardObjects();
            screwdriver = context.newObject(globalScope);
            screwdriver.put("description",
                    screwdriver,
                    "This is an internal object used by the Screwdriver build tool " +
                            "to facilitate fully embedded JavaScript execution more easily.");
            globalScope.put("__Screwdriver", globalScope, screwdriver);
            scriptScope = context.newObject(globalScope);
            scriptScope.setParentScope(globalScope);
        } finally {
            Context.exit();
        }
    }

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

    public void addField(String varName, Object varValue) {
        Context context = Context.enter();
        try {
            screwdriver.put(varName, screwdriver, varValue);
        } finally {
            Context.exit();
        }
    }

    public String execute(String scriptSource, String scriptName, int lineno, Object securityDomain) throws RhinoEvaluatorException {
        Context context = Context.enter();
        try {
            return (String)context.evaluateString(scriptScope,
                    scriptSource,
                    scriptName,
                    lineno,
                    securityDomain);
        } catch (JavaScriptException e) {
            throw new RhinoEvaluatorException(e);
        } finally {
            Context.exit();
        }
    }

}
