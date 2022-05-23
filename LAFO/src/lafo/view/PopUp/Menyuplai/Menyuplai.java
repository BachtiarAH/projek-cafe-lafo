/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.view.PopUp.menyuplai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lafo.entity.barang;
import lafo.proses.DataBase.DataBaseOperator;
import lafo.proses.DataBase.Koneksi;

/**
 *
 * @author user
 */
public class Menyuplai extends javax.swing.JFrame {

    Koneksi konDbLafo = new Koneksi();
    DataBaseOperator DbOp = new DataBaseOperator(konDbLafo);
    DefaultTableModel Tbmodel = new DefaultTableModel();
    LocalDate tgl = LocalDate.now();
    barang brang = new barang();    
    /**
     * Creates new form Menyuplai
     */
    public Menyuplai() {
        initComponents();
        tampilBarang("");
        tampilSuplier("");
        tampilMenyuplai();
        this.setLocationRelativeTo(null);
    }

    public void tampilBarang(String cari){
        String sql = "SELECT "
                + "barang.kode_Barang, "
                + "barang.Nama_barang, "
                + "SUM(detail_suplai.stok), "
                + "barang.satuan "
                + "FROM barang "
                + "LEFT JOIN detail_suplai "
                + "ON barang.kode_Barang = detail_suplai.kode_Barang "
                 + "WHERE barang.nama_barang LIKE '%"+cari+"%' "
                + "GROUP BY kode_Barang ";
        String[] header = {"kode Barang","nama Barang","stok","satuan"};
        DbOp.tabel(sql, header, jTableBrg);
    }
    
    public void tampilSuplier(String cari){
        String sql = "SELECT `kode_suplaier`,`nama_suplier`, `No_Telp`, `Alamat` FROM `suplier`"
                 + "WHERE kode_suplaier LIKE '%"+cari+"%'" ;
        String[] header = {"Kode Suplier", "Nama", "No telpon", "Alamat"};
        DbOp.tabel(sql, header, jTableSuplier);
    }
    
    public void tampilMenyuplai(){
        String[] header = {"kode barang","kode Suplier","harga satuan","jumlah","satuan","subtotal"};
        for (int i = 0; i < header.length; i++) {
            Tbmodel.addColumn(header[i]);
        }
        
        jTableMenyuplai.setModel(Tbmodel);
    }
    
    public void klikBarng(){
        String kodeBarang = jTableBrg.getValueAt(jTableBrg.getSelectedRow(), 0).toString();
        String satuan = jTableBrg.getValueAt(jTableBrg.getSelectedRow(), 3).toString();
        brang.setKode(kodeBarang);
        brang.setSatuan(satuan);
        jTextFieldKdBarang.setText(kodeBarang);
        jLabelSatuan.setText(satuan);
        jLabelSatuan1.setText(satuan);
    }
    
    public void klikSuplier(){
        String kodeSuplier = jTableSuplier.getValueAt(jTableSuplier.getSelectedRow(), 0).toString();
        jTextFieldKdSuplier.setText(kodeSuplier);
    }
    
    public void UpdateSubtotal(){
        float harga;
        float jumlah;
        
        if (jTextFieldHargaBeli.getText().equalsIgnoreCase("")) {
            harga = 0;
        }else{
            
            harga = Float.valueOf(jTextFieldHargaBeli.getText());
        }
        
        if (jTextJumlah.getText().equalsIgnoreCase("")) {
            jumlah = 0;
        }else{
            jumlah = Float.valueOf(jTextJumlah.getText());
        }
        
        float Subtotal = harga * jumlah;
        jLabelSubTotal.setText(Subtotal+"");
    }
    
    public void submitBarng(){
        String kodeBrg = jTextFieldKdBarang.getText();
        String kodeSup = jTextFieldKdSuplier.getText();
        String harga = jTextFieldHargaBeli.getText();
        String jumlaah = jTextJumlah.getText();
        String satuan = brang.getSatuan();
        String subTotal = jLabelSubTotal.getText();
        
        String[] SubBarang = {kodeBrg,kodeSup,harga,jumlaah,satuan,subTotal};
        Tbmodel.addRow(SubBarang);
    }
    
    public void klikTbMenyup(){
        String kodeBrg = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 0).toString();
        String kodeSup = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 1).toString();
        String harga = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 2).toString();
        String jumlah = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 3).toString();
        String satuan = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 4).toString();
        String subTotal = jTableMenyuplai.getValueAt(jTableMenyuplai.getSelectedRow(), 5).toString();
        
        jTextFieldKdBarang.setText(kodeBrg);
        jTextFieldKdSuplier.setText(kodeSup);
        jTextFieldHargaBeli.setText(harga);
        jTextJumlah.setText(jumlah);
        jLabelSatuan.setText(satuan);
        jLabelSatuan1.setText(satuan);
        jLabelSubTotal.setText(subTotal);
    }
    
    public void hapusSubmitedBrg(){
        Tbmodel.removeRow(jTableMenyuplai.getSelectedRow());
    }
    
    public void updateKlikedMenyuplai(){
         String kodeBrg = jTextFieldKdBarang.getText();
        String kodeSup = jTextFieldKdSuplier.getText();
        String harga = jTextFieldHargaBeli.getText();
        String jumlaah = jTextJumlah.getText();
        String satuan = brang.getSatuan();
        String subTotal = jLabelSubTotal.getText();
        
        String[] perubahan = {kodeBrg,kodeSup,harga,jumlaah,satuan,subTotal};
        Object isi;
        
        for (int i = 0; i < perubahan.length; i++) {
            isi = perubahan[i];
            Tbmodel.setValueAt(perubahan[i], jTableMenyuplai.getSelectedRow(), i);
        }
    }
    
    public void UpdateJumlahBrg(){
        int jumlahBrg = jTableMenyuplai.getRowCount();
        jLabelTtlBarang.setText(jumlahBrg+"");
    }
    
    public void UpdateGrandTotal(){
        float GrandTotal = 0;
        float subTotal = 0;
        
        for (int i = 0; i < jTableMenyuplai.getRowCount(); i++) {
            if ((jTableMenyuplai.getValueAt(i, 5) == null)||(jTableMenyuplai.getValueAt(i, 5).toString().equalsIgnoreCase(""))) {
                subTotal = 0;
            }else  {
                
            subTotal = Float.valueOf(jTableMenyuplai.getValueAt(i, 5).toString());
            }
            GrandTotal +=  subTotal;
        }
        
        jLabelGrandTotal.setText(GrandTotal + "");
    }
    
    public String getTanggal(){
        String bulan = "";
        if (tgl.getMonthValue()<10) {
            bulan = "0"+tgl.getMonthValue();
        }else{
            bulan = tgl.getMonthValue()+"";
        }
        String tahun = (tgl.getYear()+"").substring(2);
        String tanggal = tgl.getDayOfMonth()+bulan+tahun;
        
        return tanggal;
                
                
    }
    
    public String GenerateKodeMenyuplai(){
        int index = 1;
        String indexString = "";
        String bulan = "";
        if (tgl.getMonthValue()<10) {
            bulan = "0"+tgl.getMonthValue();
        }else{
            bulan = tgl.getMonthValue()+"";
        }
        String tahun = tgl.getYear()+"";

        String kode = "MSP"+tgl.getDayOfMonth()+bulan+tahun.substring(2);
        String sql = "SELECT `Kode_Menyuplai` FROM `menyuplai`\n" +
                        "WHERE Kode_Menyuplai LIKE '"+kode+"'\n" +
                        "ORDER by Kode_Menyuplai DESC LIMIT 1;";
        
        ResultSet rs = DbOp.getResultSql(sql, true);
        
        try {
            while (rs.next()) {
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menyuplai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (index < 10) {
            indexString = "0"+index;
        }else{
            indexString = index+"";
        }
        
        kode += indexString;
        return kode;
    }
    
    String antrianSuplier;
    
    public void UpdateAntrianSuplier(){
        antrianSuplier = jTableMenyuplai.getValueAt(0, 1).toString();
    }
    
    public void SubmitMenyuplai(){
        if (!(Tbmodel.getValueAt(0, 1).toString().equalsIgnoreCase(antrianSuplier))) {
            UpdateAntrianSuplier();
        String sqlMenyuplai = "INSERT INTO `menyuplai`"
                + "(`Kode_Menyuplai`, `Tanggal_menyuplai`, `kode_suplaier`, `Id_Pegawai`) "
                + "VALUES ('"
                + ""+GenerateKodeMenyuplai()+"','"
                + ""+getTanggal()+"','"
                + ""+antrianSuplier+"','"
                + "[value-4]')";
            System.out.println("udah beda");
        }else{
            String detailSup = "INSERT INTO `detail_suplai`"
                    + "(`harga_beli`, `qty`, `Id_detail_suplai`, `satuan`, `Kode_Menyuplai`, `kode_Barang`, `stok`, `harga_beli_per_satuan`) "
                    + "VALUES ("
                    + "'[value-1]',"
                    + "'[value-2]',"
                    + "'[value-3]',"
                    + "'[value-4]',"
                    + "'[value-5]',"
                    + "'[value-6]',"
                    + "'[value-7]',"
                    + "'[value-8]')";
            System.out.println("masih sama");
        }
        
        Tbmodel.removeRow(0);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenyuplai = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextSuplier = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBrg = new javax.swing.JTable();
        jTextFieldCrBrg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldKdBarang = new javax.swing.JTextField();
        jTextFieldKdSuplier = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldHargaBeli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextJumlah = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSuplier = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabelSubTotal = new javax.swing.JLabel();
        jLabelSatuan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabelSatuan1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMenyuplai = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabelTtlBarang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelGrandTotal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenyuplai.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMenyuplai.setPreferredSize(new java.awt.Dimension(992, 636));

        jPanel2.setBackground(new java.awt.Color(233, 235, 239));
        jPanel2.setPreferredSize(new java.awt.Dimension(972, 292));

        jTextSuplier.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextSuplier.setPreferredSize(new java.awt.Dimension(341, 39));
        jTextSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSuplierActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(223, 205));

        jTableBrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBrgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBrg);

        jTextFieldCrBrg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldCrBrg.setPreferredSize(new java.awt.Dimension(223, 39));
        jTextFieldCrBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCrBrgActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kode Suplier");

        jTextFieldKdBarang.setMinimumSize(new java.awt.Dimension(201, 30));
        jTextFieldKdBarang.setPreferredSize(new java.awt.Dimension(201, 30));
        jTextFieldKdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKdBarangActionPerformed(evt);
            }
        });

        jTextFieldKdSuplier.setPreferredSize(new java.awt.Dimension(201, 30));
        jTextFieldKdSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKdSuplierActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Harga Beli");

        jTextFieldHargaBeli.setPreferredSize(new java.awt.Dimension(201, 30));
        jTextFieldHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHargaBeliActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Jumlah");

        jTextJumlah.setPreferredSize(new java.awt.Dimension(201, 30));
        jTextJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextJumlahActionPerformed(evt);
            }
        });

        jScrollPane3.setPreferredSize(new java.awt.Dimension(341, 205));

        jTableSuplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableSuplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSuplierMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableSuplier);

        jButton2.setText("Hapus");
        jButton2.setPreferredSize(new java.awt.Dimension(74, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(241, 102, 52));
        jButton5.setText("tambah");
        jButton5.setPreferredSize(new java.awt.Dimension(74, 33));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Sub Total");

        jLabelSubTotal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelSubTotal.setText("0");

        jLabelSatuan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSatuan.setText("satuan");

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setText("Update");
        jButton1.setPreferredSize(new java.awt.Dimension(74, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("/");

        jLabelSatuan1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSatuan1.setText("satuan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldCrBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldKdSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(77, 77, 77))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(62, 62, 62)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelSatuan1)
                                    .addComponent(jLabelSatuan))
                                .addGap(8, 8, 8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelSubTotal)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCrBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldKdSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabelSatuan1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSatuan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabelSubTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 51)); // NOI18N
        jLabel1.setText("Menyuplai");

        jPanel1.setPreferredSize(new java.awt.Dimension(960, 219));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(586, 183));

        jTableMenyuplai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableMenyuplai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMenyuplaiMouseClicked(evt);
            }
        });
        jTableMenyuplai.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableMenyuplaiPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableMenyuplai);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Total Barang");

        jLabelTtlBarang.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelTtlBarang.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Grand Total");

        jLabelGrandTotal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabelGrandTotal.setText("0");

        jButton3.setText("Bersihkan");
        jButton3.setPreferredSize(new java.awt.Dimension(74, 33));

        jButton6.setBackground(new java.awt.Color(241, 102, 52));
        jButton6.setText("Submit");
        jButton6.setPreferredSize(new java.awt.Dimension(74, 33));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabelTtlBarang)
                            .addComponent(jLabel8)
                            .addComponent(jLabelGrandTotal))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTtlBarang)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelGrandTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenyuplaiLayout = new javax.swing.GroupLayout(jPanelMenyuplai);
        jPanelMenyuplai.setLayout(jPanelMenyuplaiLayout);
        jPanelMenyuplaiLayout.setHorizontalGroup(
            jPanelMenyuplaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenyuplaiLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelMenyuplaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)))
        );
        jPanelMenyuplaiLayout.setVerticalGroup(
            jPanelMenyuplaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenyuplaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenyuplai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenyuplai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCrBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCrBrgActionPerformed
        // TODO add your handling code here:
        String cari = jTextFieldCrBrg.getText();
        tampilBarang(cari);
    }//GEN-LAST:event_jTextFieldCrBrgActionPerformed

    private void jTextFieldKdSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKdSuplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKdSuplierActionPerformed

    private void jTextFieldHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHargaBeliActionPerformed
        // TODO add your handling code here:
        UpdateSubtotal();
    }//GEN-LAST:event_jTextFieldHargaBeliActionPerformed

    private void jTextSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSuplierActionPerformed
           // TODO add your handling code here:
           String cari = jTextSuplier.getText();
           tampilSuplier(cari);
    }//GEN-LAST:event_jTextSuplierActionPerformed

    private void jTextFieldKdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKdBarangActionPerformed

    private void jTextJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextJumlahActionPerformed
        // TODO add your handling code here:
        UpdateSubtotal();
    }//GEN-LAST:event_jTextJumlahActionPerformed

    private void jTableBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBrgMouseClicked
        // TODO add your handling code here:
        klikBarng();
    }//GEN-LAST:event_jTableBrgMouseClicked

    private void jTableSuplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSuplierMouseClicked
        // TODO add your handling code here:
        klikSuplier();
    }//GEN-LAST:event_jTableSuplierMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        submitBarng();
        UpdateJumlahBrg();
        UpdateGrandTotal();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTableMenyuplaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMenyuplaiMouseClicked
        // TODO add your handling code here:
        klikTbMenyup();
    }//GEN-LAST:event_jTableMenyuplaiMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hapusSubmitedBrg();
        UpdateJumlahBrg();
        UpdateGrandTotal();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        updateKlikedMenyuplai();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableMenyuplaiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableMenyuplaiPropertyChange
        // TODO add your handling code here:
//        UpdateJumlahBrg();
//        UpdateGrandTotal();
    }//GEN-LAST:event_jTableMenyuplaiPropertyChange

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        System.out.println(GenerateKodeMenyuplai());
        if ( jTableMenyuplai.getRowCount()>0) {
        UpdateAntrianSuplier();
        while (( jTableMenyuplai.getRowCount()>0)) {            
        SubmitMenyuplai();
        }
            
        }else{
            JOptionPane.showMessageDialog(null, "tabel bawah tidak boleh kosong");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Menyuplai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menyuplai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menyuplai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menyuplai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menyuplai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelGrandTotal;
    private javax.swing.JLabel jLabelSatuan;
    private javax.swing.JLabel jLabelSatuan1;
    private javax.swing.JLabel jLabelSubTotal;
    private javax.swing.JLabel jLabelTtlBarang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMenyuplai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBrg;
    private javax.swing.JTable jTableMenyuplai;
    private javax.swing.JTable jTableSuplier;
    private javax.swing.JTextField jTextFieldCrBrg;
    private javax.swing.JTextField jTextFieldHargaBeli;
    private javax.swing.JTextField jTextFieldKdBarang;
    private javax.swing.JTextField jTextFieldKdSuplier;
    private javax.swing.JTextField jTextJumlah;
    private javax.swing.JTextField jTextSuplier;
    // End of variables declaration//GEN-END:variables
}
