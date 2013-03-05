package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.build.Build;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Common features to listeners
 *
 * @todo Create config interface to this class that allows passing in custom strings for log messages, to say things like "by compiling to JavaScript"
 *
 */
public class BuildListener implements FileAlterationListener {
    private static Logger logger = Logger.getLogger(BuildListener.class);
    protected AssetSpec assetSpec;
    protected Build build;

    public BuildListener(AssetSpec assetSpec, Build build) {
        this.assetSpec = assetSpec;
        this.build = build;
    }

    public void build(File file) {
        try {
            build.build(file);
        } catch (Exception e) {
            logger.error("An error occurred while monitoring the file system.");
            e.printStackTrace();
        }
    }

    public void delete(File file) {
        build.delete(file);
    }

    public String buildClass() {
        return "[[" + build.getClass().getSimpleName() + "]]";
    }

    @Override
    public void onFileCreate(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug(buildClass() + " Responding to the creation of a new file " + file.toString() + " by processing it.");
            build(file);
        }
    }

    @Override
    public void onFileChange(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug(buildClass() +" Responding to change in file " + file.toString() + " by processing it.");
            build(file);
        }
    }

    @Override
    public void onFileDelete(File file) {
        if (FileUtil.isActiveFile(file, assetSpec)) {
            logger.debug(buildClass() + " Responding to deletion of file " + file.toString() + " by deleting its output counterpart.");
            delete(file);
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
    public void onDirectoryDelete(File file) {
        // delete output directory
    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
        // this is *every* stop of polling
    }
}
