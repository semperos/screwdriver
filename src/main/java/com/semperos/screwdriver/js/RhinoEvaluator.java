package com.semperos.screwdriver.js;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapt Rhino to the subset of use-cases we have for it.
 *
 * This class is intended as a parent class that captures the basic
 * needs of all of our use-cases for Rhino. Most importantly, it sets
 * up the concept of a global Rhino scope, and an instance-level scope.
 *
 * If/when this is multithreaded, the global scope should be able to
 * be safely shared, while the instance-level scope should be considered thread-local
 * and contain the details for a specific evaluation with Rhino.
 *
 */
public class RhinoEvaluator {
    public static final String GLOBAL_SD_JS = "__ScrewdriverGlobal";
    public static final String INST_SD_JS = "__Screwdriver";
    public static final String SCRIPT_SOURCE_JS = "scriptSource";
    public static final String COMPILER_OPTIONS_JS = "compilerOptions";
    protected Scriptable globalScope;
    protected Scriptable instanceScope;
    protected Scriptable globalScrewdriver;
    protected Scriptable instanceScrewdriver;

    public Scriptable getGlobalScope() {
        return this.globalScope;
    }

    public Scriptable getInstanceScope() {
        return this.instanceScope;
    }

    public RhinoEvaluator() {
        Context context = Context.enter();
        globalScope = context.initStandardObjects();
        globalScrewdriver = context.newObject(globalScope);
        globalScrewdriver.put("description",
                globalScrewdriver,
                "This is a global, internal object used by the Screwdriver build tool.");
        globalScope.put(GLOBAL_SD_JS, globalScope, globalScrewdriver);
        instanceScope = context.newObject(globalScope);
        instanceScope.setParentScope(globalScope);
        instanceScrewdriver = context.newObject(instanceScope);
        instanceScrewdriver.put("description",
                instanceScrewdriver,
                "This is an instance-level object provided to embedded JavaScript scripts. " +
                        "Special variables like `scriptSource` and `compilerOptions` are made available " +
                        "in this object for standard types of JavaScript embedding (e.g., embedding " +
                        "a compiler written in JavaScript to compile languages like CoffeeScript).");
        instanceScope.put(INST_SD_JS, instanceScope, instanceScrewdriver);
        Context.exit();
    }

    public void addDependencies(Map<String, String> jsDeps) {
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

    public void addGlobalField(String varName, Object varValue) {
        Context.enter();
        globalScrewdriver.put(varName, globalScrewdriver, varValue);
        Context.exit();
    }

    public void addInstanceField(String varName, Object varValue) {
        Context.enter();
        instanceScrewdriver.put(varName, instanceScrewdriver, varValue);
        Context.exit();
    }

    public void addInstanceField(String varName, HashMap<String,String> varValues) {
        Context context = Context.enter();
        Scriptable x = context.newObject(instanceScope);
        for (Map.Entry<String,String> val : varValues.entrySet()) {
            x.put(val.getKey(), x, val.getValue());
        }
        addInstanceField(varName, x);
    }
}
