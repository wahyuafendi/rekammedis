/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */

 

public class pasien extends javax.swing.JPanel {
private Statement stat;
private Connection Con;
private Connection con;
private ResultSet res;
private PreparedStatement pst;
private String t;
private Component rootPane;
int baris;
// java.sql.Connection conn = null ;

  //  PreparedStatement pst = null;
    

    public pasien(){
        initComponents();
        koneksi();
        tabel();
        
buatkode();
//AUTONUMBER();
    }
    
     private void koneksi(){
    try {
    Class.forName("com.mysql.jdbc.Driver");
    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skripsiku", "root", "");
    stat=(Statement) con.createStatement();
    }
    catch (ClassNotFoundException | SQLException e) {
        
    JOptionPane.showMessageDialog(null, e);
    
    }
    }
     
       private void kosongkan()
       { 
       kd_pasien.setText(""); 
       nama_pasien.setText("");
       cbjk.setSelectedItem("");
       cbgd.setSelectedItem("");
       umur.setText("");
       telepon.setText("");
       email.setText("");
       alamat.setText("");
        }
       
       private void tabel(){ 
    DefaultTableModel t= new DefaultTableModel();
     t.addColumn("kd_pasien"); 
     t.addColumn("nama_pasien"); 
     t.addColumn("jeniskelamin");
     t.addColumn("goldar");
     t.addColumn("usia");
     t.addColumn("telepon");
     t.addColumn("email"); 
     t.addColumn("alamat"); 
     tabel_pasien.setModel(t); try{ res=stat.executeQuery("select * from pasien"); 
     while (res.next()) { 
     t.addRow(new Object[]{ res.getString("kd_pasien"),
     res.getString("nama_pasien"), 
     res.getString("jeniskelamin"), 
     res.getString("goldar"),
     res.getString("usia"), 
     res.getString("telepon"), 
     res.getString("email"), 
     res.getString("alamat")
     }); 
     }
     }catch (Exception e) { 
     JOptionPane.showMessageDialog(rootPane, e); 
   //  AUTONUMBER();
     } 
       }
     void cetak_satuan(){
            try{
            String namaFile = "src/laporan/kartu.jasper";
            java.sql.Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/skripsiku", "root", "");
            HashMap parameter = new HashMap();
            
            parameter.put("kode",txtcari.getText());
            File report_file = new File(namaFile);
            net.sf.jasperreports.engine.JasperReport jasperreport = (net.sf.jasperreports.engine.JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, parameter, conn);
            JasperViewer.viewReport(jasperprint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch (Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage());
        }
    

    }    
   private void buatkode(){
                  try {
    
String sql = "SELECT * FROM pasien ORDER BY kd_pasien DESC";


res=stat.executeQuery(sql);
if (res.next()) {
String kd_barang = res.getString("kd_pasien").substring(2);
String AN = "" +(Integer.parseInt(kd_barang) + 1);
String Nol = "";
if(AN.length()==1)
{Nol = "00";}
else if(AN.length()==2)
{Nol = "0";}
else if(AN.length()==3)
{Nol = "";}
kd_pasien.setText("P-"+ Nol +AN);
} else {
kd_pasien.setText("P-001");
}
} catch (Exception e) {
e.printStackTrace();
    
}
   }             
 
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        kd_pasien = new javax.swing.JTextField();
        nama_pasien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        telepon = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        bthapus = new javax.swing.JToggleButton();
        btsimpan = new javax.swing.JToggleButton();
        cbjk = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbgd = new javax.swing.JComboBox();
        umur = new javax.swing.JTextField();
        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Belwe Bd BT", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FORM PASIEN");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kode Pasien");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        kd_pasien.setEditable(false);
        kd_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_pasienActionPerformed(evt);
            }
        });
        add(kd_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 275, -1));
        add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 275, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Telepon");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jenis Kelamin");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        add(telepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 275, -1));
        add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 275, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane1.setViewportView(alamat);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 275, -1));

        tabel_pasien.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabel_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Pelanggan", "Nama Pelanggan", "Kontak", "Email", "Alamat"
            }
        ));
        tabel_pasien.setGridColor(new java.awt.Color(153, 153, 153));
        tabel_pasien.setSelectionBackground(new java.awt.Color(0, 153, 51));
        tabel_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_pasien);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 559, 210));

        btedit.setText("UBAH");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        add(btedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 80, -1));

        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 80, -1));

        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 80, -1));

        cbjk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki Laki", "Perempuan", " " }));
        add(cbjk, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 120, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Golongan Darah");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Umur");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama ");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        cbgd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O", "A", "B", "AB", "Tidak Tahu" }));
        add(cbgd, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 120, -1));
        add(umur, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 70, -1));

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 170, 30));

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 70, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panel.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Pasien");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jenis Kelamin");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
         try {
            stat.executeUpdate("update pasien set "
                + "kd_pasien='"+kd_pasien.getText()+"',"
                + "nama_pasien='"+nama_pasien.getText()+"',"
                + "jeniskelamin='"+cbjk.getSelectedItem()+"',"
                + "goldar='"+cbgd.getSelectedItem()+"',"
                + "usia='"+umur.getText()+"',"
                + "telepon='"+telepon.getText()+"',"
                + "email='"+email.getText()+"',"
                + "alamat='"+alamat.getText()+"'"
                + " where " + "kd_pasien='"+kd_pasien.getText()+"'" );
            kosongkan();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil Di Ubah");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }finally{
         tabel();
         buatkode();
        }    
    }//GEN-LAST:event_bteditActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        try { 
        stat.executeUpdate("delete from pasien where " 
        + "kd_pasien='"+kd_pasien.getText()
        +"'" ); 
        JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException | HeadlessException e) { 
        JOptionPane.showMessageDialog(null, "pesan salah : "+e);
         } finally{
         tabel();
       buatkode();
          }
    }//GEN-LAST:event_bthapusActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
   try {
 stat.executeUpdate("insert into pasien values (" 
+ "'" + kd_pasien.getText()+"',"
+ "'" + nama_pasien.getText()+"',"
+ "'" + cbjk.getSelectedItem()+"',"
+ "'" + cbgd.getSelectedItem()+"',"
+ "'" + umur.getText()+"',"
+ "'" + telepon.getText()+"',"
+ "'" + email.getText()+"'," 
+ "'" + alamat.getText()+ "')");
kosongkan(); 
JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data"); 
} catch (SQLException | HeadlessException e) { 
JOptionPane.showMessageDialog(null, "Perintah Salah : "+e);
}finally{
         tabel();   
        buatkode();// AUTONUMBER();
         
 } 
    }//GEN-LAST:event_btsimpanActionPerformed

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked
        baris = tabel_pasien.getSelectedRow();
        kd_pasien.setText(tabel_pasien.getValueAt(baris,0).toString());
        nama_pasien.setText(tabel_pasien.getValueAt(baris,1).toString());
        cbjk.setSelectedItem(tabel_pasien.getValueAt(baris,2).toString());
        cbgd.setSelectedItem(tabel_pasien.getValueAt(baris,3).toString());
        umur.setText(tabel_pasien.getValueAt(baris,4).toString());
        telepon.setText(tabel_pasien.getValueAt(baris,5).toString());
        email.setText(tabel_pasien.getValueAt(baris,6).toString());
        alamat.setText(tabel_pasien.getValueAt(baris,7).toString());
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void kd_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_pasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_pasienActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cetak_satuan();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed

        DefaultTableModel tabelTampil1 = new DefaultTableModel();
        tabelTampil1.addColumn("kd_pasien");
        tabelTampil1.addColumn("nama_pasien ");
        tabelTampil1.addColumn("jeniskelamin");
        tabelTampil1.addColumn("goldar");
        tabelTampil1.addColumn("usia");
        tabelTampil1.addColumn("telepon");
        tabelTampil1.addColumn("email");
        tabelTampil1.addColumn("alamat");
        try{
            koneksi();
            String sql = "Select * from pasien where kd_pasien like '%" + txtcari.getText() + "%'" +
            "or nama_pasien like '%" + txtcari.getText() + "%'";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                tabelTampil1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8)
                });
            }
            tabel_pasien.setModel(tabelTampil1);

        }catch (Exception e){

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JButton btedit;
    private javax.swing.JToggleButton bthapus;
    private javax.swing.JToggleButton btsimpan;
    private javax.swing.JComboBox cbgd;
    private javax.swing.JComboBox cbjk;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kd_pasien;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JTextField telepon;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField umur;
    // End of variables declaration//GEN-END:variables
}
   

