/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.proses;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author RSI-15
 */
public class Utility extends javax.swing.JFrame{
    
    
    public static void setSideBar(JPanel mainPanel, JPanel panel){
        //cleaning panel
        mainPanel.removeAll();
        
        //adding panel
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    
}
