/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

import java.io.File;

/**
 *
 * @author Shehata.Ibrahim
 */
public class ListItem {

        File file;

        public ListItem(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        @Override
        public String toString() {
            return file.getName();
        }
    }
