package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.build.BuildJs;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Watch files that target JavaScript
 */
public class BuildJsListener implements FileAlterationListener {
    private static Logger logger = Logger.getLogger(BuildJsListener.class);
    JsAssetSpec assetSpec;
    BuildJs buildJs;

    public BuildJsListener(JsAssetSpec jsAssetSpec) {
        assetSpec = jsAssetSpec;
        buildJs = new BuildJs(assetSpec);
    }

    public void buildFile(File file) {
        try {
            buildJs.build(file);
        } catch (RhinoEvaluatorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(File file) {
        buildJs.delete(file);
    }

    @Override
    public void onDirectoryDelete(File file) {
        // delete output directory
    }

    @Override
    public void onFileCreate(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug("Responding to the creation of a new file " + file.toString() + " by compiling it to JavaScript.");
            buildFile(file);
        }
    }

    @Override
    public void onFileChange(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug("Responding to change in file " + file.toString() + " by recompiling it to JavaScript.");
            buildFile(file);
        }
    }

    @Override
    public void onFileDelete(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug("Responding to deletion of file " + file.toString() + " by deleting its output counterpart.");
            deleteFile(file);
        }
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
    public void onStop(FileAlterationObserver fileAlterationObserver) {
        // this is *every* stop of polling
    }
}
