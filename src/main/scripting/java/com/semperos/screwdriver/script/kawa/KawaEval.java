package com.semperos.screwdriver.script.kawa;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/11/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class KawaEval {
    public static Object evalString(String script) throws Exception {
        ScriptEngine engine = setupEngine();
        return engine.eval(script);
    }

    public static Object evalFile(File file) throws Exception {
        FileReader fr = new FileReader(file);
        ScriptEngine engine = setupEngine();
        return engine.eval(fr);
    }

    private static ScriptEngine setupEngine() {
        ScriptEngineManager man = new ScriptEngineManager();
        return man.getEngineByName("scheme");
    }
}
