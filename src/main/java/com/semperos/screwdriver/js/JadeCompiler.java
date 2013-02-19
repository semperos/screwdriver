package com.semperos.screwdriver.js;

import com.semperos.screwdriver.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Compiler for Jade templates (compiles to HTML)
 */
public class JadeCompiler implements JsCompilation {
    RhinoCompiler rhinoCompiler;
    HashMap<String,Object> compilerOptions;
    HashMap<String,Object> compilerLocals;

    public JadeCompiler() {
        rhinoCompiler = new RhinoCompiler(new JsRuntimeSupport());
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("env.js", "com/semperos/screwdriver/js/vendor/env.rhino.1.2.js");
        deps.put("jade.js", "com/semperos/screwdriver/js/vendor/jade-0.27.7.js");
        rhinoCompiler.addDependencies(deps);
    }

    public HashMap<String, Object> getCompilerOptions() {
        return compilerOptions;
    }

    public void setCompilerOptions(HashMap<String, Object> compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    public HashMap<String, Object> getCompilerLocals() {
        return compilerLocals;
    }

    public void setCompilerLocals(HashMap<String, Object> compilerLocals) {
        this.compilerLocals = compilerLocals;
    }

    /**
     * Compile Jade files to HTML
     *
     * @param sourceFile
     * @return
     * @throws IOException
     * @throws RhinoEvaluatorException
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
