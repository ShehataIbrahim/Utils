package com.streams.utils;


import java.awt.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Iterator;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Shehata.Ibrahim
 */
public class ExcelToText extends javax.swing.JFrame {


    class ComboItem {

        String key, value;

        public ComboItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public ComboItem() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key;
        }

    }
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();

    public ExcelToText() {
        jfcOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("preLines.properties"));
            for (Object key : props.keySet()) {
                comboModel.addElement(new ComboItem(key.toString(),
                                                    props.getProperty(key.toString())));
            }
        } catch (IOException ex) {
            showException(this, ex);
        }
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtPreLine = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        lblFilePath = new javax.swing.JLabel();
        txtAfterLine = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        txtSheetIndex = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSeparator = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Postfix");

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBrowseActionPerformed(evt);
                }
            });

        txtAfterLine.setText("');");

        jLabel2.setText("Suffix");

        btnGenerate.setText("Generate File");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGenerateActionPerformed(evt);
                }
            });

        txtSheetIndex.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSheetIndex.setText("0");

        jLabel3.setText("Sheet Index");

        txtSeparator.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSeparator.setText("','");

        jLabel4.setText("Separator");

        jComboBox1.setModel(comboModel);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBox1ActionPerformed(evt);
                }
            });

        javax.swing.GroupLayout layout =
            new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                                                                                          layout.createSequentialGroup().addComponent(lblFilePath,
                                                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                      javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                      Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnBrowse)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                false).addComponent(jLabel4,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    79,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    Short.MAX_VALUE).addComponent(jLabel2,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(txtSeparator,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            40,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    292,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    Short.MAX_VALUE).addComponent(jLabel3,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  66,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    false).addComponent(btnGenerate,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        Short.MAX_VALUE).addComponent(txtSheetIndex))).addComponent(txtAfterLine))).addGroup(layout.createSequentialGroup().addComponent(jLabel1,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         71,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jComboBox1,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   0,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   Short.MAX_VALUE).addComponent(txtPreLine)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jComboBox1,
                                                                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                           javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                           javax.swing.GroupLayout.PREFERRED_SIZE).addGap(2,
                                                                                                                                                                                                          2,
                                                                                                                                                                                                          2).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(txtPreLine,
                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel1)).addGap(18,
                                                                                                                                                                                                                                                                                                                                                                                18,
                                                                                                                                                                                                                                                                                                                                                                                18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnBrowse).addComponent(lblFilePath,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         23,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         18,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(txtAfterLine,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(txtSheetIndex,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel3).addComponent(txtSeparator,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      7,
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      Short.MAX_VALUE).addComponent(btnGenerate).addContainerGap()));

        pack();
    }
    JFileChooser jfcOpen = new JFileChooser();

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {
        if (jfcOpen.showOpenDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
            lblFilePath.setText(jfcOpen.getSelectedFile().getAbsolutePath());
        }
    }

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            PrintWriter writer =
                new PrintWriter(jfcOpen.getSelectedFile().getName().substring(0,
                                                                              jfcOpen.getSelectedFile().getName().lastIndexOf(".")) +
                                ".txt", "UTF-8");
            Workbook myWorkBook = new XSSFWorkbook(jfcOpen.getSelectedFile());
            Sheet mySheet =
                myWorkBook.getSheetAt(Integer.valueOf(txtSheetIndex.getText()));
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                XSSFRow myRow = (XSSFRow)rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                StringBuilder lineBuilder = new StringBuilder();
                while (cellIter.hasNext()) {
                    XSSFCell myCell = (XSSFCell)cellIter.next();
                    if (myCell.getCellType() == Cell.CELL_TYPE_STRING) {
                        lineBuilder.append(myCell.toString().trim());
                    } else {
                        lineBuilder.append(myCell.getRawValue().trim());
                    }
                    if (cellIter.hasNext()) {
                        lineBuilder.append(txtSeparator.getText());
                    }
                }
                writer.println(txtPreLine.getText() + lineBuilder.toString() +
                               txtAfterLine.getText());
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            showException(btnGenerate, ex);
        } catch (InvalidFormatException ex) {
            showException(btnGenerate, ex);
        }
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        txtPreLine.setText(((ComboItem)comboModel.getSelectedItem()).getValue());
    }

    private void showException(Component comp, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(comp, e.getMessage(), "Error",
                                      JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ExcelToText().setVisible(true);
                }
            });
    }
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JTextField txtAfterLine;
    private javax.swing.JTextField txtPreLine;
    private javax.swing.JTextField txtSeparator;
    private javax.swing.JTextField txtSheetIndex;
}
