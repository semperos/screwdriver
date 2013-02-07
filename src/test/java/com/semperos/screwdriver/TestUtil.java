package com.semperos.screwdriver;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtil {
    public static File testAssetDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                "src/test/resources/com/semperos/screwdriver/sample/assets");
    }

    public static File testOutputDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                "src/test/resources/com/semperos/screwdriver/sample/output");
    }
}
