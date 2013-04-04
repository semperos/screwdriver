package com.semperos.screwdriver.js;

import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.js.rhino.RhinoCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Generate and process RequireJS optimizer configurations for JavaScript files.
 */
public class RjsConfigCompiler extends AbstractCompiler {
    private static final String ALMOND_JS = "vendor/almond-0.2.4";
    private List<String> rjsModules;
    private Config cfg;

    public RjsConfigCompiler(Config cfg) {
        this.cfg = cfg;
        JsRuntimeSupport jsRuntimeSupport = new JsRuntimeSupport();
        rhinoCompiler = new RhinoCompiler(jsRuntimeSupport);
        rjsModules = cfg.getRjsModules();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("r.js", "com/semperos/screwdriver/js/vendor/r-patched.js");
        rhinoCompiler.addDependencies(deps);
        File almondFile = new File(cfg.getOutputDirectory(), cfg.getJsSubDirectoryName() + "/" + ALMOND_JS + ".js");
        if (!almondFile.exists()) {
            throw new RuntimeException("You must include " + ALMOND_JS + " inside your JavaScript assets directory to use the RequireJS (r.js) Optimizer.");
        }
    }

    public void compile() throws IOException, RhinoEvaluatorException {
        processRjsModuleConfigs();
        rhinoCompiler.registerCompiler("process-rjs", "com/semperos/screwdriver/js/extension/process-rjs.js");
        rhinoCompiler.compile();
    }

    public Scriptable newBaseConfig() {
        Context ctx = Context.enter();
        Scriptable baseConfigObj = ctx.newObject(rhinoCompiler.getInstanceScope());
        try {
            baseConfigObj.put("mainConfigFile", baseConfigObj, cfg.getRjsMainConfigFile()); // test common.js
//            baseConfigObj.put("optimize", baseConfigObj, "closure");
            baseConfigObj.put("optimize", baseConfigObj, "none");
//            Scriptable closureConfigObj = ctx.newObject(rhinoCompiler.getInstanceScope());
//            closureConfigObj.put("CompilerOptions", closureConfigObj, ctx.newObject(rhinoCompiler.getInstanceScope()));
//            closureConfigObj.put("CompilationLevel", closureConfigObj, "SIMPLE_OPTIMIZATIONS");
//            closureConfigObj.put("loggingLevel", closureConfigObj, "WARNING");
//            baseConfigObj.put("closure", baseConfigObj, closureConfigObj);
            baseConfigObj.put("baseUrl", baseConfigObj, cfg.getRjsBaseUrl()); // "src/test/resources/com/semperos/screwdriver/sample/output/javascripts"
            baseConfigObj.put("findNestedDependencies", baseConfigObj, true);
            baseConfigObj.put("name", baseConfigObj, ALMOND_JS);
            baseConfigObj.put("wrap", baseConfigObj, true);
            baseConfigObj.put("logLevel", baseConfigObj, 0);
        } finally {
            Context.exit();
        }
        return baseConfigObj;
    }

    /**
     * Given list of module names, dynamically generate array of r.js configs,
     * one per module. Stick those as an array in {@code __Screwdriver.rjs.moduleConfigs}.
     */
    public void processRjsModuleConfigs() {
        Context ctx = Context.enter();
        try{
            // Now include module-specific configuration for each target module
            Scriptable rjs = ctx.newObject(rhinoCompiler.getInstanceScope());
            Scriptable moduleConfigs = ctx.newArray(rhinoCompiler.getInstanceScope(), rjsModules.size());
            for (int i = 0; i < rjsModules.size(); i++) {
                String m = rjsModules.get(i);
                Object[] ms = new Object[]{ m };
                Scriptable baseConfigObj = newBaseConfig();
                baseConfigObj.put("include", baseConfigObj, ctx.newArray(rhinoCompiler.getInstanceScope(), ms));
                baseConfigObj.put("insertRequire", baseConfigObj, ctx.newArray(rhinoCompiler.getInstanceScope(), ms));
                baseConfigObj.put("out", baseConfigObj, String.format(cfg.getRjsOutFormat(), m)); // "src/test/resources/com/semperos/screwdriver/sample/output/built/javascripts/" + m + "-built.js"
                moduleConfigs.put(i, moduleConfigs, baseConfigObj);
            }
            // "Save" changes
            rjs.put("moduleConfigs", rjs, moduleConfigs);
            rhinoCompiler.addInstanceField("rjs", rjs);
        } finally {
            Context.exit();
        }
    }
}
