package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Base representation of a type of asset in the pipeline.
 */
public class AssetSpec {
    private File assetPath;
    private List<String> assetExtensions;
    private List<String> assetIncludes;
    private List<String> assetExcludes;
    private File outputPath;

    public List<String> getAssetIncludes() {
        return assetIncludes;
    }

    public void setAssetIncludes(List<String> assetIncludes) {
        this.assetIncludes = assetIncludes;
    }

    public List<String> getAssetExcludes() {
        return assetExcludes;
    }

    public void setAssetExcludes(List<String> assetExcludes) {
        this.assetExcludes = assetExcludes;
    }

    public File getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(File assetPath) {
        this.assetPath = assetPath;
    }

    public List<String> getAssetExtensions() {
        return assetExtensions;
    }

    public void setAssetExtensions(List<String> assetExtensions) {
        this.assetExtensions = assetExtensions;
    }

    public File getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(File outputPath) {
        this.outputPath = outputPath;
    }

    public AssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        this(assetPath, assetExtensions, null, outputPath);
    }

    public AssetSpec(File assetPath, List<String> assetExtensions, List<String> assetIncludes, File outputPath) {
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
    public List<File> findFiles() {
        List<File> assets = new ArrayList<File>();
        List<String> extensions = getAssetExtensions();
        for (String ext : extensions) {
            IOFileFilter fileFilter;
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
            } else if (assetExcludes != null && assetExcludes.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < assetExcludes.size(); i++) {
                    sb.append("(")
                            .append(assetExcludes.get(i).trim())
                            .append(")");
                    if (i != assetExcludes.size() - 1) {
                        sb.append("|");
                    }
                }
                fileFilter = new NotFileFilter(new RegexFileFilter(sb.toString()));
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
     * In the simplest case (copying), a target file name is the
     * same as the source file name. For compilation workflows,
     * this looks more like {@literal foo.coffee} to {@literal foo.js}.
     *
     * @param sourceFileName
     * @return
     */
    protected String outputFileName(String sourceFileName) {
        return sourceFileName;
    }
}
