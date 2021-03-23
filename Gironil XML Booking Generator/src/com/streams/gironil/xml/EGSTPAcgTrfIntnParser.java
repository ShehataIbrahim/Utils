/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.xml;

import com.streams.CorruptedFileException;
import com.streams.MissingConfigurationException;
import com.streams.gironil.Configs;
import com.streams.gironil.persistance.controller.ParametersJdbcController;
import com.streams.utils.FileUtils;
import com.streams.utils.FlaggedObject;
import com.streams.utils.XMLSAXParser;
import com.streams.utils.XMLSTAXParser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Shehata.Ibrahim
 */
public class EGSTPAcgTrfIntnParser {

    FlaggedObject result = new FlaggedObject();

    public FlaggedObject parseInputFile(File inputFile, String inputSchemaKey) throws SQLException, MissingConfigurationException, SAXException, IOException, CorruptedFileException, ParserConfigurationException {

        String schemaFile = ParametersJdbcController.getSchemaPath(inputSchemaKey);
        System.out.println("schemaFile: " + schemaFile);
        String validationResult = FileUtils.validateToSchema(schemaFile, inputFile.getAbsolutePath());
        if (!validationResult.isEmpty()) {
            throw new CorruptedFileException(validationResult);
        }
        HashSet<String> nodes = new HashSet<String>();
        nodes.add(Configs.BATCH_HEADER_NODE);
        XMLSTAXParser parser = new XMLSTAXParser(nodes) {
            @Override
            public void insertObjects(ArrayList<HashMap<String, String>> nodesMaps) {
                processHeader(nodesMaps);
            }
        };
        parser.parseFile(inputFile.getAbsolutePath(), true);
        
        XMLSAXParser transParser = new XMLSAXParser(Configs.BATCH_TRANSACTION_NODE) {
            @Override
            public void insertObjects(ArrayList<HashMap<String, String>> nodesMaps) {
                processTransactions(nodesMaps);
            }
        };
        SAXParserFactory.newInstance().newSAXParser().parse(inputFile,transParser);
	
        return new FlaggedObject();
    }
    public void processTransactions(ArrayList<HashMap<String, String>> nodesMaps) {
        System.out.println("Printing Transactions");
        processObjects(nodesMaps);
    }

    public void processHeader(ArrayList<HashMap<String, String>> nodesMaps) {
           System.out.println("Printing Header");
        processObjects(nodesMaps);
    }   
    public void processObjects(ArrayList<HashMap<String, String>> nodesMaps) {
        int i=0;
        for (HashMap<String, String> nodesMap : nodesMaps) {
            i++;
            System.out.println("--------------Node "+i+"--------------");
            for (String key : nodesMap.keySet()) {
                System.out.println(key + " :\t" + nodesMap.get(key));
            }
        }
    }

    public static void main(String[] args) {
        try {

            new EGSTPAcgTrfIntnParser().parseInputFile(new File("Z:\\gironil\\0000000001_ACC_EgyptPost3049210531.xml"), Configs.XSDACCEPT_GIRO_KEY);

        } catch (SQLException ex) {
            Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingConfigurationException ex) {
            Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CorruptedFileException ex) {
            Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ParserConfigurationException ex) {
                Logger.getLogger(EGSTPAcgTrfIntnParser.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
