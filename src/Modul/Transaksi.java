/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modul;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dede
 */
import Koneksi.koneksiDB;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Transaksi extends javax.swing.JFrame {

    private DefaultTableModel model;
    String no_parkir, plat_nomor, kd_kendaraan,nm_kendaraan, nm_kategori,kd_kategori, jam_masuk, jam_keluar;
            int  tarif;
            double lama_parkir, total;
           
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        
         //membuat obyek1
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tb_trans.setModel(model);
        model.addColumn("NO PARKIR");
        model.addColumn("PLAT NOMOR");
        model.addColumn("KODE KENDARAAN");
        model.addColumn("JENIS KENDARAAN");
        model.addColumn("TARIF /JAM");
        model.addColumn("KODE KATEGORI");
        model.addColumn("KATEGORI PARKIR");
        model.addColumn("JAM MASUK PARKIR");
        model.addColumn("JAM KELUAR PARKIR");
        model.addColumn("LAMA PARKIR");
        model.addColumn("TOTAL");
        
        getDataTransaksi();
        combo_kate();
        combo_jenis();
        
    }
    public void getDataTransaksi(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel kategori        
            String sql = "SELECT * FROM transaksi";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[11];
                obj[0]=res.getString("no_parkir");
                obj[1]=res.getString("plat_nomor");
                obj[2]=res.getString("kd_kendaraan");
                obj[3]=res.getString("nm_kendaraan");
                obj[4]=res.getString("tarif");
                obj[5]=res.getString("kd_kategori");
                obj[6]=res.getString("nm_kategori");
                obj[7]=res.getString("jam_masuk");
                obj[8]=res.getString("jam_keluar");
                obj[9]=res.getString("lama_parkir");
                obj[10]=res.getString("total");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void loadDataTransaksi(){
        no_parkir = tnoparkir.getText();
        plat_nomor = tplat.getText();
        kd_kendaraan = (String) cbjenis.getSelectedItem();
        nm_kendaraan = nm_jenis.getText();
        tarif = Integer.parseInt(ttarif1.getText());
        kd_kategori = (String) cbkate.getSelectedItem();
        nm_kategori = nm_kate.getText();
        jam_masuk = tmasuk.getText();
        jam_keluar = tkeluar.getText();
        lama_parkir = Double.parseDouble(tlama.getText());
        total = Double.parseDouble(ttotal.getText());
        
    }
     public void dataSelect(){
        //deklarasi variabel
        int i = tb_trans.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        tnoparkir.setText(""+model.getValueAt(i,0));
        tplat.setText(""+model.getValueAt(i,1));
        ttarif1.setText(""+model.getValueAt(i,2));
    }
     public void baru(){
        no_parkir = "";
        plat_nomor = "";
        nm_kendaraan = "";
        tarif = 0;
        nm_kategori = "";
        jam_masuk = "";
        jam_keluar = "";
        lama_parkir = 0;
        total = 0;
        
        tnoparkir.setText(no_parkir);
        tplat.setText(plat_nomor);
        ttarif1.setText("");
        cbjenis.setSelectedItem("");
        cbkate.setSelectedItem("");
    }
     public void simpanDataTransaksi(){
        //panggil fungsi load data
        loadDataTransaksi();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO transaksi(no_parkir, plat_nomor, kd_kendaraan,nm_kendaraan, tarif, kd_kategori,nm_kategori,lama_parkir, jam_masuk, jam_keluar, total)" +
                    "VALUES('"+ no_parkir +"','"+ plat_nomor +"','"+ kd_kendaraan +"','"+ nm_kendaraan +"','"+ tarif +"','"+ kd_kategori +"','"+ nm_kategori +"','"+ lama_parkir +"','"+ jam_masuk +"','"+ jam_keluar +"','"+ total +"')";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataTransaksi();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     public void perbaruiDataTransaksi(){
        //fungsi load data kategori
        loadDataTransaksi();
        
        try{
            //uji koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //kirim perintah sql
            String sql = "UPDATE transaksi SET plat_nomor = '"+ plat_nomor +"'," 
                    + " kd_kendaraan = '"+ kd_kendaraan +"'"
                    + " nm_kendaraan = '"+ nm_kendaraan +"'"
                    + " tarif = '"+ tarif +"'" 
                    + " kd_kategori = '"+ kd_kategori +"'" 
                    + " nm_kategori = '"+ nm_kategori +"'" 
                    + " jam_masuk = '"+ jam_masuk +"'"
                    + " jam_keluar = '"+ jam_keluar +"'"
                    + " lama_parkir = '"+ lama_parkir +"'"
                    + " total = '"+ total +"'"
                    + " WHERE no_parkir = '"+ no_parkir +"'" ;
            PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataTransaksi();
            
            //kosongkan data
            baru();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DIUBAH");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     
    public void combo_kate()
    {
        try {
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        String sql = "select kd_kategori from kategori order by kd_kategori asc";
        ResultSet res = stat.executeQuery(sql); 
        
        while(res.next()){
        Object[] ob = new Object[3];
        ob[0] = res.getString(1);
        
        cbkate.addItem((String) ob[0]);
        }
        res.close(); stat.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampilkan_kate()
    {
        try {
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        String sql = "select nm_kategori from kategori where kd_kategori='"+cbkate.getSelectedItem()+"'";
        
        ResultSet res = stat.executeQuery(sql);
        
        while(res.next()){
        Object[] ob = new Object[3];
        ob[0] = res.getString(1);
        
        nm_kate.setText((String) ob[0]);
        }
        res.close(); stat.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
    
    public void combo_jenis()
    {
        try {
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        String sql = "select kd_kendaraan from jenis_kendaraan order by kd_kendaraan asc";
        ResultSet res = stat.executeQuery(sql);
        
        while(res.next()){
        Object[] ob = new Object[3];
        ob[0] = res.getString(1);
        
        cbjenis.addItem((String) ob[0]);
        }
        res.close(); stat.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void tampilkan_jenis()
    {
        try {
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        String sql = "select * from jenis_kendaraan where kd_kendaraan='"+cbjenis.getSelectedItem()+"'";
        
        ResultSet res = stat.executeQuery(sql);
        
        while(res.next()){
        nm_jenis.setText(res.getString("nm_kendaraan"));
        ttarif1.setText(res.getString("tarif"));
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
    
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tnoparkir = new javax.swing.JTextField();
        tplat = new javax.swing.JTextField();
        ttotal = new javax.swing.JTextField();
        tlama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tmasuk = new javax.swing.JTextField();
        tkeluar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_trans = new javax.swing.JTable();
        cbjenis = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbkate = new javax.swing.JComboBox<>();
        bbaru = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        nm_jenis = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        bhitung = new javax.swing.JButton();
        nm_kate = new javax.swing.JTextField();
        ttarif1 = new javax.swing.JTextField();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel1.setText("NO PARKIR");

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("TRANSAKSI PARKIR");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel3.setText("TARIF PARKIR /JAM");

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel4.setText("PLAT NOMOR");

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel6.setText("LAMA PARKIR");

        tnoparkir.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        tplat.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        ttotal.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });

        tlama.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel7.setText("JAM MASUK PARKIR");

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel8.setText("JAM KELUAR PARKIR");

        tmasuk.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        tkeluar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        tkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkeluarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel9.setText("JENIS KENDARAAN");

        tb_trans.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_transMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_trans);

        cbjenis.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        cbjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenisActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel10.setText("KATEGORI PARKIR");

        cbkate.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        cbkate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkateActionPerformed(evt);
            }
        });

        bbaru.setBackground(new java.awt.Color(0, 102, 153));
        bbaru.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bbaru.setForeground(new java.awt.Color(255, 255, 255));
        bbaru.setText("BARU");
        bbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbaruActionPerformed(evt);
            }
        });

        bubah.setBackground(new java.awt.Color(0, 102, 153));
        bubah.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bubah.setForeground(new java.awt.Color(255, 255, 255));
        bubah.setText("UBAH");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });

        bhapus.setBackground(new java.awt.Color(0, 102, 153));
        bhapus.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bhapus.setForeground(new java.awt.Color(255, 255, 255));
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bsimpan.setBackground(new java.awt.Color(0, 102, 153));
        bsimpan.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(255, 255, 255));
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bkeluar.setBackground(new java.awt.Color(255, 0, 0));
        bkeluar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bkeluar.setForeground(new java.awt.Color(255, 255, 255));
        bkeluar.setText("X");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        nm_jenis.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        nm_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_jenisActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("TOTAL");

        bhitung.setBackground(new java.awt.Color(0, 102, 153));
        bhitung.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        bhitung.setForeground(new java.awt.Color(255, 255, 255));
        bhitung.setText("HITUNG");
        bhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhitungActionPerformed(evt);
            }
        });

        nm_kate.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        ttarif1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bkeluar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bhitung, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(bsimpan)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(27, 27, 27)
                                        .addComponent(ttarif1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nm_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tnoparkir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(114, 114, 114))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bubah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(24, 24, 24)
                                            .addComponent(tmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(tkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tplat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbkate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(113, 113, 113)
                                .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tlama, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nm_kate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bkeluar))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tnoparkir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tplat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbkate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nm_kate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nm_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tlama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ttarif1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bbaru)
                    .addComponent(bubah)
                    .addComponent(bhapus)
                    .addComponent(bsimpan)
                    .addComponent(bhitung))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbaruActionPerformed
        baru();
    }//GEN-LAST:event_bbaruActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        perbaruiDataTransaksi();
    }//GEN-LAST:event_bubahActionPerformed
    
    
     public void hapusDataTransaksi(){
        //panggil fungsi ambil data
        loadDataTransaksi(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA "+ no_parkir +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM transaksi WHERE no_parkir ='"+ no_parkir +"'";
                PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataTransaksi();
                
                //fungsi reset data
                baru();
                JOptionPane.showMessageDialog(null, "DATA KENDARAAN BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }
     
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        hapusDataTransaksi();
    }//GEN-LAST:event_bhapusActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        simpanDataTransaksi();
    }//GEN-LAST:event_bsimpanActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_bkeluarActionPerformed

    private void tkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkeluarActionPerformed
        // TODO add your handling code here:
        double JamMasuk,JamKeluar,LamaParkir;
        JamMasuk=Double.parseDouble(tmasuk.getText());
        JamKeluar=Double.parseDouble(tkeluar.getText());
        LamaParkir=JamKeluar-JamMasuk;
       
        String.valueOf(LamaParkir);
        tlama.setText(""+LamaParkir);
    }//GEN-LAST:event_tkeluarActionPerformed

    private void bhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhitungActionPerformed
        // TODO add your handling code here:
        {double JamMasuk,JamKeluar,LamaParkir;
        JamMasuk=Double.parseDouble(tmasuk.getText());
        JamKeluar=Double.parseDouble(tkeluar.getText());
        LamaParkir=JamKeluar-JamMasuk;
       
        String.valueOf(LamaParkir);
        tlama.setText(""+LamaParkir);}
        double Total,Jam,Hasil;
        Total=Double.parseDouble(ttarif1.getText());
        Jam=Double.parseDouble(tlama.getText());
        Hasil=Total*Jam;
       
        String.valueOf(Hasil);
        ttotal.setText(String.format("%.0f",Hasil));
    }//GEN-LAST:event_bhitungActionPerformed

    private void cbjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenisActionPerformed
        tampilkan_jenis();
    }//GEN-LAST:event_cbjenisActionPerformed

    private void cbkateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkateActionPerformed
        tampilkan_kate();
    }//GEN-LAST:event_cbkateActionPerformed

    private void nm_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_jenisActionPerformed
        
    }//GEN-LAST:event_nm_jenisActionPerformed

    private void tb_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_transMouseClicked
        dataSelect();
    }//GEN-LAST:event_tb_transMouseClicked

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbaru;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bhitung;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JComboBox<String> cbkate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField nm_jenis;
    private javax.swing.JTextField nm_kate;
    private javax.swing.JTable tb_trans;
    private javax.swing.JTextField tkeluar;
    private javax.swing.JTextField tlama;
    private javax.swing.JTextField tmasuk;
    private javax.swing.JTextField tnoparkir;
    private javax.swing.JTextField tplat;
    private javax.swing.JTextField ttarif1;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
