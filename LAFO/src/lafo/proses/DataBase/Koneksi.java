package lafo.proses.DataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Koneksi {
    
    public static java.sql.Connection conn;
    
    //variabel for database connection
    static String port = "3306";
    static String DataBase = "s2_cafe_lafo_v2";
    static String url = "jdbc:mysql://localhost:"+port+"/"+DataBase;
    static String user = "root";
    static String password = "";
     
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
        
        
        
        String sql = "SELECT * FROM `penngguna`";
        
        //open connection
        
       
            
        
    }
    
}
