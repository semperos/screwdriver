package com.semperos.screwdriver.js;

import com.semperos.screwdriver.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Compiler for Jade templates (compiles to HTML)
 */
public class JadeCompiler implements JsCompilation {
    RhinoCompiler rhinoCompiler;
    Map<String,Object> compilerOptions;
    Map<String,Object> compilerLocals;

    public JadeCompiler() {
        rhinoCompiler = new RhinoCompiler(new JsRuntimeSupport());
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("env.js", "com/semperos/screwdriver/js/vendor/env.rhino.1.2.js");
        deps.put("jade.js", "com/semperos/screwdriver/js/vendor/jade-0.27.7.js");
        rhinoCompiler.addDependencies(deps);
    }

    public Map<String, Object> getCompilerOptions() {
        return compilerOptions;
    }

    public void setCompilerOptions(Map<String, Object> compilerOptions) {
        this.compilerOptions = compilerOptions;
    }

    public Map<String, Object> getCompilerLocals() {
        return compilerLocals;
    }

    public void setCompilerLocals(Map<String, Object> compilerLocals) {
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
