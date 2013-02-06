package com.semperos.screwdriver.js;

import org.mozilla.javascript.JavaScriptException;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/5/13
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RhinoEvaluatorException extends Exception {
    public RhinoEvaluatorException(JavaScriptException e) {
        super(e.getValue().toString(), e);
    }
}
