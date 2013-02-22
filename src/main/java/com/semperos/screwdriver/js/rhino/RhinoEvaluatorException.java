package com.semperos.screwdriver.js.rhino;

import org.apache.log4j.Logger;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.JavaScriptException;

/**
 * Handle exceptions that occur while evaluated JavaScript inside Rhino.
 * Specifically, do more work to ensure enough information is printed before
 * exiting.
 */
public class RhinoEvaluatorException extends Exception {
    private static Logger logger = Logger.getLogger(RhinoEvaluatorException.class);

    public RhinoEvaluatorException(JavaScriptException e) {
        super(e.getValue().toString(), e);
        logger.error("JavaScript Source Stacktrace");
        logger.error(e.getScriptStackTrace());
        e.printStackTrace();
        throw new EvaluatorException("An unrecoverable JavaScript compilation error occurred. See above.");
    }

    public RhinoEvaluatorException(JavaScriptException e, String fileName) {
        this(e);
        logger.error("Source file that caused error:");
        logger.error(fileName);
    }
}
