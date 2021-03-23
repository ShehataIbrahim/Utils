/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

import com.streams.gironil.Configs;
import com.streams.gironil.persistance.Batches;
import com.streams.gironil.persistance.DBController;
import com.streams.gironil.persistance.FilesBatches;
import com.streams.gironil.persistance.JdbcController;
import com.streams.gironil.persistance.Transactions;
import com.streams.gironil.persistance.exceptions.PreexistingEntityException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shehata.Ibrahim
 */
public class BookingCreator {
    
    private static Pattern p = Pattern.compile("[0-9]+");
    public static final String DATE_TIME_PATTERN = "yyyyMMddhhmmssSS";
    public static final String FILE_DATE_PATTERN = "yyyyMMdd-hhmmss";
    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final int BUFFER_SIZE = 500;
    
    public static boolean isNumeric(String value) {
        Matcher m = p.matcher(value);
        return m.matches();
    }
    
    public static String paddString(String toPad, int width, char fill) {
        return new String(new char[width - toPad.length()]).replace('\0', fill) + toPad;
    }
    
    public static String paddInt(int toPadNumber, int width, char fill) {
        String toPad = new Integer(toPadNumber).toString();
        return new String(new char[width - toPad.length()]).replace('\0', fill) + toPad;
    }
    
    public static String paddLong(long toPadNumber, int width, char fill) {
        String toPad = new Long(toPadNumber).toString();
        return new String(new char[width - toPad.length()]).replace('\0', fill) + toPad;
    }
    
    public static String paddDouble(double toPadNumber, int width, char fill) {
        String toPad = new Double(toPadNumber).toString();
        return new String(new char[width - toPad.length()]).replace('\0', fill) + toPad;
    }
    
    public static String tailString(String toTail, int width, char fill) {
        if(width<toTail.length())
            return toTail.substring(0,width);
        return toTail + new String(new char[width - toTail.length()]).replace('\0', fill);
    }
    
    public static void main(String[] args) {
        for(int i=0;i<100;i++)
        {
             formatter.applyPattern(DATE_TIME_PATTERN);
        System.out.println(tailString(formatter.format(new Date()), 16, '0')+0);
        }
    }
    
    public static String generateBookingForFile(File inputFile, String processedFolder, String outputFolder) throws CorruptedFileException, EmptyFileException, IOException, MissingCompanyCodeException {
        Date creationDate = new Date();
        
        Header header = getFileHeader(inputFile, creationDate);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputFile), Configs.DEFAULT_ENCODING));
        formatter.applyPattern(FILE_DATE_PATTERN);
        String outputFileName = outputFolder + "\\" + formatter.format(creationDate) + "-booking-400.txt";
        File outputFile = new File(outputFileName);
        int id = 0;
        while (outputFile.exists()) {
            
            outputFileName = outputFolder + "\\" + formatter.format(creationDate) + id + "-booking-400.txt";
            outputFile = new File(outputFileName);
            id++;
        }
        outputFile.createNewFile();
        PrintStream writer = new PrintStream(outputFile, Configs.DEFAULT_ENCODING);
        writer.println(header.getHeaderString());
        doFileMapping(reader, writer, creationDate, header.getBatchId());
        writer.flush();
        writer.close();
        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.moveFile(inputFile.getAbsolutePath(), processedFolder);
        
        
        return outputFile.getName() + "\t was created for: " + inputFile.getName();
    }
    private static final Random rand = new Random(1000);
    
    private static Header getFileHeader(File f, Date creationDate) throws IOException, EmptyFileException, CorruptedFileException, MissingCompanyCodeException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
       
        int transactionsCount = 0;
        String companyCode = "";
        double totalAmount = 0;
        if (reader.ready()) {
            transactionsCount++;
            String[] split = reader.readLine().split(",");
            companyCode = split[8];
            if (split[1].length() != 14 || !isNumeric(split[1])) {
                throw new CorruptedFileException(transactionsCount, "Account number is invalid");
            }
            if (!split[4].equals(split[5])) {
                throw new CorruptedFileException(transactionsCount, "Amount fileds are not the same");
            }
            try {
                
                totalAmount += Double.parseDouble(split[4]);
            } catch (Exception e) {
                throw new CorruptedFileException(transactionsCount, "Amount is not numeric");
            }
        } else {
            throw new EmptyFileException();
        }
        while (reader.ready()) {
            transactionsCount++;
            String[] split = reader.readLine().split(",");
            if (!companyCode.equals(split[8])) {
                throw new CorruptedFileException(transactionsCount, "Company code is not the same");
            }
            if (split[1].length() != 14 || !isNumeric(split[1])) {
                throw new CorruptedFileException(transactionsCount, "Account number is invalid");
            }
            if (!split[4].equals(split[5])) {
                throw new CorruptedFileException(transactionsCount, "Amount fileds are not the same");
            }
            try {
                //String fl=String.valueOf(Float.parseFloat(split[4]));
                
                totalAmount += Double.parseDouble(split[4]);
            } catch (Exception e) {
                throw new CorruptedFileException(transactionsCount, "Amount is not numeric");
            }
        }
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        System.out.println(totalAmount);
        StringBuilder header = new StringBuilder("01");
        header.append(paddString(companyCode, 15, '0'));
        String companyName =null;
        try{
         companyName = getCompanyName(companyCode);
        }catch(NullPointerException ex)
        {
            throw new com.streams.MissingCompanyCodeException(companyCode);
        }
        header.append(tailString("\t" + companyName, 50, ' '));
        formatter.applyPattern(DATE_TIME_PATTERN);
        header.append(tailString(formatter.format(creationDate), 16, '0')+"0");
        formatter.applyPattern(DATE_PATTERN);
        header.append(paddInt(transactionsCount, 7, '0'));
        double originalTotal = totalAmount;
        totalAmount *= 100;
        totalAmount = Math.round(totalAmount);
        
        header.append(paddLong(new Double(totalAmount).longValue(), 15, '0'));
        header.append(formatter.format(creationDate));
        reader.close();
        Header h = new Header();
        
        h.setHeaderString(tailString(header.toString(), 189, ' '));
        Batches b = new Batches();
        String batchId = "H" + getId();
        b.setCompanyCode(companyCode);
        b.setCreationDate(creationDate);
        b.setNumberOfTransactions(transactionsCount);
        b.setTotalAmount(new Float(originalTotal));
        b.setBatchId(batchId);
        b.setCompanyName(companyName);
        h.setBatchId(batchId);
        try {
            DBController.getBatchController().create(b);
            DBController.getFilesBatchesController().create(new FilesBatches(batchId,f.getName(),new Date()));
        } catch (PreexistingEntityException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        return h;
    }
    static SimpleDateFormat formatter = new SimpleDateFormat();
    
    private static String getCompanyName(String companyCode) {
        return DBController.getCompanyController().findCompanyInfo(companyCode).getName();
    }
    
    private static void doFileMapping(BufferedReader reader, PrintStream writer, Date creationDate, String batchId) throws IOException {
        formatter.applyPattern(DATE_TIME_PATTERN);
        String dateTime = tailString(formatter.format(creationDate), 16, '0')+"0";
        
        formatter.applyPattern(DATE_PATTERN);
        ArrayList<Transactions> transactions = new ArrayList<Transactions>();
        while (reader.ready()) {
            Transactions trans = new Transactions();
            trans.setTransactionId("T" + getId());
            StringBuilder transaction = new StringBuilder("02");
            String[] split = reader.readLine().split(",");
            /* Setting Transaction Info*/
            trans.setAccountNumber(split[1]);
            trans.setCustomerName(split[2]);
            trans.setCustomerReference(split[3]);
            trans.setBicCode(split[6]);
            trans.setBranchCode(split[7]);
            trans.setCompanyCode(split[8]);
            trans.setBatchId(batchId);
            trans.setTransactionSerial(Integer.parseInt(split[0]));
            trans.setCreationDate(creationDate);
            /*End of setting Transaction Info */
            transaction.append(paddString(split[8], 15, '0'));
            transaction.append(dateTime);
            transaction.append("0" + split[1]);
            transaction.append(paddString(split[2], 50, '0'));
            transaction.append(paddString(split[3], 20, '0'));
            Double originalAmount = Double.parseDouble(split[4]);
           // System.out.println("original total amount "+originalAmount);
            trans.setAmount(originalAmount.floatValue());
            String amount = paddLong(Math.round(originalAmount * 100), 15, '0');
            transaction.append(amount);
            transaction.append(amount);
            transaction.append(paddString(split[6], 20, '0'));
            transaction.append(paddString(split[7], 20, '0'));
            
            writer.println(transaction.toString());
            transactions.add(trans);
            if (transactions.size() == BUFFER_SIZE) {
                insertTransactions(transactions);
                transactions = new ArrayList<Transactions>();
            }
        }
        insertTransactions(transactions);
    }
    
    public static String generateBookingForMultipleFiles(ArrayList<File> inputFiles, String processedFolder, String outputFolder) throws CorruptedFileException, EmptyFileException, IOException, MissingCompanyCodeException {
        Date creationDate = new Date();
        formatter.applyPattern(FILE_DATE_PATTERN);
        String outputFileName = outputFolder + "\\" + formatter.format(creationDate) + "-booking-400.txt";
        File outputFile = new File(outputFileName);
        int id = 0;
        while (outputFile.exists()) {
            
            outputFileName = outputFolder + "\\" + formatter.format(creationDate) + id + "-booking-400.txt";
            outputFile = new File(outputFileName);
            id++;
        }
        outputFile.createNewFile();
        PrintStream writer = new PrintStream(outputFile, Configs.DEFAULT_ENCODING);
        for (File inputFile : inputFiles) {
            Header header = getFileHeader(inputFile, creationDate);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(inputFile), Configs.DEFAULT_ENCODING));
            
            writer.println(header.getHeaderString());
            doFileMapping(reader, writer, creationDate, header.getBatchId());
            writer.flush();
            
            try {
                reader.close();
            } catch (Exception e) {
            }
            FileUtils.moveFile(inputFile.getAbsolutePath(), processedFolder);
            
        }
        writer.close();
        return outputFile.getName() + "\t was created for: List of Files";
    }
    
    private static String getId() {
        formatter.applyPattern(DATE_TIME_PATTERN);
        String id = formatter.format(new Date());
        id = id + rand.nextInt();
        return id;
        
    }
  //  public final static ArrayList<Thread> runningThreads = new ArrayList<Thread>();

    private static void insertTransactions(final ArrayList<Transactions> transactions) {
       /* Thread t = new Thread() {
            @Override
            public void run() {
                JdbcController.insertTransactions(transactions);
                
                runningThreads.remove(this);
            }
        };
        t.start();
        runningThreads.add(t);
        */
           JdbcController.insertTransactions(transactions);
    }
}
