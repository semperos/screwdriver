package com.semperos.screwdriver.js;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/8/13
 * Time: 2:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class LessCompilerTest {
    private LessCompiler compiler;

    @Before
    public void setUp() throws Exception {
        this.compiler = new LessCompiler();
    }

    @Test
    public void testCompile() throws Exception {
        String source = ".class { width: (1 + 1) }";
        String expected = ".class {\n" +
                "  width: 2;\n" +
                "}".trim();
        String result = this.compiler.compile(source).trim();
        System.out.println("RESULT LESS :");
        System.out.println(result);
        assertEquals(result,expected);
    }
}
