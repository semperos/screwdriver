package com.semperos.screwdriver.js;

import com.semperos.screwdriver.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Compile CoffeeScript via Rhino
 */
public class CoffeeScriptCompiler implements JsCompilation {
    private RhinoCompiler rhinoCompiler;

    public CoffeeScriptCompiler() {
        rhinoCompiler = new RhinoCompiler();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("coffee-script.js", "com/semperos/screwdriver/js/vendor/coffee-script-1.4.0.js");
        rhinoCompiler.addDependencies(deps);
    }

    /**
     * Compile CoffeeScript to JavaScript
     *
     * @todo Research why the registerCompiler method has to go here.
     *
     * @return The resultant JavaScript
     * @throws IOException
     * @throws RhinoEvaluatorException
     */
    @Override
    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("CoffeeScriptCompiler", "com/semperos/screwdriver/js/extension/compile-coffeescript.js");
        rhinoCompiler.addSourceFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.compilerArgs(FileUtil.readFile(sourceFile));
        return rhinoCompiler.compile();
    }
}
