package com.semperos.screwdriver.js.coffeescript;

import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoffeeScriptCompilerTest {
    private CoffeeScriptCompiler compiler;

    @Before
    public void setUp() throws Exception {
        this.compiler = new CoffeeScriptCompiler();
    }

    @Test
    public void testCompile() throws Exception {
        String expected = "(function() {\n" +
                "  var asdf;\n" +
                "\n" +
                "  asdf = 2 + 2;\n" +
                "\n" +
                "}).call(this);".trim();
        String result = this.compiler.compile("asdf = 2 + 2;").trim();
        assertEquals(result,expected);
    }
}
