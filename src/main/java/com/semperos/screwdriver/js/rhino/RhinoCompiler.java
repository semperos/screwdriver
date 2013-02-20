package com.semperos.screwdriver.js.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Rhino evaluation with facilities to support running a compiler
 * written in JavaScript designed to compile alternative JavaScript
 * languages down to raw JavaScript.
 */
public class RhinoCompiler extends RhinoEvaluator {
    private InputStream program;
    private String programName;
    public static final String SCRIPT_SOURCE_JS = "scriptSource";
    public static final String COMPILER_OPTIONS_JS = "compilerOptions";
    public static final String COMPILER_LOCALS_JS = "compilerLocals";
    public static final String SCRIPT_FILE_PATH_JS = "scriptFilePath";

    public RhinoCompiler() {
    }

    public RhinoCompiler(ScriptableObject scriptableObject) {
        super(scriptableObject);
    }

    /**
     * Convenience method for calling {@link RhinoEvaluator#addInstanceField(String, Object)}
     * to set the {@code scriptSource} field in the instance-local store.
     *
     * @param source The source code for an altJS language that needs to be compiled to JavaScript
     */
    public void addScriptSource(String source) {
        addInstanceField(SCRIPT_SOURCE_JS, source);
    }

    /**
     * Convenience method for calling {@link RhinoEvaluator#addInstanceField(String, Object)}
     * to set the {@code compilerOptions} field in the instance-local store.
     *
     * @param compilerOptions A map of options to pass to the underlying JavaScript compiler
     */
    public void addCompilerOptions(Map<String,Object> compilerOptions) {
        addInstanceField(COMPILER_OPTIONS_JS, compilerOptions);
    }

    /**
     * Convenience method for calling {@link RhinoEvaluator#addInstanceField(String, Object)}
     * to set the {@code compilerOptions} field in the instance-local store.
     *
     * @param compilerLocals A map of options to pass to the underlying JavaScript compiler
     */
    public void addCompilerLocals(Map<String,Object> compilerLocals) {
        addInstanceField(COMPILER_LOCALS_JS, compilerLocals);
    }

    /**
     * Convenience method for calling {@link RhinoEvaluator#addInstanceField(String, Object)}
     * to set the {@literal scriptFilePath} field in the instance-local store.
     * @param absolutePathToScript
     */
    public void addSourceFilePath(String absolutePathToScript) {
        addInstanceField(SCRIPT_FILE_PATH_JS, absolutePathToScript);
    }

    /**
     * Register your compiler written in JavaScript with a name, for Rhino compatibility
     * (and ostensibly better debugging).
     *
     * @param compilerName Name of compiler you want to show up in error messages
     * @param compilerProgramFile The JavaScript code needed to use the underlying JavaScript compiler
     */
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
        compilerArgs(scriptSource, new HashMap<String, Object>());
    }

    /**
     * Pass in both the source code to be compiled, as well as any custom compiler options
     * needed for the underlying JavaScript compiler.
     *
     * @param scriptSource
     * @param compilerOptions
     */
    public void compilerArgs(String scriptSource, Map<String,Object> compilerOptions) {
        compilerArgs(scriptSource, compilerOptions, new HashMap<String,Object>());

    }

    /**
     * Pass in the source code to be compiled, any arguments to the compiler, and then
     * any locals expected to be defined
     * @param scriptSource
     * @param compilerOptions
     * @param compilerLocals
     */
    public void compilerArgs(String scriptSource, Map<String,Object> compilerOptions, Map<String,Object> compilerLocals) {
        addScriptSource(scriptSource);
        addCompilerOptions(compilerOptions);
        addCompilerLocals(compilerLocals);
    }

    /**
     * Rhino expects us to pass in line number and security domain for all code
     * evaluation, so this is arity simply passes in default, lax values for those.
     *
     * @return
     * @throws java.io.IOException
     * @throws RhinoEvaluatorException
     */
    public String compile () throws IOException, RhinoEvaluatorException {
        return compile(1, null);
    }

    /**
     * After having setup a compiler by (1) registering it and (2) passing in its arguments
     * @param lineno The starting line number of the source code to be compiled (used for debugging)
     * @param securityDomain
     * @return
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    public String compile (int lineno, Object securityDomain) throws IOException, JavaScriptException {
        Context context = Context.enter();
        Reader reader = new InputStreamReader(program);
        Object ret = context.evaluateReader(instanceScope,
                reader,
                programName,
                lineno,
                securityDomain);
        if (ret instanceof org.mozilla.javascript.Undefined) {
            // This is really 'void'. Perhaps a separate 'compile' method
            // should be used that has 'void' as its return type, and that
            // get used by the likes of the RjsConfigCompiler
            return "";
        } else {
            return (String) ret;
        }
    }
}
