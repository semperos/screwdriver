package com.semperos.screwdriver.scripting.clojure;

import clojure.lang.RT;
import org.apache.commons.io.FilenameUtils;

/**
 * Evaluate Clojure from Java, useful for scripting the build in Clojure
 */
public class ClojureEval {
    public static void evalResource(String resourcePath) throws Exception {
        // Clojure expects the name of the resource, not the actual filename, so
        // chop off the extension
        if (FilenameUtils.isExtension(resourcePath, "clj")) {
            resourcePath = resourcePath.substring(0, resourcePath.lastIndexOf(".clj"));
        }
        RT.var("clojure.core", "load").invoke(resourcePath);
    }
}
