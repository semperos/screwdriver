package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.build.BuildCss;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Watch LESS files
 */
public class CompileLessListener implements FileAlterationListener {
    private static Logger logger = Logger.getLogger(CompileLessListener.class);
    BuildCss buildCss;

    public CompileLessListener(CssAssetSpec cssAssetSpec) {
        buildCss = new BuildCss(cssAssetSpec);
    }

    public void buildFile(File file) {
        try {
            buildCss.build(file);
        } catch (RhinoEvaluatorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(File file) {
        buildCss.delete(file);
    }

    @Override
    public void onDirectoryDelete(File file) {
        // delete output directory
    }

    @Override
    public void onFileCreate(File file) {
        logger.debug("Responding to the creation of a new file " + file.toString() + " by compiling it to CSS.");
        buildFile(file);
    }

    @Override
    public void onFileChange(File file) {
        logger.debug("Responding to change in file " + file.toString() + " by recompiling it to CSS.");
        buildFile(file);
    }

    @Override
    public void onFileDelete(File file) {
        logger.debug("Responding to deletion of file " + file.toString() + " by deleting its output counterpart.");
        deleteFile(file);
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
