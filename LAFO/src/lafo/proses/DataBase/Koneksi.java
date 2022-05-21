package lafo.proses.DataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lafo.proses.FileDBConfigHandler;


public class Koneksi {
    
    //instansiasi objek
    FileDBConfigHandler sourceConfig = new FileDBConfigHandler("confiDB.txt");
    
    public static java.sql.Connection conn;
    
    //variabel for database connection
    String port = "3306";
    String ip = sourceConfig.getIpServer();
    String DataBase = sourceConfig.getDBName();
    String url = "jdbc:mysql://"+ip+":"+port+"/"+DataBase;
    String user = sourceConfig.getUsername();
    String password = sourceConfig.getPassword();

   

    static{
        try{
//            DriverManager = new DriverManager()
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void connecting(){
        
        
        try{
            conn = DriverManager.getConnection(url, user, password); 
            if (conn.isValid(0)) {
                System.out.println("koneksi berhasil");
            }
        }
        catch(Exception e){
                System.out.println("koneksi gagal : "+e);
                //System.out.println(sqlE);
        }
        finally{
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
    
    public void connectionClose(){
        /*try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    public static void main(String[] args) throws SQLException {
        
        
        
//        String sql = "SELECT * FROM `penngguna`";
        
        //open connection
        
       Koneksi k = new Koneksi();
        
        System.out.println(k.ip);
        System.out.println(k.DataBase);
        System.out.println(k.url);
        System.out.println(k.user);
        System.out.println(k.password);
            
        
    }
    
}
