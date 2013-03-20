package com.semperos.screwdriver.build;

import java.io.File;

/**
 * The build conversation
 */
public interface Build {
    public boolean processFile(File sourceFile, File outputFile) throws Exception;
    public boolean build(File sourceFile) throws Exception;
    public void buildAll() throws Exception;
    public void delete(File sourceFile);
}
