package com.semperos.screwdriver.js;

import java.io.File;
import java.io.IOException;

/**
 * API for compiler written in JavaScript, evaluated via Rhino
 */
public interface JsCompilation {
    public String compile(String sourceCode, File sourceFile) throws IOException, RhinoEvaluatorException;
}
