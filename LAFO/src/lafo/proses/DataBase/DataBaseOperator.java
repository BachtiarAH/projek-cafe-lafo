

package lafo.proses.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class DataBaseOperator extends Koneksi{
private Koneksi koneksi;
private String sqlTabel;
private String[] field;

    //konstruktor
    public DataBaseOperator(Koneksi koneksi) {
        this.koneksi = koneksi;
    }
    
    //fungsi untuk meng eksekusi sql
    public void DatabaseExecutor(String sql, boolean CloseAfterExecute){
        try {
            koneksi.connecting();
            PreparedStatement pst = koneksi.conn.prepareStatement(sqlTabel);
        }catch(SQLException SQLe){
            System.out.println("gagal eksekusi sql: "+SQLe);
        }finally{
            if (CloseAfterExecute) {
                koneksi.connectionClose();
            }
        }
    }
    
    //fungsi untuk menampilkan data dari database ke jtabel 
    public void tabel(String sqlTabel, String[] FieldTabel, JTable tabel){
        DefaultTableModel tbmodel = new DefaultTableModel();
        
        //seting table heeader
        
        //menambahkan header pada tabel model
        for (int i = 0; i < FieldTabel.length; i++) {
            tbmodel.addColumn(FieldTabel[i]);
        }
        

        //menambahkan header pada awal tabel
        tabel.setModel(tbmodel);
        int kolomLength = tbmodel.getColumnCount() ;
        
        //mengisi data 
        //membuat object untuk penampung data
        Object[] tempData = new Object[kolomLength];

        try{
            //mengambil data dari data base melalui object koneksi
            koneksi.connecting();
            PreparedStatement pst = koneksi.conn.prepareStatement(sqlTabel);
            ResultSet rs = pst.executeQuery();
            
            //mengambil hasil dari query keadlam object penampung data (tempData)
            while(rs.next()){
                for (int i = 0; i < kolomLength; i++) {
                    tempData[i] = rs.getString(i+1);
                }
                
                //menambahkan barisbaru dengan isi data ke tabel model
                tbmodel.addRow(tempData);
           }
            
        }
        catch(SQLException sE){
            System.out.println(sE);
        }
        finally{
            
            //menutup koneksi
            koneksi.connectionClose();
        }
        
        //memasukan tabel model kedalam jtabel
        tabel.setModel(tbmodel);
        
        
    }
    
}
