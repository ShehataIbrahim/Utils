/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

import com.streams.gironil.Configs;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Shehata.Ibrahim
 */
public class FilesListModel extends DefaultListModel {

        ArrayList<String> modelFiles = new ArrayList<String>();

        public void addElement(ListItem element) {

            if (!modelFiles.contains(element.getFile().getAbsolutePath()) && element.getFile().getAbsolutePath().toLowerCase().endsWith(Configs.INPUT_FILE_EXTENSION)) {
                modelFiles.add(element.getFile().getAbsolutePath());
                super.addElement(element);
            }
        }

        public void removeElement(ListItem element) {
            super.removeElement(element);
            modelFiles.remove(element.getFile().getAbsolutePath());
        }

        public void diffElements(File[] filesList) {
            ArrayList<String> files = new ArrayList<String>();
            ArrayList<ListItem> forRemovaleFiles = new ArrayList<ListItem>();
            //adding new Items
            for (File f : filesList) {
                files.add(f.getAbsolutePath());
                addElement(new ListItem(f));
            }
            //removing old items
            for (int i = 0; i < getSize(); i++) {
                ListItem item = (ListItem) getElementAt(i);
                if (!files.contains(item.getFile().getAbsolutePath())) {
                    forRemovaleFiles.add(item);
                }
            }
            for (ListItem it : forRemovaleFiles) {
                removeElement(it);
            }
        }
    }