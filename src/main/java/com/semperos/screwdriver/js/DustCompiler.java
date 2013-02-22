package com.semperos.screwdriver.js;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.rhino.JsUtil;
import com.semperos.screwdriver.js.rhino.RhinoCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Compile Dust templates to JavaScript via Rhino
 */
public class DustCompiler extends AbstractCompiler implements JsCompilation {
    private boolean wrapAmd;

    public DustCompiler() {
        this(true);
    }

    public DustCompiler(boolean wrapAmd) {
        this.wrapAmd = wrapAmd;
        rhinoCompiler = new RhinoCompiler(new JsRuntimeSupport());
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("jade.js", "com/semperos/screwdriver/js/vendor/dust-full-1.2.0.js");
        rhinoCompiler.addDependencies(deps);
    }

    @Override
    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        rhinoCompiler.registerCompiler("DustCompiler", "com/semperos/screwdriver/js/extension/compile-dust.js");
        rhinoCompiler.addSourceFilePath(sourceFile.getAbsolutePath());
        rhinoCompiler.addScriptSource(FileUtil.readFile(sourceFile));
        compilerOptions.put("dustRegistrationName", FilenameUtils.getBaseName(sourceFile.toString()));
        rhinoCompiler.addCompilerOptions(compilerOptions);
        String compiledOutput = rhinoCompiler.compile();
        if (this.wrapAmd) {
            ArrayList<String> reqs = new ArrayList<>();
            ArrayList<String> defs = new ArrayList<>();
            reqs.add("dust");
            defs.add("dust");
            return JsUtil.wrapAmd(compiledOutput, reqs, defs);
        } else {
            return compiledOutput;
        }
    }
}
