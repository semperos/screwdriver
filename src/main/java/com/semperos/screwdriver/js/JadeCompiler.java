package com.semperos.screwdriver.js;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.rhino.RhinoCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Compiler for Jade templates (compiles to HTML)
 */
public class JadeCompiler extends AbstractCompiler implements JsCompilation {

    public JadeCompiler() {
        rhinoCompiler = new RhinoCompiler(new JsRuntimeSupport());
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("env.js", "com/semperos/screwdriver/js/vendor/env.rhino.1.2.js");
        deps.put("jade.js", "com/semperos/screwdriver/js/vendor/jade-0.27.7.js");
        rhinoCompiler.addDependencies(deps);
    }

    /**
     * Compile Jade files to HTML
     *
     * @param sourceFile
     * @return
     * @throws IOException
     * @throws com.semperos.screwdriver.js.rhino.RhinoEvaluatorException
     */
    @Override
    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("JadeCompiler", "com/semperos/screwdriver/js/extension/compile-jade.js");
        rhinoCompiler.addSourceFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.addScriptSource(FileUtil.readFile(sourceFile));
        rhinoCompiler.addCompilerOptions(compilerOptions);
        rhinoCompiler.addCompilerLocals(compilerLocals);
        return rhinoCompiler.compile();
    }
}
