package com.streams.utils;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.SAXException;

/**
 * @author Shehata Ibrahim
 * @since <22-5-2011>
 * @version 1.0 General Purpose SAX Parser used to read XML file targeting some
 *          specific node to save its value to a java bean
 */
public abstract class XMLSTAXParser {

	private String currentPath = ""; // Used to save current Node Path
	private HashMap<String, String> valuesMap = new HashMap<String, String>(); // Used
																				// to
																				// Save
																				// (Path,Value)
																				// pairs
	private int insertionCount = 0;
	private int invalidCount = 0;
	// private String targetNode; // The Tag specifies the Object start
	boolean objectStarted = false; // Flag to record that object start
	boolean writingXML = false; // Flag to record if there is XML Structure
								// saving is currently running
	String currentXML = ""; // Current stored XML Structure
	String currentXMLPath = ""; // Current XML Path
	HashSet<String> xmlNodes = new HashSet<String>(); // Set of nodes needs to
														// have its XML
														// structure
														// saved
	int bulkSize = 1000;
	ArrayList<HashMap<String, String>> nodesList = new ArrayList<HashMap<String, String>>();
	HashSet<String> targetNode;
	String return_value = "";
	String return_xpath = "";
	boolean isAppend = false;
	private String errors = null;// used for logging parsing errors

	private HashMap<String, Object> paramList = new HashMap<String, Object>();

	public void setParameter(String paramName, Object value) {
		if (!paramList.containsKey(paramName))
			paramList.put(paramName, value);
		else
			System.out.println("duplicate Parameter setting In XMLSAXParser \nParameter Name:"
					+ paramName + "\n Parameter Value:" + value);
	}

	public Object getParameter(String paramName) {
		return paramList.get(paramName);
	}

	public String getErrors() {
		if (errors == null || errors.isEmpty())
			return null;
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	/**
	 * 
	 * @param ObjectNode
	 *            : Defines the tag where the Object starts
	 */
	public XMLSTAXParser(HashSet<String> ObjectNode) {
		targetNode = ObjectNode;

	}

	public int getBulkSize() {
		return bulkSize;
	}

	public void setBulkSize(int bulkSize) {
		this.bulkSize = bulkSize;
	}

	public String parseFileWithReturn(String fileName, String returnXpath,
			boolean appendObjects) {
		return_xpath = returnXpath;
		isAppend = appendObjects;
		parseFile(fileName, false);

		return return_value;
	}

	public void parseFile(String fileName, boolean isSingleNode) {
		Date startDate = null;
		Date endDate = null;
		if (isSingleNode)
			bulkSize = 1;
		try {
			XMLInputFactory xmlif = XMLInputFactory.newInstance();

			XMLStreamReader xmlr = xmlif.createXMLStreamReader(fileName,
					new FileInputStream(fileName));
			startDate = new Date();
			while (xmlr.hasNext()) {
				int eventtype = xmlr.next();
				switch (eventtype) {
				case XMLEvent.START_ELEMENT:
					HashMap<String, String> att = new HashMap<String, String>();
					if (xmlr.getAttributeCount() > 0) {

						for (int i = 0; i < xmlr.getAttributeCount(); i++) {
							att.put(xmlr.getAttributeLocalName(i),
									xmlr.getAttributeValue(i));
						}
					}
					startElement(xmlr.getLocalName(), att);
					break;

				case XMLEvent.END_DOCUMENT:
					if (nodesList.size() > 0) {

						insertObjects(nodesList);
					} else if (isAppend) {
						nodesList.add(valuesMap);
						insertObjects(nodesList);
						if (valuesMap.containsKey(return_xpath))
							return_value = valuesMap.get(return_xpath);
					}
					break;
				case XMLEvent.END_ELEMENT:
					boolean exitFlag = isSingleNode && objectStarted;
					endElement(xmlr.getLocalName());
					if (exitFlag && !objectStarted)
						return;
					break;
				case XMLEvent.CHARACTERS:
					characters(xmlr.getText());
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return Set of all XML Paths needs to be Saved as XML Structure
	 */
	public HashSet<String> getXmlNodes() {
		return xmlNodes;
	}

	/**
	 * 
	 * @param xmlNodes
	 *            Set of all XML Paths needs to be Saved as XML Structure
	 */
	public void setXmlNodes(HashSet<String> xmlNodes) {
		this.xmlNodes = xmlNodes;
	}

	/**
	 * 
	 * @param xmlMap
	 *            Node Object XML saved in Path,Value Pairs that will be used in
	 *            insertion process
	 */
	public abstract void insertObjects(
			ArrayList<HashMap<String, String>> nodesMaps);

	public void startElement(String qName, HashMap<String, String> attributes) {
		if (!targetNode.contains(qName)) { // if it is not the target
											// node
											// that starts the object
											// recording and the object
											// didn't start yet ... node
											// will be discarded
			if (!objectStarted)
				return;
		} else
		// if it is the target node then turn the flag to record that the
		// object started
		{
			objectStarted = true;
		}
		if (xmlNodes.contains(currentPath + "/" + qName)) { // Check if current
															// tag is an XML Tag
			writingXML = true; // change the flag to indicate that XML structure
								// capping started
			currentXMLPath = currentPath + "/" + qName; // save the current Path
														// of the XML to be
														// saved as XML Key
		}
		if (writingXML) {// Start recording the XML structure "Tags /Attributes"
			currentXML += "<" + qName;
			for (String key : attributes.keySet()) {
				currentXML += " " + key + "=\"" + attributes.get(key) + "\"";
			}
			currentXML += ">";
		}
		currentPath += "/" + qName; // add Current tag the whole Path
		for (String key : attributes.keySet()) {
			// add all attributes as /path/attribute-name$ , value
			valuesMap.put(currentPath + "/" + key + "$", attributes.get(key));
		}

	}

	public void characters(String s) throws SAXException {
		if (!objectStarted)
			return;
		// get current value
		if (!s.trim().isEmpty()) {
			if (valuesMap.containsKey(currentPath)) {
				valuesMap.put(currentPath, valuesMap.get(currentPath) + ","
						+ s);
			} else {
				valuesMap.put(currentPath, s); // save path,value Pair
			}
			if (writingXML) // if XML structure capping running ..add the
							// current string to the current XML structure
				currentXML += s;
		}
	}

	public void endElement(String qName) {
		if (writingXML) {// if capping XML save keep constructing the XML
							// Structure
			currentXML += "</" + qName + ">";
		}
		if (xmlNodes.contains(currentPath)) { // if reached the end of the XML
												// structure capping save the
												// path,value Pair and reset
												// everything
			writingXML = false;
			valuesMap.put(currentXMLPath, currentXML);
			currentXML = "";
			currentXMLPath = "";
		}
		if (currentPath.contains("/" + qName)) { // remove the current tag from
													// the recorded path
			currentPath = currentPath.substring(0,
					currentPath.lastIndexOf("/" + qName));
		}
		if (targetNode.contains(qName)) { // if reached the end of the
											// object
											// 1-call the abstract
											// method
											// 2-reset the path,value
											// map
											// 3- change the flag to
											// search for a new object
			if (!isAppend) {
				nodesList.add(valuesMap);
				if (nodesList.size() == bulkSize) {
					insertObjects(nodesList);
					nodesList = new ArrayList<HashMap<String, String>>();
				}
				if (valuesMap.containsKey(return_xpath))
					return_value = valuesMap.get(return_xpath);
				valuesMap = new HashMap<String, String>();
			}
			objectStarted = false;

		}

	}

	/**
	 * sample method for XMLSAXParser usage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
            SimpleDateFormat formatter= new SimpleDateFormat("mm:ss.SS");
            for(int i=0;i<5;i++)
            {
                
            Date startDate=new Date();
		HashSet<String> nodes = new HashSet<String>();
		nodes.add("GrpHdr");
		nodes.add("OrgnlGrpInf");
		XMLSTAXParser h = new XMLSTAXParser(nodes) {

			@Override
			public void insertObjects(
					ArrayList<HashMap<String, String>> nodesMaps) {
				/*System.out.println("Insert  Object call");
				for (Iterator iterator = nodesMaps.iterator(); iterator
						.hasNext();) {
					HashMap<String, String> hashMap = (HashMap<String, String>) iterator
							.next();
					for (String key : hashMap.keySet())
						System.out.println(key + ": " + hashMap.get(key));
					System.out.println("-----------------");
				}
*/
			}

		};
		h.setBulkSize(100);
		String date = h.parseFileWithReturn(
				"C:\\Users\\shehata.ibrahim\\Desktop\\Giro\\outputs\\outputs\\0000000001_ACC_EgyptPost3049210531.xml", "/GrpHdr/CreDtTm",
				true);
	        Date endDate=new Date();
                System.out.println(formatter.format(startDate));
                System.out.println(formatter.format(endDate));
                System.out.println(endDate.getTime()-startDate.getTime());
                System.out.println("-----"+i+"-----");
            }
	}

	public void setInsertionCount(int insertionCount) {
		this.insertionCount = insertionCount;
	}

	public int getInsertionCount() {
		return insertionCount;
	}

	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}

	public int getInvalidCount() {
		return invalidCount;
	}

}
