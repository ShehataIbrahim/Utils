/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

/**
 *
 * @author Shehata.Ibrahim
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
   public static String moveFile(String sourceFile, String targetFolder)
   {
   	try {
   		File source=new File(sourceFile);
   		File target=new File(targetFolder);
   		if(source.getParentFile().getAbsolutePath().equalsIgnoreCase(target.getAbsolutePath()))
   			return "";
			String fileCopyMSG = copyFile(sourceFile, targetFolder);
			if (fileCopyMSG == "")
				new File(sourceFile).delete();
			else
				return fileCopyMSG;
			return "";
		} catch (Exception e) {
			return e.getMessage();
		}
   }
	public static String copyFile(String sourceFile, String targetFolder) {
		try {
			File source = new File(sourceFile);
			if(!source.exists())
				return "source file doesnt exist";
			File target = new File(targetFolder);
			if (!target.exists())
				target.mkdirs();
			
			if(!targetFolder.endsWith("\\"))
				targetFolder+="\\";
			File dest = new File(targetFolder + source.getName());
			if (dest.exists()) {
				dest = new File(dest.getAbsolutePath()+ new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date()));
			}
			dest.createNewFile();
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(source);
				out = new FileOutputStream(dest);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "";
	}
}
