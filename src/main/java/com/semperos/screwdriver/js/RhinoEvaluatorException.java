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
        System.err.println("JavaScript Source Stacktrace:");
        System.err.println(e.getScriptStackTrace());
        e.printStackTrace();
    }

    public RhinoEvaluatorException(JavaScriptException e, String fileName) {
        this(e);
        System.err.println("Source file that caused error:");
        System.err.println(fileName);
    }
}
