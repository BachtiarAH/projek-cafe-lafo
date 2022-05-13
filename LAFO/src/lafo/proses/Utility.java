/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.proses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    public static void tulisConfigurasi(String IpServer, String Username, String Pass, String Name){
        
        try{
        //instansiasi object file writer
        File FileConfig = new File("configurasiDB.txt");
        FileWriter WriterConfigDb = new FileWriter(FileConfig);
        BufferedWriter BufferWriterConfigDB = new BufferedWriter(WriterConfigDb);
        
        //menuliskan congihurasi pada bufferedWriter
        BufferWriterConfigDB.write(IpServer);
        BufferWriterConfigDB.newLine();
        BufferWriterConfigDB.write(Username);
        BufferWriterConfigDB.newLine();
        BufferWriterConfigDB.write(Pass);
        BufferWriterConfigDB.newLine();
        BufferWriterConfigDB.write(Name);
        
        //Flush configurasi ke Wrieter
        BufferWriterConfigDB.flush();
        
        //Flush configurasi ke file
        WriterConfigDb.flush();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
