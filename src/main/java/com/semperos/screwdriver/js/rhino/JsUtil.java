package com.semperos.screwdriver.js.rhino;

import java.util.List;

/**
 * Miscellaneous utilities for dealing with JavaScript from Java
 */
public class JsUtil {
    public static String wrapAmd(String rawCode, List<String> requires, List<String> defines) {
        StringBuilder sb = new StringBuilder("define([");
        for (int i = 0; i < requires.size(); i++) {
            sb.append("\"" + requires.get(i) + "\"");
            if (i != requires.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("], function(");
        for (int i = 0; i < defines.size(); i++) {
            sb.append(defines.get(i));
            if (i != defines.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") {\n").append(rawCode).append("\n});");
        return sb.toString();
    }
}
