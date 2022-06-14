package lafo.view.PopUp.pegawai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lafo.entity.user;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;


public class TambahPegawai extends javax.swing.JFrame {
     Koneksi konDbLaf = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLaf);
    user tempPegawai = new user();
    String mode;
    

    public TambahPegawai() {
        initComponents();
//        coba();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    
    
    public String generateCode(){
        int index = 1;
        String nol = "0";
        String nolTanggal = "0";
        String nolBulan = "0";
        String kode;
        String inisial;
        
        int tanggal = tgldaftar.getDate().getDate();
        int bulan = tgldaftar.getDate().getMonth()+1;
        int tahun = tgldaftar.getDate().getYear()+1900;
        String Stahun = String.valueOf(tahun).substring(2);
        
        if (tanggal > 10) {
            nolTanggal = "";
        }
        
        if (bulan > 10) {
            nolBulan = "";
        }
        
        if (cmbakses.getSelectedItem().toString().equalsIgnoreCase("ADMIN")) {
            inisial = "ADM";
        }else{
            inisial = "PGW";
        }
        
        kode = inisial+nolTanggal+tanggal+nolBulan+bulan+Stahun;
        
        String sql = "SELECT Id_Pegawai FROM `pegawai` WHERE Id_Pegawai LIKE '"+kode+"%' ORDER BY Id_Pegawai DESC";
        ResultSet rs = DbOp.getResultSql(sql, true);
        
         try {
             if (rs.next()) {
                 index =Integer.valueOf(rs.getString(1).toString().substring(10))+1 ;
                 if (index < 10) {
                     nol = "0";
                 }else{
                     nol = "";
                 }
             }} catch (SQLException ex) {
             Logger.getLogger(TambahPegawai.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         kode = inisial+nolTanggal+tanggal+nolBulan+bulan+Stahun+nol+index;
        
        return kode;
    }
    
    public void coba(){
        Date date = new Date(2022, 5-1, 1);
        
        tgldaftar.setDate(date);
//        tgldaftar.getDate().setMonth(12);
//        tgldaftar.getDate().setYear(2022);
    }
    
    public void cmbgender(){
        String sql = "SELECT pegawai.gender FROM `pegawai`;";
        ResultSet rs = DbOp.getResultSql(sql, true);
        try {
            while (rs.next()) {
                cmbgender.addItem(rs.getString("gender"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TambahPegawai.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }    
    public void cmbakses(){
        String sql = "SELECT pegawai.hak_akses FROM `pegawai`;";
        ResultSet rs = DbOp.getResultSql(sql, true);
        try {
            while (rs.next()) {
                cmbakses.addItem(rs.getString("hak_akses"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TambahPegawai.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);}     
    }
    
    
    
    public void Tambahpegawai(){
        tempPegawai.setKode(txtkode.getText());
        tempPegawai.setNama(txtnama.getText());
        tempPegawai.setKelamin(cmbgender.getSelectedItem().toString());
        tempPegawai.setAlamat(txtalamat.getText());
        tempPegawai.setNoHp(txthp.getText());
        tempPegawai.setHakAkses(cmbakses.getSelectedItem().toString());
        tempPegawai.setStatus(cmbstatus.getSelectedItem().toString());
    
        String tgl, bln, thn;
        int tanggal, bulan, tahun;
        tanggal = tgldaftar.getDate().getDate();
        bulan = tgldaftar.getDate().getMonth()+1;
        tahun = tgldaftar.getDate().getYear();
        
        if (tanggal < 10){
            tgl = "0"+tanggal;
        }
        else {
            tgl = tanggal+"";
        }
        if (bulan < 10){
            bln = "0"+bulan;
        }
        else{
            bln = bulan+"";
        }
        String TanggalGabung; 
        TanggalGabung = tahun +"-"+ bln +"-"+ tgl;
        
        String sql = "INSERT INTO `pegawai`"
                + "(`Id_Pegawai`,`Nama_Pegawai`,`gender`,`Alamat`,`No_Hp`,`Tanggal_Terdaftar`,`status`,`hak_akses`)"
                + "VALUES"
                + "('"+tempPegawai.getKode()+"', '"+tempPegawai.getNama()+"', '"+tempPegawai.getKelamin()+"','"+tempPegawai.getAlamat()+"','"+tempPegawai.getNoHp()+"'"
                + ",'"+TanggalGabung+"','"+tempPegawai.getStatus()+"','"+tempPegawai.getHakAkses()+"')"; 
          
          System.out.println(sql);
        DbOp.DatabaseExecutor(sql, true);
        String massageSucc = "Berhasil Menambahkan "+tempPegawai.getNama();
        JOptionPane.showMessageDialog(this, massageSucc);
    }
        
    public void tambahAkun(){
        String kode = txtkode.getText();
        String username = txtpassword1.getText();
        String password = txtpassword.getText();
        
        String sql = "INSERT INTO `akun` (`Username`, `password`, `Id_Pegawai`) "
                + "VALUES ('"+username+"', '"+password+"', '"+kode+"')";
        
        DbOp.DatabaseExecutor(sql, true);
    }
    
        public void editpegawai(){
        try {
            String sql = ("UPDATE `pegawai` SET "
                    + "`Nama_Pegawai`='"+txtnama.getText()+"',"
                    + "`Alamat`='"+txtalamat.getText()+"',"
                    + "`No_Hp`='"+txthp.getText()+"',"
                    + "`status`='"+cmbstatus.getSelectedItem()+"',"
                    + "`hak_akses`='"+cmbakses.getSelectedItem()+"'"
                    + " WHERE Id_Pegawai ='"+txtkode.getText()+"'");
            DbOp.DatabaseExecutor(sql, true);
            
        }catch (Exception e){   
        }
    }
    
    public void editAkun(){
        String username = txtpassword1.getText();
        String password = txtpassword.getText();
        String kode = txtkode.getText();
        String sql = "UPDATE `akun` SET `Username`='"+username+"',`password`='"+password+"' WHERE Id_Pegawai = '"+kode+"'";
        DbOp.DatabaseExecutor(sql, true);
    }
        
       public void bersihkanform(){
           txtkode.setText("");
           txtnama.setText("");
           txtalamat.setText("");
           txthp.setText("");
           txtpassword.setText("");
           
       }


        
        
        
        
        

//        jTextField_kodebarang.setText(inisial);
//        return kode;
        
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtkode = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtpassword = new javax.swing.JTextField();
        txthp = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        cmbgender = new javax.swing.JComboBox<>();
        cmbakses = new javax.swing.JComboBox<>();
        txtalamat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tgldaftar = new com.toedter.calendar.JDateChooser();
        btnsimpan = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        cmbstatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtpassword1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1352, 753));

        txtkode.setPreferredSize(new java.awt.Dimension(690, 55));
        txtkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodeActionPerformed(evt);
            }
        });

        txtnama.setPreferredSize(new java.awt.Dimension(690, 55));
        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        txtpassword.setPreferredSize(new java.awt.Dimension(690, 55));
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });

        txthp.setPreferredSize(new java.awt.Dimension(690, 55));
        txthp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthpActionPerformed(evt);
            }
        });

        jTextField6.setText("jTextField1");
        jTextField6.setPreferredSize(new java.awt.Dimension(690, 55));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        cmbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "L", "P" }));
        cmbgender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbgenderActionPerformed(evt);
            }
        });

        cmbakses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Pegawai" }));
        cmbakses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbaksesActionPerformed(evt);
            }
        });
        cmbakses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbaksesKeyReleased(evt);
            }
        });

        txtalamat.setPreferredSize(new java.awt.Dimension(690, 55));
        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel1.setText("Kode Pegawai");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel2.setText("Jenis kelamin");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel3.setText("No. HP");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel5.setText("Password");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel6.setText("Nama Lengkap");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel8.setText("Tanggal Terdaftar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel10.setText("Hak Akses");

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/Frame 28.png"))); // NOI18N
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/Frame 27.png"))); // NOI18N
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        cmbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Tidak Aktif" }));
        cmbstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbstatusActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel9.setText("Status");

        jButton1.setText("generate code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtpassword1.setPreferredSize(new java.awt.Dimension(690, 55));
        txtpassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassword1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel7.setText("Username");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cmbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthp, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(61, 61, 61))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(69, 69, 69)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(cmbakses, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(tgldaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(224, 224, 224)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(cmbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbakses, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtpassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(tgldaftar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(588, 588, 588)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeActionPerformed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void txthpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthpActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatActionPerformed

    private void cmbaksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbaksesActionPerformed

    }//GEN-LAST:event_cmbaksesActionPerformed

    private void cmbaksesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbaksesKeyReleased
  
    }//GEN-LAST:event_cmbaksesKeyReleased

    private void cmbgenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbgenderActionPerformed

    }//GEN-LAST:event_cmbgenderActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
bersihkanform();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
    
    
        if (mode.equalsIgnoreCase("tambah")) {
            Tambahpegawai();
            tambahAkun();
        }
        
        if (mode.equalsIgnoreCase("edit")) {
            editAkun();
            editpegawai();
        }
        JOptionPane.showMessageDialog(null, "data telah disimpan");
        bersihkanform();
        this.setVisible(false);
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void cmbstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbstatusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
        String kode = generateCode();
        txtkode.setText(kode);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "mohon masukan tanggal terlebih dahulu");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtpassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassword1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassword1ActionPerformed

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
            java.util.logging.Logger.getLogger(TambahPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahPegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    public javax.swing.JComboBox<String> cmbakses;
    public javax.swing.JComboBox<String> cmbgender;
    public javax.swing.JComboBox<String> cmbstatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField6;
    public com.toedter.calendar.JDateChooser tgldaftar;
    public javax.swing.JTextField txtalamat;
    public javax.swing.JTextField txthp;
    public javax.swing.JTextField txtkode;
    public javax.swing.JTextField txtnama;
    public javax.swing.JTextField txtpassword;
    public javax.swing.JTextField txtpassword1;
    // End of variables declaration//GEN-END:variables
}
