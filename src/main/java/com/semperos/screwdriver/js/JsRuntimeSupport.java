package com.semperos.screwdriver.js;

import org.apache.log4j.Logger;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsRuntimeSupport extends ScriptableObject {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(JsRuntimeSupport.class);
    private static final boolean silent = false;

    @Override
    public String getClassName() {
        return "test";
    }

    public static void print(Context cx, Scriptable thisObj, Object[] args,
                             Function funObj) {
        if (silent)
            return;
        for (int i = 0; i < args.length; i++)
            logger.info(Context.toString(args[i]));
    }

    public static void load(Context cx, Scriptable thisObj, Object[] args,
                            Function funObj) throws FileNotFoundException, IOException {
        JsRuntimeSupport shell = (JsRuntimeSupport) getTopLevelScope(thisObj);
        for (int i = 0; i < args.length; i++) {
            logger.info("Loading file " + Context.toString(args[i]));
            shell.processSource(cx, Context.toString(args[i]));
        }
    }

    private void processSource(Context cx, String filename)
            throws IOException {
        cx.evaluateReader(this, new InputStreamReader(getInputStream(filename)), filename, 1, null);
    }

    private InputStream getInputStream(String file) throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        return cl.getResourceAsStream(file);
    }
}
