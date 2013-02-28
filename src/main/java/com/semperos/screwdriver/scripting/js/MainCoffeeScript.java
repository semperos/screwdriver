package com.semperos.screwdriver.scripting.js;

import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import org.apache.log4j.Logger;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;

/**
 * Entry-point to script Screwdriver build with CoffeeScript
 */
public class MainCoffeeScript {
    private static Logger logger = Logger.getLogger(MainCoffeeScript.class);
    private static final String SD_COFFEE_BUILD = "src/main/resources/screwdriver_build.coffee";

    public static void main(String[] args) throws RhinoEvaluatorException, ScriptException, IOException {
        String resource;
        if (args.length > 0) {
            resource = args[0];
        } else {
            resource = SD_COFFEE_BUILD;
        }
        logger.info(String.format("Running Screwdriver with CoffeeScript script '%s'...", resource));
        CoffeeScriptEval.evalFile(new File(resource));
    }
}
