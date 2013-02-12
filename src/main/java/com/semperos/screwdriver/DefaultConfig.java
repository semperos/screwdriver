package com.semperos.screwdriver;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/11/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultConfig {
    public static boolean getDebug() {
        return false;
    }


    public static String getAssetDirectory() {
        return System.getProperty("user.dir");
    }

    public static String getOutputDirectory() {
        return System.getProperty("user.dir") + File.separator + "target" + File.separator + "client";
    }

    public static ArrayList<String> getJsIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getCssIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getImageIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getJsExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getCssExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getImageExcludes() {
        return new ArrayList<String>();
    }
}
