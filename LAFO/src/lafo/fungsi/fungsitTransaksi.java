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

/**
 *
 * @author RSI-08
 */
public class fungsitTransaksi {
    Koneksi konDbLafo = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLafo);
    menu mn = new menu();
    diskon disc = new diskon();
    JTable tabelTr;
    DefaultTableModel tbmodel = new DefaultTableModel();
    
    

    public fungsitTransaksi(JTable tabelTr) {
        this.tabelTr = tabelTr;
    }
    
    public void SetTableModel(){
        String[] tbHeader = {"Nama Menu","Jumlah","Harga","Diskon","total"};
        //menambahkan header pada tabel model
        for (int i = 0; i < tbHeader.length; i++) {
            tbmodel.addColumn(tbHeader[i]);
        }
    }

    public void setEntiti(menu menu, diskon diskon){
        this.mn = menu;
        this.disc = diskon;
    }
    
    public void addMenu(){
        Object[] tempData = null;
        
        tempData[0] = mn.getNama();
        tempData[1] = mn.getJumlah();
        tempData[2] = mn.getHarga();
        tempData[3] = disc.getJumlahDiskon();
        tempData[4] = mn.getJumlah() * mn.getHarga() - disc.getJumlahDiskon();
        
        tbmodel.addRow(tempData);
        
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
