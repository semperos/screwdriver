package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.ArrayList;

/**
 * Base representation of a type of asset in the pipeline.
 */
public class AssetSpec {
    private File assetPath;
    private ArrayList<String> assetExtensions;
    private ArrayList<String> assetIncludes;
    private File outputPath;

    public File getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(File assetPath) {
        this.assetPath = assetPath;
    }

    public ArrayList<String> getAssetExtensions() {
        return assetExtensions;
    }

    public void setAssetExtensions(ArrayList<String> assetExtensions) {
        this.assetExtensions = assetExtensions;
    }

    public File getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(File outputPath) {
        this.outputPath = outputPath;
    }

    public AssetSpec(File assetPath, ArrayList<String> assetExtensions, File outputPath) {
        this(assetPath, assetExtensions, null, outputPath);
    }

    public AssetSpec(File assetPath, ArrayList<String> assetExtensions, ArrayList<String> assetIncludes, File outputPath) {
        this.assetPath = assetPath;
        this.assetExtensions = assetExtensions;
        this.assetIncludes = assetIncludes;
        this.outputPath = outputPath;
    }

    /**
     * Retrieve all actual {@code File} objects for this asset type
     *
     * @return List of all assets of this type currently in filesystem
     */
    public ArrayList<File> findFiles() {
        ArrayList<File> assets = new ArrayList<File>();
        ArrayList<String> extensions = getAssetExtensions();
        for (String ext : extensions) {
            RegexFileFilter fileFilter;
            if (assetIncludes != null && assetIncludes.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < assetIncludes.size(); i++) {
                    sb.append("(")
                            .append(assetIncludes.get(i).trim())
                            .append(")");
                    if (i != assetIncludes.size() - 1) {
                        sb.append("|");
                    }
                }
                fileFilter = new RegexFileFilter(sb.toString());
            } else {
                fileFilter = new RegexFileFilter(".*?\\." + ext);
            }

            File path = getAssetPath();
            if (!path.exists()) {
                throw new RuntimeException("One of the directories that Screwdriver expects to work with " +
                        "does not exist: " + path.getAbsolutePath());
            } else {
                assets.addAll(FileUtils.listFiles(
                        path,
                        fileFilter,
                        DirectoryFileFilter.DIRECTORY
                ));
            }
        }
        return assets;
    }

    public static String getRelevantPath(File baseThatDoesNotMatter, File baseForOutput, File sourceFile) {
        String fromBase = baseThatDoesNotMatter.getAbsolutePath();
        if (!fromBase.endsWith("/")) {
            fromBase += "/";
        }
        String toBase = baseForOutput.getAbsolutePath();
        if (!toBase.endsWith("/")) {
            toBase += "/";
        }
        String namePattern = sourceFile.getName() + "$";
        String relevantPath = sourceFile.getAbsolutePath().replace(fromBase, "");
        relevantPath = relevantPath.replaceAll(namePattern, "");
        return toBase + relevantPath;
    }

    public File outputFile(File sourceFile) {
        String targetName = outputFileName(sourceFile.getName());
        String path = getRelevantPath(assetPath, outputPath, sourceFile);
        return FileUtils.getFile(path, targetName);
    }

    /**
     * NOTE: This method is intended to be overridden by subclasses
     * that need specific file name mangling for compiled output,
     * e.g., {@literal foo.coffee} to {@literal foo.js}.
     *
     * @param sourceFileName
     * @return
     */
    protected String outputFileName(String sourceFileName) {
        return sourceFileName;
    }
}
