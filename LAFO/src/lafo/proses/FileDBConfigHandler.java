/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.proses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 *
 * @author user
 */
public class FileDBConfigHandler {
    
    //instansiasi onjeck
    
    //writer
    public File file;
    private FileWriter writer;
    private BufferedWriter bufferWriter;
    
    //reader
    private FileReader reader;
    private BufferedReader bufferReader;
    
    //constructor
    public FileDBConfigHandler(String file) {
        try {
            
        this.file = new File(file);
        
        this.reader = new FileReader(this.file);
        this.bufferReader = new BufferedReader(this.reader);
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }   

    private void setWriter(){
         try {    
         
        //writer
        this.writer = new FileWriter(this.file);
        this.bufferWriter = new BufferedWriter(writer);
      
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void setReader(){
        try {
        //reader
        this.reader = new FileReader(this.file);
        this.bufferReader = new BufferedReader(this.reader);
        } catch (Exception e) {
        }
    }
    
    //set file
    public void setFile(File file) {
        this.file = file;
    }
     
    //fungsi menulis file configurasi
    public void tulisConfigurasi(String IpServer, String Username, String Pass, String Name){
        
        try{
            
        //setwriter
        setWriter();
       
        //menuliskan congihurasi pada bufferedWriter
        bufferWriter.write(IpServer);
        bufferWriter.newLine();
        bufferWriter.write(Username);
        bufferWriter.newLine();
        bufferWriter.write(Pass);
        bufferWriter.newLine();
        bufferWriter.write(Name);
        
        //Flush configurasi ke Wrieter
        bufferWriter.flush();
        
        
        //Flush configurasi ke file
        writer.flush();
        
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    //mengambil configurasi dari file
    //mengambil ip
    public String getIpServer(){
        String Ip = "";
        
            try {
                bufferReader.mark(999);
                
                Ip = bufferReader.readLine();
                
                bufferReader.reset();
                
            
            } catch (IOException e) {
                System.out.println("ip "+e);
            } 
        
        return Ip;
    }
    
    //mengambil username
    public String getUsername(){
        String username = "";
        
        try {
            bufferReader.mark(999);
            
            for (int i = 0; i < 2; i++) {
                username = bufferReader.readLine();
            }
            
            bufferReader.reset();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return username;
    }
    
    public String getPassword(){
        String Pass = "";
        
        try {
            bufferReader.mark(999);
            
            for (int i = 0; i < 3; i++) {
                Pass = bufferReader.readLine();
            }
            
            bufferReader.reset();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Pass;
    }
    
    public String getDBName(){
        String DBName = "";
        
//        setReader();
        
        try {
            bufferReader.mark(999);
            
            for (int i = 0; i < 4; i++) {
                DBName = bufferReader.readLine();
            }
            
            
            bufferReader.reset();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return DBName;
    }
    
    public String getUrl(){
        String Url = "";
        
        try {
            bufferReader.mark(999);
            
            Url = bufferReader.readLine() + " "
                    + bufferReader.readLine() + " "
                    + bufferReader.readLine() + " "
                    + bufferReader.readLine();
            
            bufferReader.reset();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Url;
    }
}
