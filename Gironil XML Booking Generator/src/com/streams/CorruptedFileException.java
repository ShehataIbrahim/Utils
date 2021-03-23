/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

/**
 *
 * @author Shehata.Ibrahim
 */
public class CorruptedFileException extends Exception {
    private int corruptedTransaction;

    public CorruptedFileException(String corruptionCause) {
        this.corruptionCause=corruptionCause;
    }

    public int getCorruptedTransaction() {
        return corruptedTransaction;
    }

    public void setCorruptedTransaction(int corruptedTransaction) {
        this.corruptedTransaction = corruptedTransaction;
    }

    public String getCorruptionCause() {
        return corruptionCause;
    }

    public void setCorruptionCause(String corruptionCause) {
        this.corruptionCause = corruptionCause;
    }
    private String corruptionCause;

    public CorruptedFileException(int corruptedTransaction, String corruptionCause) {
        this.corruptedTransaction = corruptedTransaction;
        this.corruptionCause = corruptionCause;
    }
}
