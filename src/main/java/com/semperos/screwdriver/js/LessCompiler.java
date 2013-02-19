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
public class LessCompiler implements JsCompilation {
    private RhinoCompiler rhinoCompiler;

    public LessCompiler() {
        rhinoCompiler = new RhinoCompiler(new JsRuntimeSupport());
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("util.js", "com/semperos/screwdriver/js/extension/util.js");
        deps.put("env.js", "com/semperos/screwdriver/js/vendor/env.rhino.1.2.js");
        deps.put("less.js", "com/semperos/screwdriver/js/vendor/less-1.3.3.js");
        rhinoCompiler.addDependencies(deps);
    }

    /**
     * Compile LESS to JavaScript
     *
     * @return The resultant JavaScript
     * @throws java.io.IOException
     * @throws RhinoEvaluatorException
     */
    @Override
    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("LessCompiler", "com/semperos/screwdriver/js/extension/compile-less.js");
        rhinoCompiler.addSourceFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.compilerArgs(new LessSource(sourceFile).getNormalizedContent());
        try {
            return rhinoCompiler.compile();
        } catch (JavaScriptException e) {
            throw new RhinoEvaluatorException(e, sourceFile.toString());
        }
    }
}
