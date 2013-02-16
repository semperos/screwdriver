package com.semperos.screwdriver.js;

import org.apache.log4j.Logger;
import org.mozilla.javascript.JavaScriptException;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/5/13
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RhinoEvaluatorException extends Exception {
    private static Logger logger = Logger.getLogger(RhinoEvaluatorException.class);

    public RhinoEvaluatorException(JavaScriptException e) {
        super(e.getValue().toString(), e);
        logger.error("JavaScript Source Stacktrace");
        logger.error(e.getScriptStackTrace());
        e.printStackTrace();
    }

    public RhinoEvaluatorException(JavaScriptException e, String fileName) {
        this(e);
        logger.error("Source file that caused error:");
        logger.error(fileName);
    }
}
