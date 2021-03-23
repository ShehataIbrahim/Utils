/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.utils;

/**
 *
 * @author Shehata.Ibrahim
 */
public class FlaggedObject {
    private boolean flag=false;
    private Object content=null;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getContent() {
        if(content==null) {
            return "";
        }
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
    
    
}
