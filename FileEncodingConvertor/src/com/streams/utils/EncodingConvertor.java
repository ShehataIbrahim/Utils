
package com.streams.utils;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Shehata.Ibrahim
 */
public class EncodingConvertor extends javax.swing.JFrame {

    private DefaultComboBoxModel srcEncodingModel = null;
    private DefaultComboBoxModel targetEncodingModel = null;
    private JFileChooser jfcOpen = new JFileChooser();
    String targetEncoding=null;
    /**
     * Creates new form EncodingConvertor
     */
    public EncodingConvertor() {
        srcEncodingModel = new DefaultComboBoxModel();
        targetEncodingModel = new DefaultComboBoxModel();
        for (String encoding : Charset.availableCharsets().keySet()) {
            srcEncodingModel.addElement(encoding);
            targetEncodingModel.addElement(encoding);
        }
        jfcOpen.setDialogType(JFileChooser.OPEN_DIALOG);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtPreview = new javax.swing.JTextArea();
        btnBrowse = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        cmbSourceEncoding = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        chkAutoPreview = new javax.swing.JCheckBox();
        btnPreview = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        chkNullTarget = new javax.swing.JCheckBox();
        cmbTargetEncoding = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtPreview.setColumns(20);
        txtPreview.setRows(5);
        jScrollPane1.setViewportView(txtPreview);

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        cmbSourceEncoding.setModel(srcEncodingModel);
        cmbSourceEncoding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSourceEncodingActionPerformed(evt);
            }
        });

        jLabel2.setText("Source Encoding");

        chkAutoPreview.setText("Auto Preview");
        chkAutoPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoPreviewActionPerformed(evt);
            }
        });

        btnPreview.setText("Preview");
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewActionPerformed(evt);
            }
        });

        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        chkNullTarget.setText("No Target Encoding");
        chkNullTarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNullTargetActionPerformed(evt);
            }
        });

        cmbTargetEncoding.setModel(targetEncodingModel);
        cmbTargetEncoding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTargetEncodingActionPerformed(evt);
            }
        });

        jLabel1.setText("Target Encoding");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBrowse))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSourceEncoding, 0, 115, Short.MAX_VALUE)
                            .addComponent(cmbTargetEncoding, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkNullTarget)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkAutoPreview)
                                .addGap(18, 18, 18)
                                .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSourceEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(chkAutoPreview)
                    .addComponent(btnPreview))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkNullTarget)
                    .addComponent(cmbTargetEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExport)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {
        if (jfcOpen.showOpenDialog(btnBrowse) == JFileChooser.APPROVE_OPTION) {
            lblPath.setText(jfcOpen.getSelectedFile().getAbsolutePath());
        }
    }

    private void cmbSourceEncodingActionPerformed(java.awt.event.ActionEvent evt) {
        if (chkAutoPreview.isSelected()) {
            preview();
        }
    }
   private void showException(Component comp, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(comp, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void preview() {
        if (lblPath.getText().isEmpty()) {
            return;
        }
        txtPreview.setText("");
        new Thread() {
            @Override
            public void run() {
                try {
                    BufferedReader reader;
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(jfcOpen.getSelectedFile()), srcEncodingModel.getSelectedItem().toString()));
                    txtPreview.removeAll();
                    while (reader.ready()) {
                        txtPreview.append(reader.readLine() + "\n");
                    }

                    reader.close();
                } catch (IOException ex) {
                   showException(txtPreview, ex);
                }
            }
        }.start();

    }
    private void chkAutoPreviewActionPerformed(java.awt.event.ActionEvent evt) {
        btnPreview.setEnabled(!chkAutoPreview.isSelected());
    }

    private void btnPreviewActionPerformed(java.awt.event.ActionEvent evt) {
        preview();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            FileEncodingConvertor.convertFileEncoding(srcEncodingModel.getSelectedItem().toString(), targetEncoding, jfcOpen.getSelectedFile(), new File(jfcOpen.getSelectedFile().getName()));
                JOptionPane.showMessageDialog(btnExport, "File was exported","Success",JOptionPane.INFORMATION_MESSAGE);
                
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(btnExport, ex.getMessage(),"Fail",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chkNullTargetActionPerformed(java.awt.event.ActionEvent evt) {
  cmbTargetEncoding.setEnabled(!chkNullTarget.isSelected());
        if(chkNullTarget.isSelected())
       targetEncoding=null;
   else
       targetEncoding=cmbTargetEncoding.getSelectedItem().toString();
    }

    private void cmbTargetEncodingActionPerformed(java.awt.event.ActionEvent evt) {
        targetEncoding=cmbTargetEncoding.getSelectedItem().toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EncodingConvertor().setVisible(true);
            }
        });
    }
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnPreview;
    private javax.swing.JCheckBox chkAutoPreview;
    private javax.swing.JCheckBox chkNullTarget;
    private javax.swing.JComboBox cmbSourceEncoding;
    private javax.swing.JComboBox cmbTargetEncoding;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPath;
    private javax.swing.JTextArea txtPreview;
}
