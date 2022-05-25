package lafo.view.PopUp.Barang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import lafo.entity.barang;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;
import lafo.view.MainJframe;
import static lafo.view.MainJframe.jTableBarang;

/**
 *
 * @author Hp
 */
public class TambahBarang extends javax.swing.JFrame {

    Koneksi konDbLaf = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLaf);
    barang tempBarang = new barang();
    String mode;
    /**
     * Creates new form TambahBarang
     */
    public TambahBarang(String mode) {
        initComponents();
        this.mode = mode;
        setComboBox();
        
    }
    
    public void setComboBox(){
        String sql = "SELECT barang.satuan FROM `barang` GROUP BY satuan;";
        ResultSet rs = DbOp.getResultSql(sql, true);
        
        try {
            while (rs.next()) {
                jComboBoxSatuan.addItem(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TambahBarang.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        if (mode.equalsIgnoreCase("edit")) {
//            setJform();
            
        }
    }
    
    
    //membuat kode barang
    public String generateKodeBarang(){
        //deklarasi variabel
        int intIndexKode;
        String IndexKode;
        String kode ;
        String indexRs = "00";
        
        //buat inisial
        String nama=jTextFieldNamaBarang.getText();
        String inisial = nama.substring(0, 2).toUpperCase();
        
        
        //membuat kodesuplier
   
        kode = "BR"+inisial;
        String sql = "SELECT barang.kode_Barang FROM barang\n" +
            "WHERE barang.kode_Barang LIKE '"+kode+"%'\n" +
            "ORDER BY barang.kode_Barang DESC\n" +
            "LIMIT 1;";
        ResultSet rs = DbOp.getResultSql(sql, true);
        try {
            
                while (rs.next()) {                
                
                indexRs = rs.getString(1).substring(4,6);
            }
        
            
                intIndexKode= Integer.valueOf(indexRs)+1;
                
                if (intIndexKode < 10) {
                    IndexKode = "0"+intIndexKode;
                }else{
                    IndexKode = intIndexKode+"";
                }
            
    
            
            
            kode = "BR"+inisial+IndexKode;
            
            System.out.println(kode);
        
        } catch (SQLException ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

//        jTextField_kodebarang.setText(inisial);
        return kode;
    }
    
    public void tambahBarang(){
        tempBarang.setKode(jTextFieldKodeBarang.getText());
        tempBarang.setNama(jTextFieldNamaBarang.getText());
        tempBarang.setSatuan(jComboBoxSatuan.getEditor().getItem().toString());
        
        String sql = "INSERT INTO `barang` "
                + "(`kode_Barang`, `Nama_barang`, `satuan`) "
                + "VALUES "
                + "('"+tempBarang.getKode()+"', '"+tempBarang.getNama()+"', '"+tempBarang.getSatuan()+"')";
        
        DbOp.DatabaseExecutor(sql, true);
        String massageSucc = "Berhasil Menambahkan "+tempBarang.getNama();
        JOptionPane.showMessageDialog(this, massageSucc);
    }
    
    public void updateBarang(){
        tempBarang.setKode(jTextFieldKodeBarang.getText());
        tempBarang.setNama(jTextFieldNamaBarang.getText());
        tempBarang.setSatuan(jComboBoxSatuan.getEditor().getItem().toString());
        
            String sql = "UPDATE `barang` "
                    + "SET "
                    + "`kode_Barang`='"+tempBarang.getKode()+"',"
                    + "`Nama_barang`='"+tempBarang.getNama()+"',"
                    + "`satuan`='"+tempBarang.getSatuan()+"'"
                    + " WHERE barang.kode_Barang = '"+tempBarang.getKode()+"'";
        System.out.println(sql);
        DbOp.DatabaseExecutor(sql, true);
        String massageSucc = "Berhasil Memperbarui "+tempBarang.getNama();
        JOptionPane.showMessageDialog(this, massageSucc);
        
    }
    
    public void clearForm(){
        jTextFieldKodeBarang.setText("");
        jTextFieldNamaBarang.setText("");
    }
    
    public void RefreshTabelBarang(){
        String sql = "SELECT "
                + "barang.kode_Barang, "
                + "barang.Nama_barang, "
                + "SUM(detail_suplai.stok), "
                + "barang.satuan "
                + "FROM barang "
                + "LEFT JOIN detail_suplai "
                + "ON barang.kode_Barang = detail_suplai.kode_Barang "
                + "GROUP BY kode_Barang";
        String[] header = {"kode Barang","nama Barang","stok","satuan"};
        DbOp.tabel(sql, header, jTableBarang);
    }
    
    public void setJform(JTable tabel){
        jTextFieldKodeBarang.setText(tabel.getValueAt(jTableBarang.getSelectedRow(), 0).toString());
        jTextFieldNamaBarang.setText(tabel.getValueAt(jTableBarang.getSelectedRow(), 1).toString());
            jComboBoxSatuan.getEditor().setItem(tabel.getValueAt(tabel.getSelectedRow(), 3));
//        jTextFieldKodeBarang.setText(MainJframe.jTableBarang.getValueAt(jTableBarang.getSelectedRow(), 0).toString());
//        jTextFieldNamaBarang.setText(MainJframe.jTableBarang.getValueAt(jTableBarang.getSelectedRow(), 1).toString());
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
        label2 = new java.awt.Label();
        jTextFieldNamaBarang = new javax.swing.JTextField();
        jTextFieldKodeBarang = new javax.swing.JTextField();
        label3 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBoxSatuan = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(592, 623));

        label1.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        label1.setText("Kode Barang");

        label2.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        label2.setText("Nama Barang");

        jTextFieldNamaBarang.setPreferredSize(new java.awt.Dimension(56, 48));
        jTextFieldNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaBarangActionPerformed(evt);
            }
        });
        jTextFieldNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNamaBarangKeyReleased(evt);
            }
        });

        jTextFieldKodeBarang.setPreferredSize(new java.awt.Dimension(478, 48));
        jTextFieldKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKodeBarangActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        label3.setText("Satuan\n");

        jButton1.setBackground(new java.awt.Color(241, 102, 52));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SUBMIT");
        jButton1.setPreferredSize(new java.awt.Dimension(478, 46));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(241, 102, 52));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("UPDATE");
        jButton2.setPreferredSize(new java.awt.Dimension(478, 46));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(146, 147, 147));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CLEAR");
        jButton3.setPreferredSize(new java.awt.Dimension(478, 46));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxSatuan.setEditable(true);
        jComboBoxSatuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxSatuan.setPreferredSize(new java.awt.Dimension(56, 48));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldKodeBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxSatuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        label1.getAccessibleContext().setAccessibleName("Kode Barang\n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaBarangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!(jTextFieldKodeBarang.getText().equalsIgnoreCase("") && jTextFieldNamaBarang.getText().equalsIgnoreCase(""))) {
        tambahBarang();
        RefreshTabelBarang();
        this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "kode barang dan nama barang tidak boleh kososng!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        updateBarang();
        RefreshTabelBarang();
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldNamaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNamaBarangKeyReleased
        // TODO add your handling code here:
        if ((jTextFieldNamaBarang.getText().length() == 2)) {
            
        jTextFieldKodeBarang.setText(generateKodeBarang());
        }
    }//GEN-LAST:event_jTextFieldNamaBarangKeyReleased

    private void jTextFieldKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKodeBarangActionPerformed

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
            java.util.logging.Logger.getLogger(TambahBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahBarang("edit").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxSatuan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldKodeBarang;
    private javax.swing.JTextField jTextFieldNamaBarang;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    // End of variables declaration//GEN-END:variables
}
