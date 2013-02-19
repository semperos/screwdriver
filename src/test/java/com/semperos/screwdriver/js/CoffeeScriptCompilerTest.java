package com.semperos.screwdriver.js;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
                "  var __hasProp = {}.hasOwnProperty,\n" +
                "    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };\n" +
                "\n" +
                "  define([], function() {\n" +
                "    var Screwdriver;\n" +
                "    return Screwdriver = (function(_super) {\n" +
                "\n" +
                "      __extends(Screwdriver, _super);\n" +
                "\n" +
                "      function Screwdriver() {\n" +
                "        return Screwdriver.__super__.constructor.apply(this, arguments);\n" +
                "      }\n" +
                "\n" +
                "      return Screwdriver;\n" +
                "\n" +
                "    })(Tool);\n" +
                "  });\n" +
                "\n" +
                "}).call(this);";
        String result = this.compiler.compile(new File("src/test/resources/com/semperos/screwdriver/sample/assets/javascripts/tools/screwdriver.coffee"));
        assertEquals(result.trim(),expected.trim());
    }
}
