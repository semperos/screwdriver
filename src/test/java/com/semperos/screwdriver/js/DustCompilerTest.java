package com.semperos.screwdriver.js;

import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class DustCompilerTest {
    private DustCompiler compiler;

    @Before
    public void setUp() throws Exception {
        compiler = new DustCompiler();
    }

    @Test
    public void testCompile() throws Exception {
        File f = new File("src/test/resources/com/semperos/screwdriver/sample/assets/javascripts/views/home_template.dust");
        String result = compiler.compile(f);
        String fileBaseName = FilenameUtils.getBaseName(f.toString());
        Pattern dustPatt = Pattern.compile("dust\\.register\\(\"" + fileBaseName + "\",");
        assertTrue(result.startsWith("define"));
        assertTrue(result.endsWith("});"));
        assertTrue(dustPatt.matcher(result).find());
    }
}
