/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modul;

/**
 *
 * @author Dede
 */
import Koneksi.koneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Futama extends javax.swing.JFrame {
ResultSet Data;
String user,pass,level;
public boolean berhasil = false;
    /**
     * Creates new form Futama
     */
    public Futama() {
        initComponents();
    enable();
    }
    
    public void enable(){
        bkate.hide();
        bjenis.hide();
        btrans.hide();
        blapor.hide();
        plogin.hide();
        pmenu.hide();
        lstatus.hide();
        blogout.hide();
    }
    
    public boolean login() throws SQLException {
    if (cblevel.getSelectedItem().equals("Admin")){
        try{
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        Data = stat.executeQuery("select id, pass from login where id ='"+user+"'"
        + "and pass ='"+pass+"' ");
        berhasil = Data.next();
        
            plogin.hide();
            bkate.show();
            bjenis.show();
            btrans.show();
            blapor.show();
            lstatus.setText("Admin");
        
    }catch (SQLException salah){
            System.out.println(salah);
            }
    
    } else if (cblevel.getSelectedItem().equals("Petugas")){
        try{
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        Data = stat.executeQuery("select id,pass from login where id ='"+user+"'"
        + "and pass = '"+pass+"'");
        berhasil = Data.next();
            plogin.hide();
            bkate.show();
            bjenis.show();
            btrans.show();
            blapor.show();
            lstatus.setText("Petugas");
        }catch (SQLException salah){
            System.out.println(salah);
            }
    }  else if (cblevel.getSelectedItem().equals("Manager")){
        try{
        Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        Data = stat.executeQuery("select id,pass from login where id ='"+user+"'"
        + "and pass = '"+pass+"'");
            plogin.hide();
            blapor.show();
            lstatus.setText("Manager");
        
        }catch (SQLException salah){
            System.out.println(salah);
        }
    }
        return berhasil;
    }
    
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        plogin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tuser = new javax.swing.JTextField();
        tpass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        bmasuk = new javax.swing.JButton();
        cblevel = new javax.swing.JComboBox<>();
        bbatal = new javax.swing.JButton();
        pmenu = new javax.swing.JPanel();
        bjenis = new javax.swing.JButton();
        blapor = new javax.swing.JButton();
        btrans = new javax.swing.JButton();
        bkate = new javax.swing.JButton();
        blogout = new javax.swing.JButton();
        lstatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jButton5.setText("jButton5");

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("PARKIR 100% AMAN NYAMAN");

        plogin.setBackground(new java.awt.Color(204, 204, 255));
        plogin.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true), "Login", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Rockwell", 0, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        tuser.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel4.setText("Level");

        bmasuk.setText("Masuk");
        bmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmasukActionPerformed(evt);
            }
        });

        cblevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Level Status --", "Admin", "Petugas", "Manager" }));

        bbatal.setText("Batal");
        bbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ploginLayout = new javax.swing.GroupLayout(plogin);
        plogin.setLayout(ploginLayout);
        ploginLayout.setHorizontalGroup(
            ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ploginLayout.createSequentialGroup()
                .addGap(47, 70, Short.MAX_VALUE)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ploginLayout.createSequentialGroup()
                        .addComponent(bbatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(bmasuk))
                    .addComponent(tuser, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpass, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cblevel, javax.swing.GroupLayout.Alignment.LEADING, 0, 149, Short.MAX_VALUE))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        ploginLayout.setVerticalGroup(
            ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ploginLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cblevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ploginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bmasuk)
                    .addComponent(bbatal))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pmenu.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Rockwell", 0, 12))); // NOI18N

        bjenis.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        bjenis.setText("JENIS KENDARAAN");
        bjenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bjenisMouseClicked(evt);
            }
        });
        bjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bjenisActionPerformed(evt);
            }
        });

        blapor.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        blapor.setText("LAPORAN");
        blapor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blaporActionPerformed(evt);
            }
        });

        btrans.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btrans.setText("TRANSAKSI");
        btrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btransActionPerformed(evt);
            }
        });

        bkate.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        bkate.setText("KATEGORI");
        bkate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bkateMouseClicked(evt);
            }
        });
        bkate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkateActionPerformed(evt);
            }
        });

        blogout.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        blogout.setText("Logout");
        blogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pmenuLayout = new javax.swing.GroupLayout(pmenu);
        pmenu.setLayout(pmenuLayout);
        pmenuLayout.setHorizontalGroup(
            pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pmenuLayout.createSequentialGroup()
                        .addComponent(bkate)
                        .addGap(29, 29, 29)
                        .addComponent(btrans)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(bjenis)
                        .addGap(27, 27, 27)
                        .addComponent(blapor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(blogout)))
                .addContainerGap())
        );
        pmenuLayout.setVerticalGroup(
            pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmenuLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blapor)
                    .addComponent(bjenis)
                    .addComponent(btrans)
                    .addComponent(bkate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(blogout)
                .addContainerGap())
        );

        lstatus.setText("Status");

        jMenu1.setText("MENU");

        jMenuItem1.setText("Login");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Keluar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(plogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(lstatus)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(plogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lstatus))
                .addGap(18, 18, 18)
                .addComponent(pmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bkateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bkateMouseClicked
        new Fkategori().setVisible(true);
       
    }//GEN-LAST:event_bkateMouseClicked

    private void btransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btransActionPerformed
        new Transaksi().setVisible(true);
        
    }//GEN-LAST:event_btransActionPerformed

    private void bjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bjenisActionPerformed

    private void bjenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bjenisMouseClicked
        new Fjenis().setVisible(true);
        
    }//GEN-LAST:event_bjenisMouseClicked

    private void bmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmasukActionPerformed
        user = tuser.getText();
        pass = tpass.getText();
        
        
    try {
        login();
    } catch (SQLException ex) {
        Logger.getLogger(Futama.class.getName()).log(Level.SEVERE, null, ex);
    }
        pmenu.setVisible(true);
        blogout.setVisible(true);
        lstatus.show();
        
    }//GEN-LAST:event_bmasukActionPerformed

    private void bkateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bkateActionPerformed

    private void bbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbatalActionPerformed
        plogin.setVisible(false);
    }//GEN-LAST:event_bbatalActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        plogin.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void blogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blogoutActionPerformed
        plogin.setVisible(true);
        pmenu.hide();
        tuser.setText("");
        tpass.setText("");
        lstatus.hide();
        bkate.hide();
        btrans.hide();
        bjenis.hide();
        blapor.hide();
        
    }//GEN-LAST:event_blogoutActionPerformed

    private void blaporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blaporActionPerformed
        new Flaporan().setVisible(true);
    }//GEN-LAST:event_blaporActionPerformed

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
            java.util.logging.Logger.getLogger(Futama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Futama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Futama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Futama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Futama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbatal;
    private javax.swing.JButton bjenis;
    private javax.swing.JButton bkate;
    private javax.swing.JButton blapor;
    private javax.swing.JButton blogout;
    private javax.swing.JButton bmasuk;
    private javax.swing.JButton btrans;
    private javax.swing.JComboBox<String> cblevel;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel lstatus;
    private javax.swing.JPanel plogin;
    private javax.swing.JPanel pmenu;
    private javax.swing.JPasswordField tpass;
    private javax.swing.JTextField tuser;
    // End of variables declaration//GEN-END:variables
}
