/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author Shehata.Ibrahim
 */
public class SwingUtils {

    public static void centerScreen(Window frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width - frame.getWidth()) / 2, (dim.height - frame.getHeight()) / 2);
    }
}
