package com.semperos.screwdriver.scripting.clojure;

import clojure.lang.RT;
import org.apache.commons.io.FilenameUtils;

/**
 * Evaluate Clojure from Java, useful for scripting the build in Clojure
 */
public class ClojureEval {
    /**
     * Add arguments to Clojure runtime so script can access them
     */
    public static void addArgs() {
    }

    public static void evalResource(String resourcePath) throws Exception {
        // Clojure expects the name of the resource, not the actual filename, so
        // chop off the extension
        if (FilenameUtils.getExtension(resourcePath).equals("")) {
            resourcePath += ".clj";
        } else if (!FilenameUtils.getExtension(resourcePath).equals("clj")) {
            throw new RuntimeException("You must pass in a Clojure file to be evaluated with a '.clj' extension.");
        }
        RT.loadResourceScript(resourcePath);
    }
}
