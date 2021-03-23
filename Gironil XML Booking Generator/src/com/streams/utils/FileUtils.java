
package com.streams.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
/**
 *
 * @author Shehata.Ibrahim
 * 
 */
public class FileUtils {
	/**
    *
    * @param schemaPath : XML Schema file path.
    * @param xmlFilePath : XML file path for the input XML file
    * @return : Empty String if the XML file is valid against the schema ,Error message if the file is inValid
    * @throws SAXException
    * @throws IOException
    */
   public static String validateToSchema(String schemaPath, String xmlFilePath) throws SAXException, IOException {
       try {
           Source xmlFile = new StreamSource(new File(xmlFilePath));
           SchemaFactory schemaFactory = SchemaFactory
                   .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
           Schema schema = schemaFactory.newSchema(new File(schemaPath));
           Validator validator = schema.newValidator();
           try {
               validator.validate(xmlFile);
               return "";
           } catch (SAXException e) {
               return e.getLocalizedMessage();
           }
       } catch (MalformedURLException ex) {
           return ex.getLocalizedMessage();
       }
   }
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
