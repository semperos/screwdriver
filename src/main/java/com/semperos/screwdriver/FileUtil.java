package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Formalize constructor for assetSpec-specific builders.
 */
public class FileUtil {
    public static String readFile(File sourceFile) throws IOException {
        return readFile(sourceFile, Charset.forName("UTF-8"));
    }

    public static String readFile(File sourceFile, Charset charset) throws IOException {
        return FileUtils.readFileToString(sourceFile, charset);
    }

    public static void writeFile(String sourceCode, File targetFile) throws IOException {
        writeFile(sourceCode, targetFile, Charset.forName("UTF-8"));
    }

    public static void writeFile(String sourceCode, File targetFile, Charset charset) throws IOException {
        FileUtils.writeStringToFile(targetFile, sourceCode, charset);
    }

    public static void copyFile(File sourceFile, File outputFile) throws IOException {
        FileUtils.copyFile(sourceFile, outputFile);
    }

    /**
     * Limit directories to visible directories
     *
     * @return
     */
    public static IOFileFilter defaultDirectoryFilter() {
        return FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
    }

    /**
     * Setup an {@link IOFileFilter} that navigates recursively through
     * directories, returning files that match {@code extension}
     *
     * @param extension The extension (leading "." optional) to filter for
     * @return An instance of {@link IOFileFilter} to be used for a file system observer
     */
    public static IOFileFilter fileFilterForExtension(String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
        }
        IOFileFilter directories = defaultDirectoryFilter();
        IOFileFilter files       = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(extension));
        return FileFilterUtils.or(directories, files);
    }

    public static IOFileFilter fileFilterForExtensions(List<String> extensions) {
        IOFileFilter[] fileFilters = new IOFileFilter[extensions.size()];
        for (int i = 0; i < extensions.size(); i++) {
            fileFilters[i] = fileFilterForExtension(extensions.get(i));
        }
//        IOFileFilter directories = defaultDirectoryFilter();
//        IOFileFilter files = FileFilterUtils.or(fileFilters);
        return FileFilterUtils.or(fileFilters);
    }

    public static boolean isActiveFile(File file, AssetSpec assetSpec) {
        return assetSpec.findFiles().contains(file);
    }

    public static boolean needsBuilding(File sourceFile, File outputFile) {
        return (!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile));
    }
}

