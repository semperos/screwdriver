package com.semperos.screwdriver.js.coffeescript;

import com.semperos.screwdriver.js.RhinoEvaluatorException;
import org.mozilla.javascript.JavaScriptException;

/**
 * Custom exception for errors that occur when compiling
 * CoffeeScript via Rhino
 */
public class CoffeeScriptCompileException extends RhinoEvaluatorException {
    public CoffeeScriptCompileException(JavaScriptException e) {
        super(e);
    }
}
