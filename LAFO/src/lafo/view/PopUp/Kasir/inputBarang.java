/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.view.PopUp.Kasir;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lafo.entity.diskon;
import lafo.entity.menu;
import lafo.fungsi.fungsitTransaksi;
import lafo.view.MainJframe;
import static lafo.view.MainJframe.jLabelTotal;
import static lafo.view.MainJframe.jTableBarang;

/**
 *
 * @author RSI-16
 */
public class inputBarang extends javax.swing.JFrame {
    menu mntp;
    MainJframe frameUtama;
    CariDiskon CrDis = new CariDiskon("tunggal");
    fungsitTransaksi fTrans = new fungsitTransaksi(MainJframe.jTableBarang);
    

    public static diskon diss = new diskon();
//    float disk = diss.getJumlahDiskon();
    /**
     * Creates new form inputBarang
     */
    public inputBarang() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }


    public JLabel getjLabelDiskon() {
        return jLabelDiskon;
    }

    public void setjLabelDiskon(JLabel jLabelDiskon) {
        this.jLabelDiskon = jLabelDiskon;
    }
    
    public void setjLabelDiskon(String text){
        this.jLabelDiskon.setText(text);
    }

    public JLabel getjLabelNamaDiskon() {
        return jLabelNamaDiskon;
    }

    public void setjLabelNamaDiskon(JLabel jLabelNamaDiskon) {
        this.jLabelNamaDiskon = jLabelNamaDiskon;
    }
    
    public void setjLabelNamaDiskon(String text){
        this.jLabelNamaDiskon.setText(text);
    }

    public menu getMntp() {
        return mntp;
    }

    public void setMntp(menu mntp) {
        this.mntp = mntp;
    }

    public MainJframe getFrameUtama() {
        return frameUtama;
    }

    public void setFrameUtama(MainJframe frameUtama) {
        this.frameUtama = frameUtama;
    }
    
    public void startRun(){
        this.jLabelNama.setText(mntp.getNama());
        this.jLabelHarga.setText("Rp. "+mntp.getHarga()+"");
        diss.setJumlahDiskon(0);
        this.setVisible(true);
    }
    
    public void UpdateSubTotal(){
        float subTotal = mntp.getHarga() * mntp.getJumlah() - diss.getJumlahDiskon();
        if (jTextFieldQty.getText().equalsIgnoreCase("")) {
            mntp.setJumlah(0);
            subTotal = mntp.getHarga() * mntp.getJumlah() - diss.getJumlahDiskon();
        }else{
            mntp.setJumlah(Integer.valueOf(jTextFieldQty.getText()));
            subTotal = mntp.getHarga() * mntp.getJumlah() - diss.getJumlahDiskon();
        }
        jLabelSubTotal.setText("Rp. "+subTotal);
        System.out.println(mntp.getSubtotal());
    }
    
    public static void UpdateSubTotal(menu mn, diskon diss){
        float subTotal = mn.getHarga()*mn.getJumlah()-diss.getJumlahDiskon();
        jLabelSubTotal.setText(subTotal+"");
    }
    
    
    public void SetDiskon(String kode){
//        this.jLabelDiskon.setText(diss.);
    }
    
    public static void SetDatDis(diskon disc){
        
        diss = disc;
        jLabelDiskon.setText(diss.getKode());
        jLabelNamaDiskon.setText(diss.getNama());
        jLabelTDis.setText(diss.getJumlahDiskon()+"");
    }
    
    public void clear(){
        jLabelNama.setText("?");
        jLabelHarga.setText("RP. 0");
        jTextFieldQty.setText("");
        jLabelNamaDiskon.setText("HARGANORMAL");
        jLabelTDis.setText("RP. 0");
        jLabelSubTotal.setText("RP. 0");
    }
    
    private void SubmitMenu(){     
  
        JTable tabelTr = MainJframe.jTableTransaksi;
        String namaMenu = mntp.getNama();
        String kodeMenu = mntp.getKode();
        int QytMenu = mntp.getJumlah();
        float harga = mntp.getHarga();
        float potongan = diss.getJumlahDiskon();
        float SubTotal = QytMenu * harga - potongan;
        boolean sama = false;
        int qtyNow = 0;
        int Tambahan = 0;
        
        Object[] c = new Object[]{namaMenu,kodeMenu,QytMenu,harga,potongan,SubTotal};
        MainJframe.tbModTrans.addRow(c);
        
        if (fungsitTransaksi.tbmodel.getRowCount()>0) {
            
        for (int i = MainJframe.tbModTrans.getRowCount(); i >0 ; i--) {
            if (fungsitTransaksi.tbmodel.getValueAt(i-1, 1).toString().equalsIgnoreCase(mntp.getNama())) {
                qtyNow = Integer.valueOf(fungsitTransaksi.tbmodel.getValueAt(i-1, 2).toString());
                Tambahan = qtyNow + mntp.getJumlah();
                fungsitTransaksi.tbmodel.setValueAt(Tambahan, i-1, 2);
                sama = true;
                break;
            }
            
            sama = false;
        }
        }
        
        if (!sama) {
            
        tabelTr.setModel(MainJframe.tbModTrans);
        }
        this.setVisible(false);
        
        MainJframe.UpdateTotal();

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
        jTextFieldQty = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelNama = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelHarga = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelSubTotal = new javax.swing.JLabel();
        jLabelDiskon = new javax.swing.JLabel();
        jLabelNamaDiskon = new javax.swing.JLabel();
        jLabelTDis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(592, 623));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldQty.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldQty.setPreferredSize(new java.awt.Dimension(476, 46));
        jTextFieldQty.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextFieldQtyInputMethodTextChanged(evt);
            }
        });
        jTextFieldQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldQtyKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(241, 102, 52));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIMPAN");
        jButton1.setMaximumSize(new java.awt.Dimension(500, 300));
        jButton1.setPreferredSize(new java.awt.Dimension(476, 46));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nama Barang");

        jLabelNama.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNama.setText("?");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Masukkan Quantity");

        jLabelHarga.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelHarga.setText("Rp. 0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Harga");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Masukkan Diskon");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Sub Total");

        jLabelSubTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelSubTotal.setText("Rp. 0");

        jLabelDiskon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelDiskon.setText("HARGANORMAL");
        jLabelDiskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDiskonMouseClicked(evt);
            }
        });
        jLabelDiskon.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jLabelDiskonInputMethodTextChanged(evt);
            }
        });
        jLabelDiskon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelDiskonPropertyChange(evt);
            }
        });

        jLabelNamaDiskon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNamaDiskon.setText("Nama Diskon");

        jLabelTDis.setText("Rp. 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                            .addComponent(jTextFieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabelNama))
                        .addGap(59, 59, 59))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHarga)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTDis)
                            .addComponent(jLabelNamaDiskon)
                            .addComponent(jLabelDiskon)
                            .addComponent(jLabel5)
                            .addComponent(jLabelSubTotal)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNama)
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelHarga)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDiskon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNamaDiskon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTDis)
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSubTotal)
                .addGap(9, 9, 9)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQtyKeyReleased
        // TODO add your handling code here:
        UpdateSubTotal();
    }//GEN-LAST:event_jTextFieldQtyKeyReleased

    private void jTextFieldQtyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextFieldQtyInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQtyInputMethodTextChanged

    private void jLabelDiskonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDiskonMouseClicked
        // TODO add your handling code here:
//        CrDis.setInBr(this);
        CrDis.mn = mntp;
        CrDis.action();
//            CrDis.setVisible(true);
    }//GEN-LAST:event_jLabelDiskonMouseClicked

    private void jLabelDiskonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelDiskonPropertyChange
        // TODO add your handling code here:
//        System.out.println("ganti");
//        float subTotal = mntp.getHarga()*mntp.getJumlah()-diss.getJumlahDiskon();
//        this.UpdateSubTotal();
    }//GEN-LAST:event_jLabelDiskonPropertyChange

    private void jLabelDiskonInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jLabelDiskonInputMethodTextChanged
        // TODO add your handling code here:
        jLabelSubTotal.setText("Rp. ganti");
    }//GEN-LAST:event_jLabelDiskonInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        SubmitMenu();
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed


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
            java.util.logging.Logger.getLogger(inputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabelDiskon;
    private javax.swing.JLabel jLabelHarga;
    private javax.swing.JLabel jLabelNama;
    public static javax.swing.JLabel jLabelNamaDiskon;
    public static javax.swing.JLabel jLabelSubTotal;
    public static javax.swing.JLabel jLabelTDis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldQty;
    // End of variables declaration//GEN-END:variables
}
