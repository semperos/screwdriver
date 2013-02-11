package com.semperos.screwdriver.build;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Formalize constructor for assetSpec-specific builders.
 */
public class BuildUtil {
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
}

