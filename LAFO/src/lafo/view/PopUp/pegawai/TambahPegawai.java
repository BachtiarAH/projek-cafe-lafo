package lafo.view.PopUp.pegawai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        cmbgender();
        cmbakses();
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
//        tempPegawai.setgender(cmbgender.getText());/
        tempPegawai.setAlamat(txtnama.getText());
//        tempPegawai.nohp(txtnama.getText());
//        tempPegawai.setHakakses(txtnama.getText());
//        tempPegawai.setPassword(txtnama.getText());
//        tempPegawai.setTerdaftar(txtnama.getText());
       
    
    }


        
        
        
        
        

//        jTextField_kodebarang.setText(inisial);
//        return kode;
        
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelLogo = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        panel_Header = new javax.swing.JPanel();
        LabelNamaToko = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelNamaUSer = new javax.swing.JLabel();
        jLabelAkses = new javax.swing.JLabel();
        panelNavigasiBar = new javax.swing.JPanel();
        jLabelLogoTransaksi = new javax.swing.JLabel();
        jLabelManajemenData = new javax.swing.JLabel();
        jLabelLaporan = new javax.swing.JLabel();
        jLabelUsesrs = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
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
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelLogo.setPreferredSize(new java.awt.Dimension(108, 76));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/logog lavo pojok.png"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(40, 70));

        javax.swing.GroupLayout JPanelLogoLayout = new javax.swing.GroupLayout(JPanelLogo);
        JPanelLogo.setLayout(JPanelLogoLayout);
        JPanelLogoLayout.setHorizontalGroup(
            JPanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLogoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        JPanelLogoLayout.setVerticalGroup(
            JPanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLogoLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panel_Header.setBackground(new java.awt.Color(241, 102, 52));
        panel_Header.setPreferredSize(new java.awt.Dimension(1280, 76));

        LabelNamaToko.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LabelNamaToko.setForeground(new java.awt.Color(255, 255, 255));
        LabelNamaToko.setText("CAFE LAFO");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/user.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(36, 36));

        jLabelNamaUSer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabelNamaUSer.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNamaUSer.setText("Nama");

        jLabelAkses.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAkses.setText("akses");

        javax.swing.GroupLayout panel_HeaderLayout = new javax.swing.GroupLayout(panel_Header);
        panel_Header.setLayout(panel_HeaderLayout);
        panel_HeaderLayout.setHorizontalGroup(
            panel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HeaderLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(LabelNamaToko)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNamaUSer)
                    .addComponent(jLabelAkses))
                .addGap(183, 183, 183))
        );
        panel_HeaderLayout.setVerticalGroup(
            panel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HeaderLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_HeaderLayout.createSequentialGroup()
                            .addComponent(jLabelNamaUSer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelAkses)))
                    .addComponent(LabelNamaToko))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelNavigasiBar.setBackground(new java.awt.Color(42, 48, 48));
        panelNavigasiBar.setPreferredSize(new java.awt.Dimension(106, 699));
        panelNavigasiBar.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                panelNavigasiBarComponentMoved(evt);
            }
        });

        jLabelLogoTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/airplay.png"))); // NOI18N
        jLabelLogoTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoTransaksiMouseClicked(evt);
            }
        });

        jLabelManajemenData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/box.png"))); // NOI18N
        jLabelManajemenData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelManajemenDataMouseClicked(evt);
            }
        });

        jLabelLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/repeat.png"))); // NOI18N
        jLabelLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLaporanMouseClicked(evt);
            }
        });

        jLabelUsesrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/users.png"))); // NOI18N
        jLabelUsesrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelUsesrsMouseClicked(evt);
            }
        });

        jLabelLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/log-out.png"))); // NOI18N
        jLabelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelNavigasiBarLayout = new javax.swing.GroupLayout(panelNavigasiBar);
        panelNavigasiBar.setLayout(panelNavigasiBarLayout);
        panelNavigasiBarLayout.setHorizontalGroup(
            panelNavigasiBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavigasiBarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelLogOut)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNavigasiBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelNavigasiBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelNavigasiBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelManajemenData)
                        .addComponent(jLabelLogoTransaksi))
                    .addGroup(panelNavigasiBarLayout.createSequentialGroup()
                        .addGroup(panelNavigasiBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelUsesrs)
                            .addComponent(jLabelLaporan))
                        .addGap(1, 1, 1)))
                .addGap(27, 27, 27))
        );
        panelNavigasiBarLayout.setVerticalGroup(
            panelNavigasiBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNavigasiBarLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabelLogoTransaksi)
                .addGap(40, 40, 40)
                .addComponent(jLabelManajemenData)
                .addGap(45, 45, 45)
                .addComponent(jLabelLaporan)
                .addGap(37, 37, 37)
                .addComponent(jLabelUsesrs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                .addComponent(jLabelLogOut)
                .addGap(33, 33, 33))
        );

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

        cmbgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        cmbgender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbgenderActionPerformed(evt);
            }
        });

        cmbakses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
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

        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/icon/Frame 27.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cmbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthp, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(cmbakses, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tgldaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(72, 72, 72))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jLabel5)
                    .addContainerGap(1216, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
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
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tgldaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(588, 588, 588)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(423, 423, 423)
                    .addComponent(jLabel5)
                    .addContainerGap(758, Short.MAX_VALUE)))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel11.setText("Profile User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(panel_Header, javax.swing.GroupLayout.DEFAULT_SIZE, 1378, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNavigasiBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPanelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNavigasiBar, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelLogoTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoTransaksiMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jLabelLogoTransaksiMouseClicked

    private void jLabelManajemenDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelManajemenDataMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jLabelManajemenDataMouseClicked

    private void jLabelLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLaporanMouseClicked
        // TODO add your handling code here:
  
    }//GEN-LAST:event_jLabelLaporanMouseClicked

    private void jLabelUsesrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelUsesrsMouseClicked
        // TODO add your handling code here:
       

    }//GEN-LAST:event_jLabelUsesrsMouseClicked

    private void jLabelLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabelLogOutMouseClicked

    private void panelNavigasiBarComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelNavigasiBarComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_panelNavigasiBarComponentMoved

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
    private javax.swing.JPanel JPanelLogo;
    private javax.swing.JLabel LabelNamaToko;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmbakses;
    private javax.swing.JComboBox<String> cmbgender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelAkses;
    private javax.swing.JLabel jLabelLaporan;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelLogoTransaksi;
    private javax.swing.JLabel jLabelManajemenData;
    public javax.swing.JLabel jLabelNamaUSer;
    private javax.swing.JLabel jLabelUsesrs;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JPanel panelNavigasiBar;
    private javax.swing.JPanel panel_Header;
    private com.toedter.calendar.JDateChooser tgldaftar;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txthp;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpassword;
    // End of variables declaration//GEN-END:variables
}
