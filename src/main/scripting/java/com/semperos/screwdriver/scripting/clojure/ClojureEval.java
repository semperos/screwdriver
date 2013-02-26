package com.semperos.screwdriver.scripting.clojure;

import clojure.lang.RT;

/**
 * Evaluate Clojure from Java, useful for scripting the build in Clojure
 */
public class ClojureEval {
    public static void evalResource(String resourcePath) throws Exception {
        // Ensure that the .clj extension is removed
        RT.var("clojure.core", "load").invoke(resourcePath);
    }
}
