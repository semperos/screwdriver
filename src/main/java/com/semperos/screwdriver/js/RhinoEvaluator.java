package com.semperos.screwdriver.js;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @todo Full-fledged embedding of JavaScript
 *
 * Expose methods to add keys to a globally-scoped JavaScript
 * object, which JavaScripts would have to know about and use.
 */

/**
 * Adapt Rhino to the subset of use-cases we have for it.
 *
 * Currently, this class focuses on the use-case of running a JavaScript
 * compiler on {@code String}'s of source code written in an alternative
 * JavaScript language (e.g., CoffeeScript).
 *
 * Methods related specifically to compilation may be moved out if
 * other distinct use-cases arise.
 */
public class RhinoEvaluator {
    public static final String GLOBAL_SD_JS = "__ScrewdriverGlobal";
    public static final String INST_SD_JS = "__Screwdriver";
    public static final String SCRIPT_SOURCE_JS = "scriptSource";
    public static final String COMPILER_OPTIONS_JS = "compilerOptions";
    private InputStream program;
    private String programName;
    private Scriptable globalScope;
    private Scriptable instanceScope;
    private Scriptable globalScrewdriver;
    private Scriptable instanceScrewdriver;

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

    /**
     * Convenience method for calling {@link RhinoEvaluator#addInstanceField}
     * to set the {@code scriptSource} field in the instance-local store.
     *
     * @param source
     */
    public void addScriptSource(String source) {
       addInstanceField(SCRIPT_SOURCE_JS, source);
    }

    public void addCompilerOptions(HashMap<String,String> compilerOptions) {
        addInstanceField(COMPILER_OPTIONS_JS, compilerOptions);
    }

    public void registerCompiler(String compilerName, String compilerProgramFile) {
        programName = compilerName;
        ClassLoader classLoader = getClass().getClassLoader();
        program = classLoader.getResourceAsStream(compilerProgramFile);
    }

    /**
     * The most essential argument to a compiler is the source on which it will work.
     * This passes that in with no extra options to the underlying compiler.
     *
     * @param scriptSource
     */
    public void compilerArgs(String scriptSource) {
        compilerArgs(scriptSource, new HashMap<String, String>());
    }

    /**
     * Pass in both the source code to be compiled, as well as any custom compiler options
     * needed for the underlying JavaScript compiler.
     *
     * @param scriptSource
     * @param compilerOptions
     */
    public void compilerArgs(String scriptSource, HashMap<String,String> compilerOptions) {
        addScriptSource(scriptSource);
        addCompilerOptions(compilerOptions);
    }

    /**
     * Rhino expects us to pass in line number and security domain for all code
     * evaluation, so this is arity simply passes in default, lax values for those.
     *
     * @return
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    public String compile () throws IOException, RhinoEvaluatorException {
        return compile(0, null);
    }

    /**
     * After having setup a compiler by (1) registering it and (2) passing in its arguments
     * @param lineno The starting line number of the source code to be compiled (used for debugging)
     * @param securityDomain
     * @return
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    public String compile (int lineno, Object securityDomain) throws IOException, RhinoEvaluatorException {
        Context context = Context.enter();
        try {
            Reader reader = new InputStreamReader(program);
            try {
                return (String)context.evaluateReader(instanceScope,
                        reader,
                        programName,
                        lineno,
                        securityDomain);
            } catch (JavaScriptException e) {
                throw new RhinoEvaluatorException(e);
            }
        } finally {
            Context.exit();
        }
    }
}
