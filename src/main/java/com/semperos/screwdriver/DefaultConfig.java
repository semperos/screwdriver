package com.semperos.screwdriver;

import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Default values for configurable aspects of Screwdriver.
 */
public class DefaultConfig {
    public static boolean isDebugMode() {
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

    public static ArrayList<String> getJsExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getCssIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getCssExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getImageIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getImageExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getTemplateIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getTemplateExcludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getServerTemplateIncludes() {
        return new ArrayList<String>();
    }

    public static ArrayList<String> getServerTemplateExcludes() {
        return new ArrayList<String>();
    }

    public static boolean isOptimizeJs() {
        return false;
    }

    public static boolean isOptimizeCss() {
        return false;
    }

    public static boolean isOptimizeImage() {
        return false;
    }

    public static ArrayList<String> getRjsModules() {
        return new ArrayList<String>();
    }

    public static IOFileFilter getJsFileFilter() {
        return null;
    }

    public static IOFileFilter getCssFileFilter() {
        return null;
    }

    public static IOFileFilter getImageFileFilter() {
        return null;
    }

    public static IOFileFilter getJsDirFilter() {
        return null;
    }

    public static IOFileFilter getCssDirFilter() {
        return null;
    }

    public static IOFileFilter getImageDirFilter() {
        return null;
    }

    public static IOFileFilter getTemplateFileFilter() {
        return null;
    }

    public static IOFileFilter getTemplateDirFilter() {
        return null;
    }

    public static IOFileFilter getServerTemplateFileFilter() {
        return null;
    }

    public static IOFileFilter getServerTemplateDirFilter() {
        return null;
    }

    public static Map<String,Object> getServerTemplateLocals() {
        return new HashMap<String,Object>();
    }
}
