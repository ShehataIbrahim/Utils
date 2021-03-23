/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

/**
 *
 * @author Shehata.Ibrahim
 */
public class Header{
        String headerString;
        String batchId;

        public Header(String headerString, String batchId) {
            this.headerString = headerString;
            this.batchId = batchId;
        }

        public Header() {
        }

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public void setHeaderString(String headerString) {
            this.headerString = headerString;
        }

        public String getHeaderString() {
            return headerString;
        }
        
    }
