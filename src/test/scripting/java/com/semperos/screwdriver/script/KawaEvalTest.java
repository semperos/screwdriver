package com.semperos.screwdriver.script;

import com.semperos.screwdriver.script.kawa.KawaEval;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/12/13
 * Time: 1:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class KawaEvalTest {
    @Test
    public void testKawaEvalString() throws Exception {
        assertEquals("foo", KawaEval.evalString("\"foo\""));
        assertEquals("42", KawaEval.evalString("(+ 21 21)").toString());
    }

    @Test
    public void testKawaEvalFile() throws Exception {
        File kawaScript = new File("src/test/resources/com/semperos/screwdriver/sample/screwdriver-config.scm");
        assertEquals("42", KawaEval.evalFile(kawaScript).toString());
    }
}
