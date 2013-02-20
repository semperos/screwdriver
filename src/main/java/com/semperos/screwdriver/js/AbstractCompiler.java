package com.semperos.screwdriver.js;

import com.semperos.screwdriver.js.rhino.RhinoCompiler;

import java.util.Map;

/**
 * Common needs for compilers that use Rhino under the covers
 */
abstract public class AbstractCompiler {
    protected RhinoCompiler rhinoCompiler;
    protected Map<String,Object> compilerOptions;
    protected Map<String,Object> compilerLocals;

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
}
