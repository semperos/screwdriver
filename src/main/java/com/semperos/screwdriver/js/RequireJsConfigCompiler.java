package com.semperos.screwdriver.js;

import java.util.HashMap;

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
public class RequireJsConfigCompiler extends RhinoCompiler {
    private RhinoCompiler rhinoCompiler;

    public RequireJsConfigCompiler() {
        rhinoCompiler = new RhinoCompiler();
        HashMap<String,String> deps = new HashMap<String,String>();
        deps.put("r.js", "com/semperos/screwdriver/js/vendor/r-2.1.4.js");
        rhinoCompiler.addDependencies(deps);
    }

}
