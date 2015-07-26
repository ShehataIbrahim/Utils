
package com.streams.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Shehata.Ibrahim
 */
public class FileEncodingConvertor {

    public static boolean convertFileEncoding(String srcEncoding, String targetEncoding, File sourceFile, File outputFile) throws UnsupportedEncodingException, IOException {
        BufferedReader reader = null;
        if (srcEncoding == null) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
        } else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), srcEncoding));
        }
        PrintWriter writer = null;
        if (targetEncoding == null) {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        } else {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), targetEncoding));
        }
        while (reader.ready()) {
            writer.println(reader.readLine());
        }
        writer.flush();
        writer.close();
        reader.close();
        return true;
    }

    public static boolean convertFileEncoding(String targetEncoding, File sourceFile, File outputFile) throws UnsupportedEncodingException, IOException {

        return convertFileEncoding(null, targetEncoding, sourceFile, outputFile);
    }
}
