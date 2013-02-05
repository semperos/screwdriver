package com.semperos.screwdriver.js.coffeescript;

import org.mozilla.javascript.JavaScriptException;

/**
 * Custom exception for errors that occur when compiling
 * CoffeeScript via Rhino
 */
public class CoffeeScriptCompileException extends Exception {
    public CoffeeScriptCompileException(JavaScriptException e) {
        super(e.getValue().toString(), e);
    }
}
