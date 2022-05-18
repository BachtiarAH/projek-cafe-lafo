/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.fungsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lafo.entity.diskon;
import lafo.entity.menu;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;
import lafo.view.MainJframe;

/**
 *
 * @author RSI-08
 */
public class fungsitTransaksi {
    Koneksi konDbLafo = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLafo);
    menu mn = new menu();
    diskon disc = new diskon();
    javax.swing.JTable tabelTr;
    public static DefaultTableModel tbmodel = new DefaultTableModel();
    
    

    public fungsitTransaksi(JTable tabelTr) {
        this.tabelTr = tabelTr;
//        this.SetTableModel();
//        this.tabelTr.setModel(tbmodel);
    }
    
    public void SetTableModel(){
        String[] tbHeader = {"Nama Menu","Jumlah","Harga","Diskon","total"};
        Object[] obj = new Object[]{} ;
        //menambahkan header pada tabel model
        for (int i = 0; i < tbHeader.length; i++) {
            tbmodel.addColumn(tbHeader[i]);
//            obj[i] = tbHeader;
        }
        
//        tbmodel.addRow(tbHeader);
        MainJframe.jTableTransaksi.setModel(tbmodel);
    }

    public void setEntiti(menu menu, diskon diskon){
        this.mn = menu;
        this.disc = diskon;
    }
    
    public String[][] getModelString(){
        String[][] ModelS = null;
        
        for (int i = 0; i < MainJframe.jTableTransaksi.getRowCount(); i++) {
            for (int j = 0; j < MainJframe.jTableTransaksi.getColumnCount(); j++) {
                ModelS[i][j] = MainJframe.jTableTransaksi.getValueAt(i, j).toString();
            }
        }
        
        return ModelS;
    }
    
    public void addMenu(Object[] isi){
//        String[][] ModelS = getModelString();
//        ModelS[ModelS.length+1] = isi;
        JTable tabel = MainJframe.jTableTransaksi;
        Object[] tempIsi = new Object[tabel.getColumnCount()];
        Object[] c = new Object[]{"a",1};
        
        //mengambil data tabel
        for (int i = 0; i < tabel.getRowCount() ; i++) {
            for (int j = 0; j < tabel.getColumnCount(); j++) {
            
                tempIsi[j]=tabel.getValueAt(i, j);
            }
//                tbmodel.addRow(tempIsi);
            
                
        }
        
        //menambah data ke tabel model
            for (int k = 0; k < isi.length; k++) {
                tempIsi[k] = isi[k];
//                tbmodel.addColumn(tempIsi[k]);
            }
            
            tbmodel.addRow(isi);
//        MainJframe.jTableTransaksi.addRowSelectionInterval(0, 4);
        tabel.setModel(tbmodel);
        
    }
    
    public void SubmitMenu(Object[] isi){
        tbmodel.addRow(isi);
        MainJframe.jTableTransaksi.setVisible(false);
        MainJframe.jTableTransaksi.setVisible(true);
//        MainJframe.jTableTransaksi.setModel(tbmodel);
    }
    
    public void cekStok() throws SQLException{
        boolean isCukup;
        String sqlResep = "";
        
        ResultSet resep = DbOp.getResultSql(sqlResep, true);
        
        while (resep.next()) {            
            
        }
    }
    
    public void SubmitTransaksi(){
        
    }
    
}
