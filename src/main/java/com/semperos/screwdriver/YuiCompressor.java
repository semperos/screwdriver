/**
 * Borrowed heavily from https://github.com/remear/java-asset-pipeline
 */
package com.semperos.screwdriver;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;

/**
 * Compress JavaScript or CSS using the YUI Compressor
 */
public class YuiCompressor {
    public static void compress(String extension, File inputFile, File outputFile) {
        try {
            Reader in = new InputStreamReader(new FileInputStream(inputFile), "UTF-8");
            Writer out = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");

            if (extension.equals("js")) {
                JavaScriptCompressor c = new JavaScriptCompressor(in, new YuiCompressorErrorReporter());
                c.compress(out,
                        -1,     // lineBreakPos
                        true,   // munge?
                        false,  // verbose?
                        false,  // preserveAllSemicolons?
                        false); // disableOptimizations?
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @todo Incorporate logging
     */
    private static class YuiCompressorErrorReporter implements ErrorReporter {

        @Override
        public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.err.println(message);
            } else {
                System.err.println("YuiCompressor WARNING: " + line + ":" + lineOffset + ':' + message);
            }
        }

        @Override
        public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.err.println(message);
            } else {
                System.err.println("YuiCompressor ERROR: " + line + ":" + lineOffset + ':' + message);
            }
        }

        @Override
        public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
            error(message, sourceName, line, lineSource, lineOffset);
            return new EvaluatorException(message);
        }
    }
}
