/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.view.PopUp.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lafo.entity.menu;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;
import lafo.view.MainJframe;

/**
 *
 * @author Hp
 */
public class PopUpTambahMenu extends javax.swing.JFrame {
    InputBarang ib =new InputBarang();
    Koneksi con = new Koneksi();
    DataBaseOperator dbop = new DataBaseOperator(con);
    menu tempmenu = new menu();
    String mode = "tambah";
    /**
     * Creates new form PopUpTambahMenu
     */
    
    public PopUpTambahMenu() {
        initComponents();
        clearBarang();
        jButtonHapusResep.setVisible(false);
        jButtonEditResep.setVisible(false);
    }

    public void TambahResep (){ 
            
            String kode = labelKodeBrg.getText();
            String nama = labelNamaBrg.getText();
            String qty = textFieldqty.getText();
            String satuan = labelSatuan.getText();
            
            String[] data = {kode,nama,qty,satuan};
            
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            
            model.addRow(data);
            
}
    
    public void  Hapusresep(){
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            
            model.removeRow(jTable1.getSelectedRow());
        }

    public String generatekodemenu(){
        String kode = "";
        String angka = "1";
        String nol ="00";
        int index;
        String kodeBaru;
        
        if (jComboBoxKategori.getSelectedItem().toString().equalsIgnoreCase("Makanan")) {
            kode = "MK";
        }else if(jComboBoxKategori.getSelectedItem().toString().equalsIgnoreCase("minuman")){
            kode = "MN";
        }
//        System.out.println(jComboBoxKategori.getSelectedItem().toString());
        
        String sql ="SELECT `kode_Menu` as kode FROM `menu`  WHERE kode_menu LIKE '%"+kode+"%' ORDER BY kode_Menu DESC;";
        ResultSet rs = dbop.getResultSql(sql, true);
        
        try {
            if (rs.next()) {
                index =  Integer.valueOf(rs.getString("kode").substring(2))+1;
                angka = index+"";
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PopUpTambahMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        kodeBaru = kode+nol+angka; 
        
        return kodeBaru;
 }
    
    public void clear (){
        textFieldKodeMenu.setText("");
        textFieldNamaMenu.setText("");
        textFieldHarga.setText("");
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
        }
    }
    
    public void clearBarang(){
        labelKodeBrg.setText("BRG___");
        labelNamaBrg.setText("?");
        labelSatuan.setText("satuan");
        textFieldqty.setText("");
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    public void tambahMenu(){
     tempmenu.setKode(textFieldKodeMenu.getText());
        tempmenu.setNama(textFieldNamaMenu.getText());
       tempmenu.setKategori(jComboBoxKategori.getSelectedItem().toString());
         tempmenu.setHarga(Float.valueOf(textFieldHarga.getText()) );
         
 String sql = "INSERT INTO `menu` "
                + "(`kode_Menu`, `Nama_Menu`, `Harga`, `Kategori`) "
                + "VALUES "
                + "('"+tempmenu.getKode()+"', '"+tempmenu.getNama()+"', '"+tempmenu.getHarga()+"','"+tempmenu.getKategori()+"')";
 
//        System.out.println(sql);
        dbop.DatabaseExecutor(sql, true);
        String massageSucc = "Berhasil Menambahkan "+tempmenu.getNama();
        JOptionPane.showMessageDialog(this, massageSucc);
 }
    
    public void insertResep(){
        String kodeMenu = textFieldKodeMenu.getText();
        String kodeBarang;
        String qty;
        
        
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            kodeBarang = jTable1.getValueAt(i, 0).toString();
            qty = jTable1.getValueAt(i, 2).toString();
            
            String sql = "INSERT INTO `resep`(`qty`, `kode_Barang`, `kode_Menu`, `totalResepDDigunakan`) "
                    + "VALUES "
                    + "('"+qty+"','"+kodeBarang+"','"+kodeMenu+"','0')";
            dbop.DatabaseExecutor(sql, true);
//            System.out.println(sql);
        }
    }
    
    public void deletResep(){
        String kodeMenu = textFieldKodeMenu.getText();
        String sql = "DELETE FROM `resep` WHERE kode_Menu = '"+kodeMenu+"'";
        
        dbop.DatabaseExecutor(sql, true);
        
    }
    
    public void UpdateResep(){
        deletResep();
        insertResep();
        
    }
    
    public void klikResep(){
        String kode = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String nama = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String qty = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String satuan = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        
        labelKodeBrg.setText(kode);
        labelNamaBrg.setText(nama);
        labelSatuan.setText(satuan);
    }
    
    public void EditResep(){
        String qty = textFieldqty.getText();        
//        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        
        jTable1.setValueAt(qty, jTable1.getSelectedRow(),2 );
        
    }
    
    public void refreshMenu(){
        String sql = "SELECT * FROM `menu`";
        String[] header = {"kode", "Nama Menu" , "Harga", "kategori"};
        dbop.tabel(sql, header, MainJframe.jTablemenu);
    }
    
    public void tulisDataKlik(String kodeMenu){
        String namaMenu = "",kategori="minuman",harga="0";
        String kodeBrg,namabrg,qty,satuan;
        
        String sqlMenu = "SELECT * FROM `menu` WHERE kode_Menu = '"+kodeMenu+"'";
        
        ResultSet rs = dbop.getResultSql(sqlMenu, true);
        
        try {
            if (rs.next()) {
                namaMenu = rs.getString("nama_menu");
                kategori = rs.getString("kategori");
                harga = rs.getString("harga");
            }
            
            textFieldKodeMenu.setText(kodeMenu);
            textFieldNamaMenu.setText(namaMenu);
            textFieldHarga.setText(harga);
            jComboBoxKategori.setSelectedItem(kategori);
        } catch (SQLException ex) {
            Logger.getLogger(PopUpTambahMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sqlResep = "SELECT resep.kode_Barang,barang.Nama_barang,resep.qty,barang.satuan FROM `resep` JOIN barang ON barang.kode_Barang = resep.kode_Barang WHERE kode_Menu = '"+kodeMenu+"'";
        String[] header = {"kode", "nama barang", "qty", "satuan"};
        dbop.tabel(sqlResep, header, jTable1);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        textFieldKodeMenu = new java.awt.TextField();
        textFieldNamaMenu = new java.awt.TextField();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        textFieldHarga = new java.awt.TextField();
        jComboBoxKategori = new javax.swing.JComboBox<>();
        label4 = new java.awt.Label();
        labelNamaBrg = new java.awt.Label();
        labelKodeBrg = new java.awt.Label();
        textFieldqty = new java.awt.TextField();
        labelSatuan = new java.awt.Label();
        jButtonTambahResep = new javax.swing.JButton();
        jButtonHapusResep = new javax.swing.JButton();
        jButtonEditResep = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButtonEditKode = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        label1.setText("Kode Menu");

        textFieldKodeMenu.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        textFieldKodeMenu.setPreferredSize(new java.awt.Dimension(377, 51));

        textFieldNamaMenu.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        textFieldNamaMenu.setPreferredSize(new java.awt.Dimension(377, 51));

        label2.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        label2.setText("Nama Menu");

        label3.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        label3.setText("Kategori");

        textFieldHarga.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        textFieldHarga.setPreferredSize(new java.awt.Dimension(377, 51));
        textFieldHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldHargaKeyTyped(evt);
            }
        });

        jComboBoxKategori.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jComboBoxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman" }));
        jComboBoxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKategoriActionPerformed(evt);
            }
        });

        label4.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        label4.setText("Harga\n");

        labelNamaBrg.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        labelNamaBrg.setText("?");

        labelKodeBrg.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        labelKodeBrg.setText("BRSU01");

        textFieldqty.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        textFieldqty.setPreferredSize(new java.awt.Dimension(115, 43));
        textFieldqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldqtyKeyTyped(evt);
            }
        });

        labelSatuan.setFont(new java.awt.Font("Dialog", 0, 23)); // NOI18N
        labelSatuan.setText("/ Satuan\n");

        jButtonTambahResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/pluss icon.png"))); // NOI18N
        jButtonTambahResep.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonTambahResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahResepActionPerformed(evt);
            }
        });

        jButtonHapusResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/trash icon.png"))); // NOI18N
        jButtonHapusResep.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonHapusResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusResepActionPerformed(evt);
            }
        });

        jButtonEditResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/edit icon.png"))); // NOI18N
        jButtonEditResep.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonEditResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditResepActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "kode", "nama barang", "qty", "satuan"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("kode");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("nama barang");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("qty");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("satuan");
        }

        jButton4.setBackground(new java.awt.Color(159, 162, 171));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(241, 102, 52));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Simpan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButtonEditKode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/edit icon.png"))); // NOI18N
        jButtonEditKode.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonEditKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditKodeActionPerformed(evt);
            }
        });

        jButton7.setText("generate kode");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEditKode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton7))
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFieldNamaMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFieldKodeMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textFieldqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(labelSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTambahResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonHapusResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldKodeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldNamaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditKode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(labelNamaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTambahResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonHapusResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonEditResep, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxKategoriActionPerformed

    private void jButtonTambahResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahResepActionPerformed
        // TODO add your handling code here:
        if ((!textFieldqty.getText().equalsIgnoreCase("")) && !labelKodeBrg.getText().toString().equalsIgnoreCase("BRG___")) {
        TambahResep();
        clearBarang();
            
        }else{
            JOptionPane.showMessageDialog(null, "mohon masukan data dengan benar");
        }
    }//GEN-LAST:event_jButtonTambahResepActionPerformed

    private void jButtonHapusResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusResepActionPerformed
        // TODO add your handling code here:
        Hapusresep();
        clearBarang();
        jButtonTambahResep.setVisible(true);
        jButtonHapusResep.setVisible(false);
        jButtonEditResep.setVisible(false);
    }//GEN-LAST:event_jButtonHapusResepActionPerformed

    private void jButtonEditResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditResepActionPerformed
        // TODO add your handling code here:
        EditResep();
        clearBarang();
        jButtonTambahResep.setVisible(true);
        jButtonHapusResep.setVisible(false);
        jButtonEditResep.setVisible(false);
    }//GEN-LAST:event_jButtonEditResepActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (!textFieldKodeMenu.getText().toString().equalsIgnoreCase("") && jTable1.getRowCount()>0) {
            if (mode.equalsIgnoreCase("tambah")) {
                tambahMenu();
                insertResep();
                
            }else if(mode.equalsIgnoreCase("edit")){
                UpdateResep();
            }
            
            
            clear();
            this.setVisible(false);
            refreshMenu();
            
        }else{
            JOptionPane.showMessageDialog(null, "tolong lengkapi form");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       // TODO add your handling code here:
       clear(); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonEditKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditKodeActionPerformed
        // TODO add your handling code here:
        ib.setVisible(true);
    }//GEN-LAST:event_jButtonEditKodeActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        textFieldKodeMenu.setText(generatekodemenu());
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        klikResep();
        jButtonTambahResep.setVisible(false);
        jButtonHapusResep.setVisible(true);
        jButtonEditResep.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void textFieldqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldqtyKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldqtyKeyTyped

    private void textFieldHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldHargaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_textFieldHargaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopUpTambahMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpTambahMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpTambahMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpTambahMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpTambahMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonEditKode;
    private javax.swing.JButton jButtonEditResep;
    private javax.swing.JButton jButtonHapusResep;
    private javax.swing.JButton jButtonTambahResep;
    private javax.swing.JComboBox<String> jComboBoxKategori;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    public static java.awt.Label labelKodeBrg;
    public static java.awt.Label labelNamaBrg;
    public static java.awt.Label labelSatuan;
    private java.awt.TextField textFieldHarga;
    private java.awt.TextField textFieldKodeMenu;
    private java.awt.TextField textFieldNamaMenu;
    private java.awt.TextField textFieldqty;
    // End of variables declaration//GEN-END:variables
}
