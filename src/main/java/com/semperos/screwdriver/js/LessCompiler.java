package com.semperos.screwdriver.js;

import com.semperos.screwdriver.js.rhino.RhinoCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import org.mozilla.javascript.JavaScriptException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Compile LESS to CSS via Rhino
 */
public class LessCompiler extends AbstractCompiler implements JsCompilation {

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
     * @throws com.semperos.screwdriver.js.rhino.RhinoEvaluatorException
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
