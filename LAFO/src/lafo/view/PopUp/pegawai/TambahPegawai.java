package lafo.view.PopUp.pegawai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lafo.entity.user;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;
import lafo.view.MainJframe;


public class TambahPegawai extends javax.swing.JFrame {
     Koneksi konDbLaf = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLaf);
    user tempPegawai = new user();
    String mode;
    

    public TambahPegawai() {
        initComponents();
        
    }
    
  
        
 
//    kode hak Akses
    public String GenerateKodeAkses(){  
        int indexkode;
        String IndexKode;
        String IndeksRs = "0";
        String nol = "0";
        String initialjbtn ;
        String Kode;
        String jabatan;
        String tgl, bln, thn,Sthun;
        int tanggal, bulan, tahun;
         
        tanggal = tgldaftar.getDate().getDate();
        bulan = tgldaftar.getDate().getMonth();
        tahun = tgldaftar.getDate().getYear()+1901;
        Sthun = String.valueOf(tahun).substring(2);
        
        if (tanggal < 10){
            tgl = "0"+tanggal;
        }else {
            tgl = tanggal+"";
        }
        if (bulan < 10){
            bln = "0"+bulan;
        }else{
            bln = bulan+"";
        }
         jabatan = tgl+""+bln+""+Sthun;
         
        if (cmbakses.getSelectedItem().toString().equalsIgnoreCase("admin")) {
       
         Kode = "ADM"+jabatan;
         String sql = "SELECT pegawai.Id_Pegawai FROM pegawai\n" +
            "WHERE pegawai.Id_Pegawai LIKE '"+Kode+"%'\n" +
            "ORDER BY pegawai.Id_Pegawai DESC\n" +
            "LIMIT 1;"; 
        ResultSet rs = DbOp.getResultSql(sql, true);
        try {            
                while (rs.next()) {                               
                IndeksRs = rs.getString(1).substring(9);
            }
               indexkode = Integer.valueOf(IndeksRs)+1;
                
                if (indexkode < 10) {
                    IndexKode = "0"+indexkode;
                }else{
                    IndexKode = indexkode+"";
                }
         
            Kode = "ADM"+jabatan+IndexKode;
            
            System.out.println(Kode);
        
        } catch (SQLException ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
         Kode = "PGW"+jabatan;
         String sql = "SELECT pegawai.Id_Pegawai FROM pegawai\n" +
            "WHERE pegawai.Id_Pegawai LIKE '"+Kode+"%'\n" +
            "ORDER BY pegawai.Id_Pegawai DESC\n" +
            "LIMIT 1;"; 
        ResultSet rs = DbOp.getResultSql(sql, true);
        try {            
                while (rs.next()) {                               
                IndeksRs = rs.getString(1).substring(8,10);
            }
               indexkode = Integer.valueOf(IndeksRs)+1;
                
                if (indexkode < 10) {
                    IndexKode = "0"+indexkode;
                }else{
                    IndexKode = indexkode+"";
                }
         
            Kode = "PGW"+jabatan+IndexKode;
            
            System.out.println(Kode);
        
        } catch (SQLException ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        return Kode;
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
        tahun = tgldaftar.getDate().getYear()+1900;
        
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
          
          
       DbOp.DatabaseExecutor(sql, true);
        String massageSucc = "Berhasil Menambahkan "+tempPegawai.getNama();
        JOptionPane.showMessageDialog(this, massageSucc);
    }
        
        public void tambahakun(){
        tempPegawai.setUsername(txtusername.getText());
        tempPegawai.setPassword(txtpassword.getText());
        tempPegawai.setKode(txtkode.getText());

        String sql = "INSERT INTO `akun`(`Username`, `password`, `Id_Pegawai`) VALUES ('"+tempPegawai.getUsername()+"', '"+tempPegawai.getPassword()+"', '"+tempPegawai.getKode()+"')";
        
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
        btnakses = new javax.swing.JButton();
        txtusername = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        tgldaftar.setDateFormatString("YYYY MM dd");

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/Frame 28.png"))); // NOI18N
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/Frame 27.png"))); // NOI18N
        btnhapus.setPreferredSize(new java.awt.Dimension(0, 0));
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

        btnakses.setText("Generate");
        btnakses.setPreferredSize(new java.awt.Dimension(237, 63));
        btnakses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaksesActionPerformed(evt);
            }
        });

        txtusername.setPreferredSize(new java.awt.Dimension(690, 55));
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel11.setText("Username");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnakses, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(cmbgender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txthp, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(tgldaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4))
                            .addGap(520, 520, 520))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtalamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                                .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(154, 154, 154)
                                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbakses, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtusername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnakses, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbakses, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbstatus, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(tgldaftar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(546, 546, 546)
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
    Tambahpegawai();
    tambahakun();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void cmbstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbstatusActionPerformed

    private void btnaksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaksesActionPerformed
        try {
        String kode = GenerateKodeAkses();
      txtkode.setText(kode);
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "pilih tanggal terlebih dahulu");
        }
    }//GEN-LAST:event_btnaksesActionPerformed

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameActionPerformed

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
    private javax.swing.JButton btnakses;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    public javax.swing.JComboBox<String> cmbakses;
    public javax.swing.JComboBox<String> cmbgender;
    public javax.swing.JComboBox<String> cmbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField6;
    public com.toedter.calendar.JDateChooser tgldaftar;
    public javax.swing.JTextField txtalamat;
    public javax.swing.JTextField txthp;
    public javax.swing.JTextField txtkode;
    public javax.swing.JTextField txtnama;
    public javax.swing.JTextField txtpassword;
    public javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
