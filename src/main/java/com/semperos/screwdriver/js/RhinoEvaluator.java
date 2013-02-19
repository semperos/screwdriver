package com.semperos.screwdriver.js;

import org.apache.log4j.Logger;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

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
    private static Logger logger = Logger.getLogger(RhinoEvaluator.class);
    public static final String GLOBAL_SD_JS = "__ScrewdriverGlobal";
    public static final String INST_SD_JS = "__Screwdriver";
    protected ScriptableObject globalScope;
    protected Scriptable instanceScope;
    protected Scriptable globalScrewdriver;
    protected Scriptable instanceScrewdriver;

    public Scriptable getGlobalScope() {
        return this.globalScope;
    }

    public Scriptable getInstanceScope() {
        return this.instanceScope;
    }

    /**
     * @todo The two Screwdriver objects that get added to the JS runtime
     * would probably be better implemented as "host objects," so that Mozilla's API
     * could be used to set things like instance methods, static methods, etc.
     * See https://developer.mozilla.org/en/docs/Rhino/Embedding_tutorial#javaScriptHostObjects
     * and https://developer.mozilla.org/en-US/docs/Rhino/Examples#Implementing_Host_Objects
     */
    public RhinoEvaluator() {
        this(null);
    }

    public RhinoEvaluator(ScriptableObject scriptableObject) {
        Context context = Context.enter();
        if (scriptableObject == null) {
            globalScope = context.initStandardObjects();
        } else {
            globalScope = (ScriptableObject) context.initStandardObjects(scriptableObject);
            // For now, the reason to do the above at all is to define required functions
            // in JS land.
            /**
             * @todo This should be the technique used for other JS libraries that require us to provide definitions of things like print
             */
            String[] necessaryFunctions = { "print", "load" };
            globalScope.defineFunctionProperties(necessaryFunctions, globalScope.getClass(), ScriptableObject.DONTENUM);
            Scriptable argsObj = context.newArray(globalScope, new Object[] {});
            globalScope.defineProperty("arguments", argsObj, ScriptableObject.DONTENUM);
        }
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
                            context.evaluateReader(globalScope, reader, dep.getKey(), 1, null);
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

    /**
     * Given a {@link HashMap}, do the right thing for creating a JavaScript object
     * in the instance scope of this evaluator.
     *
     * Nothing is done to the values in the {@link HashMap} provided, so only use
     * simple values that Rhino can deal with.
     *
     * @param varName
     * @param varValues
     */
    public void addInstanceField(String varName, HashMap<String,Object> varValues) {
        Context context = Context.enter();
        Scriptable x = context.newObject(instanceScope);
        for (Map.Entry<String,Object> val : varValues.entrySet()) {
            x.put(val.getKey(), x, val.getValue());
        }
        addInstanceField(varName, x);
    }
}
