package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.build.BuildJs;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Handle how CoffeeScript maps to JavaScript files
 */
public class CompileCoffeeScriptListener implements FileAlterationListener {
    private static Logger logger = Logger.getLogger(CompileCoffeeScriptListener.class);
    JsAssetSpec jsAssetSpec;
    BuildJs buildJs;

    public CompileCoffeeScriptListener(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
        buildJs = new BuildJs(jsAssetSpec);
    }

    public void buildFile(File file) {
        try {
            buildJs.build(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RhinoEvaluatorException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(File file) {
        buildJs.delete(file);
    }

    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
        // this is *every* start of polling
    }

    @Override
    public void onDirectoryCreate(File file) {
        // no op
    }

    @Override
    public void onDirectoryChange(File file) {
        // what does change mean? if it's "rename", then need to do a recompile,
        // perhaps with configurable amount of "safety" or consistency
        // (full consistency would probably require re-compiling the whole codebase
        // in this situation, since you don't know what the previous dir name was)
    }

    @Override
    public void onDirectoryDelete(File file) {
        // delete output directory
    }

    @Override
    public void onFileCreate(File file) {
        logger.info("Compiling new file " + file.toString() + " to JavaScript.");
        buildFile(file);
    }

    @Override
    public void onFileChange(File file) {
        logger.info("Compiling " + file.toString() + " to JavaScript.");
        buildFile(file);
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("Compiling " + file.toString() + " to JavaScript.");
        deleteFile(file);
    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
        // this is *every* stop of polling
    }
}
