package com.semperos.screwdriver.js;

import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;

import java.io.File;
import java.io.IOException;

/**
 * API for compiler written in JavaScript, evaluated via Rhino
 */
public interface JsCompilation {
    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException;
}
