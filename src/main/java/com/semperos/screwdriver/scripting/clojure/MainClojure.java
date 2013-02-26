package com.semperos.screwdriver.scripting.clojure;

import org.apache.log4j.Logger;

/**
 * Entry-point for using Clojure to script Screwdriver builds
 */
public class MainClojure {
    private static Logger logger = Logger.getLogger(MainClojure.class);
    private static final String SD_CLOJURE_CONFIG = "screwdriver-config.clj";

    public static void main(String[] args) throws Exception {
        String resource;
        if (args.length > 0) {
            resource = args[0];
        } else {
            resource = SD_CLOJURE_CONFIG;
        }
        logger.info(String.format("Running Screwdriver with Clojure script '%s' on the classpath...", resource));
        ClojureEval.evalResource(resource);
    }
}
