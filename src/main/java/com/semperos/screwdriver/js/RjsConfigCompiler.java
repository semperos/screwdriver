package com.semperos.screwdriver.js;

import com.semperos.screwdriver.Config;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.HashMap;
import java.util.List;

/**
 * @todo Steps needed:
 *   * Create generic {@link org.mozilla.javascript.ScriptableObject} class to handle
 *     properties used for RequireJS optimizer's config (see hardcoded-build.js).
 *   * For each needed build (determined by user-provided list of main JS modules to target),
 *     create unique instance of that object and set it's data accordingly (i.e., change values
 *     based on the config needs for each main JS module)
 *   * Add those objects to an array, say of __Screwdriver.rjsMainModules, which can then
 *     be iterated through in the evaluated JavaScript and passed to requirejs.optimize
 */
public class RjsConfigCompiler extends RhinoCompiler {
    private RhinoCompiler rhinoCompiler;
    private List<String> rjsModules;

    public RjsConfigCompiler(Config cfg) {
        rhinoCompiler = new RhinoCompiler();
        rjsModules = cfg.getRjsModules();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("r.js", "com/semperos/screwdriver/js/vendor/r-2.1.4.js");
        rhinoCompiler.addDependencies(deps);
    }

    public void compile(Config cfg) {
        rhinoCompiler.registerCompiler("process-rjs", "com/semperos/screwdriver/js/extension/process-rjs.js");
    }

    public Scriptable newBaseConfig() {
        Context ctx = Context.enter();
        Scriptable baseConfigObj = ctx.newObject(instanceScope);
        try {
            baseConfigObj.put("mainConfigFile", baseConfigObj, "output/javascripts/common.js");
            baseConfigObj.put("optimize", baseConfigObj, "closure");
            Scriptable closureConfigObj = ctx.newObject(instanceScope);
            closureConfigObj.put("CompilerOptions", closureConfigObj, ctx.newObject(instanceScope));
            closureConfigObj.put("CompilationLevel", closureConfigObj, "SIMPLE_OPTIMIZATIONS");
            closureConfigObj.put("loggingLevel", closureConfigObj, "WARNING");
            baseConfigObj.put("closure", baseConfigObj, closureConfigObj);
            baseConfigObj.put("baseUrl", baseConfigObj, "output/javascripts");
            baseConfigObj.put("findNestedDependencies", baseConfigObj, true);
            baseConfigObj.put("name", baseConfigObj, "vendor/almond-0.2.4");
            baseConfigObj.put("wrap", baseConfigObj, true);
            baseConfigObj.put("logLevel", baseConfigObj, 0);
            baseConfigObj.put("originalBaseUrl", baseConfigObj, "output/javascripts");
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
            instanceScrewdriver.put("rjs", instanceScrewdriver, ctx.newObject(instanceScope));
            // Now include module-specific configuration for each target module
            Scriptable rjs = (Scriptable) instanceScrewdriver.get("rjs", instanceScrewdriver);
            rjs.put("moduleConfigs", rjs, ctx.newArray(instanceScope, rjsModules.size()));
            Scriptable moduleConfigs = (Scriptable) rjs.get("moduleConfigs", rjs);
            for (int i = 0; i < rjsModules.size(); i++) {
                String m = rjsModules.get(i);
                Object[] ms = new Object[]{ m };
                Scriptable baseConfigObj = newBaseConfig();
                baseConfigObj.put("include", baseConfigObj, ctx.newArray(instanceScope, ms));
                baseConfigObj.put("insertRequire", baseConfigObj, ctx.newArray(instanceScope, ms));
                baseConfigObj.put("out", baseConfigObj, "output/built/javascripts/" + m + "-built.js");
                moduleConfigs.put(i, moduleConfigs, baseConfigObj);
            }
        } finally {
            Context.exit();
        }
    }
}
