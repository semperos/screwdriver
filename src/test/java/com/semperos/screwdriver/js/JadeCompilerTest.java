package com.semperos.screwdriver.js;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class JadeCompilerTest {
    private JadeCompiler compiler;

    @Before
    public void setup() throws Exception {
        compiler = new JadeCompiler();
    }

    @Test
    public void testCompile() throws Exception {
        String pageTitle = "Screwdriver Compilation Test";
        boolean usingJade = true;
        HashMap<String, Object> compilerLocals = new HashMap<String, Object>();
        compilerLocals.put("pageTitle", pageTitle);
        compilerLocals.put("youAreUsingJade", usingJade);
        compiler.setCompilerLocals(compilerLocals);
        String expected = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <title>" + pageTitle + "</title>\n" +
                "    <script type=\"text/javascript\">\n" +
                "      if (foo) {\n" +
                "         bar()\n" +
                "      }\n" +
                "    </script>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>Jade - node template engine</h1>\n" +
                "    <div id=\"container\">\n" +
                "      <p>You are amazing</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
        HashMap<String,Object> compilerOptions = new HashMap<>();
        compilerOptions.put("pretty", true);
        compiler.setCompilerOptions(compilerOptions);
        String result = compiler.compile(new File("src/test/resources/com/semperos/screwdriver/sample/assets/server_templates/index.jade"));
        assertEquals(result.trim(),expected.trim());
    }
}
