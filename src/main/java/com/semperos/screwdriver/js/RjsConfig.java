package com.semperos.screwdriver.js;

import org.mozilla.javascript.ScriptableObject;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/14/13
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class RjsConfig extends ScriptableObject {
    private String mainConfigFile;
    private String optimize;
    private String baseUrl;
    private boolean findNestedDependencies;
    private String[] include;
    private String[] insertRequire;
    private String name;
    private boolean wrap;
    private String out;
    private int logLevel;
    private String originalBaseUrl;

    public String getMainConfigFile() {
        return mainConfigFile;
    }

    public void setMainConfigFile(String mainConfigFile) {
        this.mainConfigFile = mainConfigFile;
    }

    public String getOptimize() {
        return optimize;
    }

    public void setOptimize(String optimize) {
        this.optimize = optimize;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isFindNestedDependencies() {
        return findNestedDependencies;
    }

    public void setFindNestedDependencies(boolean findNestedDependencies) {
        this.findNestedDependencies = findNestedDependencies;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }

    public String[] getInsertRequire() {
        return insertRequire;
    }

    public void setInsertRequire(String[] insertRequire) {
        this.insertRequire = insertRequire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public String getOriginalBaseUrl() {
        return originalBaseUrl;
    }

    public void setOriginalBaseUrl(String originalBaseUrl) {
        this.originalBaseUrl = originalBaseUrl;
    }

    @Override
    public String getClassName() {
        return "com.semperos.screwdriver.js.RjsConfig";
    }
}
