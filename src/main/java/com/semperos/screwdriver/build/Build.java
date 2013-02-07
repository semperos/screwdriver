package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.RhinoEvaluatorException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Build {
    /**
     * Since most of the compilers we're working with are not
     * incremental and require their source code in one String datum,
     * this method provides that utility.
     *
     * This method should use a default {@link java.nio.charset.Charset},
     * preferrably {@code UTF-8}.
     *
     * @param sourceFile The source code to be compiled as part of this build process
     * @return The content of the file as a {@code String}
     */
    public String readFile(File sourceFile) throws IOException;

    /**
     * Specify the {@link java.nio.charset.Charset} to use when reading
     * the source code file.
     *
     * @param sourceFile
     * @param charset
     * @return
     */
    public String readFile(File sourceFile, Charset charset) throws IOException;

    public String outputFileName(String sourceFileName);

    /**
     * Write the given source code string to a file based
     * on the source file's name. This applies logical transformations
     * relative to a particular compilation, e.g., it would write the compiled
     * JavaScript output of a file named {@code foo.coffee} to a file named
     * {@code foo.js} with the same nested directory structure.
     *
     * @param sourceCode
     * @param sourceFile
     */
    public void writeFile(String sourceCode, File sourceFile) throws IOException;

    /**
     * This arity of {@code writeFile} does the same as the other,
     * but also allows overriding the default file name transformations
     * there described and allows passing in a custom name.
     *
     * Note that this custom name should include the file extension,
     * e.g., {@code bar.custom.js}.
     *
     * @param sourceCode
     * @param sourceFile
     * @param charset
     */
    public void writeFile(String sourceCode, File sourceFile, Charset charset) throws IOException;

    /**
     * Convenience function intended to read a file, compile it,
     * and then write the output.
     *
     * @param sourceFile
     */
    public void build(File sourceFile) throws IOException, RhinoEvaluatorException;

    /**
     * Convenience method same as other arity, but allowing a custom
     * file name for the output.
     *
     * @param sourceFile
     * @param charset
     */
    public void build(File sourceFile, Charset charset) throws IOException, RhinoEvaluatorException;

    /**
     * This is designed as the usual entry point for building assets.
     * It is assumed that assets will have been passed in at object
     * instantiation and that this method would use that internal
     * state.
     */
    public void buildAll() throws IOException, RhinoEvaluatorException;
}
