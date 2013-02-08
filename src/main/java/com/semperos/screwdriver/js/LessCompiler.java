package com.semperos.screwdriver.js;

import org.mozilla.javascript.JavaScriptException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/8/13
 * Time: 1:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class LessCompiler {
    private RhinoCompiler rhinoCompiler;

    public LessCompiler() {
        rhinoCompiler = new RhinoCompiler();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("env.js", "com/semperos/screwdriver/js/vendor/env.rhino.1.2.js");
        deps.put("less.js", "com/semperos/screwdriver/js/vendor/less-1.3.3.js");
        rhinoCompiler.addDependencies(deps);
    }

    /**
     * Compile LESS to JavaScript
     *
     * @param lessSource The LESS source code to be compiled
     * @return The resultant JavaScript
     * @throws java.io.IOException
     * @throws RhinoEvaluatorException
     */
    public String compile(String lessSource, File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("LessCompiler", "com/semperos/screwdriver/js/extension/compile-less.js");
        rhinoCompiler.addScriptFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.compilerArgs(new LessSource(sourceFile).getNormalizedContent());
        String ret;
        try {
            return rhinoCompiler.compile();
        } catch (JavaScriptException e) {
            throw new RhinoEvaluatorException(e, sourceFile.toString());
        }
    }
}
