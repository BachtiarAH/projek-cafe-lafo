/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.proses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
//import sun.util.calendar.LocalGregorianCalendar;
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
    
    public static String GetTanggal(){
        
        String date = "";
        LocalDateTime myDateObj = LocalDateTime.now();
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyy");  
        String formattedDate = myDateObj.format(myFormatObj);
        
        date = formattedDate;
        
        return date;
        
    }
    
    public static String GetTanggalFormatDb(){
        LocalDate tgl = LocalDate.now();
        
        
        String hasil;
        int tahun = tgl.getYear();
        int bulan = tgl.getMonthValue();
        int tanggal = tgl.getDayOfMonth();
        String Stanggal;
        String SBulan;
        if (tanggal<10) {
            Stanggal = "0"+tanggal;
        }else{
            Stanggal = ""+tanggal;
        }
        
        if (bulan<10) {
            SBulan = "0"+bulan;
        }else{
            SBulan = ""+bulan;
            
        }
        hasil = tahun+"-"+SBulan+"-"+Stanggal;
        
        
        return hasil;
    }
    
    public static void main(String[] args) {
        System.out.println("DTR"+GetTanggalFormatDb());
    }
   
}
