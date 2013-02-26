package com.semperos.screwdriver.scripting.js;

import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;

/**
 * Evaluate CoffeeScript to run Screwdriver
 */
public class CoffeeScriptEval {
    private static String compile(File file) throws IOException, RhinoEvaluatorException {
        CoffeeScriptCompiler csc = new CoffeeScriptCompiler();
        return csc.compile(file);
    }

    private static ScriptEngine setupEngine() {
        ScriptEngineManager man = new ScriptEngineManager();
        return man.getEngineByExtension("js");
    }

    public static void evalFile(File file) throws IOException, RhinoEvaluatorException, ScriptException {
        String js = compile(file);
        ScriptEngine engine = setupEngine();
        engine.eval(js);
    }

}
