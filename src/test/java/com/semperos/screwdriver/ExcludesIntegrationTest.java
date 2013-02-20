package com.semperos.screwdriver;

import com.semperos.screwdriver.build.BuildAll;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Testing "exclude" directives for assets
 */
public class ExcludesIntegrationTest {
    Config cfg;

    @Before
    public void setup() throws Exception {
        TestUtil.deleteAssetDirectories();
        cfg = TestUtil.baseConfig();
    }

    @After
    public void tearDown() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

    @Test
    public void testRegexExclusions() throws Exception {
        ArrayList<String> cssExcludes = new ArrayList<String>();
        cssExcludes.add("modals\\.less");
        cssExcludes.add("mixins\\.less");
        cssExcludes.add("variables\\.less");
        cfg.setCssExcludes(cssExcludes);
        BuildAll.build(cfg);
        Collection<File> files = TestUtil.cssOutputFiles();
        assertEquals(2, files.size());
    }

    @Test
    public void testFileFilterExclusions() throws Exception {
        // exclude directories named "bootstrap"
        IOFileFilter dirFilter = FileFilterUtils.and(
                FileFilterUtils.notFileFilter(new NameFileFilter("bootstrap")),
                DirectoryFileFilter.DIRECTORY);
        cfg.setCssDirFilter(dirFilter);
        BuildAll.build(cfg);
        Collection<File> files = TestUtil.cssOutputFiles();
        assertEquals(2, files.size());
    }
}
